package geometry;

public class Camera {
	Point view;
	Point lu; // left up starting point
	Point vecx, vecy; //two directions of x,y axis, length matters!

	public Camera() {
		// TODO Auto-generated constructor stub
		view = new Point(-20.0, 0.0, 2.0);
//		lu= new Point(-4,2.0,1.2);
//		vecx = new Point(0, -.005, 0);
//		vecy = new Point(0, 0, -.005);
		lu= new Point(8, 4.0, 1.6);
		vecx = new Point(0, -.01, 0);
		vecy = new Point(-0.006/7, 0, -.01);
	}
	public Camera(int in) {
		// TODO Auto-generated constructor stub
		if (in==1) {
			view = new Point(-20.0, 0.0, 0.0);
			lu= new Point(-12.0,-1.0,1.0);
			vecx = new Point(0, .005, 0);
			vecy = new Point(0, 0, -.005);
		}
		else {
			view = new Point(0.0, 0.0, 20.0);
			lu= new Point(-1.0,1.0,12.0);
			vecx = new Point(.01, 0,  0);
			vecy = new Point(0,  -.01, 0);
		}
	}
	Point getView() {
		return view;
	}
	public synchronized Line getLineOfPixel (int x, int y) {
		Point tx=new Point(vecx);
		Point ty=new Point(vecy);
		tx.times(x);
		ty.times(y);
		tx.plus(ty);
		tx.plus(lu);
		tx.minus(getView());
		return new Line(getView(), tx);
	}
	public synchronized Line getLineOfPixelWithDelta (int x, int y, double dx, double dy) {
		Point tx=new Point(vecx);
		Point ty=new Point(vecy);
		tx.times(dx+x);
		ty.times(dy+y);
		tx.plus(ty);
		tx.plus(lu);
		tx.minus(getView());
		return new Line(getView(), tx);
	}
}
