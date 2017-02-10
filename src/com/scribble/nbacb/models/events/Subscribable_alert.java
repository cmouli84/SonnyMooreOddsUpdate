
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Subscribable_alert {

    private String key;
    private String display;
    private Boolean _default;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
