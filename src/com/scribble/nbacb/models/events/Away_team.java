
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Away_team {

    private String abbreviation;
    private Object colour_1;
    private Object colour_2;
    private String division;
    private String full_name;
    private String name;
    private String search_name;
    private Long id;
    private Object location;
    private Logos logos;
    private String medium_name;
    private String short_name;
    private String conference;
    private Boolean has_injuries;
    private Boolean has_rosters;
    private String updated_at;
    private Long subscription_count;
    private String api_uri;
    private String resource_uri;
    private List<Subscribable_alert> subscribable_alerts = null;
    private String subscribable_alert_text;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Object getColour_1() {
        return colour_1;
    }

    public void setColour_1(Object colour_1) {
        this.colour_1 = colour_1;
    }

    public Object getColour_2() {
        return colour_2;
    }

    public void setColour_2(Object colour_2) {
        this.colour_2 = colour_2;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Logos getLogos() {
        return logos;
    }

    public void setLogos(Logos logos) {
        this.logos = logos;
    }

    public String getMedium_name() {
        return medium_name;
    }

    public void setMedium_name(String medium_name) {
        this.medium_name = medium_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public Boolean getHas_injuries() {
        return has_injuries;
    }

    public void setHas_injuries(Boolean has_injuries) {
        this.has_injuries = has_injuries;
    }

    public Boolean getHas_rosters() {
        return has_rosters;
    }

    public void setHas_rosters(Boolean has_rosters) {
        this.has_rosters = has_rosters;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Long getSubscription_count() {
        return subscription_count;
    }

    public void setSubscription_count(Long subscription_count) {
        this.subscription_count = subscription_count;
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

    public List<Subscribable_alert> getSubscribable_alerts() {
        return subscribable_alerts;
    }

    public void setSubscribable_alerts(List<Subscribable_alert> subscribable_alerts) {
        this.subscribable_alerts = subscribable_alerts;
    }

    public String getSubscribable_alert_text() {
        return subscribable_alert_text;
    }

    public void setSubscribable_alert_text(String subscribable_alert_text) {
        this.subscribable_alert_text = subscribable_alert_text;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
