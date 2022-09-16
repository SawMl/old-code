package shangchi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;
import java.util.PriorityQueue;

public class OrderingGenerator7623 {

	private static HashMap<Pair<Integer,Integer>,Integer> magicMap;
	private static HashMap<Integer,ArrayList<Pair<Integer,Integer>>> keyMap;
	
	public static ArrayList<Integer> getRandomOrdering(ArrayList<ArrayList<Integer>> magic, int length){
		loadPairsToMap(magic);
		ArrayList<Integer> ordering = new ArrayList<Integer>();
		ordering.add(1); //first element
		Queue<Integer> queue = new PriorityQueue<>();
		queue.add(0);
		Random r = new Random();
		int num;
		for (int i=0;i<length-1;i++) {
			num = r.nextInt(i+1);
			queue.add(num);
		}
		
	
		
		//which pairs makes a 1?
		//randomly choose one and place it into index 0 and index 1
		//next index in random sequence
		//which pairs make x?
		
		int index;// = queue.poll();
		ArrayList<Pair<Integer,Integer>> list;// = keyMap.get(ordering.get(index));
		//num = r.nextInt(list.size());
		Pair<Integer,Integer> pair;// = list.get(num);
		//ordering.set(index, pair.first);
		//ordering.add(index+1,pair.second);
		while (queue.size()>1) {
			index = queue.poll();
			list = keyMap.get(ordering.get(index));
			num = r.nextInt(list.size());
			pair = list.get(num);
			ordering.set(index, pair.first);
			ordering.add(index+1,pair.second);
		}
		return ordering;
		
	}
	
	private static Pair<Integer,Integer> getPair(int num){
		
		int left = (int) num / 10;
		int right = num % 10;
		
	
		return new Pair<Integer,Integer>(left,right);
	}
	
	private static void loadPairsToMap(ArrayList<ArrayList<Integer>> magic) {
		magicMap = new HashMap<Pair<Integer,Integer>,Integer>();
		keyMap = new HashMap<Integer,ArrayList<Pair<Integer,Integer>>>();
		int num;
		Pair<Integer,Integer> newPair;
		//Add the rules of magic to a HashMap
		for (ArrayList<Integer> row : magic) {
			num = row.get(1);
			newPair = getPair(row.get(0));
			magicMap.put(newPair,num);
			if (!keyMap.containsKey(num)) {
				keyMap.put(num, new ArrayList<Pair<Integer,Integer>>());
			}
			keyMap.get(num).add(newPair);
		}
	}
}
