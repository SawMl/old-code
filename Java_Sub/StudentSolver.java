import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class StudentSolver {

	private static int m;
	private static int n;
	private static int[][] chessBoard;
	private static int COUNT;
	private static boolean[][] choices;



		public static ArrayList<Pair<Integer,Integer>> solve(int[][] board){
			ArrayList<Pair<Integer,Integer>> solutions = new ArrayList<Pair<Integer,Integer>>();
			chessBoard = board;
			m = board.length;
			n = board[0].length;
			choices = new boolean[m][n];
			/*
			HashSet<Integer> g = new HashSet<Integer>();
			g.add(0);
			g.add(2);
			g.add(4);

			ArrayList<HashSet<Integer>> h = preComputeNextPossibleColTypes(g);

			Iterator<HashSet<Integer>> iterator =h.iterator();
			for (int i=0;i<h.size();i++) {
				System.out.println(iterator.next());
			}
			System.exit(0);
			*/

			COUNT=0;
			int sum = launch(0,preComputeNextPossibleColTypes(new HashSet<Integer>()));

			System.out.print(sum);

			for (int i=0;i<m;i++) {
				for (int j=0;j<n;j++) {
					if (choices[i][j]==true)
						solutions.add(new Pair<Integer,Integer>(i,j));
				}
			}


			return solutions;
		}


		private static int launch(int colIndex, ArrayList<HashSet<Integer>> list) {
			HashSet<Integer> currentType;

			if (colIndex==n) {
				return 0;
			}

			int[] take = new int[list.size()/2+1]; //Dont need to check every possible combination
			for (int i=0;i<take.length;i++) {
				currentType = getNextColType(list,colIndex,i);
				COUNT++;
				take[i] = getNextColTypeValue(list,colIndex,i) + launch(colIndex+1,preComputeNextPossibleColTypes(currentType));
			}

			int max=0;
			int index=0;
			for (int j=0;j<take.length;j++) {
				if (take[j]>max) {
					max=take[j];
					index=j;
				}
			}

			//set board values here

			COUNT = getNextColTypeValue(list,colIndex,index);
			return max;
		}

		private static void recursiveCombination(ArrayList<HashSet<Integer>> list, HashSet<Integer> colType, int pivot) {

			HashSet<Integer> combination = new HashSet<Integer>();
			if (!colType.contains(pivot))combination.add(pivot);
			if (!combination.isEmpty())list.add(combination);

			for (int i=pivot+2;i<m;i++) {
				combination = new HashSet<Integer>();
				if (!colType.contains(pivot))combination.add(pivot);
				if (!colType.contains(i))combination.add(i);
				if (combination.size()>1)list.add(combination);
			}

			for (int i=pivot+2;i<m;i++) {
				for (int j=i+2;j<m;j++) {
					combination = new HashSet<Integer>();
					if (!colType.contains(pivot))combination.add(pivot);
					if (!colType.contains(i))combination.add(i);
					if (!colType.contains(j))combination.add(j);
					if (combination.size()>2)list.add(combination);
				}
			}

			for (int i=pivot+2;i+pivot<m;i++) {
				for (int j=i+2;j<m;j++) {
					for (int k=j+2;k<m;k++) {
						combination = new HashSet<Integer>();
						if (!colType.contains(pivot))combination.add(pivot);
						if (!colType.contains(i))combination.add(i);
						if (!colType.contains(j))combination.add(j);
						if (!colType.contains(k))combination.add(k);
						if (combination.size()>3)list.add(combination);
					}
				}
			}

			if (pivot<m-1) {
				recursiveCombination(list, colType, pivot+1);
			}

		}

		private static ArrayList<HashSet<Integer>> preComputeNextPossibleColTypes(HashSet<Integer> colType){
			ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();

			recursiveCombination(list,colType,0);

			return list;
		}

		private static int computeColVal(int colIndex, HashSet<Integer> colType) {
			Iterator<Integer> iterator = colType.iterator();
			int sum=0;
			while (iterator.hasNext()) {
				sum+=chessBoard[iterator.next()][colIndex];
			}
			return sum;
		}

		private static int getNextColTypeValue(ArrayList<HashSet<Integer>> list, int colIndex, int rank){

			ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
			for (int i=0;i<list.size();i++) {
				pairList.add(new Pair<Integer,Integer>(computeColVal(colIndex,list.get(i)),i));
			}
			Collections.sort(pairList,Collections.reverseOrder());

			HashSet<Integer> h = list.get(pairList.get(rank).second);
			for (int i=0;i<m;i++) {
				if (h.contains(i))
					choices[i][colIndex]=true;
				else
					choices[i][colIndex]=false;
			}

			return pairList.get(rank).first;

		}

		private static HashSet<Integer> getNextColType(ArrayList<HashSet<Integer>> list, int colIndex, int rank){
			ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
			for (int i=0;i<list.size();i++) {
				pairList.add(new Pair<Integer,Integer>(computeColVal(colIndex,list.get(i)),i));
			}
			Collections.sort(pairList,Collections.reverseOrder());

			return list.get(pairList.get(rank).second);
		}
}
