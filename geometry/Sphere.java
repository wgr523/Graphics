package geometry;

public class Sphere extends Base {
	double radius;
	
	public Sphere(Point c, double d) {
		// TODO Auto-generated constructor stub
		super();
		radius = d;
		center = c;
	}
	
	public Sphere(Point c, double d, int color, double rhod, double rhos, double s) {
		// TODO Auto-generated constructor stub
		super(color, rhod, rhos, s);
		radius = d;
		center = c;
	}

	@Override
	public double normalInner(Line l, Point p) {
		// TODO Auto-generated method stub
		if (p==null || l==null) return 0;
		Point point = getNormal(p);
		return Math.abs(point.inner(l.w));
	}

	@Override
	public T_Point intersect_T(Line l) {
		// TODO Auto-generated method stub
		double ret;
//		double a=1.0;//l.w.modulo2();
		Point tmp = new Point(l);
		tmp.minus(center);
		double b=tmp.inner(l.w)*2;
		double c=tmp.modulo2()-radius*radius;
		double delta=b*b-(4*c);
		if (delta<0) return null;
//		if (delta==0) ret = (-b/a)/2; else 
		ret = (-b-Math.sqrt(delta))/2;
		if (ret<Options.DOUBLE_EPS) return null;
		tmp = new Point(l.w);
		tmp.times(ret);
		tmp.plus(l);
		return new T_Point(ret, tmp);
	}

}
