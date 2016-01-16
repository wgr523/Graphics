package geometry;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class Cube extends Base {
	public Point lo,hi;

	ArrayList<Triangle> list;
	public Cube() {
		// TODO Auto-generated constructor stub
		super();
		list=new ArrayList<Triangle>();
	}
	public Cube(Point origin, double scale, String file) {
		this( origin,  scale,  file, .8,.8,.8);
	}
	public Cube(Point origin, double scale, String file, double r, double g, double b) {
		// TODO Auto-generated constructor stub
		super();
		list=new ArrayList<>();
		ArrayList<Point> points=new ArrayList<>();
//		HashSet<Triangle> set = new HashSet<>();
		FileInputStream is;
		Scanner in;
		try {
			is = new FileInputStream(file);
			in = new Scanner(is);
			String readin;
			int t1,t2,t3;
			double d1,d2,d3;
			Point clr = new Point(r,g,b);
			while (in.hasNext()) {
				readin = in.next();
				if (readin.charAt(0)!='v' && readin.charAt(0)!='f') in.nextLine();
				else {
					if (readin.charAt(0)=='v') {
						d1=in.nextDouble();
						d2=in.nextDouble();
						d3=in.nextDouble();
						Point tmp = new Point(d1,d2,d3);
						tmp.times(scale);
						tmp.plus(origin);
						points.add(tmp);
					}
					if (readin.charAt(0)=='f') {
						t1=in.nextInt()-1;
						t2=in.nextInt()-1;
						t3=in.nextInt()-1;
						Triangle tmp= new Triangle(	points.get(t1),points.get(t2),points.get(t3) );
						tmp.setColor(clr);
						list.add(tmp);
//						set.add(tmp);
					}
				}
			}
			//				while((readin=in.readLine())!=null)
			//					System.out.println("Read: "+readin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		root = Kdtree.createKdtree(set, 0);
		calcHiLo();
		System.out.println("Create ok!");
		System.out.println(lo);
		System.out.println(hi);
	}
	public Cube(Point origin, double scale) {
		// TODO Auto-generated constructor stub
		super();
		Triangle [] t = new Triangle [8];
		Point [] p = new Point [6];
		p[0]=new Point(0,0,1);
		p[1]=new Point(0,-1,0);
		p[2]=new Point(-1,0,0);
		p[3]=new Point(0,1,0);
		p[4]=new Point(1,0,0);
		p[5]=new Point(0,0,-1);
		for (int i=0;i<6;i++) {p[i].times(scale);p[i].plus(origin);}
		t[0]=new Triangle(p[0], p[1], p[2]);
		t[1]=new Triangle(p[0], p[2], p[3]);
		t[2]=new Triangle(p[0], p[3], p[4]);
		t[3]=new Triangle(p[0], p[4], p[1]);
		t[4]=new Triangle(p[5], p[2], p[1]);
		t[5]=new Triangle(p[5], p[3], p[2]);
		t[6]=new Triangle(p[5], p[4], p[3]);
		t[7]=new Triangle(p[5], p[1], p[4]);
//		t[8]=new Triangle(p[0], p[2], p[3]);
//		t[9]=new Triangle(p[0], p[3], p[1]);
//		t[10]=new Triangle(p[4], p[7], p[6]);
//		t[11]=new Triangle(p[4], p[5], p[7]);

		list=new ArrayList<Triangle>();
		for (int i=0;i<8;i++)
			list.add(t[i]);
		calcHiLo();
		System.out.println(lo);
		System.out.println(hi);
	}
	void addTri(Triangle e) {
		list.add(e);
	}
	
	void calcHiLo() {
		double min[],max[];
		min=new double [3];
		max=new double [3];
		for (int i=0;i<3;i++) {min[i]=Double.POSITIVE_INFINITY;max[i]=Double.NEGATIVE_INFINITY;}
		for (Iterator<Triangle> it = list.iterator();it.hasNext();) {
			Triangle e = it.next();
			for (int i=0;i<3;i++) {
				if (e.x0.get(i) < min[i]) min[i]=e.x0.get(i);
				else
					if (e.x0.get(i) > max[i]) max[i]=e.x0.get(i);
				if (e.x1.get(i) < min[i]) min[i]=e.x1.get(i);
				else
					if (e.x1.get(i) > max[i]) max[i]=e.x1.get(i);
				if (e.x2.get(i) < min[i]) min[i]=e.x2.get(i);
				else
					if (e.x2.get(i) > max[i]) max[i]=e.x2.get(i);
			}
		}
		lo=new Point(min[0],min[1],min[2]);
		hi=new Point(max[0],max[1],max[2]);
	}
	
	@Override
	public T_Point_Obj_Normal intersect_Everyone(Line l) {
		// TODO Auto-generated method stub
//		if (! Kdtree.woo(lo, hi, l)) return new T_Point_Obj_Normal(0, null, null, null);
		T_Point_Obj_Normal tmp;
		double tmp2=Double.MAX_VALUE;
		Point ret1 = null;
		Base ret2 = null;
		Point normalret=null;
		for (Triangle e : list) {
			tmp = e.intersect_Everyone(l);
			if (tmp!=null && tmp.point!=null) {
				if (tmp2>Options.DOUBLE_EPS && tmp.t<tmp2) {
					tmp2=tmp.t;
					ret1 = tmp.point;
					ret2 = e;
					normalret = tmp.normal;
				}
			}
		}
		return new T_Point_Obj_Normal(tmp2, ret1, ret2, normalret);
	}

	@Override
	public Point getNormal(Point p) {
		// TODO Auto-generated method stub
		//		for (Triangle e : list) {
		//			Point ret = e.getNormal(p);
		//			if (ret!=null)
		//				return ret;
		//		}
		return null;
	}

	@Override
	public void setColor(double x1, double x2, double x3) {
		// TODO Auto-generated method stub
		for (Triangle e : list) {
			e.setColor(x1, x2, x3);
		}
	}

	@Override
	public void setColor(Point clr) {
		// TODO Auto-generated method stub
		for (Triangle e : list) {
			e.setColor(clr);
		}
	}
}
