package geometry;

import java.util.ArrayList;

public class Cube extends Base {

	ArrayList<Triangle> list;
	public Cube() {
		// TODO Auto-generated constructor stub
		super();
		list=new ArrayList<Triangle>();
	}
	public Cube(Point origin) {
		// TODO Auto-generated constructor stub
		super();
		Triangle [] t = new Triangle [12];
		Point [] p = new Point [8];
		int cnt=0;
		for (int x3=0;x3<2;x3++)
			for (int x2=0;x2<2;x2++)
				for (int x1=0;x1<2;x1++) {
					p[cnt] = new Point(x1,x2,x3);
					p[cnt].plus(origin);
					cnt++;
				}
		t[0]=new Triangle(p[0], p[1], p[5]);
		t[1]=new Triangle(p[0], p[5], p[4]);
		t[2]=new Triangle(p[1], p[3], p[7]);
		t[3]=new Triangle(p[1], p[7], p[5]);
		t[4]=new Triangle(p[3], p[2], p[6]);
		t[5]=new Triangle(p[3], p[6], p[7]);
		t[6]=new Triangle(p[2], p[6], p[4]);
		t[7]=new Triangle(p[0], p[2], p[4]);
		t[8]=new Triangle(p[0], p[2], p[3]);
		t[9]=new Triangle(p[0], p[3], p[1]);
		t[10]=new Triangle(p[4], p[7], p[6]);
		t[11]=new Triangle(p[4], p[5], p[7]);

		list=new ArrayList<Triangle>();
		for (int i=0;i<12;i++)
			list.add(t[i]);
	}
	void addTri(Triangle e) {
		list.add(e);
	}
	
	@Override
	public T_Point_Obj_Normal intersect_Everyone(Line l) {
		// TODO Auto-generated method stub
		T_Point_Obj_Normal tmp;
		double tmp2=Double.MAX_VALUE;
		Point ret1 = null;
		Base ret2 = null;
		Point normalret=null;
		for (Triangle e : list) {
			tmp = e.intersect_Everyone(l);
			if (tmp!=null && tmp.point!=null) {
				if (tmp.t<tmp2) {
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
