package squidGame;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

public class MaxN {
	
	private static int numLines;
	private static HashMap<HashSet<Integer>,Integer> map1;
	private static HashMap<HashSet<Integer>,HashSet<HashSet<Integer>>> map2;
	

	public static int compute(int numLine) {
		
		numLines = numLine;
		HashSet<Integer> hset = new HashSet<Integer>();
		map1 = new HashMap<HashSet<Integer>,Integer>();
		map2 = new HashMap<HashSet<Integer>,HashSet<HashSet<Integer>>>();
		return standInLine(hset,1);
	}
	
	private static int standInLine(HashSet<Integer> list, int num) {
		if (map1.containsKey(list))
			return map1.get(list);
		int max = num-1;
		
		HashSet<HashSet<Integer>> set = possibleLeafs(list, num);
		
		
		Iterator<HashSet<Integer>> iterator = set.iterator();
		int t;
		while (iterator.hasNext()) {
			t = standInLine(iterator.next(),num+1);
			if (t>max) {
				max=t;
			}
		}
		map1.put(list, max);
		return max;
	}
	
	@SuppressWarnings("unchecked")
	private static HashSet<HashSet<Integer>> possibleLeafs(HashSet<Integer> s,int num){
		if (map2.containsKey(s))
			return map2.get(s);
		HashSet<HashSet<Integer>> set = new HashSet<HashSet<Integer>>();
		HashSet<Integer> c;
		int x;
		Iterator<Integer> iterator = s.iterator();
		
		while (iterator.hasNext()) {
			x = iterator.next();
			if (isPerfect(x,num)) {
				c = (HashSet<Integer>)s.clone();
				c.remove(x);
				c.add(num);
				set.add(c);
			}
		}
		if (s.size()<numLines) {
			c = (HashSet<Integer>)s.clone();
			c.add(num);
			set.add(c);
		}
		map2.put(s, set);
		return set;
	}
	
	private static boolean isPerfect(int x, int y) {
		int num = x + y;
		int z = (int)Math.sqrt(num);
		return z*z == num;
	}
}
