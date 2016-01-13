package geometry;

public class BWPlane extends Plane {
	public BWPlane(Point x, double d) {
		super(x, d);
		// TODO Auto-generated constructor stub
	}
	public BWPlane(Point x, Point d) {
		super(x, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColor(Point p) {
		// TODO Auto-generated method stub
		int tmp = (int)(p.x/4+1000)+(int)(p.y/4+1000);
		return tmp%2==0?Options.WHITE:0;
	}
}
