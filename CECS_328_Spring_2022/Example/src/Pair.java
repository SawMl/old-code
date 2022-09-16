

@SuppressWarnings("rawtypes")
public class Pair<T, S> implements Comparable {
	public T first;
	public S second;
	public Pair(T f, S s) { first = f; second = s; }
	public Pair() { first=null; second=null; }
	public String toString() { return "(" + first.toString() + ", " + second.toString() + ")"; }
	public boolean equals(Pair<T, S> other) { return ((this.first.equals(other.first))&&(this.second.equals(other.second))); }
	public int hashCode() { 
		if (first.getClass().equals(String.class)) 
			return ((String)first+","+(String)second).hashCode(); 
		else return first.hashCode()+second.hashCode(); }
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object otherObject) {
		Pair<T,S> other=(Pair<T,S>) otherObject;
		if (first.getClass().equals(String.class)) {
			String fHelper=(String) first; String oHelper=(String)other.first;
			if (fHelper.compareTo(oHelper)!=0) return fHelper.compareTo(oHelper); }
		if (first.getClass().equals(Integer.class))
			if (first!=other.first) return (Integer)first-(Integer)other.first;
		if (second.getClass().equals(String.class)) {
			String fHelper=(String)second; String oHelper=(String)other.second;
			if (fHelper.compareTo(oHelper)!=0) return fHelper.compareTo(oHelper); }
		if (second.getClass().equals(Integer.class))
			if (second!=other.second) return (Integer)second-(Integer)other.second;
		return 0;
	}
}
