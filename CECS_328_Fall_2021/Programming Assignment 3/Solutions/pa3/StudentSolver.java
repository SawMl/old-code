package pa3;
import java.util.HashSet;
import java.util.Iterator;

public class StudentSolver {
	
	public static HashSet<Integer> solve(boolean[][] m, int k1x, int k2x){
		
		HashSet<Integer> l1 = new HashSet<Integer>();
		
		for (int i=0;i<m.length;i++) {
			if (hasK(m,i,k1x,k2x)) {
				l1.add(i);
			}
		}
		
		
		Iterator<Integer> iterator = l1.iterator();
		int x;
		while (iterator.hasNext()) {
			x=iterator.next();
			if (!setContains(l1,m,x,k1x,k2x)) {
				l1.remove(x);
				iterator = l1.iterator();
			}
			
		}
		
		return l1;
	}
	
	private static boolean hasK(boolean[][] m, int i, int k1x, int k2x) {
		int nonContra=0;
		int contra=0;
		for (int j=0;j<m.length;j++) {
			if (j!=i) {
				if (m[i][j]) {
					nonContra++;
				}
				else {
					contra++;
				}
			}
		}
		return (nonContra>=k1x && contra>=k2x);
	}
	
	private static boolean setContains(HashSet<Integer> set, boolean[][] m, int i, int k1x, int k2x) {
		
		int nonContra=0;
		int contra=0;
		
		for (int j=0;j<m.length;j++)
			if (j!=i) 
				if (set.contains(j)) {
					if (m[i][j]) 
						nonContra++;
					else 
						contra++;
				}
		
		return (nonContra>=k1x && contra>=k2x);
	}
	
}
