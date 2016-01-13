package geometry;

public class Triangle extends Base {
	Double d;
	Point n;
	Point x0,e1,e2;
	
	public Triangle(Point x0,Point x1,Point x2) {
		this.x0 = x0;
		Point tmp = new Point(x0);
		tmp.minus(x1);
		this.e1 = tmp;
		tmp = new Point(x0);
		tmp.minus(x2);
		this.e2 = tmp;
		n = new Point(
				e1.z*e2.y-e1.y*e2.z, 
				e2.x*e1.z-e2.z*e1.x, 
				e1.x*e2.y-e1.y*e2.x).normalize();
		d = -n.inner(this.x0);
	}
	
	@Override
	public T_Point_Obj_Normal intersect_Everyone(Line l) {
		// TODO Auto-generated method stub
		double div = Options.deterministic(l.w, e1, e2);
		Point s= new Point(x0);
		s.minus(l);
//		double ret = -(n.inner(l)+d)/(n.inner(l.w));
//		if (ret<Options.DOUBLE_EPS) return null;
		double a1,a2,a3;
		a1=Options.deterministic(s, e1, e2)/div;
		a2=Options.deterministic(l.w, s, e2)/div;
		a3=Options.deterministic(l.w, e1, s)/div;
		if (a1<Options.DOUBLE_EPS || a2<0 ||a3<0 ||a2>1||a3>1||a2+a3>1) return null;
		Point tmp = new Point(l.w);
		tmp.times(a1);
		tmp.plus(l);
		return new T_Point_Obj_Normal(a1, tmp, this, getNormal(tmp));
	}

	@Override
	public Point getNormal(Point p) {
		// TODO Auto-generated method stub
		if (n.inner(p)+d>Options.DOUBLE_EPS) return null;
		return new Point(n);
	}
	
}
