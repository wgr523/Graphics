package geometry;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Complex extends Base {

//	ArrayList<Triangle> list;
	Kdtree root;
	public Complex() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Complex(Point origin, double scale, String file, double r, double g, double b) {
		// TODO Auto-generated constructor stub
		super();
//		list=new ArrayList<>();
		ArrayList<Point> points;
		points=new ArrayList<>();
		HashSet<Triangle> set = new HashSet<>();
		FileInputStream is;
		Scanner in;
		try {
			is = new FileInputStream(file);
			in = new Scanner(is);
			String readin;
			int t1,t2,t3;
			double d1,d2,d3;
//			Point clr = new Point(r,g,b);
			while (in.hasNext()) {
				readin = in.next();
				if (readin.charAt(0)=='#') in.nextLine();
				else {
					if (readin.charAt(0)=='v') {
						d1=in.nextDouble();
						d2=in.nextDouble();
						d3=in.nextDouble();
						Point tmp = new Point(d3,d1,d2);
						tmp.times(scale);
						tmp.plus(origin);
						points.add(tmp);
					}
					if (readin.charAt(0)=='f') {
						t1=in.nextInt()-1;
						t2=in.nextInt()-1;
						t3=in.nextInt()-1;
						Triangle tmp= new Triangle(points.get(t1),points.get(t2),points.get(t3) );
//						list.add(tmp);
						tmp.setReflectionStill(.3);
						tmp.setColor(r, g, b);
//						tmp.setColor(clr);
						set.add(tmp);
					}
				}
			}
//			while((readin=in.readLine())!=null)
//				System.out.println("Read: "+readin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root = Kdtree.createKdtree(set, 0);
		System.out.println(root.lo);
		System.out.println(root.hi);
		System.out.println("Create kd tree ok!");
	}
	
	@Override
	public T_Point_Obj_Normal intersect_Everyone(Line l) {
		// TODO Auto-generated method stub
		return root.getIntersect(l);
	}

	@Override
	public Point getNormal(Point p) {
		// TODO Auto-generated method stub
//		for (Triangle e : list) {
//			Point ret = e.getNormal(p);
//			if (ret!=null)
//				return ret;
//		}
		System.out.println("Shouldn't ask this obj!!!");
		return null;
	}

	@Override
	public void setColor(double x1, double x2, double x3) {
		// TODO Auto-generated method stub
//		for (Triangle e : list) {
//			e.setColor(x1, x2, x3);
//		}
	}
	
	@Override
	public void setColor(Point clr) {
		// TODO Auto-generated method stub
//		for (Triangle e : list) {
//			e.setColor(clr);
//		}
	}
	
}
