package geometry;

public class Sphere extends Base {
	double radius;
	Point center;
	
	public Sphere(Point c, double d) {
		// TODO Auto-generated constructor stub
		super();
		radius = d;
		center = c;
	}
	
	public Sphere(Point c, double d, Point color, double rhod, double rhos, double s) {
		// TODO Auto-generated constructor stub
		super(color, rhod, rhos, s);
		radius = d;
		center = c;
	}

	@Override
	public T_Point_Obj_Normal intersect_Everyone(Line l) {
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
		if (ret>Options.DOUBLE_EPS) {
			tmp = new Point(l.w);
			tmp.times(ret);
			tmp.plus(l);
			return new T_Point_Obj_Normal(ret, tmp, this, getNormal(tmp));
		}
		else {
			ret = (-b+Math.sqrt(delta))/2;
			if (ret<Options.DOUBLE_EPS)
				return null;
			tmp = new Point(l.w);
			tmp.times(ret);
			tmp.plus(l);
			return new T_Point_Obj_Normal(ret, tmp, this, getNormal(tmp));
		}
	}
	
	public Point getCenter() {
		// TODO Auto-generated method stub
		if (!lighted)
			return center;
		Point ret = new Point(center);
		double theta=Math.random()*Math.PI*2;
		double phi=Math.random()*Math.PI;
		double rad=Math.random()*radius;
		ret.plus(new Point(rad*Math.cos(phi)*Math.cos(theta), rad*Math.cos(phi)*Math.sin(theta), rad*Math.sin(phi)));
		return ret;
	}
	
	public Point getNormal(Point p) {
		// TODO Auto-generated method stub
		Point t = new Point(p);
		t.minus(center);
		return t.normalize();
	}
}
