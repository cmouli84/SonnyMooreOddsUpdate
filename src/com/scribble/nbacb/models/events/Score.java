
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private Home home;
    private Away away;
    private String winning_team;
    private String losing_team;
    private Boolean tie_game;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }

    public String getWinning_team() {
        return winning_team;
    }

    public void setWinning_team(String winning_team) {
        this.winning_team = winning_team;
    }

    public String getLosing_team() {
        return losing_team;
    }

    public void setLosing_team(String losing_team) {
        this.losing_team = losing_team;
    }

    public Boolean getTie_game() {
        return tie_game;
    }

    public void setTie_game(Boolean tie_game) {
        this.tie_game = tie_game;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
