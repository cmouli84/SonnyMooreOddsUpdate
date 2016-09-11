
package com.scribble.nbacb.models.events;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Box_score {

private Integer id;
private Boolean has_statistics;
private Progress progress;
private String updated_at;
private String api_uri;
private Score score;
private Integer minutes;
private Integer segment_number;
private Integer down;
private String formatted_distance;
private Integer yards_from_goal;
private Integer home_timeouts_left;
private Integer away_timeouts_left;
private Boolean under_review;
private String field_position;
private Team_in_possession team_in_possession;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The id
*/
public Integer getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Integer id) {
this.id = id;
}

/**
* 
* @return
* The has_statistics
*/
public Boolean getHas_statistics() {
return has_statistics;
}

/**
* 
* @param has_statistics
* The has_statistics
*/
public void setHas_statistics(Boolean has_statistics) {
this.has_statistics = has_statistics;
}

/**
* 
* @return
* The progress
*/
public Progress getProgress() {
return progress;
}

/**
* 
* @param progress
* The progress
*/
public void setProgress(Progress progress) {
this.progress = progress;
}

/**
* 
* @return
* The updated_at
*/
public String getUpdated_at() {
return updated_at;
}

/**
* 
* @param updated_at
* The updated_at
*/
public void setUpdated_at(String updated_at) {
this.updated_at = updated_at;
}

/**
* 
* @return
* The api_uri
*/
public String getApi_uri() {
return api_uri;
}

/**
* 
* @param api_uri
* The api_uri
*/
public void setApi_uri(String api_uri) {
this.api_uri = api_uri;
}

/**
* 
* @return
* The score
*/
public Score getScore() {
return score;
}

/**
* 
* @param score
* The score
*/
public void setScore(Score score) {
this.score = score;
}

/**
* 
* @return
* The minutes
*/
public Integer getMinutes() {
return minutes;
}

/**
* 
* @param minutes
* The minutes
*/
public void setMinutes(Integer minutes) {
this.minutes = minutes;
}

/**
* 
* @return
* The segment_number
*/
public Integer getSegment_number() {
return segment_number;
}

/**
* 
* @param segment_number
* The segment_number
*/
public void setSegment_number(Integer segment_number) {
this.segment_number = segment_number;
}

/**
* 
* @return
* The down
*/
public Integer getDown() {
return down;
}

/**
* 
* @param down
* The down
*/
public void setDown(Integer down) {
this.down = down;
}

/**
* 
* @return
* The formatted_distance
*/
public String getFormatted_distance() {
return formatted_distance;
}

/**
* 
* @param formatted_distance
* The formatted_distance
*/
public void setFormatted_distance(String formatted_distance) {
this.formatted_distance = formatted_distance;
}

/**
* 
* @return
* The yards_from_goal
*/
public Integer getYards_from_goal() {
return yards_from_goal;
}

/**
* 
* @param yards_from_goal
* The yards_from_goal
*/
public void setYards_from_goal(Integer yards_from_goal) {
this.yards_from_goal = yards_from_goal;
}

/**
* 
* @return
* The home_timeouts_left
*/
public Integer getHome_timeouts_left() {
return home_timeouts_left;
}

/**
* 
* @param home_timeouts_left
* The home_timeouts_left
*/
public void setHome_timeouts_left(Integer home_timeouts_left) {
this.home_timeouts_left = home_timeouts_left;
}

/**
* 
* @return
* The away_timeouts_left
*/
public Integer getAway_timeouts_left() {
return away_timeouts_left;
}

/**
* 
* @param away_timeouts_left
* The away_timeouts_left
*/
public void setAway_timeouts_left(Integer away_timeouts_left) {
this.away_timeouts_left = away_timeouts_left;
}

/**
* 
* @return
* The under_review
*/
public Boolean getUnder_review() {
return under_review;
}

/**
* 
* @param under_review
* The under_review
*/
public void setUnder_review(Boolean under_review) {
this.under_review = under_review;
}

/**
* 
* @return
* The field_position
*/
public String getField_position() {
return field_position;
}

/**
* 
* @param field_position
* The field_position
*/
public void setField_position(String field_position) {
this.field_position = field_position;
}

/**
* 
* @return
* The team_in_possession
*/
public Team_in_possession getTeam_in_possession() {
return team_in_possession;
}

/**
* 
* @param team_in_possession
* The team_in_possession
*/
public void setTeam_in_possession(Team_in_possession team_in_possession) {
this.team_in_possession = team_in_possession;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
