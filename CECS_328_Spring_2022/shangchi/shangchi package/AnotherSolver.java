package shangchi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class AnotherSolver {

	private static HashMap<Pair<Integer,Integer>,Integer> magicMap;
	private static HashMap<Integer,HashSet<Pair<Integer,Integer>>> keyMap;
	private static ArrayList<Integer> originalOrdering;
	private static HashMap<Pair<ArrayList<Integer>,Integer>,Pair<Boolean,ArrayList<Integer>>> functMap;
	
	public static ArrayList<Integer> solve
	(ArrayList<Integer> initialOrder,
			ArrayList<ArrayList<Integer>> magic){
		
		ArrayList<Integer> answer = null;
		magicMap = new HashMap<Pair<Integer,Integer>,Integer>();
		keyMap = new HashMap<Integer,HashSet<Pair<Integer,Integer>>>();
		functMap = new HashMap<Pair<ArrayList<Integer>,Integer>,Pair<Boolean,ArrayList<Integer>>>();
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
		
		ArrayList<Number> numList = new ArrayList<Number>();
		
		for (int x : originalOrdering) {
			numList.add(new Number(x));
		}
		
		Number numberOne = new Number(1);
		boolean b = kenEqual(numList,numberOne);
		answer = getSolutionsFromOne(numberOne);
	
		
		return answer;
	
	}
	

	
	
	
	private static boolean kenEqual(ArrayList<Number> list, Number num) {
		
		if (list.size()==1) {
			Number number = list.get(0);
			if (number.num==num.num) {
				return true;
			}
			return false;
		}
		
		if (list.size()==2) {
			Number leftNumber = list.get(0);
			Number rightNumber = list.get(1);
			if (leftAndRightMakeNum(leftNumber,rightNumber,num.num)){
				num.setParents(leftNumber, rightNumber);
				leftNumber.setSon(num);
				rightNumber.setSon(num);
				leftNumber.setPartner(rightNumber);
				rightNumber.setPartner(leftNumber);
				return true;
		    }
		}
		
		
		return kenEqualThree(list,num);
	}
	
	private static boolean kenEqualThree(ArrayList<Number> list, Number num) {
		
		ArrayList<Number> left = new ArrayList<Number>();
		ArrayList<Number> right = (ArrayList<Number>)list.clone();
		
		left.add(right.get(0));
		right.remove(0);
		boolean flag = false;
		while (right.size()>0) {
			
			flag = funkshun(left,right,num);
			
			if (flag)
				return true;
			
			left.add(right.get(0));
			right.remove(0);
		}
		
		return false;
	}
	
	private static boolean leftAndRightMakeNum(Number left, Number right, int num) {
		Pair<Integer,Integer> pair = new Pair<>(left.num,right.num);
		int x = magicMap.get(pair);
		if (x==num)
			return true;
		return false;
	}
	
	private static boolean funkshun(ArrayList<Number> left, ArrayList<Number> right, Number num) {
		
		HashSet<Pair<Integer,Integer>> set = keyMap.get(num.num); //Look at the pairs that can make num
		
		for (Pair<Integer,Integer> pair : set) {
			Number leftNum = new Number(pair.first);
			Number rightNum = new Number(pair.second);
			if (kenEqual(left,leftNum)) {
				if (kenEqual(right,rightNum)) {
					leftNum.partner = rightNum;
					rightNum.partner = leftNum;
					leftNum.setSon(num);
					rightNum.setSon(num);
					num.leftParent = leftNum;
					num.rightParent = rightNum;
					return true;
				}
			}
		}
		return false;
	}
	
	
	private static Pair<Integer,Integer> getPair(int num){
		
		int left = (int) num / 10;
		int right = num % 10;
		
	
		return new Pair<Integer,Integer>(left,right);
	}
	
	private static ArrayList<Integer> getSolutionsFromOne(Number num){
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Number> temp = new ArrayList<>();
		temp.add(num);
		int index = 0;
		
		while (index>=0) {
			num = temp.get(index);
			temp.set(index,num.leftParent);
			temp.add(index+1, num.rightParent);
			stack.push(index);
			index = nextIndex(temp);
		}
		
		ArrayList<Integer> solutions = new ArrayList<Integer>();
		
		while (!stack.isEmpty()) {
			solutions.add(stack.pop());
		}
		
		return solutions;
		
		
	}
	
	private static int nextIndex(ArrayList<Number> list) {
		for (int i=0;i<list.size();i++) {
			if (list.get(i).hasParents()) {
				return i;
			}
		}
		return -1;
	}

	
	public static boolean arePartners(Number left, Number right) {
		if (left.partner==null || right.partner==null)
			return false;
		if (left.partner == right.partner) {
			return true;
		}
		return false;
	}
}
