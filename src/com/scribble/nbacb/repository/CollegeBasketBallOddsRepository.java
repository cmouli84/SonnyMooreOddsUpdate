package com.scribble.nbacb.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.scribble.nbacb.models.PowerRanking;
import com.scribble.nbacb.models.events.Event;
import com.scribble.nbacb.models.schedule.Current_season;
import com.scribble.nbacb.models.schedule.Season;

public class CollegeBasketBallOddsRepository {

	public List<PowerRanking> getSonnyMoorePowerRaking() throws MalformedURLException, IOException
	{
		String url = "http://sonnymoorepowerratings.com/m-basket.htm";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String content;
		StringBuffer response = new StringBuffer();

		ArrayList<PowerRanking> powerRankings = new ArrayList<PowerRanking>();
		Boolean contentStarted = false;
		while ((content = in.readLine()) != null) {
			response.append(content);

			if (contentStarted)
			{
				content = content.trim();

				if (contentStarted && content.equals("<BR>"))
				{
					break;
				}
				
				for (String inputLine : content.split("\n"))
				{
					int firstIndexOfSpace = inputLine.indexOf(' ');
					String teamName = inputLine.substring(firstIndexOfSpace, inputLine.indexOf("    ", firstIndexOfSpace));
					inputLine = inputLine.substring(inputLine.indexOf("    ", firstIndexOfSpace)).trim();
					firstIndexOfSpace = inputLine.indexOf(' ');
					int wins = Integer.parseInt(inputLine.substring(0, firstIndexOfSpace).trim());
					inputLine = inputLine.substring(firstIndexOfSpace).trim();
					firstIndexOfSpace = inputLine.indexOf(' ');
					int loses = Integer.parseInt(inputLine.substring(0, firstIndexOfSpace).trim());
					inputLine = inputLine.substring(firstIndexOfSpace).trim();
					firstIndexOfSpace = inputLine.indexOf(' ');
					int ties = Integer.parseInt(inputLine.substring(0, firstIndexOfSpace).trim());
					inputLine = inputLine.substring(firstIndexOfSpace).trim();
					firstIndexOfSpace = inputLine.indexOf(' ');
					double sos = Double.parseDouble(inputLine.substring(0, firstIndexOfSpace).trim());
					inputLine = inputLine.substring(firstIndexOfSpace).trim();
					double powerRanking = Double.parseDouble(inputLine);
					
					powerRankings.add(new PowerRanking()
							{{
								setTeamName(GetTeamName(teamName));
								setWins(wins);
								setLoses(loses);
								setTies(ties);
								setSOS(sos);
								setPowerRanking(powerRanking);
							}});
				}							
			}

			if (content.startsWith("<B>"))
			{
				contentStarted = true;
			}
			
		}
		in.close();
		
		System.out.println("Power Ranking Count " + powerRankings.size());
		
		return powerRankings;
	}
	
	public List<Event> getCurrentEvents() throws MalformedURLException, IOException, ParseException
	{
		List<Event> matches = new ArrayList<>();
		Calendar toDateCalendar = Calendar.getInstance();
		toDateCalendar.add(Calendar.HOUR, 24);
		List<Event> todayMatches = getMatchesByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<Event> tomorrowMatches = getMatchesByDate(new SimpleDateFormat("yyyy-MM-dd").format(toDateCalendar.getTime()));
		
		for (Event event: todayMatches)
		{
			matches.add(event);
		}
		for (Event event: tomorrowMatches)
		{
			matches.add(event);
		}

		return matches;
	}	

	private List<Event> getMatchesByDate(String matchDate) throws MalformedURLException, IOException, ParseException
	{
		String scheduleUrl = "http://api.thescore.com/ncaab/schedule";
		String eventsUrlFormat = "http://api.thescore.com/ncaab/events?id.in=%s";
		List<Event> matches = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		
		String scheduleResponse = getWebResponse(scheduleUrl);
		Season season = mapper.readValue(scheduleResponse, Season.class);

		List<Integer> eventIds = null;
		for (Current_season currSeason: season.getCurrent_season())
		{
			if (currSeason.getId().equals(matchDate))
			{
				eventIds = currSeason.getEvent_ids();
				break;
			}
		}
		
		if (isToday(new SimpleDateFormat("yyyy-MM-dd").parse(matchDate)))
		{
			for (Integer eventId: season.getCurrent_group().getEvent_ids())
			{
				if (!eventIds.contains(eventId))
				{
					eventIds.add(eventId);
				}
			}
		}
		
		if (eventIds != null)
		{
			String strEventIds = String.join(",", Arrays.toString(eventIds.toArray()));
			String eventsUrl = String.format(eventsUrlFormat, URLEncoder.encode(strEventIds, "utf-8"));
			
			String eventsResponse = getWebResponse(eventsUrl);
			System.out.println(eventsUrl);
			System.out.println(eventsResponse);
			matches = Arrays.asList(mapper.readValue(eventsResponse, Event[].class));
		}
		
		return matches;
	}	
	
	
	private String getWebResponse(String url) throws MalformedURLException, IOException, ProtocolException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
	private String GetTeamName(String teamName)
	{
		String formattedTeamName = teamName.trim().toUpperCase();
		
		return formattedTeamName;
	}
	
	private static Boolean isToday(Date matchDate) {
		Calendar matchDateCalendar = Calendar.getInstance();
		matchDateCalendar.setTime(matchDate);
		Calendar currentDateCalendar = Calendar.getInstance();
		return (matchDateCalendar.get(Calendar.YEAR) == currentDateCalendar.get(Calendar.YEAR) 
				&& matchDateCalendar.get(Calendar.MONTH) == currentDateCalendar.get(Calendar.MONTH) 
				&& matchDateCalendar.get(Calendar.DATE) == currentDateCalendar.get(Calendar.DATE));
	}

	
}
