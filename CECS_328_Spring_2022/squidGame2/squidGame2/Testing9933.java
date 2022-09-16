package squidGame2;

public class Testing9933 {

	public static String message = " ";
	
	public static boolean test(int x, int n, boolean printProblem) {
		String exceptionMessage = "";
		
		if (printProblem) {
			System.out.println("x="+x+", n="+n);
		}
		
		int myAnswer = AnotherSolver9732.solve(x, n);
		
		try {
			
			int studentAnswer = StudentSolver.solve(x, n);
			
			if (studentAnswer!=myAnswer) {
				message = "student answer incorrect";
				return false;
			}
			
			message = "Passed.";
			return true;
		} catch (Exception e) {
			
			exceptionMessage = e.getMessage();
		}
		
		
		message = exceptionMessage;
		return false;
	}
	
}
