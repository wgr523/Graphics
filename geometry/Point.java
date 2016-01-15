package geometry;

public class Point {
	public double x,y,z;
	
	public Point() {
		x=0.0;
		y=0.0;
		z=0.0;
	}
	
	public Point(double x1, double y1, double z1) {
		x=x1;y=y1;z=z1;
	}
	
	public Point(Point point) {
		x=point.x;
		y=point.y;
		z=point.z;
	}
	public double get(int index) {
		return index==0? x : (index==1) ? y : (index==2 ? z : 0);
	}
	public double modulo () {
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	public double modulo2 () {
		return (x*x+y*y+z*z);
	}
	
	public Point normalize() {
		double n = modulo();
		return new Point(x/n, y/n, z/n);
	}
	
	public void plus(Point p) {
		x+=p.x;y+=p.y;z+=p.z;
	}
	public void minus(Point p) {
		x-=p.x;y-=p.y;z-=p.z;
	}
	public void times(double d) {
		x*=d;y*=d;z*=d;
	}
	public void times(Point b) {
		x*=b.x;y*=b.y;z*=b.z;
	}
	public double inner(Point p) {
		return x*p.x+y*p.y+z*p.z;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x="+x+" y="+y+" z="+z;
	}
}
