package geometry;

public class Tmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point tmppoint = new Point(-1,2,0);
		Line tmpline = new Line(tmppoint, 1,-1,0);
		Sphere or = new Sphere(new Point(), 1);
		System.out.println(or.intersect(tmpline));
		System.out.println(or.getNormal(or.intersect(tmpline)));

		System.out.println(or.reflect(tmpline, or.intersect(tmpline)));
	}

}
