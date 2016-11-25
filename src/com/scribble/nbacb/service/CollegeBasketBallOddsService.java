package com.scribble.nbacb.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.scribble.nbacb.models.PowerRanking;
import com.scribble.nbacb.models.SonnyMoorePrediction;
import com.scribble.nbacb.models.events.Event;
import com.scribble.nbacb.repository.CollegeBasketBallOddsRepository;

public class CollegeBasketBallOddsService {

	private CollegeBasketBallOddsRepository nbacbRepository = new CollegeBasketBallOddsRepository();

	public void updateNflSonnyMooreRankings(Table eventsTable) 
			throws IOException, ParseException
	{
		List<PowerRanking> sonnyMoorePowerRankings = nbacbRepository.getNflSonnyMoorePowerRaking();
		List<Event> events = nbacbRepository.getNflCurrentEvents();

		updateSonnyMooreRanking(eventsTable, sonnyMoorePowerRankings, events);		
	}

	public void updateNcaabSonnyMooreRankings(Table eventsTable, Table ncaabTeamsTable) 
			throws IOException, ParseException
	{
		Map<String, String> teamMap = getNcaabTeams(ncaabTeamsTable);
		
		List<PowerRanking> sonnyMoorePowerRankings = nbacbRepository.getNcaabSonnyMoorePowerRaking();
		
		for (PowerRanking ranking: sonnyMoorePowerRankings) 
		{
			if (teamMap.containsKey(ranking.getTeamName()))
			{
				ranking.setTeamName(teamMap.get(ranking.getTeamName()).toUpperCase());
			}
		}
		
		List<Event> events = nbacbRepository.getNcaabCurrentEvents();

		updateSonnyMooreRanking(eventsTable, sonnyMoorePowerRankings, events);		
	}
	
	private Map<String, String> getNcaabTeams(Table ncaabTeamsTable) 
	{
        ScanSpec spec = new ScanSpec();
        ItemCollection<ScanOutcome> result = ncaabTeamsTable.scan(spec);
        
        Map<String, String> scoreTeams = new HashMap<>();
        for (Item item: result)
        {
        	scoreTeams.put(item.getString("SonnyMooreTeamName").trim().toUpperCase(),
        			item.getString("ScoreApiTeamName").trim().toUpperCase());
        }
        
        return scoreTeams;
	}
	
	private void updateSonnyMooreRanking(Table eventsTable, List<PowerRanking> sonnyMoorePowerRankings, List<Event> events)
			throws ParseException {
		Calendar toDateCalendar = Calendar.getInstance();
		Date currentTime = toDateCalendar.getTime();
		toDateCalendar.add(Calendar.HOUR, 1);
		Date toDate = toDateCalendar.getTime();
		Calendar resultCalendar = Calendar.getInstance();
		resultCalendar.add(Calendar.MINUTE, -220);
		Date resultToDateCalendar = resultCalendar.getTime();
		resultCalendar.add(Calendar.HOUR, -1);
		Date resultFromDateCalendar = resultCalendar.getTime();
		
		for (Event event: events)
		{
			DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			Date eventDate = format.parse(event.getGame_date());

			if (eventDate.after(currentTime) && eventDate.before(toDate))
			{
				String homeTeamName = event.getHome_team().getFull_name().trim().toUpperCase();
				String awayTeamName = event.getAway_team().getFull_name().trim().toUpperCase();

				PowerRanking homeTeam = (homeTeamName == null) ? null : getMatchingTeamName(sonnyMoorePowerRankings, homeTeamName);
				PowerRanking awayTeam = (awayTeamName == null) ? null : getMatchingTeamName(sonnyMoorePowerRankings, awayTeamName);

				addEvent(eventsTable, new SonnyMoorePrediction() {{
					setEventId(event.getId());
					setHomeTeamName(event.getHome_team().getFull_name());
					setAwayTeamName(event.getAway_team().getFull_name());
					setHomeTeamRanking((homeTeam == null) ? 0 : homeTeam.getPowerRanking());
					setAwayTeamRanking((awayTeam == null) ? 0 : awayTeam.getPowerRanking());
					setGameDate(eventDate);
				}});
			}

			if (eventDate.after(resultFromDateCalendar) && eventDate.before(resultToDateCalendar))
			{
				System.out.println("Event Id before update : " + event.getId());
				updateEvent(eventsTable, event);
			}
		}
	}
	
