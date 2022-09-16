
public class Point implements Comparable<Point> {
	
	Pair<Double,Double> coords;
	int index, severity, heightIndex;
	Point next;
	Point prev;
	double x, y;
	
	public Point(Pair<Double,Double> coords, int index, int severity) {
		this.coords = coords;
		this.index=index;
		this.severity=severity;
		x = coords.first;
		y = coords.second;
	}
	
	public Point(Point other) {
		this.coords=other.coords;
		this.index=other.index;
		this.severity=other.severity;
		this.heightIndex=other.heightIndex;
		this.x=other.x;
		this.y=other.y;
	}

	public int getHeightIndex() {
		return heightIndex;
	}



	public void setHeightIndex(int heightIndex) {
		this.heightIndex = heightIndex;
	}



	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public Pair<Double, Double> getCoords() {
		return coords;
	}

	public void setCoords(Pair<Double, Double> coords) {
		this.coords = coords;
	}

	public Point getNext() {
		return next;
	}

	public void setNext(Point next) {
		this.next = next;
	}

	public Point getPrev() {
		return prev;
	}

	public void setPrev(Point prev) {
		this.prev = prev;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}
	

	@Override
	public int compareTo(Point o) {
		return (int) ((this.y - o.y)*1000);
	}
	
	@Override
	public String toString() {
		return ""+index;
	}
	
	
	
}
