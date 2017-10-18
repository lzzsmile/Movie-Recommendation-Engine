
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> al = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord cr : parser){
            String id = cr.get("id");
            String title = cr.get("title");
            String year = cr.get("year");
            String country = cr.get("country");
            String genre = cr.get("genre");
            String director = cr.get("director");
            String poster = cr.get("poster");
            String minutes = cr.get("minutes");
            Movie mv = new Movie(id, title, year, genre, director, country, poster, minutes);
            al.add(mv);
        }
        return al;
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename){
        ArrayList<EfficientRater> al = new ArrayList<EfficientRater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord cr : parser){
            String raterid = cr.get("rater_id");
            String movieid = cr.get("movie_id");
            double rating = Double.parseDouble(cr.get("rating"));
            boolean addin = false;
            if(al.size() != 0){
                for(int i = 0; i < al.size(); i++){
                    if(al.get(i).getID().equals(raterid)){
                        al.get(i).addRating(movieid, rating);
                        addin = true;
                        break;
                    }
                }
                if(addin == false){
                    EfficientRater rater = new EfficientRater(raterid);
                    rater.addRating(movieid, rating);
                    al.add(rater);
                }
            }
            else {
                EfficientRater rater = new EfficientRater(raterid);
                rater.addRating(movieid, rating);
                al.add(rater);
            }
        }
        return al;
    }
    
    public void testLoadRaters(){
        String source = "data/ratings.csv";
        ArrayList<EfficientRater> raters = loadRaters(source);
        
        String id = "193";
        for(int j = 0; j < raters.size(); j++){
            if(raters.get(j).getID().equals(id)){
                System.out.println(raters.get(j).numRatings());
            }
        }
        
        
        int max = 0;
        for(int j = 0; j < raters.size(); j++){
            if(raters.get(j).numRatings() > max){
                max = raters.get(j).numRatings();
            }
        }
        ArrayList<String> ids = new ArrayList<String>();
        for(int k = 0; k < raters.size(); k++){
            if(raters.get(k).numRatings() == max){
                ids.add(raters.get(k).getID());
            }
        }
        System.out.println("Raters who have " + max + " ratings: " + ids.toString());
        
        
        int num = 0;
        String movieid = "1798709";
        for(int j = 0; j < raters.size(); j++){
            Rater rat = raters.get(j);
            ArrayList<String> items = rat.getItemsRated();
            if(items.contains(movieid)){
                //ArrayList<String> copy = items;
                //while(copy.indexOf(movieid) != -1){
                    num = num+1;
                    //copy.remove(copy.indexOf(movieid));
                //}
            }
        }
        System.out.println("There are " + num + " " + movieid);
        
        ArrayList<String> itemsall = new ArrayList<String>();
        for(int j = 0; j < raters.size(); j++){
            Rater rat = raters.get(j);
            ArrayList<String> temp = rat.getItemsRated();
            for(int k = 0; k < temp.size(); k++){
                String idtemp = temp.get(k);
                if(!itemsall.contains(idtemp)){
                    itemsall.add(idtemp);
                }
            }
        }
        System.out.println("There are " + itemsall.size() + " movies");
        System.out.println("\n");
        System.out.println("There are " + raters.size() + " raters");
        for(int i = 0; i < raters.size(); i++){
            System.out.println(raters.get(i).getID() + "\t" + raters.get(i).numRatings());
            ArrayList<String> items = raters.get(i).getItemsRated();
            for(int j = 0; j < items.size(); j++){
                double rating = raters.get(i).getRating(items.get(j));
                System.out.println(items.get(j) + "\t" + rating);
            }
            System.out.print("\n");
        }

    }
    
    public void tesLoadMovies(){
        String source = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(source);
        
        int numge = 0;
        String genre = "Comedy";
        for(int j = 0; j < movies.size(); j++){
            String ge = movies.get(j).getGenres();
            if(ge.indexOf(genre) != -1){
                numge = numge + 1;
            }
        }
        System.out.println("There are " + numge +" " + genre + " in the list");
        
        
        int nummi = 0;
        for(int j = 0; j < movies.size(); j++){
            int minute = movies.get(j).getMinutes();
            if(minute > 150){
                nummi = nummi+1;
            }
        }
        System.out.println("There are " + nummi +" movies are greater than 150 minutes");
        
        
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        for(int j = 0; j < movies.size(); j++){
            String dir = movies.get(j).getDirector();
            String[] dirs = dir.split(",");
            for(int k = 0; k < dirs.length; k++){
                String temp = dirs[k].trim();
                if(!count.containsKey(temp)){
                    count.put(temp, 1);
                }
                else{
                    count.put(temp, count.get(temp)+1);
                }
            }
        }
        int max = 0;
        ArrayList<String> names = new ArrayList<String>();
        for(String s : count.keySet()){
            if(count.get(s) > max){
                max = count.get(s);
            }
        }
        for(String s1 : count.keySet()){
            if(count.get(s1) == max){
                names.add(s1);
            }
        }
        System.out.println(names.toString() + " directed the most movies: " + max);
        
        System.out.println("Total movies in the list: " + movies.size());
        for(int i = 0; i < movies.size(); i++){
            System.out.println(movies.get(i));
        }
    }
}
