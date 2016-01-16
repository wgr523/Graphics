package geometry;
//
//import java.io.*;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Scanner;
//
public class Tmp {
//
	public static void main(String[] args) {
//		Base block = new Cube(new Point(0, 0 ,0), 1, ".obj",0 , 0, 1);
		Base block = new Cube(new Point(0, 0 ,0), 1);
		Line tmpline = new Line(new Point(.2,.3,0.1), 0,-1,0);
		T_Point_Obj_Normal tm = block.intersect_Everyone(tmpline) ;
		if (tm!=null) {
			System.out.println( tm.t);
			System.out.println( tm.obj);
			System.out.println( tm.point);
			System.out.println( tm.normal);
		}
//		//		HashSet<Triangle> set = new HashSet<>();
////		
//////		Triangle [] t = new Triangle [12];
////		Point [] p = new Point [8];
////		int cnt=0;
////		for (int x3=0;x3<2;x3++)
////			for (int x2=0;x2<2;x2++)
////				for (int x1=0;x1<2;x1++) {
////					p[cnt] = new Point(x1,x2,x3);
////					cnt++;
////				}
////		set.add(new Triangle(p[0], p[1], p[5]));
////		set.add(new Triangle(p[0], p[5], p[4]));
////		set.add(new Triangle(p[1], p[3], p[7]));
////		set.add(new Triangle(p[1], p[7], p[5]));
////		set.add(new Triangle(p[3], p[2], p[6]));
////		set.add(new Triangle(p[3], p[6], p[7]));
////		set.add(new Triangle(p[2], p[6], p[4]));
////		set.add(new Triangle(p[0], p[2], p[4]));
////		set.add(new Triangle(p[0], p[2], p[3]));
////		set.add(new Triangle(p[0], p[3], p[1]));
////		set.add(new Triangle(p[4], p[7], p[6]));
////		set.add(new Triangle(p[4], p[5], p[7]));
////		Kdtree treee = Kdtree.createKdtree(set, 0);
////		System.out.println("Create ok!");
////		Complex co = new Complex(new Point(), .1, "block.obj");
////		Line tmpline = new Line(new Point(-1,-1,-1), 1,1,1);
////		System.out.println( co.intersect(tmpline) );
////		T_Point_Obj_Normal oo = treee.getIntersect(tmpline);
////		System.out.println( oo==null?null:oo.point );
////		Point inter1 = new Point();
////		Point inter2 = new Point(1,2,31);
////		Point inter3 = new Point();
////		set.add(inter1);set.add(inter1);set.add(inter1);set.add(inter2);
////		System.out.println(set.remove(inter3));
////		System.out.println(treee.lo);
////		System.out.println(treee.hi);
//
//		
//
//		// TODO Auto-generated method stub
////		Cube cuu = new Cube(new Point(0,-1,0));
////		cuu.setColor(1, 0,0);
//		
//
////		Plane plane = new BWPlane(new Point(0, 0,1),0);
////		System.out.println(plane.getNormal(new Point()));
////		System.out.println(cuu.intersect(tmpline));
////		System.out.println(cuu.intersect_Everyone(tmpline).normal);
//		/*
//		Sphere or = new Sphere(new Point(), 1);
//		Line r1 = or.refractIn(tmpline,or.intersect(tmpline));
//		System.out.println(or.getNormal(or.intersect(tmpline)));
//		System.out.println(r1);
//		Point inter2 = or.intersect(r1);
//		System.out.println(inter2);
//		System.out.println(or.refractOut(r1, inter2));*/
//
////		Triangle tt= new Triangle( new Point(0,0,-1),new Point(-1,0,0),new Point(1,0,1));
////		System.out.println(tt.intersect(tmpline));
////		System.out.println(tt.getNormal(new Point()));
////		System.out.println(tt.reflect(tmpline, tt.intersect(tmpline)));
////		System.out.println(or.getNormal(or.intersect(tmpline)));
////		System.out.println(Options.deterministic(new Point(-1,100,4), new Point(9,1,-1),new Point(1,17,1)));
////		System.out.println(or.reflect(tmpline, or.intersect(tmpline)));
	}
//
}
