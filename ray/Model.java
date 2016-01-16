package ray;

import geometry.Base;
import geometry.Line;
import geometry.Point;

public class Model {
	public static double D (Line v, Line l, Base obj, Point p, Line r) {
		return obj.getRhod();
	}
	/*public static double S (Line v, Line l, Base obj, Point p, Line r) {//Phong's S not D
		Point n = obj.getNormal(p);
		double tmp = Math.pow(r.w.inner(v.w),obj.getS())/l.w.inner(n);
		return obj.getRhos()*tmp;
	}*/
	public static double S (Point v, Point l, Point n, double s, double rhos) {
		Point tp = new Point(v);
		tp.minus(l);
		tp =tp.normalize();
		double tmp = Math.abs(Math.pow(n.inner(tp),s)/n.inner(l));
//		System.out.println(obj.getRhos()*tmp);
//		if (Math.abs(rhos*tmp)>Options.DOUBLE_EPS)
		return rhos*tmp;
//		return 0;
	}
}
