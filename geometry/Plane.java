package geometry;

public class Plane extends Base {
	Double d;
	Point n;
	Point x0,x1,x2;
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
	
	public void setBound(Point x0,Point x1,Point x2) {
		this.x0 = x0;
		this.x1 = x1;
		this.x2 = x2;
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
