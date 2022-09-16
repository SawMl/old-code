package squidGame1779;
import java.util.ArrayList;

public class Node {

	public Node previous;
	public Node next;
	public ArrayList<ArrayList<Integer>> list;
	public int num;
	
	public Node(Node previous, ArrayList<ArrayList<Integer>> list, int num) {
		this.previous=previous;
		this.list = list;
		this.num = num;
	}
	
}
