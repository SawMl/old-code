package squidGame1779;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Testing9643 {

	public static String message = " ";
	
	public static boolean test(int numLines, boolean printProblem) {
		String exceptionString = "";
		try {
			int maxN = getMaxN(numLines);
			Random r = new Random();
			int random = r.nextInt(10)+4;
			random = maxN - (int)(maxN/random);
		
			ArrayList<ArrayList<Integer>> answer = StudentSolver.solve(numLines,random);
			if (printProblem) {
				System.out.println(numLines+", "+random);
				//System.out.println(answer);
			}
		
			if (missingNumbers(answer,random)) {
				message = "Missing numbers";
				return false;
			}
			if (repeatedNumbers(answer, random)) {
				message = "Repeated number";
				return false;
			}
		
			if (notPerfect(answer)) {
				message = "Numbers in line not perfect square";
				return false;
			}
		
		
			
			message = "passed";
			return true;
		} 
		catch (Exception e) {
			
			exceptionString = e.getMessage();
		}
		
		message = "Exception: " + exceptionString;
		return false;
		
	}
	
	private static boolean notPerfect(ArrayList<ArrayList<Integer>> list) {
		for (ArrayList<Integer> element : list) {
			if (notPerfectStack(element)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean notPerfectStack(ArrayList<Integer> stack) {
		if (stack.size()<=1) {
			return false;
		}
		Collections.sort(stack,Collections.reverseOrder());
		int cnt=0;
		int x = stack.get(cnt);
		int y = stack.get(cnt+1);
		if (!Square.isPerfect(x+y))
			return true;
		cnt = cnt + 1;
		while (cnt + 1 < stack.size()) {
			x = stack.get(cnt);
			y = stack.get(cnt+1);
			if (!Square.isPerfect(x+y))
				return true;
			cnt = cnt + 1;
		}
		return false;
	}
	
	private static boolean missingNumbers(ArrayList<ArrayList<Integer>> list, int maxNum) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (ArrayList<Integer> element : list) {
			newList.addAll(element);
		}
		if (newList.size()<maxNum)
			return true;
		Collections.sort(newList);
		for (int i=1;i<=maxNum;i++) {
			if (newList.get(i-1)!=i) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean repeatedNumbers(ArrayList<ArrayList<Integer>> list, int maxNum) {
		HashSet<Integer> set = new HashSet<Integer>();
		int count = 0;
		for (ArrayList<Integer> element : list) {
			count += element.size();
			set.addAll(element);
		}
		if (set.size()==count)
			return false;
		return true;
	}
	
	private static int getMaxN(int x) {
		int out = 1;
		for (int i=1;i<x;i++)
			out+=(int)(i/2+1)*2;
		return out;
	}
}
