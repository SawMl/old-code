package shangchi;
import java.util.ArrayList;
import java.util.HashMap;

public class Testing90210 {

	public static String message = " ";
	private static HashMap<Pair<Integer,Integer>,Integer> magicMap;
	private static HashMap<Integer,ArrayList<Pair<Integer,Integer>>> keyMap;
	
	public static boolean test(int numIngredients, boolean printproblem) {
		
		String exceptionString = "";
		
		ArrayList<ArrayList<Integer>> magic = MagicGenerator2440.getExampleMagic();
		ArrayList<Integer> initialOrder = OrderingGenerator7623.getRandomOrdering(magic, numIngredients);
		loadPairsToMap(magic);
		
		try {
			ArrayList<Integer> studentSolutions = AnotherSolver.solve
					((ArrayList<Integer>)initialOrder.clone(),
					(ArrayList<ArrayList<Integer>>) magic.clone());
			
			
			
			if (studentSolutions == null) {
				message = "Student answer is null.";
				return false;
			}
			
			if (studentSolutions.size() != numIngredients-1) {
				message = "Student answer is not the correct length";
				return false;
			}
			
			if (!answerWorks(studentSolutions,initialOrder,magic)) {
				message = "Student answer doesn't work";
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
	
	private static boolean answerWorks(ArrayList<Integer> solutions,ArrayList<Integer> initialOrdering,
			ArrayList<ArrayList<Integer>> magic) {
		int x, y, z;
		Pair<Integer,Integer> pair;
		for (int num : solutions) {
			x = initialOrdering.get(num);
			y = initialOrdering.get(num+1);
			pair = new Pair<>(x,y);
			z = magicMap.get(pair);
			initialOrdering.remove(num+1);
			initialOrdering.set(num, z);
		}
		if (initialOrdering.size()==1) {
			if (initialOrdering.get(0)==1) {
				return true;
			}
		}
		return false;
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
	
	private static Pair<Integer,Integer> getPair(int num){
		
		int left = (int) num / 10;
		int right = num % 10;
		
	
		return new Pair<Integer,Integer>(left,right);
	}
	
	
}
