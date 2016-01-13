package geometry;

import bmp.Bmp;
import ray.Trace;

public class Graphics {

//	static ArrayList<Sphere> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GManager manager = new GManager();
		Camera camera = new Camera(1);
		
		Point c = new Point(2.5,1.25,.2);
		Sphere circle = new Sphere(c, 0.8,(200<<16)+(100<<8)+4,.4,.2, 8);
		circle.setReflection();
		manager.add(circle);
		Point c2=new Point(2.5, -1, .2);
		Sphere circle2 = new Sphere(c2, 0.8);
//		circle2.setColor(65535);
		circle2.setRefraction();
		manager.add(circle2);
		Triangle triangle1= new Triangle( new Point(-3.0,-2.0,-1.0),new Point(-3.0,-2.0,3.5),new Point(2.6,-2.0,-3.5));
		triangle1.setColor(65535);
//		manager.add(triangle1);
		Triangle triangle2= new Triangle(new Point(-3.0,-2.0,3.5), new Point(2.6,-2.0,3.5),new Point(2.6,-2.0,-3.5));
		triangle2.setColor(65535<<8);
//		manager.add(triangle2);
		Triangle triangle3= new Triangle(new Point(2.6,2.0,3.5), new Point(2.6,-2.0,3.5),  new Point(2.6,2.0,-3.5));
		triangle3.setColor(65535<<7);
//		triangle3.setReflection();
//		manager.add(triangle3);
		Triangle triangle4= new Triangle( new Point(2.6,-2.0,3.5), new Point(2.6,-2.0,-3.5), new Point(2.6,2.0,-3.5));
		triangle4.setColor(65535<<6);
//		triangle4.setReflection();
//		manager.add(triangle4);
		Plane plane1 = new BWPlane(new Point(0,0,1), 10);
		manager.add(plane1);
		Plane plane2 = new Plane(new Point(0,-1,0), 10);
		manager.add(plane2);
		plane2.setColor(255);
		
		Sphere l1 = new Sphere(new Point(2.5,0,.8),.02);
		l1.setLight(1);
		manager.addLight(l1);

		Sphere l2 = new Sphere(new Point(2,1.9,0), .02);
		l2.setLight(1);
		manager.addLight(l2);
		Sphere l3 = new Sphere(new Point(2,-1.3,1.5), .02);
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
		
		/*pic = new Bmp();
		pic.setFile("he5.bmp");
		camera = new Camera(2);
		for (int ix=0;ix<Options.WIDTH;ix++){
			for (int iy=0;iy<Options.HEIGHT;iy++) {
				int intclr;
				intclr = trace.rayTracer(camera.getLineOfPixel(ix, iy), 0);
				pic.setPixel(ix, iy, Options.WHITE-intclr);
			}
			System.out.println(100*ix/(Options.WIDTH-1)+" %");
		}
		pic.write();*/
		
	}

}
