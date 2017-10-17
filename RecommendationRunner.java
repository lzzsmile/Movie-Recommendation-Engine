
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> al = MovieDatabase.filterBy(new MinutesFilter(70, 120));
        ArrayList<String> movieid = new ArrayList<String>(12);
        for(int i = 0; i < 12; i++){
            movieid.add(al.get(i));
        }
        return movieid;
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        int minimalrater = 5;
        int numsimilarrater = 20;
        ArrayList<Rating> al = fr.getSimilarRatings(webRaterID, numsimilarrater, minimalrater);
		System.out.println("<style>");
        System.out.println("h2.error {");
        System.out.println("  background-color: #FFD700;");
        System.out.println("    color: #DC143C;");
        System.out.println("  margin: 5;");
        System.out.println("}");
        System.out.println("</style>");
        System.out.println("<div class=\"content\">");
        System.out.println("  <div class=\"ui-widget\">");
        System.out.println("    <html>");
        System.out.println("<style>");
        System.out.println("  #customers,");
        System.out.println("  h2 {");
        System.out.println("    font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;");
        System.out.println("    border-collapse: collapse;");
        System.out.println("    width: 100%;");
        System.out.println("    text-align: center;");
        System.out.println("  }");
        System.out.println("  #customers td,");
        System.out.println("  #customers th,");
        System.out.println("  h2 {");
        System.out.println("    border: 1px solid #ddd;");
        System.out.println("    padding: 8px;");
        System.out.println("  }");
        System.out.println("  #customers tr:nth-child(even) {");
        System.out.println("    background-color: #f2f2f2;");
        System.out.println("  }");
        System.out.println("  #customers tr:hover {");
        System.out.println("    background-color: #ddd;");
        System.out.println("  }");
        System.out.println("  #customers th {");
        System.out.println("    padding-top: 12px;");
        System.out.println("    padding-bottom: 12px;");
        System.out.println("    text-align: center;");
        System.out.println("    background-color: #4CAF50;");
        System.out.println("    color: white;");
        System.out.println("  }");
        System.out.println("  #customers img {");
        System.out.println("    height: 50%;");
        System.out.println("  }");
        System.out.println("  h2 {");
        System.out.println("    background-color: #4CAF50;");
        System.out.println("  }");
        System.out.println("</style><h2>Zhuangzhi Li brings you the following movies that you may like.</h2>");
        System.out.println("<table id=\"customers\">");
        System.out.println("  <tr>");
        System.out.println("    <th>#</th>");
        System.out.println("    <th>Poster</th>");
        System.out.println("    <th>Title</th>");
        System.out.println("    <th>Genre</th>");
        System.out.println("    <th>Year</th>");
        System.out.println("    <th>Time</th>");
        System.out.println("  </tr>  <tr>");
        for(int i = 0; i < al.size(); i++){
			int num = i+1;
            System.out.println("    <td>"+num+"</td>");
            System.out.println("    <td><img src=");
            System.out.println(     "\""+MovieDatabase.getPoster(ratings.get(i).getItem())+"\"");
            System.out.println(" /> </td>");
            System.out.println("    <td>"+MovieDatabase.getTitle(ratings.get(i).getItem())+"</td>");
            System.out.println("    <td>"+MovieDatabase.getCountry(ratings.get(i).getItem())+"</td>");
            System.out.println("    <td>"+MovieDatabase.getYear(ratings.get(i).getItem())+"</td>");
            System.out.println("    <td>"+MovieDatabase.getMinutes(ratings.get(i).getItem())+" Minutes"+"</td>");
            System.out.println("  </tr>  <tr>");
        }
    }
    
}
