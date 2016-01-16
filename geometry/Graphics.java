package geometry;

import bmp.Bmp;
import ray.Trace;

public class Graphics {

//	static ArrayList<Sphere> list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GManager manager = new GManager();
		Camera camera = new Camera();
		double licen=0.6;
		Point cen;// = new Point(3, 0, 0);

//		Cube cuu = new Cube(new Point(5,-2,2));
//		cuu.setColor(.2, .1,.1);
//		cuu.setReflection();
//		manager.add(cuu);
		
//		double raaa=0.8;
//		for (int i=10;i>0;i--)
//		{
//			double xtmp=i;
//			xtmp/=10;
//			cen = new Point(1.5*(i-7), xtmp, xtmp+1);
//			circle = new Sphere(cen,raaa,new Point(0,xtmp/5+.4,0),.2,.5, 2);
//			raaa/=1.2;
//			manager.add(circle);
//		}
		Sphere circle;
		cen = new Point(2.5,1.5,.1);
		circle = new Sphere(cen, 0.8,new Point(.1,.1,.1),.4,.5, 8);
		circle.setReflection(.7);
		manager.add(circle);
		cen = new Point(3,0,.6);
		circle = new Sphere(cen, 0.3,new Point(1,0,0),.09,.04, 16);
		circle.setReflection(.08);
		manager.add(circle);
		cen=new Point(2.5, -1.5, .1);
		Sphere circle2 = new Sphere(cen, 0.8);
		circle2.setRefraction();
		manager.add(circle2);
		
//		Triangle triangle1= new Triangle( new Point(-3.0,-2.0,-1.0),new Point(-3.0,-2.0,3.5),new Point(2.6,-2.0,-3.5));
//		triangle1.setColor(1,1,0);
//		manager.add(triangle1);
//		Triangle triangle2= new Triangle(new Point(-3.0,-2.0,3.5), new Point(2.6,-2.0,3.5),new Point(2.6,-2.0,-3.5));
////		triangle2.setColor(65535<<8);
//		manager.add(triangle2);
//		Triangle triangle3= new Triangle(new Point(2.6,2.0,3.5), new Point(2.6,-2.0,3.5),  new Point(2.6,2.0,-3.5));
////		triangle3.setColor(65535<<7);
//		triangle3.setReflection();
//		manager.add(triangle3);
//		Triangle triangle4= new Triangle( new Point(2.6,-2.0,3.5), new Point(2.6,-2.0,-3.5), new Point(2.6,2.0,-3.5));
////		triangle4.setColor(65535<<6);
//		triangle4.setReflection();
//		manager.add(triangle4);
		Plane plane1 = new BWPlane(new Point(0,0,1), .71);
		manager.add(plane1);
		
		Plane plane2;
		plane2= new Plane(new Point(0,-1,0), 5);
		plane2.setColor(0,0,1);
		plane2.setReflection(.8);
		manager.add(plane2);
		
//		plane2= new Plane(new Point(-1,0,0), 10);
//		plane2.setColor(0,.1,0);
//		plane2.setReflection(1);
//		manager.add(plane2);

		plane2= new Plane(new Point(0,1,0), 5);
		manager.add(plane2);
		plane2.setColor(0,0,1);
		plane2.setReflection(0.8);
		
		Base block = new Complex(new Point(0, 0 ,0), 1, "dragon.obj",0 , 0, 1);
//		block.setReflectionStill(.6);
		manager.add(block);
//		Base cuu = new Cube(new Point(0,2,0), .3);
//		manager.add(cuu);
//		Sphere l1;
//		for (int i=-16;i<-9;i+=2)
//		{
//			l1 = new Sphere(new Point(i,0,4),licen*10);
//			l1.setLight(1);
//			manager.addLight(l1);
//		}

		Sphere l2 = new Sphere(new Point(-3,0,2.8), licen);
		l2.setLight(1);
		manager.addLight(l2);
//		l2 = new Sphere(new Point(-13,0.3,0), licen);
//		l2.setLight(1);
//		manager.addLight(l2);
		l2 = new Sphere(new Point(2,0,3.3), licen);
		l2.setLight(1);
		manager.addLight(l2);
//		Sphere l3 = new Sphere(new Point(2,-.3,3.5), licen);
//		l3.setLight(1);
//		manager.addLight(l3);
//		System.out.println(line);
//		list.add(c2);
//		System.out.println(circle.intersect(line));

		Bmp pic = new Bmp();
		pic.setFile("final.bmp");
		Trace trace = new Trace(manager,camera);
		trace.workOutPictureAA(pic);
		pic.write();
		
//		l2.setLight(.1);
//		pic = new Bmp();
//		pic.setFile("she.bmp");
//		camera = new Camera(2);
//		trace = new Trace(manager,camera);
//		trace.workOutPicture(pic);
//		pic.write();
	}

}
