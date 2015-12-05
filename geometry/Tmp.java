package geometry;

public class Tmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point tmppoint = new Point(-1,2,0);
		Line tmpline = new Line(tmppoint, 1,-1,0);
		Sphere or = new Sphere(new Point(), 1);
		Line r1 = or.refractIn(tmpline,or.intersect(tmpline));
		System.out.println(or.getNormal(or.intersect(tmpline)));
		System.out.println(r1);
		Point inter2 = or.intersect(r1);
		System.out.println(inter2);
		System.out.println(or.refractOut(r1, inter2));

//		Triangle tt= new Triangle( new Point(0,0,-1),new Point(-1,0,0),new Point(1,0,1));
//		System.out.println(tt.intersect(tmpline));
//		System.out.println(tt.getNormal(new Point()));
//		System.out.println(tt.reflect(tmpline, tt.intersect(tmpline)));
//		System.out.println(or.getNormal(or.intersect(tmpline)));
//		System.out.println(Options.deterministic(new Point(-1,100,4), new Point(9,1,-1),new Point(1,17,1)));
//		System.out.println(or.reflect(tmpline, or.intersect(tmpline)));
	}

}
