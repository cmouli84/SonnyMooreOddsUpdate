
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Standings {

    private Away_ away;
    private Home_ home;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Away_ getAway() {
        return away;
    }

    public void setAway(Away_ away) {
        this.away = away;
    }

    public Home_ getHome() {
        return home;
    }

    public void setHome(Home_ home) {
        this.home = home;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
