package squidGame1779;
import java.util.ArrayList;
import java.util.Collections;

public class StudentSolver {

	public static ArrayList<ArrayList<Integer>> solve(int numLines, int numPpl){
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		
		for (int i=0;i<numLines;i++) {
			list.add(new ArrayList<Integer>());
		}
		
		return launchFunction(list,numPpl);
		
	}
	
	private static ArrayList<ArrayList<Integer>> launchFunction(ArrayList<ArrayList<Integer>> list, int numPpl) {
		list.get(0).add(1);
		Node node = new Node(null,list,1);
		node = function(node,numPpl);
		return node.list;
	}
	
	private static Node function(Node previous, int numPpl) {
		int num = previous.num;
		Node node, futureNode;
		if (num==numPpl) {
			return previous;
		}
		num = num + 1;
		ArrayList<ArrayList<Integer>>list = previous.list;
		ArrayList<ArrayList<Integer>>cloneList;
		ArrayList<Integer>cloneLine;
		boolean flag=false;
		ArrayList<Integer> saveLine = null;
		for (ArrayList<Integer> line : list) {
			if (line.isEmpty()) {
				flag = true;
				saveLine = line;
				break;
			}
			
			if (canAdd(line,num)) {
				cloneLine = (ArrayList<Integer>)line.clone();
				cloneLine.add(num);
				cloneList = (ArrayList<ArrayList<Integer>>)list.clone();
				cloneList.remove(line);
				cloneList.add(cloneLine);
				node = new Node(previous,cloneList,num);
				futureNode = function(node,numPpl);
				num = futureNode.num;
				if (num==numPpl) {
					previous.next = node;
					return futureNode;
				}
			}
		}
		if (flag) {
			cloneList = (ArrayList<ArrayList<Integer>>)list.clone();
			cloneList.remove(saveLine);
			saveLine=(ArrayList<Integer>)saveLine.clone();
			saveLine.add(num);
			cloneList.add(saveLine);
			node = new Node(previous,cloneList,num);
			futureNode = function(node,numPpl);
			num=futureNode.num;
			if (num==numPpl) {
				previous.next = node;
				return futureNode;
			}
		}
		return previous;
	}
	
	private static boolean canAdd(ArrayList<Integer> line, int num) {
		Collections.sort(line,Collections.reverseOrder());
		if (Square.isPerfect(num+line.get(0)))
			return true;
		return false;
	}
	
	
}
