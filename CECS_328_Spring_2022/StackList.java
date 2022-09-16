package shangchi;
import java.util.ArrayList;
import java.util.Collections;


public class StackList {

	ArrayList<Integer> list;
	
	public StackList() {
		list = new ArrayList<Integer>();
	}
	
	public void push(int a) {
		list.add(a);
	}
	
	public int pop() {
		
		int index = list.size() - 1;
		int a = list.get(index);
		list.remove(index);
		return a;
	}
	
	public void addToAll(int a) {
		int b;
		for (int i=0;i<list.size();i++) {
			b = list.get(i);
			b = b + a;
			list.set(i, b);
		}
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void addAllToEnd(StackList stacklist) {
		while (!stacklist.isEmpty()) {
			this.push(stacklist.pop());
		}
	}
	
	public ArrayList<Integer> getList(){
		return list;
	}
	
	public void sort() {
		Collections.sort(list,Collections.reverseOrder());
	}
}
