package shangchi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class MagicGenerator2440 {

	public static ArrayList<ArrayList<Integer>> getRandomMagic(){
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list.add(new ArrayList<Integer>());
		list.get(0).add(11);
		list.add(new ArrayList<Integer>());
		list.get(1).add(12);
		list.add(new ArrayList<Integer>());
		list.get(2).add(13);
		
		list.add(new ArrayList<Integer>());
		list.get(3).add(21);
		list.add(new ArrayList<Integer>());
		list.get(4).add(22);
		list.add(new ArrayList<Integer>());
		list.get(5).add(23);
		
		list.add(new ArrayList<Integer>());
		list.get(6).add(31);
		list.add(new ArrayList<Integer>());
		list.get(7).add(32);
		list.add(new ArrayList<Integer>());
		list.get(8).add(33);
		
		int num;
	
		for (int i=1;i<4;i++) {
			num= getRandomOpenIndex(list);
			list.get(num).add(i);
			
			num = getRandomOpenIndex(list);
			list.get(num).add(i);
			
			num = getRandomOpenIndex(list);
			list.get(num).add(i);
		}
		
		Collections.sort(list, new LengthComparator());
		return list;
	}
	
	private static int getRandomOpenIndex(ArrayList<ArrayList<Integer>> magic) {
		Collections.sort(magic, new LengthComparator());
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> row : magic) {
			if (row.size()==1) {
				list.add(row);
			}
		}
		Random r = new Random();
		int index = r.nextInt(list.size());
		return index;
	}
	
	public static ArrayList<ArrayList<Integer>> getExampleMagic(){
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list.add(new ArrayList<Integer>());
		list.get(0).add(11);
		list.get(0).add(2);
		list.add(new ArrayList<Integer>());
		list.get(1).add(12);
		list.get(1).add(2);
		list.add(new ArrayList<Integer>());
		list.get(2).add(13);
		list.get(2).add(1);
		
		list.add(new ArrayList<Integer>());
		list.get(3).add(21);
		list.get(3).add(3);
		list.add(new ArrayList<Integer>());
		list.get(4).add(22);
		list.get(4).add(2);
		list.add(new ArrayList<Integer>());
		list.get(5).add(23);
		list.get(5).add(1);
		
		list.add(new ArrayList<Integer>());
		list.get(6).add(31);
		list.get(6).add(1);
		list.add(new ArrayList<Integer>());
		list.get(7).add(32);
		list.get(7).add(3);
		list.add(new ArrayList<Integer>());
		list.get(8).add(33);
		list.get(8).add(3);
		
		return list;
		
	}
}
