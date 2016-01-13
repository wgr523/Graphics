package geometry;

import bmp.Bmp;
import ray.Trace;

public class Graphics {

//	static ArrayList<Sphere> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GManager manager = new GManager();
		Camera camera = new CameraFocus();
		double licen=0.02;
		Point cen;// = new Point(3, 0, 0);

		Cube cuu = new Cube(new Point(5,-2,2));
		cuu.setColor(.2, 0,0);
		manager.add(cuu);
		Sphere circle;
		double raaa=0.8;
		for (int i=10;i>0;i--)
		{
			double xtmp=i;
			xtmp/=10;
			cen = new Point(1.5*(i-7), xtmp, xtmp+1);
			circle = new Sphere(cen,raaa,new Point(0,xtmp/5+.4,0),.2,.5, 2);
			raaa/=1.2;
			manager.add(circle);
		}
		cen = new Point(2.5,1.25,.2);
		circle = new Sphere(cen, 0.8,new Point(),.4,.5, 8);
		circle.setReflection();
		manager.add(circle);
		
		cen=new Point(2.5, -1, .2);
		Sphere circle2 = new Sphere(cen, 0.8);
		circle2.setRefraction();
		manager.add(circle2);
		Triangle triangle1= new Triangle( new Point(-3.0,-2.0,-1.0),new Point(-3.0,-2.0,3.5),new Point(2.6,-2.0,-3.5));
		triangle1.setColor(1,1,0);
//		manager.add(triangle1);
		Triangle triangle2= new Triangle(new Point(-3.0,-2.0,3.5), new Point(2.6,-2.0,3.5),new Point(2.6,-2.0,-3.5));
//		triangle2.setColor(65535<<8);
//		manager.add(triangle2);
		Triangle triangle3= new Triangle(new Point(2.6,2.0,3.5), new Point(2.6,-2.0,3.5),  new Point(2.6,2.0,-3.5));
//		triangle3.setColor(65535<<7);
//		triangle3.setReflection();
//		manager.add(triangle3);
		Triangle triangle4= new Triangle( new Point(2.6,-2.0,3.5), new Point(2.6,-2.0,-3.5), new Point(2.6,2.0,-3.5));
//		triangle4.setColor(65535<<6);
//		triangle4.setReflection();
//		manager.add(triangle4);
		Plane plane1 = new BWPlane(new Point(0,0,1), 10);
		manager.add(plane1);
		Plane plane2 = new Plane(new Point(0,-1,0), 10);
		manager.add(plane2);
		plane2.setColor(0,0,1);
		
		Sphere l1;
		for (int i=-16;i<-9;i+=2)
		{
			l1 = new Sphere(new Point(i,0,4),licen*10);
			l1.setLight(1);
			manager.addLight(l1);
		}

		Sphere l2 = new Sphere(new Point(-3,1.9,0), licen);
		l2.setLight(1);
		manager.addLight(l2);
		l2 = new Sphere(new Point(-13,0.3,0), licen);
		l2.setLight(1);
		manager.addLight(l2);
		l2 = new Sphere(new Point(4.8,2.5,2.3), licen*2);
		l2.setLight(1);
		manager.addLight(l2);
		Sphere l3 = new Sphere(new Point(2,-1.3,1.5), licen);
		l3.setLight(1);
		manager.addLight(l3);
//		System.out.println(line);
//		list.add(c2);
//		System.out.println(circle.intersect(line));
		// when draw, window and geometry should map
		Bmp pic = new Bmp();
		pic.setFile("he10.bmp");
		Trace trace = new Trace(manager,camera);
		trace.workOutPicture(pic);
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
