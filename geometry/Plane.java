package geometry;

public class Plane extends Base {
	Double d;
	Point n;
	public Plane(Point x, double d) {
		// TODO Auto-generated constructor stub
		super();
		n=x.normalize();
		this.d=d;
	}
	
	public Plane(Point x, Point d) {
		// TODO Auto-generated constructor stub
		super();
		n=x.normalize();
		this.d=-x.inner(d);
	}
	
	@Override
	public T_Point intersect_T(Line l) {
		// TODO Auto-generated method stub
		double ret = -(n.inner(l)+d)/(n.inner(l.w));
		if (ret<Options.DOUBLE_EPS) return null;
		Point tmp = new Point(l.w);
		tmp.times(ret);
		tmp.plus(l);
		return new T_Point(ret, tmp);
	}

	@Override
	public Point getNormal(Point p) {
		// TODO Auto-generated method stub
		return n;
	}
	
}
