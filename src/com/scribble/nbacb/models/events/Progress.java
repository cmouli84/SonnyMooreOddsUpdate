
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;

public class Progress {

    private String clock_label;
    private String string;
    private String status;
    private String event_status;
    private Long segment;
    private String segment_string;
    private String segment_description;
    private String clock;
    private Boolean overtime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getClock_label() {
        return clock_label;
    }

    public void setClock_label(String clock_label) {
        this.clock_label = clock_label;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public Long getSegment() {
        return segment;
    }

    public void setSegment(Long segment) {
        this.segment = segment;
    }

    public String getSegment_string() {
        return segment_string;
    }

    public void setSegment_string(String segment_string) {
        this.segment_string = segment_string;
    }

    public String getSegment_description() {
        return segment_description;
    }

    public void setSegment_description(String segment_description) {
        this.segment_description = segment_description;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public Boolean getOvertime() {
        return overtime;
    }

    public void setOvertime(Boolean overtime) {
        this.overtime = overtime;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
