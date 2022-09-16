package shangchi;

import java.util.ArrayList;
import java.util.Comparator;

public class LengthComparator implements Comparator<ArrayList<Integer>> {

	@Override
	public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
		// TODO Auto-generated method stub
		int result = o1.size()-o2.size();
		if (result==0) {
			return (o1.get(0) - o2.get(0));
		}
		return result;
	}

}
