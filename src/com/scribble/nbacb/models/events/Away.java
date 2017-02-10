
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Away {

    private Long score;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
