package squidGame1779;

import java.util.HashMap;
import java.util.HashSet;

public class Square {

	private static HashMap<Integer, HashSet<Integer>> memoizedChildren 
				= new HashMap<Integer, HashSet<Integer>>();
	
	private static HashMap<Integer,Integer> memoizedClosestSquare
				= new HashMap<Integer,Integer>();
	
	private static HashMap<Integer,Boolean> memoizedIsPerfect
				= new HashMap<Integer,Boolean>();
	
	
	public static HashSet<Integer> getChildren(int parent){
		if (memoizedChildren.containsKey(parent))
			return memoizedChildren.get(parent);
		
		HashSet<Integer> set = new HashSet<Integer>();
		
		int reference = parent + parent - 1;
		int perfectSquare = nextSquare(reference);
		while (perfectSquare > parent) {
			set.add(perfectSquare - parent);
			perfectSquare = nextSquare(perfectSquare-1);
		}
		
		memoizedChildren.put(parent, set);
		return set;
	}
	
	private static int nextSquare(int num) {
		if (memoizedClosestSquare.containsKey(num))
			return memoizedClosestSquare.get(num);
		else if (isPerfect(num)) {
			return num;
		}
		int next = nextSquare(num - 1);
		memoizedClosestSquare.put(num, next);
		return next;
	}
	
	public static boolean isPerfect(int num) {
		if (memoizedIsPerfect.containsKey(num))
			return memoizedIsPerfect.get(num);
		int z = (int)Math.sqrt(num);
		boolean flag = z*z == num;
		memoizedIsPerfect.put(num, flag);
		return flag;
	}
	
	
}
