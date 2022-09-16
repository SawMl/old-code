package matrixSelect;


public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] table2= {
				{93,50,89},
				{6,58,81},
				{41,83,39},
				{92,8,70},
				{11,27,88},
				{22,77,80},
				{66,7,18}};
		
		int[][] table = {{81,54,8,13,53,60,41,77,72,35},
				{89,62,17,98,82,78,4,63,9,32},
				{25,99,6,23,85,29,70,69,52,25},
				{56,59,88,95,51,16,37,85,52,54},
				{45,58,7,24,15,88,41,34,44,28},
				};
		
		System.out.println(StudentSolver.solve(table));

	}

}
