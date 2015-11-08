package geometry;

public class Line extends Point{
	Point w;
	//keep direction vector w to be normalized
	public Line(double x1, double y1, double z1, Point d1) {
		super(x1, y1, z1);
		w=d1.normalize();
	}
	
	public Line(Point x1, double w1, double w2, double w3) {
		super(x1);
		double n = Math.sqrt(w1*w1+w2*w2+w3*w3);
		w=new Point(w1/n, w2/n, w3/n);
	}
}
