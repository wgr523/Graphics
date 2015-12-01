package geometry;


public abstract class Base {
	int color;
	boolean reflection, deflection,lighted;
	double snell;
	double light;
	double rhod,rhos,s;
	
	public Base() {
		// TODO Auto-generated constructor stub
		reflection=false;deflection=false;color=Options.WHITE;//color is white?
		light=0;lighted=false;
		rhod=.4;
		rhos=.3;
		s=6;
	}
	public Base(int color, double rhod,double rhos,double s) {
		// TODO Auto-generated constructor stub
		reflection=false;deflection=false;this.color = color;//color is white?
		light=0;lighted=false;
		this.rhod=rhod;
		this.rhos=rhos;
		this.s=s;
	}
	
	abstract public T_Point intersect_T (Line l);
	public Point intersect (Line l) {
		// only compute the first, outer side point. else return null
		T_Point tmp = intersect_T(l);
		if (tmp != null)
			return tmp.point;
		return null;
	}
	
	abstract public Point getNormal(Point p);
	
	public Line reflect(Line l, Point p) {
		// TODO Auto-generated method stub
		if (p==null || l==null) return null;
		Point point = getNormal(p);
		Point distant = new Point(p);
		distant.minus(l);
		double dis = point.inner(distant);
		point.times(-2*dis);
		point.plus(distant);
		Point tmp = new Point(point);
		tmp.times(Options.DOUBLE_EPS);
		tmp.plus(p);
		return new Line(tmp, point);
	}
	
	public double normalInner(Line l, Point p) {
		// TODO Auto-generated method stub
		if (p==null || l==null) return 0;
		Point point = getNormal(p);
		return Math.abs(point.inner(l.w));
	}
	
	public void setColor(int clr) {
		color = clr;
	}
	public void setLight(double l) {
		light=l;lighted=true;
	}
	public void setReflection() {
		reflection=true;
	}
	public int getColor() {
		return color;
	}
	public boolean isReflection() {
		return reflection;
	}
	public boolean isDeflection() {
		return deflection;
	}
	public double getLight() {
		return light;
	}
	public double getRhod() {
		return rhod;
	}
	public double getRhos() {
		return rhos;
	}
	public double getS() {
		return s;
	}
	public boolean isLighted() {
		// TODO Auto-generated method stub
		return lighted;
	}
	
}
