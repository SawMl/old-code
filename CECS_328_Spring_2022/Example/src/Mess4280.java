import java.util.Random;

public class Mess4280 {
	public double x;
	public double y;
	public int value;
	public int idx;
	
	public Mess4280() { }
	public Mess4280(double horizontal, double vertical, int v) {
		x=horizontal; y=vertical; value=v;
		idx = -1;
	}
	
	public Mess4280(double horizontal, double vertical, int v, int i) {
		x=horizontal; y=vertical; value=v;
		idx = i;
	}
	
	public void makeRandom(double maxHorizontal, double maxVertical, int maxValue, Random r) {
		x=maxHorizontal*r.nextDouble();
		y=maxVertical*r.nextDouble();
		value=1+r.nextInt(maxValue);
	}
	
	@Override
	public String toString() {
		return ""+x+","+y+","+value;
	}
	
	public boolean equals(Mess4280 other) {
		return ((x==other.x)&&(y==other.y)&&(value==other.value));
	}
}
