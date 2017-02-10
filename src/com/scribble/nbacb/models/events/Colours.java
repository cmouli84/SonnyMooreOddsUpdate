
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Colours {

    private Object away;
    private Object home;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getAway() {
        return away;
    }

    public void setAway(Object away) {
        this.away = away;
    }

    public Object getHome() {
        return home;
    }

    public void setHome(Object home) {
        this.home = home;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
