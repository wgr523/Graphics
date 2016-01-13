package geometry;


public abstract class Base {
	int color;
	boolean reflection, refraction,lighted;
	double snell;
	double light;
	double rhod,rhos,s;
	double rhoreflect,rhorefract;
	
	public Base() {
		// TODO Auto-generated constructor stub
		reflection=false;refraction=false;color=Options.WHITE;//color is white?
		light=0;lighted=false;
		rhod=.4;
		rhos=.3;
		s=6;
		snell=1.5;
	}
	public Base(int color, double rhod,double rhos,double s) {
		// TODO Auto-generated constructor stub
		reflection=false;refraction=false;this.color = color;//color is white?
		light=0;lighted=false;
		this.rhod=rhod;
		this.rhos=rhos;
		this.s=s;
		snell=1.5;
	}
	
	abstract public T_Point intersect_T (Line l);
	public Point intersect (Line l) {
		// only compute the first, outer side point. else return null
		T_Point tmp = intersect_T(l);
		if (tmp != null)
			return tmp.point;
		return null;
	}
	public Line refractIn (Line l, Point p) {
		double cos1 = (getNormal(p).inner(l.w));//note sgn problem
		double tmp1 = Math.sqrt(snell*snell+cos1*cos1-1)+cos1;
		Point ret = new Point(getNormal(p));
		ret.times(-tmp1);
		ret.plus(l.w);
		Point tmp2 = new Point(ret);
		tmp2.times(Options.DOUBLE_EPS);
		tmp2.plus(p);
		return new Line(tmp2, ret);
	}
	public Line refractOut (Line l, Point p) {
		double cos1 = (getNormal(p).inner(l.w));//note sgn problem
		double tmp1 = -snell*cos1-Math.sqrt(snell*snell*(cos1*cos1-1)+1);
		Point ret = new Point(getNormal(p));
		ret.times(-tmp1);
		ret.plus(l.w);
		Point tmp2 = new Point(ret);
		tmp2.times(Options.DOUBLE_EPS);
		tmp2.plus(p);
		return new Line(tmp2, ret);
	}
	public Line refract(Line l, Point p) {
		Line tmpl = refractIn(l, p);
		Point tmpp = intersect(tmpl);
		return refractOut(tmpl, tmpp);
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
	public void setSnell(double s) {
		snell = s;
	}
	public void setLight(double l) {
		light=l;lighted=true;
	}
	public void setReflection() {
		reflection=true;rhoreflect=.5;
	}
	public void setRefraction() {
		refraction=true;rhorefract=0.9;rhod=.1;rhos=.1;
	}
	public int getColor() {
		return color;
	}
	public int getColor(Point p) {
		return color;
	}
	public boolean isReflection() {
		return reflection;
	}
	public boolean isRefraction() {
		return refraction;
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
	public double getRhoreflect() {
		return rhoreflect;
	}
	public double getRhorefract() {
		return rhorefract;
	}
	public boolean isLighted() {
		// TODO Auto-generated method stub
		return lighted;
	}
	
}
