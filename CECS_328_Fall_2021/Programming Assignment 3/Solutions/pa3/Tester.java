package pa3;
import java.util.Random;

public class Tester {

	public static void main(String[] args) {
		boolean[][] matrix = {{false, false, true, true, true},
							   {false, true, true, true, true},
							   {true, true, false, true, true},
							   {true, true, true, false, false},
							   {true, true, true, false, false}};
		
		int k1x=2, k2x=1;
		
		
		//System.out.print(StudentSolver.solve(matrix,k1x,k2x));

		Random r = new Random();
		int x = r.nextInt(20);
		
		boolean[][] mat2 = new boolean[x][x];
		
		for (int i=0;i<mat2.length;i++)
			for (int j=i;j<mat2.length;j++) { 
				mat2[i][j] = r.nextBoolean();
				mat2[j][i] = mat2[0][j];
			}
		
		displayMatrix(mat2);
		
		int a = r.nextInt(x/2);
		int b = r.nextInt(x/2);
		
		System.out.println("k1x="+a+", k2x="+b);
		
		System.out.print(StudentSolver.solve(mat2,a,b));
		
	}
	
	static void displayMatrix(boolean[][] m) {
		for (int i=0;i<m.length;i++) {
			for (int j=0;j<m.length;j++) {
				if (m[i][j])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.print("\n");
		}
		System.out.println();
				
	}

}
