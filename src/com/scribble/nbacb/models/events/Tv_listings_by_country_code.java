
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Tv_listings_by_country_code {

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
