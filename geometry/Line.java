package geometry;

public class Line extends Point{
	public Point w;
	//keep direction vector w to be normalized
	
	//parameter x1 must not change by others
	public Line(double x1, double y1, double z1, Point d1) {
		super(x1, y1, z1);
		w=d1.normalize();
	}
	
	public Line(Point x1, double w1, double w2, double w3) {
		super(x1);
		double n = Math.sqrt(w1*w1+w2*w2+w3*w3);
		w=new Point(w1/n, w2/n, w3/n);
	}
	
	public Line(Point x1, Point x2) {
		super(x1);
		w=x2.normalize();
	}
	
	public Line(Point x1) {
		super(x1);
	}
	
	public void setDir(Point x2) {
		w=x2.normalize();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x="+x+" y="+y+" z="+z+" dirction is ("+w+")";
	}
}
