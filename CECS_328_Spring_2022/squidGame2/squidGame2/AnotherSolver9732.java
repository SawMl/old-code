package squidGame2;
import java.util.ArrayList;
import java.util.HashSet;


public class AnotherSolver9732 {

	private static HashSet<HashSet<ArrayList<Integer>>> theFinalStandings;
	private static int X,N;
	public static int solve(int x, int n) {
		X=x;
		N=n;
		theFinalStandings = new HashSet<>();
	
	
		standInLine(new HashSet<ArrayList<Integer>>(),1);
		
		
		return theFinalStandings.size();
	}
	
	private static void standInLine(HashSet<ArrayList<Integer>> lines, int person) {
		if (person==N+1) {
			if (!lines.contains(new ArrayList<Integer>()))
				theFinalStandings.add(lines);
		}
		else {
			if (lines.size()<X) {
				lines.add(new ArrayList<Integer>());
			}
			HashSet<ArrayList<Integer>> newLines;
			ArrayList<Integer> newLine;
			for (ArrayList<Integer> line : lines) {
				if (line.isEmpty() || isPerfectSum(person,line.get(line.size()-1))) {
					newLine = (ArrayList<Integer>)line.clone();
					newLine.add(person);
					newLines = (HashSet<ArrayList<Integer>>)lines.clone();
					newLines.remove(line);
					newLines.add(newLine);
					standInLine(newLines,person+1);
				}
			}
		}
	}
	
	private static boolean isPerfectSum(int x, int y) {
		int num = x + y;
		int root = (int) Math.sqrt(num);
		int num2 = root * root;
		return num == num2;
		
		
	}
	
	
}
