package com.scribble.nbacb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scribble.nbacb.service.CollegeBasketBallOddsService;

public class SonnyMooreOddsUpdateHandler implements RequestHandler<Object, Boolean> {

    @Override
    public Boolean handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        
        DynamoDB dynamoDb = new DynamoDB(Regions.US_WEST_2);
        Table events = dynamoDb.getTable("NflEvents");
        
        CollegeBasketBallOddsService service = new CollegeBasketBallOddsService();

        try {
    		service.updateNflSonnyMooreRankings(events);
        }
        catch (Exception ex) {
        	System.out.println(ex.toString());
        }
        
        // TODO: implement your handler
        return true;
    }

}
