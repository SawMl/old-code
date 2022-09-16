
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;

public class Solver4280 {
	
	private static ArrayList<Mess4280> messes=new ArrayList<Mess4280>();
	private static double ratio;
	private static Solver4280 s = new Solver4280();
	
	
	public class MessComparator implements Comparator<Mess4280> {
		@Override
		public int compare(Mess4280 o1, Mess4280 o2) {
			if (o1.x!=o2.x) return (int) Math.round(Math.signum(o1.x-o2.x));
			if (o1.y!=o2.y) return (int) Math.round(Math.signum(o1.y-o2.y));
			return o1.value-o2.value;
		}
	}
	
	public static ArrayList<Integer> run(double r, ArrayList<Pair<Pair<Double, Double>, Integer>> mess) {
		
		TreeMap<Mess4280,TreeSet<Mess4280>> inEdges=new TreeMap<Mess4280,TreeSet<Mess4280>>(s.new MessComparator());
		TreeMap<Mess4280,TreeSet<Mess4280>> outEdges=new TreeMap<Mess4280,TreeSet<Mess4280>>(s.new MessComparator());
			
		ratio = r;
		for(int i = 0; i < mess.size(); i++)
		{
			Pair<Pair<Double, Double>, Integer> current = mess.get(i);
			Mess4280 temp = new Mess4280(current.first.first, current.first.second, current.second, i);
			messes.add(temp);
		}
		
		Collections.sort(messes, new Comparator<Mess4280>() {
			@Override
			public int compare(Mess4280 o1, Mess4280 o2) {
				return (int) Math.round(Math.signum(o1.y-o2.y));
			}
		});	
		
		// Form the DAG.
		Mess4280 source=new Mess4280(0.0,Double.MIN_VALUE,0);
		Mess4280 sink=new Mess4280(0.0,Double.MAX_VALUE,0);
		for (int i=0; i<messes.size(); i++) {
			inEdges.put(messes.get(i), new TreeSet<Mess4280>(s.new MessComparator()));
			outEdges.put(messes.get(i), new TreeSet<Mess4280>(s.new MessComparator())); }
		inEdges.put(source, new TreeSet<Mess4280>(s.new MessComparator()));
		outEdges.put(source, new TreeSet<Mess4280>(s.new MessComparator()));
		inEdges.put(sink, new TreeSet<Mess4280>(s.new MessComparator()));
		outEdges.put(sink, new TreeSet<Mess4280>(s.new MessComparator()));
		
		for (int i=0; i<messes.size(); i++)
			for (int j=0; j<messes.size(); j++)
				if (i!=j) {
					double horizontalDistance=Math.abs(messes.get(i).x-messes.get(j).x);
					double verticalDistance=messes.get(i).y-messes.get(j).y;
					if (horizontalDistance*ratio<=verticalDistance) {
						inEdges.get(messes.get(i)).add(messes.get(j));
						outEdges.get(messes.get(j)).add(messes.get(i));	} }
		for (int i=0; i<messes.size(); i++) {
			inEdges.get(messes.get(i)).add(source);
			outEdges.get(source).add(messes.get(i)); }
		for (int i=0; i<messes.size(); i++) {
			inEdges.get(sink).add(messes.get(i));
			outEdges.get(messes.get(i)).add(sink); }
	
		// Topologically sort the DAG.
		TreeMap<Mess4280,Pair<Integer,Mess4280>> d=new TreeMap<Mess4280,Pair<Integer,Mess4280>>(s.new MessComparator());
		ArrayList<Mess4280> topMesss=new ArrayList<Mess4280>(); // (d value, previous mess)
		while (!outEdges.isEmpty()) {
			boolean found=false;
			Iterator<Mess4280> messIter=outEdges.keySet().iterator();
			while (messIter.hasNext()) {
				Mess4280 current=messIter.next();
				if (outEdges.get(current).isEmpty()) {
					topMesss.add(0, current);
					d.put(current, new Pair<Integer,Mess4280>(0,null));
					outEdges.remove(current);
					found=true;
					Iterator<Mess4280> iter=outEdges.keySet().iterator();
					while (iter.hasNext()) {
						Mess4280 g=iter.next();
						outEdges.get(g).remove(current); }
					break; } }
			if (!found) throw new RuntimeException("This is not a DAG."); }
		
		// Use the dynamic programming algorithm for determining the longest path in a DAG.
		for (int i=0; i<topMesss.size(); i++) {
			Mess4280 current=topMesss.get(i);
			Iterator<Mess4280> messIter=inEdges.get(current).iterator();
			Pair<Integer,Mess4280> dValue=new Pair<Integer,Mess4280>(0,null);
			while (messIter.hasNext()) {
				Mess4280 other=messIter.next();
				if (d.get(other).first.intValue()+current.value>dValue.first.intValue())
					dValue=new Pair<Integer,Mess4280>(d.get(other).first.intValue()+current.value,other); }
			d.put(current, dValue);	}
				
		ArrayList<Mess4280> output=new ArrayList<Mess4280>();
		Mess4280 currentMess=sink;
		while (currentMess!=null) {
			output.add(0, currentMess);
			currentMess=d.get(currentMess).second; }
		output.remove(sink); output.remove(source);

		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		for(int i = 0; i < output.size(); i++)
			answer.add(output.get(i).idx);
		
		return answer;
	}
}
