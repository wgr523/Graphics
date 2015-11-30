package geometry;

public class Plane extends Point {
	Double d;
	public Plane(Point x, double d) {
		// TODO Auto-generated constructor stub
		super(x);
		this.d=d;
	}
	public Plane(Point x, Point d) {
		// TODO Auto-generated constructor stub
		super(x);
		this.d=-x.inner(d);
	}
}
