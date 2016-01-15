package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class Options {
	public static final int WIDTH= 400;
	public static final int HEIGHT= 400;
	public static final int CMAX= 256;
	public static final int MAXDEPTH= 2;
	public static final double SMALLANG = Math.PI/4;
	public static final double AMBIENT = .1;
	public static final double DOUBLE_EPS = 1e-6;
	private static Point white;
	private static Point black;
	private static Point grey;
	private static Point axis[];
	public static Point AXIS(int i)
	{
		if (i<0 || i>2) return null;
		if (axis==null) {
			axis = new Point [3];
			axis[0] = new Point(1,0,0);
			axis[1] = new Point(0,1,0);
			axis[2] = new Point(0,0,1);
		}
		return axis[i];
	}
	
	public static Point WHITE()
	{
		if (white==null) white = new Point(1,1,1);
		return white;
	}
	public static Point BACKGROUND()
	{
		if (black==null) black = new Point();
		return black;
	}
	public static Point GREY()
	{
		if (grey==null) grey = new Point(.5,.5,.5);
		return grey;
	}
	public static Point colorInner(Point a,Point b){
		Point ret = new Point(a);
		ret.times(b);
		return ret;
	}
	public static Point colorPlus(Point a,Point b){
		Point ret = new Point(a);
		ret.plus(b);
		return ret;
	}
	public static Point colorTimes(Point a,double b){
		Point ret = new Point(a);
		ret.times(b);
		return ret;
	}

	public static double deterministic (Point a, Point b, Point c) {
		return a.x*b.y*c.z + a.y*b.z*c.x + a.z*b.x*c.y
				- c.x*b.y*a.z - c.y*b.z*a.x - c.z*b.x*a.y;
	}
	public static void two (Point a) {
		Point b = new Point(a);
		b.times(2);
		a=b;
	}
	public static T_Point_Obj_Normal nearestIntersect(Collection<Base> list, Line line) {
		T_Point_Obj_Normal tmp=null, ret=null;
		double tmp2=Double.MAX_VALUE;
		for (Iterator<Base> it = list.iterator();it.hasNext();) {
			Base obj = it.next();
			tmp = obj.intersect_Everyone(line);
			if (tmp!=null && tmp.point!=null) {
				if (tmp.t < tmp2) {
					tmp2=tmp.t;
					ret=tmp;
				}
			}
		}
		return ret;
	}
}
