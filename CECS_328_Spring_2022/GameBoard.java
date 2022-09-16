package bulbs;
import java.util.Random;
import java.util.ArrayList;

public class GameBoard {

	private int numRows;
	private int numWireSets;
	private ArrayList<ArrayList<Integer>> board;
	private ArrayList<Boolean> switches;
	
	
	public GameBoard(int numRows) {
		this.numRows = numRows;
		Random r = new Random();
		numWireSets = r.nextInt(numRows*3)+5;
		switches = new ArrayList<Boolean>();
		board = new ArrayList<ArrayList<Integer>>();
		initialize();
		printBoard();
	}
	
	private void initialize() {
		Random r = new Random();
		int x,y,z;
		for (int i=0;i<numRows;i++) {
			board.add(new ArrayList<Integer>());
			x = r.nextInt(numWireSets-5)+1; //1:num-5
			for (int j=0;j<3;j++) {
				z = r.nextInt(6); //0-5
				y = x + z; //
				z = r.nextInt(2);
				if (z==1) {
					y = y * -1;
				}
				board.get(i).add(y);
			}
		}
		turnOnSwitchesRandomly();
		fixBoard();
	}
	
	private void turnOnSwitchesRandomly() {
		Random r = new Random();
		int x;
		for (int i = 0;i<numWireSets;i++) {
			x = r.nextInt(2);
			if (x==1) {
				switches.add(false);
			}
			else
				switches.add(true);
		}
	}
	
	private void fixBoard() {
		for (ArrayList<Integer> row : board) {
			if (!rowIsOn(row)) {
				fixRow(row);
			}
			if (row.isEmpty()) {
				board.remove(row);
			}
		}
	}
	
	private void fixRow(ArrayList<Integer> row) {
		Pair<Integer,Integer> pair = findRange(row);
		ArrayList<Integer> rowClone = (ArrayList<Integer>)row.clone();
		int difference = pair.second - pair.first;
		int x = pair.first - 5 + difference;
		if (x<0)
			x=1;
		
		Random r = new Random();
		int index, randWire;
		
		int count=0;
		do{
			
				
			index = r.nextInt(3);
			randWire = r.nextInt(6) + x;
			rowClone = (ArrayList<Integer>)row.clone();
			rowClone.set(index, x);
		}
		while (!rowIsOn(rowClone));
		
		row = rowClone;
			
	}
	
	private Pair<Integer,Integer> findRange(ArrayList<Integer> row){
		int min = numWireSets+1;
		int max = 0, temp;
		for (int i : row) {
			temp = i;
			if (temp<0) {
				temp = temp * -1;
			}
			if (temp>max) {
				max = temp;
			}
			if (temp<min) {
				min = temp;
			}
		}
		return new Pair<Integer,Integer>(min,max);
	}
	
	
	private boolean rowIsOn(ArrayList<Integer> row) {
		if (row.isEmpty()) {
			System.out.println("Empty row");
			System.exit(0);
		}
		for (int element : row) {
			if (element<0) {
				if (!switches.get(element*-1-1))
					return true;
			}
			else {
				if (switches.get(element-1))
					return true;
			}
		}
		return false;
	}
	
	private void printBoard() {
		for (ArrayList<Integer> element : board) {
			printRow(element);
			System.out.println();
		}
	}
	
	private void printRow(ArrayList<Integer> list) {
		for (int x : list) {
			System.out.printf("%5d", x);
		}
	}
	
	public int getSize() {
		return board.size();
	}
	
	
}
