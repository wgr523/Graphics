package geometry;

public final class Options {
	public static final int WIDTH= 400;
	public static final int HEIGHT= 400;
	public static final int CMAX= 256;
	public static final int MAXDEPTH= 3;
	public static final double SMALLANG = Math.PI/4;
	public static final double AMBIENT = .1;
	//	public static final int WHITE = 16777215;
	//	public static final int BACKGROUND = 0;
	public static final double DOUBLE_EPS = 1e-6;
	private static Point white;
	private static Point black;
	private static Point grey;
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
}
