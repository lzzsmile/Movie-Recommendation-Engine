
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String mydir;
    
    public DirectorsFilter(String dir){
        mydir = dir;
    }
    
    @Override
    public boolean satisfies(String id){
        String moviedir = MovieDatabase.getDirector(id);
        String[] dirs = moviedir.split(",");
        for(int i = 0; i < dirs.length; i++){
            if(mydir.indexOf(dirs[i].trim()) != -1){
                return true;
            }
        }
        return false;
    }
}
