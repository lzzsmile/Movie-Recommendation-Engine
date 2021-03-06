
public class MinutesFilter implements Filter {
	private int min;
    private int max;
    
    public MinutesFilter(int minminute, int maxminute){
        min = minminute;
        max = maxminute;
    }
    
    @Override
    public boolean satisfies(String id){
        if(MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max){
            return true;
        }
        return false;
    }
}
