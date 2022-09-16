import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
import java.util.HashMap;

public class StudentSolver {

	private static ArrayList<Point> allPoints;
	private static double ratio;
	private static HashMap<Point,HashSet<Point>> memPossible;
	private static HashMap<Point,Integer> memSum;
	private static HashMap<HashSet<Point>,Point> memMax;

	public static ArrayList<Integer> run(double r, ArrayList<Pair<Pair<Double, Double>, Integer>> mess){
		ratio=r;
		ArrayList<Integer> solutions = new ArrayList<Integer>();
		memMax = new HashMap<HashSet<Point>,Point>();
		memSum = new HashMap<Point,Integer>();
		memPossible = new HashMap<Point,HashSet<Point>>();
		allPoints = new ArrayList<Point>();
		int cnt=0;
		//Point special;

		HashSet<Point> set = new HashSet<Point>();

		//Add all the incoming data as Point objects in a list
		for (Pair<Pair<Double,Double>,Integer> spot : mess) {
			allPoints.add(new Point(spot.first,cnt,spot.second));
			//System.out.println (spot.first.first +", "+ spot.first.second +", "+spot.second);
			cnt++;
		}
		//System.out.println();
		//special = allPoints.get(1);
		//Sort it based on position relative to the x axis
		Collections.sort(allPoints);

		//give each point object a height identity field
		for (int i=0;i<allPoints.size();i++) {
			allPoints.get(i).setHeightIndex(i);
			set.add(allPoints.get(i));
		}

		//System.out.println(nextPossible(special));
		//System.out.println(max(nextPossible(special)));
		//System.out.println(nextPossible(special));
		//System.out.println(sevSum(special));

		Point p = max(set);
		while (p!=null) {
			solutions.add(p.index);
			p = max(nextPossible(p));
		}


		return solutions;
	}

	public static HashSet<Point> nextPossible(Point p){

		if (memPossible.containsKey(p)) {
			return memPossible.get(p);
		}

		HashSet<Point> set = new HashSet<Point>();
		for (int i = p.getHeightIndex()+1;i<allPoints.size();i++) {
			if (isValid(p,allPoints.get(i))){
				set.add(allPoints.get(i));
			}
		}
		memPossible.put(p,set);



		return set;
	}

	private static boolean isValid(Point current, Point next) {
		double x1 =current.x, y1=current.y, x2=next.x, y2=next.y;
		double num;
		if (x1<x2) {
			num=x2-x1;
		}
		else {
			num = x1-x2;
		}
		if (num<=(y2-y1)/ ratio)
			return true;
		return false;
	}

	//Given a hashset of possible next points
	//return the one with the path to the greatest severity sum
	private static Point max(HashSet<Point> set) {
		if (memMax.containsKey(set)) {
			return memMax.get(set);
		}

		int sMax=0;
		int s1;
		Point maxP=null;
		for (Point p : set) {
			s1=sevSum(p);
			if (s1>sMax) {
				sMax=s1;
				maxP=p;
			}
		}

		memMax.put(set, maxP);
		return maxP;
	}

	private static int sevSum(Point p) {
		if (memSum.containsKey(p)) {
			return memSum.get(p);
		}
		if (p==null) {
			memSum.put(null, 0);
			return 0;
		}
		memSum.put(p, p.severity + sevSum(max(nextPossible(p))));
		return memSum.get(p);
	}




}
