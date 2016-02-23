package com.scribble.nbacb;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scribble.nbacb.models.SonnyMoorePrediction;
import com.scribble.nbacb.service.CollegeBasketBallOddsService;

public class SonnyMooreOddsUpdateHandler implements RequestHandler<Object, Boolean> {

    @Override
    public Boolean handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        
        DynamoDB dynamoDb = new DynamoDB(Regions.US_WEST_2);
        
        Table events = dynamoDb.getTable("Event");
        
        CollegeBasketBallOddsService service = new CollegeBasketBallOddsService();

		try {
			List<SonnyMoorePrediction> sonnyMoorePredictions = service.getSonnyMooreRankings();

	        for (SonnyMoorePrediction prediction: sonnyMoorePredictions)
	        {
	            Item currentEvent = new Item();
	            currentEvent.withInt("EventId", prediction.getEventId());
	            currentEvent.withString("HomeTeamName", prediction.getHomeTeamName());
	            currentEvent.withString("AwayTeamName", prediction.getAwayTeamName());
	            currentEvent.withDouble("HomeTeamRanking", prediction.getHomeTeamRanking());
	            currentEvent.withDouble("AwayTeamRanking", prediction.getAwayTeamRanking());
	            currentEvent.withString("EventDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(prediction.getGameDate()));
	            
	        	events.putItem(currentEvent);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // TODO: implement your handler
        return true;
    }

}
