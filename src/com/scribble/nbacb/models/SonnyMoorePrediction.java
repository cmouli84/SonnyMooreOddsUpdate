package com.scribble.nbacb.models;

import java.util.Date;

public class SonnyMoorePrediction {
	private Integer eventId;
	private String homeTeamName;
	private String awayTeamName;
	private Double homeTeamRanking;
	private Double awayTeamRanking;
	private Date gameDate;
	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the homeTeamName
	 */
	public String getHomeTeamName() {
		return homeTeamName;
	}
	/**
	 * @param homeTeamName the homeTeamName to set
	 */
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	/**
	 * @return the awayTeamName
	 */
	public String getAwayTeamName() {
		return awayTeamName;
	}
	/**
	 * @param awayTeamName the awayTeamName to set
	 */
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	/**
	 * @return the homeTeamRanking
	 */
	public Double getHomeTeamRanking() {
		return homeTeamRanking;
	}
	/**
	 * @param homeTeamRanking the homeTeamRanking to set
	 */
	public void setHomeTeamRanking(Double homeTeamRanking) {
		this.homeTeamRanking = homeTeamRanking;
	}
	/**
	 * @return the awayTeamRanking
	 */
	public Double getAwayTeamRanking() {
		return awayTeamRanking;
	}
	/**
	 * @param awayTeamRanking the awayTeamRanking to set
	 */
	public void setAwayTeamRanking(Double awayTeamRanking) {
		this.awayTeamRanking = awayTeamRanking;
	}
	/**
	 * @return the gameDate
	 */
	public Date getGameDate() {
		return gameDate;
	}
	/**
	 * @param gameDate the gameDate to set
	 */
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

}
