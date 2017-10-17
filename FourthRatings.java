
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    public FourthRatings(){
        
    }
    
    private double dotProduct(Rater me, Rater r){
        double sum = 0.0;
        ArrayList<String> myitems = me.getItemsRated();
        ArrayList<String> ritems = r.getItemsRated();
        for(String s : myitems){
            if(ritems.contains(s)){
                double myrating = me.getRating(s);
                double rrating = r.getRating(s);
                sum = sum + (myrating - 5)*(rrating - 5);
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> al = new ArrayList<Rating> ();
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for(Rater r : raters){
            if(! r.getID().equals(me.getID())){
                al.add(new Rating(r.getID(), dotProduct(me, r)));
            }
        }
        Collections.sort(al, Collections.reverseOrder());
        return al;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> al = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        for(String movieid : MovieDatabase.filterBy(new TrueFilter())){
            double sum = 0.0;
            int count = 0;
            int sumcount = 0;
            for(int k = 0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                Rater rr = RaterDatabase.getRater(r.getItem());
                if(rr.getRating(movieid) != -1){
                    count = count + 1;
                    if(r.getValue() > 0){
                        sum = sum + r.getValue()*rr.getRating(movieid);
                        sumcount = sumcount + 1;
                    }
                    
                }
            }
            if(count >= minimalRaters){
                al.add(new Rating(movieid, sum/sumcount));
            }
        }
        Collections.sort(al, Collections.reverseOrder());
        return al;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> al = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        for(String movieid : MovieDatabase.filterBy(filterCriteria)){
            double sum = 0.0;
            int count = 0;
            int sumcount = 0;
            for(int k = 0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                Rater rr = RaterDatabase.getRater(r.getItem());
                if(rr.getRating(movieid) != -1){
                    count = count + 1;
                    if(r.getValue() > 0){
                        sum = sum + r.getValue()*rr.getRating(movieid);
                        sumcount = sumcount + 1;
                    }
                }
            }
            if(count >= minimalRaters){
                al.add(new Rating(movieid, sum/sumcount));
            }
        }
        Collections.sort(al, Collections.reverseOrder());
        return al;
    }
     
    private double getAverageByID(String id, int minimalRaters){
        int numrater = 0;
        double ratetotal = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for(int j = 0; j < myRaters.size(); j++){
            Rater rat = myRaters.get(j);
            ArrayList<String> items = rat.getItemsRated();
            if(items.contains(id)){
                numrater = numrater + 1;
                ratetotal = ratetotal + rat.getRating(id);
            }
        }
        if(numrater >= minimalRaters){
            return ratetotal/numrater;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> al = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(int i = 0; i < movies.size(); i++){
            String id = movies.get(i);
            double averate = getAverageByID(id, minimalRaters);
            if(averate != 0.0){
                Rating rating = new Rating(id, averate);
                al.add(rating);
            }
        }
        return al;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> al = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(int i = 0; i < movies.size(); i++){
            String id = movies.get(i);
            double averate = getAverageByID(id, minimalRaters);
            if(averate != 0.0){
                Rating rating = new Rating(id, averate);
                al.add(rating);
            }
        }
        return al;
    }
}
