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

import org.joda.time.DateTime;

import com.scribble.nbacb.models.PowerRanking;
import com.scribble.nbacb.models.SonnyMoorePrediction;
import com.scribble.nbacb.models.events.Event;
import com.scribble.nbacb.repository.CollegeBasketBallOddsRepository;

public class CollegeBasketBallOddsService {

	private CollegeBasketBallOddsRepository nbacbRepository = new CollegeBasketBallOddsRepository();

	public List<SonnyMoorePrediction> getSonnyMooreRankings() throws IOException, ParseException
	{
		List<SonnyMoorePrediction> sonnyMoorePredictions = new ArrayList<>();
		
		List<PowerRanking> sonnyMoorePowerRankings = nbacbRepository.getSonnyMoorePowerRaking();
		List<Event> events = nbacbRepository.getCurrentEvents();

		Date currentTime = DateTime.now().toDate();
		Calendar toDateCalendar = Calendar.getInstance();
		toDateCalendar.add(Calendar.HOUR, 1);
		Date toDate = toDateCalendar.getTime();
		
		for (Event event: events)
		{
			DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			Date eventDate = format.parse(event.getGame_date());

			if (eventDate.after(currentTime) && eventDate.before(toDate))
			{
				String homeTeamName = event.getHome_team().getFull_name().toUpperCase();
				String awayTeamName = event.getAway_team().getFull_name().toUpperCase();

				PowerRanking homeTeam = getMatchingTeamName(sonnyMoorePowerRankings, homeTeamName);
				PowerRanking awayTeam = getMatchingTeamName(sonnyMoorePowerRankings, awayTeamName);

				sonnyMoorePredictions.add(new SonnyMoorePrediction() {{
					setEventId(event.getId());
					setHomeTeamName(homeTeamName);
					setAwayTeamName(awayTeamName);
					setHomeTeamRanking((homeTeam == null) ? 0 : homeTeam.getPowerRanking());
					setAwayTeamRanking((awayTeam == null) ? 0 : awayTeam.getPowerRanking());
					setGameDate(eventDate);
				}});
			}
		}
		
		return sonnyMoorePredictions;
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
