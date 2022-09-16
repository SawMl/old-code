//package matrixSelect;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Column implements Comparable<Column> {

		private int numElements;
		private Column nextColumn, previousColumn;
		private HashSet<Integer> selections;
		private ArrayList<Integer> elements;
		private int value;
		private int columnNum;
		private int valueOfFutures;
		private HashSet<Pair<Integer,Integer>> coords;

		public Column(int colIndex, ArrayList<Integer> elems, HashSet<Integer> svars, Column prev) {
			columnNum = colIndex;
			elements = elems;
			selections = svars;
			value = 0;
			calculateValue();
			valueOfFutures=0;
			previousColumn = prev;
		}

		private void calculateValue() {
			Iterator<Integer> iterator = selections.iterator();
			while (iterator.hasNext()) {
				value+=elements.get(iterator.next());
			}
		}

		public Column(ArrayList<Integer> a, int columnNum) {
			numElements = a.size();
			elements = a;
			this.columnNum=columnNum;
		}

		public Column(int columnNum, HashSet<Integer> selections) {
			this.columnNum=columnNum;
			this.selections=selections;
			this.valueOfFutures=0;
		}

		public HashSet<Pair<Integer,Integer>> getCoords(){
			coords = new HashSet<Pair<Integer,Integer>>();
			Iterator<Integer> iterator = selections.iterator();
			while (iterator.hasNext()) {
				coords.add(new Pair<Integer,Integer>(iterator.next(),this.columnNum));
			}
			//System.out.println("col val="+this.getValue());
			//System.out.println("Elements="+elements);
			return coords;
		}


		public int getValueOfFutures() {
			return valueOfFutures;
		}

		public void setValueOfFutures(int valueOfFutures) {
			this.valueOfFutures = valueOfFutures;
		}

		public void setElements(ArrayList<Integer> elements) {
			this.elements = elements;
			if (selections!=null)
				value = getValue();
		}

		public Column getNextColumn() {
			return nextColumn;
		}

		public void setNextColumn(Column nextColumn) {
			this.nextColumn = nextColumn;
		}

		public Column getPreviousColumn() {
			return previousColumn;
		}

		public void setPreviousColumn(Column previousColumn) {
			this.previousColumn = previousColumn;
		}

		public HashSet<Integer> getSelections() {
			return selections;
		}

		public void setSelections(HashSet<Integer> selections) {
			this.selections = selections;
			if (elements!=null)
				value = getValue();
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}


		@Override
		public int compareTo(Column o) {

			return this.value-o.value;
		}

		public int getColumnNum() {
			return columnNum;
		}


}
