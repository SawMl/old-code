//package matrixSelect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;

public class StudentSolver {

	private static int m;
	private static int n;
	private static ArrayList<ArrayList<Integer>> values;
	private static ArrayList <HashMap<HashSet<Integer>,Column>> dontExecuteThese;
	private static ArrayList <HashMap<HashSet<Integer>,Column>> memoized;//      HashSet<Integer>,Column>> memoized;
	private static int counter;
	private static HashMap<HashSet<Integer>,ArrayList<HashSet<Integer>>> memoizedFormations;


		public static ArrayList<Pair<Integer,Integer>> solve(int[][] board){
			ArrayList<Pair<Integer,Integer>> solutions = new ArrayList<Pair<Integer,Integer>>();
			m=board.length;
			n=board[0].length;
			memoized = new ArrayList<HashMap<HashSet<Integer>,Column>>();
			dontExecuteThese = new ArrayList<HashMap<HashSet<Integer>,Column>>();
			memoizedFormations = new HashMap<HashSet<Integer>,ArrayList<HashSet<Integer>>>();
			values = new ArrayList<ArrayList<Integer>>();
			for (int i=0;i<n;i++) {
				values.add(new ArrayList<Integer>());
				memoized.add(new HashMap<HashSet<Integer>,Column>());
				dontExecuteThese.add(new HashMap<HashSet<Integer>,Column>());
				for (int j=0;j<m;j++) {
					values.get(i).add(board[j][i]);
				}
			}
			Pair<Column,Integer> p = launch(0,preComputeNextPossibleColTypes(new HashSet<Integer>()), null);

			System.out.print(p.second);
			//System.out.print(memoized);

			Column c = p.first;
			while (c.getNextColumn()!=null)
			{
				solutions.addAll(c.getCoords());
				c=c.getNextColumn();
			}
			solutions.addAll(c.getCoords());
			return solutions;
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
			if (memoizedFormations.containsKey(colType)) {
				return memoizedFormations.get(colType);
			}
			ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
			recursiveCombination(list,colType,0);
			list.add(new HashSet<Integer>());
			memoizedFormations.put(colType,list);

			return list;
		}

	private static Pair<Column,Integer>launch(int colIndex, ArrayList<HashSet<Integer>> list, Column prev) {
			ArrayList<Column> a = new ArrayList<Column>();
			ArrayList<Column> b = new ArrayList<Column>();
			HashMap<HashSet<Integer>,Column> map;
			HashSet<Integer> set;
			ArrayList<Integer> elements;
			for (int i=0;i<list.size();i++) {
				map = memoized.get(colIndex);
				set = list.get(i);
				if (map.containsKey(set)) {
					map.get(set).setPreviousColumn(prev);
					b.add(map.get(set));
				}
				else {
					elements = values.get(colIndex);
					a.add(new Column(colIndex,elements,set,prev));
				}

			}

			Collections.sort(a,Collections.reverseOrder());
			Collections.sort(b, Collections.reverseOrder());


			if (colIndex==n-1) {
				return new Pair<Column,Integer>(a.get(0),a.get(0).getValue());
			}

			Pair<Column,Integer> p;
			Column currentCol;
			ArrayList<HashSet<Integer>> nextPossibleColTypes;

			//Recrusive calls to the future
			HashSet<Integer> selectionSet;
			map = memoized.get(colIndex);
			for (int i=0;i<a.size();i++) {
				currentCol = a.get(i);
				selectionSet = currentCol.getSelections();
				nextPossibleColTypes = preComputeNextPossibleColTypes(selectionSet);
				p = launch(colIndex+1,nextPossibleColTypes,currentCol);
				currentCol.setValueOfFutures(p.second);
				currentCol.setNextColumn(p.first);
				if (map.containsKey(selectionSet))
					map.replace(selectionSet, currentCol);
				else
					map.put(selectionSet,currentCol);
				//if (colIndex==0)
					//System.out.println(p.first.getSelections() + " "+ p.second);
			}




			//Find the column in 'B' with the greatest future values
			int bmax=0;
			int bindex=0;
			for (int i=0;i<b.size();i++) {
				if (b.get(i).getValueOfFutures()+b.get(i).getValue()>bmax) {
					bmax = b.get(i).getValueOfFutures()+b.get(i).getValue();
					bindex = i;
				}
			}

			//b[bindex] is target

			//Find the column from 'array' with the greatest future value
			Column c;
			int amax=0;
			int aindex=0;

			for (int j=0;j<a.size();j++) {
				c = a.get(j);
				//if (colIndex==0)
					//System.out.println(c.getSelections() +" "+  c.getValue());
				if ((c.getValueOfFutures()+c.getValue())>amax) {
					amax=c.getValue() + c.getValueOfFutures();
					aindex=j;
				}
			}

			//a[aindex] is target

			//Column ccc = array.get(aindex).first.getPreviousColumn();
			/*
			if (colIndex==0) {
				System.out.println();
				System.out.println(ccc.getSelections()+" "+ccc.getValueOfFutures());
				System.out.println(ccc.getSelections()+" "+ccc.getValue());
				ccc = ccc.getNextColumn();
				System.out.println(ccc.getSelections()+" "+ccc.getValue());
				ccc = ccc.getNextColumn();
				System.out.println(ccc.getSelections()+" "+ccc.getValue());
				System.exit(0);
			}*/


			//now compare the two.
			int max;
			if (amax>bmax) {
				c = a.get(aindex);
				max=amax;
				//memoize the best one

			}
			else {
				c = b.get(bindex);
				max=bmax;
				//this one should already be memoized
				//get rid of all the 'a's though


			}

			return new Pair<Column,Integer>(c,max);

	}
}
