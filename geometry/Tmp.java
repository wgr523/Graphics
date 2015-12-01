package geometry;

public class Tmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point tmppoint = new Point(0,2,0);
		Line tmpline = new Line(tmppoint, 0.1,-1,0);
//		Sphere or = new Sphere(new Point(), 1);
		Triangle tt= new Triangle(new Point(-1,0,0), new Point(0,0,-1),new Point(1,0,1));
//		System.out.println(tt.intersect(tmpline));
		System.out.println(tt.getNormal(new Point()));
//		System.out.println(or.getNormal(or.intersect(tmpline)));
//		System.out.println(Options.deterministic(new Point(-1,100,4), new Point(9,1,-1),new Point(1,17,1)));
//		System.out.println(or.reflect(tmpline, or.intersect(tmpline)));
	}

}
