package hopscotch;
import java.util.ArrayList;

public class Testing1569 {

	public static String message = " ";
	
	public static boolean test(int m, int n, int range, boolean printProblem){
		String exceptionString = "";
		
		try{
			ArrayList<ArrayList<Integer>> grid = GridGenerator9982.getGrid(m, n, range);
			ArrayList<ArrayList<Integer>> gridClone = (ArrayList<ArrayList<Integer>>)grid.clone();
			
			if (printProblem) {
				System.out.println(grid);
			}
		
			ArrayList<Integer> optimalAnswer = Optimal1989.figureOut(gridClone);
			ArrayList<Integer> studentAnswer = optimalAnswer;
		
			if (studentAnswer.size()<n+1) {
				message = "student answer too short";
				return false;
			}
			if (studentAnswer.size()>n+1) {
				message = "student answer too long";
				return false;
			}
			
			if (!validFormat(studentAnswer,n)) {
				message = "invalid answer format";
				return false;
			}
			
			if (sumOfPath(studentAnswer,grid)<sumOfPath(optimalAnswer,grid)) {
				message = "student answer suboptimal";
				return false;
			}
			
			message = "passed";
			return true;
			
		} catch (Exception e) {
			exceptionString = e.getMessage();
		}
		
		
		message = "Exception: " + exceptionString;
		return false;
	}
	
	private static boolean validFormat(ArrayList<Integer> list, int n) {
		
		int num = list.get(0);
		if (num<-1 || num>1) {	
			return false;
		}
		num = list.get(1);
		if (num<0 || num > n-1) {
			return false;
		}
		for (int i=2;i<list.size();i++) {
			num = list.get(i);
			if (num<-1 || num>1) {
				return false;
			}
		}
		return true;
	}
	
	private static int sumOfPath(ArrayList<Integer> list, ArrayList<ArrayList<Integer>> grid) {
		int m = grid.size();
		int n = grid.get(0).size();
		boolean top = list.get(0) == 1;
		int row = 0;
		if (!top) {
			row = m-1;
		}
		int col = list.get(1);
		int sum = 0;
		sum += grid.get(row).get(col);
		Pair<Integer, Integer> pair = new Pair<Integer,Integer>(row,col);
		pair = GridTraversal.getNewCoords(pair, m, n, list.get(2));
		row = pair.first;
		col = pair.second;
		sum += grid.get(row).get(col);
		for (int i=3;i<list.size();i++) {
			pair = GridTraversal.getNewCoords(pair, m, n, list.get(i));
			row = pair.first;
			col = pair.second;
			sum += grid.get(row).get(col);
		}
		return sum;
	}
	
	
}
