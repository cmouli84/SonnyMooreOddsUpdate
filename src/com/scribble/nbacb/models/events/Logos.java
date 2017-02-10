
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Logos {

    private String large;
    private String small;
    private String w72xh72;
    private String tiny;
    private Object facing;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getW72xh72() {
        return w72xh72;
    }

    public void setW72xh72(String w72xh72) {
        this.w72xh72 = w72xh72;
    }

    public String getTiny() {
        return tiny;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }

    public Object getFacing() {
        return facing;
    }

    public void setFacing(Object facing) {
        this.facing = facing;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
