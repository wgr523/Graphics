package geometry;

import bmp.Bmp;
import ray.Trace;

public class Graphics {

//	static ArrayList<Sphere> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GManager manager = new GManager();
		Camera camera = new Camera();
		
		Point c = new Point(2.5,1.25,.2);
		Sphere circle = new Sphere(c, 0.8,(200<<16)+(100<<8)+4,.4,.2, 8);
		circle.setReflection();
		manager.add(circle);
		Point c2=new Point(2.5, -1, .2);
		Sphere circle2 = new Sphere(c2, 0.8);
		circle2.setColor(65535);
//		circle2.setReflection();
		manager.add(circle2);
		Triangle triangle1= new Triangle(new Point(2.5,1.7,2), new Point(2.5,1.7,1),new Point(1.5,2,1));
		manager.add(triangle1);
		triangle1.setColor(65535);
		Triangle triangle2= new Triangle(new Point(2.5,-1.7,1.2), new Point(2.5,-1.7,1),new Point(1.5,-2,1));
		manager.add(triangle2);
		triangle2.setColor(65535<<8);
//		triangle2.setReflection();

		
		Sphere l1 = new Sphere(new Point(-2,0,.8),.01);
		l1.setLight(1);
		manager.addLight(l1);

		Sphere l2 = new Sphere(new Point(2,2.5,0), .01);
		l2.setLight(1);
		manager.addLight(l2);
		Sphere l3 = new Sphere(new Point(2,-2.5,1), .01);
		l3.setLight(1);
		manager.addLight(l3);
//		System.out.println(line);
//		list.add(c2);
//		System.out.println(circle.intersect(line));
		// when draw, window and geometry should map
		Bmp pic = new Bmp();
		pic.setFile("he4.bmp");
		Trace trace = new Trace(manager);
		for (int ix=0;ix<Options.WIDTH;ix++){
			for (int iy=0;iy<Options.HEIGHT;iy++) {
				int intclr;
				intclr = trace.rayTracer(camera.getLineOfPixel(ix, iy), 0);
				pic.setPixel(ix, iy, Options.WHITE-intclr);
			}
			System.out.println(100*ix/(Options.WIDTH-1)+" %");
		}
		pic.write();
		
	}

}
