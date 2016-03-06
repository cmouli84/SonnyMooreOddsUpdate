package com.scribble.nbacb.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.scribble.nbacb.models.PowerRanking;
import com.scribble.nbacb.models.SonnyMoorePrediction;
import com.scribble.nbacb.models.events.Event;
import com.scribble.nbacb.repository.CollegeBasketBallOddsRepository;

public class CollegeBasketBallOddsService {

	private CollegeBasketBallOddsRepository nbacbRepository = new CollegeBasketBallOddsRepository();

	public List<SonnyMoorePrediction> getSonnyMooreRankings(Map<String, String> scoreTeams, Table eventsTable) 
			throws IOException, ParseException
	{
		List<SonnyMoorePrediction> sonnyMoorePredictions = new ArrayList<>();
		
		List<PowerRanking> sonnyMoorePowerRankings = nbacbRepository.getSonnyMoorePowerRaking();
		List<Event> events = nbacbRepository.getCurrentEvents();

		Calendar toDateCalendar = Calendar.getInstance();
		Date currentTime = toDateCalendar.getTime();
		toDateCalendar.add(Calendar.HOUR, 1);
		Date toDate = toDateCalendar.getTime();
		Calendar resultCalendar = Calendar.getInstance();
		resultCalendar.add(Calendar.MINUTE, -160);
		Date resultToDateCalendar = resultCalendar.getTime();
		resultCalendar.add(Calendar.HOUR, -1);
		Date resultFromDateCalendar = resultCalendar.getTime();
		
		for (Event event: events)
		{
			DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			Date eventDate = format.parse(event.getGame_date());

			if (eventDate.after(currentTime) && eventDate.before(toDate))
			{
				String homeTeamName = scoreTeams.get(event.getHome_team().getFull_name().trim().toUpperCase());
				String awayTeamName = scoreTeams.get(event.getAway_team().getFull_name().trim().toUpperCase());

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
		
		return sonnyMoorePredictions;
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
		ArrayList<PowerRanking> secondMatch = new ArrayList<PowerRanking>();
		System.out.println("TeamName : " + teamName);
		
		for (PowerRanking powerRanking : sonnyMoorePowerRanking)
		{
			if (teamName.equals(powerRanking.getTeamName()))
			{
				System.out.println("MATCH : " + powerRanking.getTeamName());
				return powerRanking;
			}

			if (powerRanking.getTeamName().startsWith(teamName))
			{
				secondMatch.add(powerRanking);
			}
		}
		
		PowerRanking hardMatch = TryFindMatch(teamName, sonnyMoorePowerRanking, "HC");
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
		}
		
		return null;
	}
	
	private PowerRanking TryFindMatch(String teamName, List<PowerRanking> matches, String pattern)
	{
		ArrayList<PowerRanking> secondMatch = new ArrayList<>();
		if (pattern.equals("HC"))
		{
			String matchingTeamName = "";
			switch (teamName)
			{
				case "UNLV":
					matchingTeamName = "NEVADA LAS VEGAS";
					break;
				case "TEXAS CHRISTIAN":
					matchingTeamName = "TCU";
					break;
				case "INDIANA   PURDUE":
					matchingTeamName = "IUPUI";
					break;
				case "SOUTHERN CALIFORNIA":
					matchingTeamName = "SOUTHERN CAL";
					break;
				case "LOUISIANA STATE":
					matchingTeamName = "LSU";
					break;
				case "LOYOLA CHICAGO":
					matchingTeamName = "LOYOLA ILLINOIS";
					break;
				case "BRIGHAM YOUNG":
					matchingTeamName = "BYU";
					break;
				case "CAL POLY SLO":
					matchingTeamName = "CAL POLY";
					break;
				case "SOUTHERN METHODIST":
					matchingTeamName = "SMU";
					break;
				case "CHARLESTON":
					matchingTeamName = "COLLEGE OF CHARLESTON";
					break;
			}
			
			for (PowerRanking team : matches)
			{
				if (team.getTeamName().equals(matchingTeamName))
				{
					return team;
				}
			}
			return null;
		}
		if (pattern.equals("FW"))
		{
			for (PowerRanking matchName: matches)
			{
				if (matchName.getTeamName().split(" ")[0].equals(teamName.split(" ")[0]))
				{
					secondMatch.add(matchName);
				}
			}
			
			if (secondMatch.size() == 1)
			{
				return secondMatch.get(0);
			}
			return TryFindMatch(teamName, matches, "LW");
		}
		if (pattern.equals("LW"))
		{
			for (PowerRanking matchName: matches)
			{
				String[] matchArray = matchName.getTeamName().split(" ");
				String[] teamNameArray = teamName.split(" ");
				if (matchArray[matchArray.length - 1].equals(teamNameArray[teamNameArray.length - 1]))
				{
					secondMatch.add(matchName);
				}
			}
			
			if (secondMatch.size() == 1)
			{
				return secondMatch.get(0);
			}
		}
		
		return null;
	}
}
