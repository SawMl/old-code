
public class kingOfTheHill {
	
	public static int count;
	
	public static int[][] newRowToGrid(int[] array, int[][] grid, int row){
		for (int i=0;i<array.length;i++) {
			grid[row][i]=array[i];
		}
		return grid;
	}
	
	public static int[][] newColToGrid(int[] array, int[][] grid, int col){
		for (int i=0;i<array.length;i++) {
			grid[i][col]=array[i];
		}
		return grid;
	}
	
	public static int solve(int[][] grid) {
		count=0;
		int[] horizontals = new int[grid[0].length];
		int[] verticals = new int[grid.length];
		for (int j=0;j<grid[0].length;j++) {
			for (int i=0;i<grid.length;i++) {
				horizontals=getArrayFromRow(grid,i);
				horizontals=workOnArray(horizontals);
				grid=newRowToGrid(horizontals,grid,i);
			}
			for (int i=0;i<grid[0].length;i++) {
				verticals=getArrayFromCol(grid,i);
				verticals=workOnArray(verticals);
				grid=newColToGrid(verticals,grid,i);
			}
		}
		return count;
	}
	
	
	public static int[] getArrayFromRow(int[][] matrix, int row) {
		int[] array = new int[matrix[0].length];
		for (int i=0;i<matrix[0].length;i++) {
			array[i]=matrix[row][i];
		}
		return array;
	}
	
	public static int[] getArrayFromCol(int[][] matrix, int col){
		int[] array = new int[matrix.length];
		for (int i=0;i<matrix.length;i++){
			array[i]=matrix[i][col];
		}
		return array;
		}
	
	public static int[] runFunction(int[] h, int i, int left, int right) {
		while (h[i]<h[left] && h[i]<h[right]) {
			h[i]=h[i]+1;
			count++;
		}
		if (h[i]==h[left]) {
			if (left-1>0) {
				return runFunction(h,i,left-1,right);
			}
		}
		if (h[i]==h[right]) {
			if (right+1<h.length-1) {
				return runFunction(h,i,left,right+1);
			}
		}
		return h;
	}

	public static int[] workOnArray(int[] row) {
		
		for (int i=1;i<row.length-1;i++) {
			row = runFunction(row,i,i-1,i+1);
		}
		for (int i=1;i<row.length-1;i++) {
			row = runFunction(row,i,i-1,i+1);
		}
		
		return row;

	}

}
