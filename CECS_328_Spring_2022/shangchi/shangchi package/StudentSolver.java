package shangchi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class StudentSolver {

	private static HashMap<Pair<Integer,Integer>,Integer> magicMap;
	private static HashMap<Integer,HashSet<Pair<Integer,Integer>>> keyMap;
	private static ArrayList<Integer> originalOrdering;
	
	public static ArrayList<Integer> solve
	(ArrayList<Integer> initialOrder,
			ArrayList<ArrayList<Integer>> magic){
		
		magicMap = new HashMap<Pair<Integer,Integer>,Integer>();
		keyMap = new HashMap<Integer,HashSet<Pair<Integer,Integer>>>();
		originalOrdering = initialOrder;
		
		int num;
		Pair<Integer,Integer> newPair;
		//Add the rules of magic to a HashMap
		for (ArrayList<Integer> row : magic) {
			num = row.get(1);
			newPair = getPair(row.get(0));
			magicMap.put(newPair,num);
			if (!keyMap.containsKey(num)) {
				keyMap.put(num, new HashSet<Pair<Integer,Integer>>());
			}
			keyMap.get(num).add(newPair);
		}
		
		//which conditions allow me to produce a 1?
		//The last move must be one of these options
		//keyMap.get(1)
		//For each pair @ keyMap.get(1)
		//What conditions allow me to produce X,Y ?
		//The move before last must be one of these options
		//What conditions allow me to produce X
		//What conditions allow me to produce Y
		
		
		
		
		
		
		ArrayList<Integer> ordering = new ArrayList<Integer>();
		ordering.add(1);
		Stack<Integer> stackSolutions = solutions(1,new Stack<Integer>(),ordering,0);
		ArrayList<Integer> solutions = new ArrayList<Integer>();
		
		
		while(!stackSolutions.empty()) {
			solutions.add(stackSolutions.pop());
		}
		return solutions;
	}
	
	//Ordering begins with just [1]
	//stack begins empty
	//num begins at 1
	//index begins at 0
	private static Stack<Integer> solutions(int num, Stack<Integer> currentStack, ArrayList<Integer> ordering, int index){
		if (ordering.size() == originalOrdering.size()) {
			if (ordering.equals(originalOrdering))
				return currentStack;
			
			else return null;
		}
		Stack<Integer> newStack, stack = (Stack<Integer>)currentStack.clone();
		ArrayList<Integer> newOrdering; 
		stack.push(index);
		for (Pair<Integer,Integer> pair : keyMap.get(num)) {
			newOrdering = (ArrayList<Integer>)ordering.clone();
			newOrdering.set(index,pair.first);
			newOrdering.add(index+1,pair.second);
			newStack = solutions(pair.first, stack, newOrdering, index);
			if (newStack!=null){
				return newStack;
			}
			newStack = solutions(pair.second, stack, newOrdering, index+1);
			if (newStack!=null) {
				if (newStack.size()>=originalOrdering.size()) {
					System.out.println("Here");
					System.exit(0);
				}
				return newStack;
			}
		}
		return null;
	}
	
	private static Pair<Integer,Integer> getPair(int num){
		
		int left = (int) num / 10;
		int right = num % 10;
		
	
		return new Pair<Integer,Integer>(left,right);
	}
	
	
}
