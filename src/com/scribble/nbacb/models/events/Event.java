
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private Box_score box_score;
    private Colours colours;
    private List<String> conference_names = null;
    private String event_status;
    private Boolean has_play_by_play_records;
    private Long id;
    private String game_date;
    private String game_type;
    private Object game_description;
    private Boolean tba;
    private String updated_at;
    private String api_uri;
    private String resource_uri;
    private String status;
    private String recap;
    private List<Event_detail> event_details = null;
    private Tv_listings_by_country_code tv_listings_by_country_code;
    private Away_team away_team;
    private Home_team home_team;
    private Object top_match;
    private League league;
    private String location;
    private String stadium;
    private String away_conference;
    private String home_conference;
    private Boolean has_team_twitter_handles;
    private Standings standings;
    private Odd odd;
    private List<Subscribable_alert> subscribable_alerts = null;
    private Object away_ranking;
    private Object home_ranking;
    private Boolean important;
    private Object slot;
    private Object tournament_name;
    private Top_25_rankings top_25_rankings;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Box_score getBox_score() {
        return box_score;
    }

    public void setBox_score(Box_score box_score) {
        this.box_score = box_score;
    }

    public Colours getColours() {
        return colours;
    }

    public void setColours(Colours colours) {
        this.colours = colours;
    }

    public List<String> getConference_names() {
        return conference_names;
    }

    public void setConference_names(List<String> conference_names) {
        this.conference_names = conference_names;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public Boolean getHas_play_by_play_records() {
        return has_play_by_play_records;
    }

    public void setHas_play_by_play_records(Boolean has_play_by_play_records) {
        this.has_play_by_play_records = has_play_by_play_records;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public Object getGame_description() {
        return game_description;
    }

    public void setGame_description(Object game_description) {
        this.game_description = game_description;
    }

    public Boolean getTba() {
        return tba;
    }

    public void setTba(Boolean tba) {
        this.tba = tba;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getApi_uri() {
        return api_uri;
    }

    public void setApi_uri(String api_uri) {
        this.api_uri = api_uri;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecap() {
        return recap;
    }

    public void setRecap(String recap) {
        this.recap = recap;
    }

    public List<Event_detail> getEvent_details() {
        return event_details;
    }

    public void setEvent_details(List<Event_detail> event_details) {
        this.event_details = event_details;
    }

    public Tv_listings_by_country_code getTv_listings_by_country_code() {
        return tv_listings_by_country_code;
    }

    public void setTv_listings_by_country_code(Tv_listings_by_country_code tv_listings_by_country_code) {
        this.tv_listings_by_country_code = tv_listings_by_country_code;
    }

    public Away_team getAway_team() {
        return away_team;
    }

    public void setAway_team(Away_team away_team) {
        this.away_team = away_team;
    }

    public Home_team getHome_team() {
        return home_team;
    }

    public void setHome_team(Home_team home_team) {
        this.home_team = home_team;
    }

    public Object getTop_match() {
        return top_match;
    }

    public void setTop_match(Object top_match) {
        this.top_match = top_match;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getAway_conference() {
        return away_conference;
    }

    public void setAway_conference(String away_conference) {
        this.away_conference = away_conference;
    }

    public String getHome_conference() {
        return home_conference;
    }

    public void setHome_conference(String home_conference) {
        this.home_conference = home_conference;
    }

    public Boolean getHas_team_twitter_handles() {
        return has_team_twitter_handles;
    }

    public void setHas_team_twitter_handles(Boolean has_team_twitter_handles) {
        this.has_team_twitter_handles = has_team_twitter_handles;
    }

    public Standings getStandings() {
        return standings;
    }

    public void setStandings(Standings standings) {
        this.standings = standings;
    }

    public Odd getOdd() {
        return odd;
    }

    public void setOdd(Odd odd) {
        this.odd = odd;
    }

    public List<Subscribable_alert> getSubscribable_alerts() {
        return subscribable_alerts;
    }

    public void setSubscribable_alerts(List<Subscribable_alert> subscribable_alerts) {
        this.subscribable_alerts = subscribable_alerts;
    }

    public Object getAway_ranking() {
        return away_ranking;
    }

    public void setAway_ranking(Object away_ranking) {
        this.away_ranking = away_ranking;
    }

    public Object getHome_ranking() {
        return home_ranking;
    }

    public void setHome_ranking(Object home_ranking) {
        this.home_ranking = home_ranking;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public Object getSlot() {
        return slot;
    }

    public void setSlot(Object slot) {
        this.slot = slot;
    }

    public Object getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(Object tournament_name) {
        this.tournament_name = tournament_name;
    }

    public Top_25_rankings getTop_25_rankings() {
        return top_25_rankings;
    }

    public void setTop_25_rankings(Top_25_rankings top_25_rankings) {
        this.top_25_rankings = top_25_rankings;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
