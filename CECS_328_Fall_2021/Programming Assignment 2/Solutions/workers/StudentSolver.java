package workers;
import java.util.ArrayList;
import java.util.Random;

public class StudentSolver {
	
	private static ArrayList<ArrayList<Pair<Integer,Integer>>> valid;
	private static int ra;
	private static int rb;
	
	public static ArrayList<ArrayList<Pair<Integer,Integer>>> solve(ArrayList<Pair<Integer,Integer>> list){
		valid = new ArrayList<ArrayList<Pair<Integer,Integer>>>();
		
		ArrayList<Pair<Integer,Integer>> temp = new ArrayList<Pair<Integer,Integer>>();
		
		Random rand = new Random();
		ra = rand.nextInt(1000);
		rb = rand.nextInt(1000);
		for (int i=0;i<100000;i++) {
			mergeSort(list,0,list.size()-1);
			temp = copyList(list);
			if (!hasDuplicate(temp))
				valid.add(temp);
			temp = new ArrayList<Pair<Integer,Integer>>();
			ra = rand.nextInt(1000);
			rb = rand.nextInt(1000);
		}
		
		
		return valid;
	}
	
	private static boolean hasDuplicate(ArrayList<Pair<Integer,Integer>> l) {
		for (int i=0;i<valid.size();i++) {
			if (l.equals(valid.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static void printLists(ArrayList<ArrayList<Pair<Integer,Integer>>> h) {
		if(h.size()==0) {
			System.out.println("nothing to print");
		}
		for (int i=0;i<h.size();i++) {
			System.out.println(h.get(i));
		}
	}
	
	private static ArrayList<Pair<Integer,Integer>> copyList(ArrayList<Pair<Integer,Integer>> l){
		ArrayList<Pair<Integer,Integer>> newList = new ArrayList<Pair<Integer,Integer>>();
		for (int i=0;i<l.size();i++) {
			newList.add(l.get(i));
		}
		return newList;
	}
	
	private static void mergeSort(ArrayList<Pair<Integer,Integer>> A, int p, int r) {
		int q;
		if (p<r) {
			q=((p+r)/2);
			mergeSort(A,p,q);
			mergeSort(A,q+1,r);
			merge(A,p,q,r);
		}
	}
	
	private static void merge(ArrayList<Pair<Integer,Integer>> A, int p, int q, int r) {
		int n1 = q-p+1;
		int n2 = r-q;
		
		ArrayList<Pair<Integer,Integer>> arrayL = new ArrayList<Pair<Integer,Integer>>();
		ArrayList<Pair<Integer,Integer>> arrayR = new ArrayList<Pair<Integer,Integer>>();
		
		for (int i=0;i<n1;i++) {
			arrayL.add(A.get(p+i));
		}
		
		Pair<Integer,Integer> infinity = new Pair<Integer,Integer>(100000,100000);
		
		arrayL.add(infinity);
		
		for (int i=0;i<n2;i++) {
			arrayR.add(A.get(q+i+1));
		}
		
		arrayR.add(infinity);
		
		int cnt1=0;
		int cnt2=0;
		
		for (int i=p;i<=r;i++) {
			if (cnt1<arrayL.size() && cnt2<arrayR.size()) {
				if (work(arrayL.get(cnt1))<=work(arrayR.get(cnt2))) {
					A.set(i,arrayL.get(cnt1));
					cnt1++;
				}
				else {
					A.set(i,arrayR.get(cnt2));
					cnt2++;
				}
			}
		}
	}
		
		
	
	private static int work(Pair<Integer,Integer> team) {
		return ra*team.first + rb*team.second;
	}
}
