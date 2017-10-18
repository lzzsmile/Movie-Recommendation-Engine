import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recommenderServlet")
public class recommenderServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
		
		
		// read form fields
        String webRaterID = request.getParameter("id");
        Recommender rec = new Recommender();
        ArrayList<Rating> ratings = rec.printRecommendationsFor(webRaterID);
        //int length = ratings.size();
        int length = Math.min(8, ratings.size());
        // get response writer
        PrintWriter writer = response.getWriter();
        String s = "<!DOCTYPE html>\n";
        s += "<html>\n";
        s += "<head>\n";
        s += "<meta charset=\"UTF-8\">\n";
        s += "<meta name=\"author\" content=\"Zhuangzhi Li\">\n";
        s += "<link rel=\"stylesheet\" href=\"style.css\">\n";
        s += "<title>Movie Recommendation Engine</title>\n";
        s += "</head>\n";
        s += "<style>\n";
        s += "TABLE {\n";
        s += "   border: 1px solid black;\n";
        s += "   border-collapse: collapse;\n";
        s += "   margin: 0 auto;\n";
        s += "}\n";
        s += "TD {\n";
        s += "   border: 1px solid black;\n";
        s += "   padding: 5px;\n";
        s += "   vertical-align: middle;\n";
        s += "}\n"; 
        s += "TH {\n";
        s += "   border: 1px solid black;\n";
        s += "   padding: 5px;\n";
        s += "}\n"; 
        s += "TR:nth-child(even) {\n";
        s += "   background-color: #f2f2f2;\n";
        s += "}\n"; 
        s += "h3 {\n";
        s += "   text-align: center;\n";
        s += "   color: #001A57;\n";
        s += "}\n"; 
        s += "h1 {\n";
        s += "   text-align: center;\n";
        s += "   color: #000000;\n";
        s += "}\n";
        s += "</style>\n";
        s += "<body>\n";
        s += "<div class=\"titlebar\">";
        s += "  <h1>Zhuangzhi Li brings you the following movies that you may like.</h1>\n";
        s += "</div>\n";
        s += "<div class=\"content\">\n";
        s += "<p>\n";
        s += "<div class=\"lessons\">\n";
        s += "<table>\n";
        s += "  <tr>\n";
        s += "    <th>#</th>\n";
        s += "    <th>Title</th>\n";
        s += "    <th>Genre</th>\n";
        s += "    <th>Year</th>\n";
        s += "    <th>Time</th>\n";
        s += "  </tr>\n";
        for(int i = 0; i < length; i++) {
        	int num = i+1;
        	s += "  <tr>\n";
        	s += "    <td>"+num+"</td>" + "\n";
        	s += "    <td>"+MovieDatabase.getTitle(ratings.get(i).getItem())+"</td>" + "\n";
        	s += "    <td>"+MovieDatabase.getCountry(ratings.get(i).getItem())+"</td>" + "\n";
        	s += "    <td>"+MovieDatabase.getYear(ratings.get(i).getItem())+"</td>" + "\n";
        	s += "    <td>"+MovieDatabase.getMinutes(ratings.get(i).getItem())+" Minutes"+"</td>" + "\n";
        	s += "  </tr>\n";
        }
        s += "</table>\n";
        s += "</div>\n";
        s += "</p>\n";
        s += "</div>\n";
        s += "</body>\n";
        s += "</html>\n";
        
        // return response
        writer.println(s);
         
    }

}
