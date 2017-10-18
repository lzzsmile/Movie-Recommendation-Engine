import java.util.ArrayList;

public class Test {
	public static void main(){
		String webRaterID = "2";
		Recommender rec0 = new Recommender();
		ArrayList<Rating> ratings = rec0.printRecommendationsFor(webRaterID);
		for(int i = 0; i < ratings.size(); i++) {
			System.out.println(ratings.get(i));
		}
	}
	
}