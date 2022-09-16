package hopscotch;
import java.util.ArrayList;
import java.util.HashMap;

public class Optimal1989 {
	
	private static int M;
	private static int N;
	private static ArrayList<ArrayList<Integer>> GRID;
	private static HashMap<Pair<Pair<Integer,Integer>,Integer>,Pair<ArrayList<Integer>,Integer>> memoized;

	public static ArrayList<Integer> figureOut(ArrayList<ArrayList<Integer>> grid){
		ArrayList<Integer> list = new ArrayList<Integer>();
		memoized = new HashMap<Pair<Pair<Integer,Integer>,Integer>,Pair<ArrayList<Integer>,Integer>>();
		
		int m = grid.size();
		int n = grid.get(0).size();
		M=m;
		N=n;
		GRID = grid;
		int topOrBot=0;
		int startingCol=0;
		Pair<Integer,ArrayList<Integer>> pair;
		ArrayList<Integer> commands=null;
		
		//Try all starting points
		int max=0;
		for (int i=0;i<n;i++) {
			
			pair = entry(0,i,grid);
		
			if (pair.first>max) {
				
				topOrBot=1;
				max = 0;
				max += pair.first;
				commands = (ArrayList<Integer>)pair.second.clone();
				startingCol=i;
			}
			
			pair = entry(m-1,i,grid);
			
			if (pair.first>max) {
				topOrBot=0;
				max = 0;
				max += pair.first;
				commands = (ArrayList<Integer>)pair.second.clone();;
				startingCol=i;
			}
			
			
		}
		
		
		
		
		list.add(topOrBot);
		list.add(startingCol);
		list.addAll(commands);
		return list;
	}
	
	private static Pair<Integer,ArrayList<Integer>> entry(int row, int col, ArrayList<ArrayList<Integer>> grid){
		int n = grid.get(0).size();
		
		Pair<Integer,Integer> coords = new Pair<Integer,Integer>(row,col);
		
		int sum = 0;
		sum += GRID.get(row).get(col);
		
		//0
		ArrayList<Integer> list1 =  new ArrayList<Integer>();
		list1.add(0);
		Pair<Integer,Integer> newCoords = GridTraversal.getNewCoords(coords, M, N, 0);
		Pair<Integer,ArrayList<Integer>> p1 = recursiveFunction(n-1,list1,sum,newCoords);
		Pair<Integer,ArrayList<Integer>> max = p1;
		
		//1
		ArrayList<Integer> list2 =  new ArrayList<Integer>();
		list2.add(1);
		newCoords = GridTraversal.getNewCoords(coords, M, N, 1);
		Pair<Integer,ArrayList<Integer>> p2 = recursiveFunction(n-1,list2,sum,newCoords);
		if (p2.first>max.first)
			max = p2;
		
		//-1
		ArrayList<Integer> list3 =  new ArrayList<Integer>();
		list3.add(-1);
		newCoords = GridTraversal.getNewCoords(coords, M, N, -1);
		Pair<Integer,ArrayList<Integer>> p3 = recursiveFunction(n-1,list3,sum,newCoords);
		if (p3.first>max.first)
			max = p3;
		
		return max;
	}
	
	
	private static Pair<Integer,ArrayList<Integer>> recursiveFunction(int numColsLeft, ArrayList<Integer> list, int sum, Pair<Integer,Integer> coords){
		
		int newSum = sum + GRID.get(coords.first).get(coords.second);
		
		Pair<Pair<Integer,Integer>,Integer> pair = new Pair<>(coords,numColsLeft);
		Pair<ArrayList<Integer>,Integer> memPair;
		
		if (memoized.containsKey(pair)) {
			ArrayList<Integer> cloneList = (ArrayList<Integer>)list.clone();
			memPair = memoized.get(pair);
			cloneList.addAll(memPair.first);
			newSum += memPair.second;
			return new Pair<Integer,ArrayList<Integer>>(newSum,cloneList);
		}
		
		
		if (numColsLeft==1) {
			return new Pair<Integer,ArrayList<Integer>>(newSum,list);
		}
		
		
		
		//0
		Pair<Integer,Integer> newCoords = GridTraversal.getNewCoords(coords, M, N, 0);
		ArrayList<Integer> listClone = (ArrayList<Integer>)list.clone();
		listClone.add(0);
		Pair<Integer,ArrayList<Integer>> p1 = recursiveFunction(numColsLeft-1,listClone, newSum, newCoords);
		Pair<Integer,ArrayList<Integer>> max = p1;
		
		//1
		newCoords = GridTraversal.getNewCoords(coords, M, N, 1);
		listClone = (ArrayList<Integer>)list.clone();
		listClone.add(1);
		Pair<Integer,ArrayList<Integer>> p2 = recursiveFunction(numColsLeft-1,listClone, newSum, newCoords);
		if (p2.first > max.first)
			max = p2;
		
		//-1
		newCoords = GridTraversal.getNewCoords(coords, M, N, -1);
		listClone = (ArrayList<Integer>)list.clone();
		listClone.add(-1);
		Pair<Integer,ArrayList<Integer>> p3 = recursiveFunction(numColsLeft-1,listClone, newSum, newCoords);
		if (p3.first > max.first)
			max = p3;
		
	
		listClone = (ArrayList<Integer>)max.second.clone();
		for (int element : list) {
			listClone.remove(0);
		}
		int amount = max.first - newSum;
		memPair = new Pair<ArrayList<Integer>,Integer>(listClone,amount);
		memoized.put(pair, memPair);
		
		
		return max;
		
		
	}
}
