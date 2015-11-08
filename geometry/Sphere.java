package geometry;

import java.util.Random;

public class Sphere extends Base {
	double radius;
	Point center;
	
	public Sphere(Point c, double d) {
		// TODO Auto-generated constructor stub
		radius = d;
		center = c;
		color = new Random().nextInt(65536);
	}
	
	@Override
	public Point intersect (Line l) {
		// only compute the first, outer side point. else return null
		double ret;
		final double a=1.0;
		Point tmp = new Point(l);
		tmp.minus(center);
		double b=tmp.inner(l.w)*2;
		double c=tmp.modulo2()-radius*radius;
		double delta=b*b-4*a*c;
		if (delta<0) return null;
		if (delta==0) ret = -b/a/2;
		else ret = (-b-Math.sqrt(delta))/a/2;
		if (ret<0) return null;
		tmp = new Point(l.w);
		tmp.times(ret);
		Point tmp2 = new Point(l);
		tmp2.plus(tmp);
		return tmp2;
//		double c=(l.x-center.x)*(l.x-center.x)+(l.y-center.y)*(l.y-center.y)+(l.z-center.z)*(l.z-center.z);
	}

}
