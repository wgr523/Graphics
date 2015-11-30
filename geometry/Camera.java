package geometry;

public class Camera {
	Point view;
	Point lu; // left up starting point
	Point vecx, vecy; //two directions of x,y axis, length matters!
	
	public Camera() {
		// TODO Auto-generated constructor stub
		view = new Point(-20.0, 0.0, 0.0);
		lu= new Point(-12.0,-1.0,1.0);
		vecx = new Point(0, .0025, 0);
		vecy = new Point(0, 0, -.0025);
	}
	public Line getLineOfPixel (int x, int y) {
		Point tx=new Point(vecx);
		Point ty=new Point(vecy);
		tx.times(x);
		ty.times(y);
		tx.plus(ty);
		tx.plus(lu);
		tx.minus(view);
		return new Line(view, tx);
	}
}
