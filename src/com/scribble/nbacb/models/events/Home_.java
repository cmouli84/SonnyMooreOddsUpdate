
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Home_ {

    private String streak;
    private String conference;
    private String conference_abbreviation;
    private Long conference_seed;
    private Object division;
    private Object last_ten_games_record;
    private Long place;
    private String short_record;
    private Long conference_rank;
    private Object division_rank;
    private Object division_seed;
    private String formatted_rank;
    private String short_away_record;
    private String short_home_record;
    private String api_uri;
    private Long conference_ranking;
    private Long division_ranking;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getConference_abbreviation() {
        return conference_abbreviation;
    }

    public void setConference_abbreviation(String conference_abbreviation) {
        this.conference_abbreviation = conference_abbreviation;
    }

    public Long getConference_seed() {
        return conference_seed;
    }

    public void setConference_seed(Long conference_seed) {
        this.conference_seed = conference_seed;
    }

    public Object getDivision() {
        return division;
    }

    public void setDivision(Object division) {
        this.division = division;
    }

    public Object getLast_ten_games_record() {
        return last_ten_games_record;
    }

    public void setLast_ten_games_record(Object last_ten_games_record) {
        this.last_ten_games_record = last_ten_games_record;
    }

    public Long getPlace() {
        return place;
    }

    public void setPlace(Long place) {
        this.place = place;
    }

    public String getShort_record() {
        return short_record;
    }

    public void setShort_record(String short_record) {
        this.short_record = short_record;
    }

    public Long getConference_rank() {
        return conference_rank;
    }

    public void setConference_rank(Long conference_rank) {
        this.conference_rank = conference_rank;
    }

    public Object getDivision_rank() {
        return division_rank;
    }

    public void setDivision_rank(Object division_rank) {
        this.division_rank = division_rank;
    }

    public Object getDivision_seed() {
        return division_seed;
    }

    public void setDivision_seed(Object division_seed) {
        this.division_seed = division_seed;
    }

    public String getFormatted_rank() {
        return formatted_rank;
    }

    public void setFormatted_rank(String formatted_rank) {
        this.formatted_rank = formatted_rank;
    }

    public String getShort_away_record() {
        return short_away_record;
    }

    public void setShort_away_record(String short_away_record) {
        this.short_away_record = short_away_record;
    }

    public String getShort_home_record() {
        return short_home_record;
    }

    public void setShort_home_record(String short_home_record) {
        this.short_home_record = short_home_record;
    }

    public String getApi_uri() {
        return api_uri;
    }

    public void setApi_uri(String api_uri) {
        this.api_uri = api_uri;
    }

    public Long getConference_ranking() {
        return conference_ranking;
    }

    public void setConference_ranking(Long conference_ranking) {
        this.conference_ranking = conference_ranking;
    }

    public Long getDivision_ranking() {
        return division_ranking;
    }

    public void setDivision_ranking(Long division_ranking) {
        this.division_ranking = division_ranking;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
