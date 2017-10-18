
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Recommender{
    
    public Recommender() {
    	
    }
    
    public ArrayList<Rating> printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        int minimalrater = 5;
        int numsimilarrater = 20;
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, numsimilarrater, minimalrater);
        return ratings;
		
    }
    
}