	private void addEvent(Table events, SonnyMoorePrediction prediction)
	{
        Item currentEvent = new Item();
        currentEvent.withInt("EventId", prediction.getEventId());
        currentEvent.withString("HomeTeamName", prediction.getHomeTeamName());
        currentEvent.withString("AwayTeamName", prediction.getAwayTeamName());
        currentEvent.withDouble("HomeTeamRanking", prediction.getHomeTeamRanking());
        currentEvent.withDouble("AwayTeamRanking", prediction.getAwayTeamRanking());
        currentEvent.withString("EventDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(prediction.getGameDate()));
        
    	events.putItem(currentEvent);

	}
	
	private void updateEvent(Table events, Event event)
	{
		System.out.println(event.getId());
		System.out.println(event.getBox_score().getScore().getHome().getScore());
		System.out.println(event.getBox_score().getScore().getAway().getScore());
		System.out.println(getOdd(event));
		
		PrimaryKey primaryKey = new PrimaryKey();
		primaryKey.addComponent("EventId", event.getId());
		AttributeUpdate homeScoreUpdate = new AttributeUpdate("HomeScore");
		homeScoreUpdate.put(event.getBox_score().getScore().getHome().getScore());
		AttributeUpdate awayScoreUpdate = new AttributeUpdate("AwayScore");
		awayScoreUpdate.put(event.getBox_score().getScore().getAway().getScore());
		AttributeUpdate homeOdds = new AttributeUpdate("HomeOdds");
		homeOdds.put(getOdd(event));
		
		events.updateItem(primaryKey, homeScoreUpdate, awayScoreUpdate, homeOdds);
	}
	
	private Double getOdd(Event event)
	{
		return event.getOdd() == null || event.getOdd().getHome_odd().startsWith("pk") || event.getOdd().getHome_odd().startsWith("N") 
				? -999999 
				: event.getOdd().getHome_odd().startsWith("T") 
					? (-1) * Double.parseDouble(event.getOdd().getAway_odd()) 
					: Double.parseDouble(event.getOdd().getHome_odd());
	}
	
	private PowerRanking getMatchingTeamName(List<PowerRanking> sonnyMoorePowerRanking, String teamName) {
//		ArrayList<PowerRanking> secondMatch = new ArrayList<PowerRanking>();
		System.out.println("TeamName : " + teamName);
		
		for (PowerRanking powerRanking : sonnyMoorePowerRanking)
		{
			if (teamName.equals(powerRanking.getTeamName()))
			{
				System.out.println("MATCH : " + powerRanking.getTeamName());
				return powerRanking;
			}

/*			if (powerRanking.getTeamName().startsWith(teamName))
			{
				secondMatch.add(powerRanking);
			}*/
		}
		
/*		PowerRanking hardMatch = TryFindMatch(teamName, sonnyMoorePowerRanking, "HC");
		if (hardMatch != null)
		{
			System.out.println(hardMatch.getTeamName());
			return hardMatch;
		}

		if (secondMatch.size() == 1)
		{
			System.out.println("MATCH : " + secondMatch.get(0).getTeamName());
			return secondMatch.get(0);
		}
		else
		{
			PowerRanking matchTeam = TryFindMatch(teamName, sonnyMoorePowerRanking, "FW");
			if (matchTeam != null)
			{
				System.out.println(matchTeam.getTeamName());
				return matchTeam;
			}
		}*/
		
		return null;
	}
}