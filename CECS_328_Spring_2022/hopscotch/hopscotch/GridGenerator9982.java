package hopscotch;
import java.util.Random;
import java.util.ArrayList;

public class GridGenerator9982 {

	public static ArrayList<ArrayList<Integer>> getGrid(int m, int n, int range){
		
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(); 
		Random r = new Random();
		
		for (int i=0;i<m;i++) {
			list.add(new ArrayList<Integer>());
			for (int j=0;j<n;j++) {
				list.get(i).add(r.nextInt(range)+1);
			}
		}
		
		return list;
	}
}
