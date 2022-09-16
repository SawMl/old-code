package workers;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(5,7);
		Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(9,5);
		Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(10,7);
		Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(9,7);
		Pair<Integer,Integer> p5 = new Pair<Integer,Integer>(4,7);
		Pair<Integer,Integer> p6 = new Pair<Integer,Integer>(6,10);
		Pair<Integer,Integer> p7 = new Pair<Integer,Integer>(9,8);
		Pair<Integer,Integer> p8 = new Pair<Integer,Integer>(4,9);
		Pair<Integer,Integer> p9 = new Pair<Integer,Integer>(2,1);
		Pair<Integer,Integer> p10 = new Pair<Integer,Integer>(1,10);
		Pair<Integer,Integer> p11 = new Pair<Integer,Integer>(4,13);
		
		ArrayList<Pair<Integer,Integer>> list = new ArrayList<Pair<Integer,Integer>>();
		
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		list.add(p11);
		
		StudentSolver.printLists(StudentSolver.solve(list));
	}

}
