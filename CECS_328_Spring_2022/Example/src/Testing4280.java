import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Testing4280 {
	public static String message = " ";
	public static boolean test(int numMess, boolean printProblem)
	{
		String exceptionString = "";
		try {
			Random r = new Random();
			double ratio = 20.0 * r.nextDouble() + 1.0;
			double w = 100.0 + 10 * r.nextDouble();
			double h = 1000.0 + 5000.0 * r.nextDouble();
			
			ArrayList<Pair<Pair<Double, Double>, Integer>> messes = new ArrayList<Pair<Pair<Double, Double>, Integer>>();
			ArrayList<Mess4280> allMess = new ArrayList<Mess4280>();
			for(int i = 0; i < numMess; i++) {
				Mess4280 mess = new Mess4280();
				mess.makeRandom(w,  h,  10,  r);
				messes.add(new Pair(new Pair(mess.x, mess.y), mess.value));
				allMess.add(mess);
			}
			
			if(printProblem)
				System.out.println(allMess);
			
			ArrayList<Integer> answer = Solver4280.run(ratio, messes);
			ArrayList<Integer> studentAnswer = StudentSolver.run(ratio,  messes);
			
			ArrayList<Mess4280> pickup = new ArrayList<Mess4280>();
			for(Integer cur : studentAnswer) {
				pickup.add(allMess.get(cur));
			}
			
			for (int i=0; i<pickup.size()-1; i++) {
				double verticalDistance=Math.abs(pickup.get(i+1).y-pickup.get(i).y);
				double horizontalDistance=Math.abs(pickup.get(i+1).x-pickup.get(i).x);
				if (horizontalDistance*ratio>verticalDistance)
				{
					message = "Unable to get from one mess to the next.";
					return false;
				}
			}
			
			if(studentAnswer.size() < answer.size()) {
				message = "Student answer not biggest possible answer";
				return false;
			}
			message = "Passed.";
			return true;
			
		} catch (Exception e) {
			exceptionString = e.getMessage();
		}
		message = "Exception: " + exceptionString;
		return false;
	}
}
