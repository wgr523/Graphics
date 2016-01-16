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
	public Point getColor(Point p) {
		// TODO Auto-generated method stub
		int tmp = (int)(p.x/4)+(int)(p.y/2+100000000);
		return tmp%2==0?Options.GREEY():Options.GREY();
	}
	
//	@Override
//	public Point getNormal(Point p) {
//		// TODO Auto-generated method stub
//		final double rr=.1;
//		Point tmp = new Point(n);
//		double theta = ((p.x/4+p.y/4)%1)*Math.PI*rr;
//		double phi = ((p.x/4+p.y/4)%2)*Math.PI*rr;
//		double sinn = Math.sin(theta), coss = Math.cos(theta);
//		tmp.times(coss);
//		Point tmpx,tmpy;
//		tmpx = new Point(1,0,0);
//		tmpy = new Point(0,1,0);
//		tmpx.times(sinn*Math.cos(phi));
//		tmpy.times(sinn*Math.sin(phi));
//		tmp.plus(tmpx);
//		tmp.plus(tmpy);
//		return tmp;
//	}
}
