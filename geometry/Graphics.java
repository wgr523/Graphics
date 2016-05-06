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
		Point cen;

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
		
		Plane plane1 = new BWPlane(new Point(0,0,1), .71);
		manager.add(plane1);
		
		Plane plane2;
		plane2= new Plane(new Point(0,-1,0), 5);
		plane2.setColor(0,0,1);
		plane2.setReflection(.8);
		manager.add(plane2);
		

		plane2= new Plane(new Point(0,1,0), 5);
		manager.add(plane2);
		plane2.setColor(0,0,1);
		plane2.setReflection(0.8);
		
		Base block = new Complex(new Point(0, 0 ,0), 1, "dragon.obj",0 , 0, 1);
		manager.add(block);

		Sphere l2 = new Sphere(new Point(-3,0,2.8), licen);
		l2.setLight(1);
		manager.addLight(l2);
//		l2 = new Sphere(new Point(-13,0.3,0), licen);
//		l2.setLight(1);
//		manager.addLight(l2);
		l2 = new Sphere(new Point(2,0,3.3), licen);
		l2.setLight(1);
		manager.addLight(l2);

		Bmp pic = new Bmp();
		pic.setFile("out.bmp");
		Trace trace = new Trace(manager,camera);
		trace.workOutPictureAA(pic);
		pic.write();
		
	}

}
