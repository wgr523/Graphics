package geometry;

import java.awt.Shape;
import java.util.ArrayList;

import bmp.Bmp;

public class Graphics {

	static ArrayList<Sphere> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		list = new ArrayList<Sphere>();
		Point point = new Point(0.0, 0.0, 0.0);
		Line line = new Line(point, 1.0, 1.0, 1.0);
		Point c = new Point(2.0,2.0,2.0);
		Sphere circle = new Sphere(c, 1.0);
		list.add(circle);
		Sphere c2 = new Sphere(point, 2.0);
		list.add(c2);
//		System.out.println(circle.intersect(line));
		// when draw, window and geometry should map
		Bmp pic = new Bmp();
		pic.setFile("he.jpg");
		for (Sphere s:list) {
			double tx=s.center.x/3.0;
			double tz=(3.0-s.center.z)/3.0;
			for (double ix=-s.radius;ix<=s.radius;ix+=0.005)
				for (double iz=-s.radius;iz<=s.radius;iz+=0.005) {
					Point tmp = new Point(ix+s.center.x,s.center.y,iz+s.center.z);
					tmp.minus(s.center);
					if (tmp.modulo2()<=s.radius) {
						double ttx=ix+s.center.x/3.0;
						double ttz=(3.0-iz-s.center.z)/3.0;
						pic.setPixelDouble(ttx, ttz, s.color);
					}
				}
			
		}
//		pic.setPixelDouble(0.5, 0.5, 0, 128, 255);
		pic.write();
	}

}
