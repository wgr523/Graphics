package geometry;

//import java.io.IOException;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Kdtree {
	public int splitDirction;
	public double splitPlane;
	public Kdtree left,right;
	Point lo,hi;
	Collection<Triangle> list;
	static final int MinNum=16;
	static final int MaxDep=12;
	
	static Kdtree createKdtree(Collection<Triangle> p, int depth) {
		if (p.size()<MinNum || depth > MaxDep)
			return new Kdtree(p);
		Kdtree root = new Kdtree();
		int i=depth%3;
		root.splitDirction=i;
		root.calcHiLo(p);
//		System.out.println("!");
		root.splitPlane=(root.lo.get(i)+root.hi.get(i))/2;
		Collection<Triangle> p1,p2;
		p1=new HashSet<>();p2=new HashSet<>();
		for (Iterator<Triangle> it = p.iterator();it.hasNext();) {
			Triangle e = it.next();
			boolean addLeft=false, addRight=false;
			if (e.x0.get(i) <= root.splitPlane) addLeft=true; // eps?
			if (e.x0.get(i) >= root.splitPlane) addRight=true;
			if (e.x1.get(i) <= root.splitPlane) addLeft=true;
			if (e.x1.get(i) >= root.splitPlane) addRight=true;
			if (e.x2.get(i) <= root.splitPlane) addLeft=true;
			if (e.x2.get(i) >= root.splitPlane) addRight=true;
			if (addLeft) p1.add(e);
			if (addRight) p2.add(e);
		}
//		System.out.println(p1.size());
//		System.out.println(p2.size());
//		System.out.println();

//		if (!p1.isEmpty())
		root.left = createKdtree(p1, depth+1);
//		if (!p2.isEmpty())
		root.right = createKdtree(p2, depth+1);
		return root;
	}
//			else {
//				if (addRight && !addLeft) p2.add(e);
//				else {
//					root.list.add(e);
//				}
//			}
		
//		System.out.println(root.list.size());
//		System.out.println(root.lo);
//		System.out.println(root.hi);
//		try {
//			System.in.read();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		root.calcSelfHiLo();
	
	public Kdtree() {list=new HashSet<>();}
	public Kdtree(Collection<Triangle> list) {//create a leaf
		left=null;right=null;
		this.list=new HashSet<>(list);
		calcHiLo(list);
	}
	void calcHiLo(Collection<Triangle> list) {
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
//	void calcSelfHiLo() {
//		double min[],max[];
//		min=new double[3];
//		max=new double [3];
//		for (int i=0;i<3;i++) {min[i]=Double.POSITIVE_INFINITY;max[i]=Double.NEGATIVE_INFINITY;}
//		for (Iterator<Triangle> it = list.iterator();it.hasNext();) {
//			Triangle e = it.next();
//			for (int i=0;i<3;i++) {
//				if (e.x0.get(i) < min[i]) min[i]=e.x0.get(i);
//				else
//					if (e.x0.get(i) > max[i]) max[i]=e.x0.get(i);
//				if (e.x1.get(i) < min[i]) min[i]=e.x1.get(i);
//				else
//					if (e.x1.get(i) > max[i]) max[i]=e.x1.get(i);
//				if (e.x2.get(i) < min[i]) min[i]=e.x2.get(i);
//				else
//					if (e.x2.get(i) > max[i]) max[i]=e.x2.get(i);
//			}
//		}
//		selflo=new Point(min[0],min[1],min[2]);
//		selfhi=new Point(max[0],max[1],max[2]);
//	}
	
	public boolean isLeaf() {
		return (left==null)&&(right==null);
	}
	
	public Collection<Triangle> getTriangle() {
		if (!isLeaf()) return null;
		return list;
	}
	
	public T_Point_Obj_Normal getIntersect(Line line) {
		if (!haines(lo, hi, line)) return null;
		if (isLeaf()) return nearestIntersect(list, line);
		T_Point_Obj_Normal lef=null,rig=null;
		int i=splitDirction;
		if ( 1.0 / line.w.get(i) >=0 )
		{
			lef = left.getIntersect(line);
			if (lef == null || lef.point == null) return right.getIntersect(line);
			return lef;
		}
		else {
			rig = right.getIntersect(line);
			if (rig == null || rig.point == null) return left.getIntersect(line);
			return rig;
		}
	}
//			lef = left==null ? null : left.getIntersect(line);
//			if (left == null || lef == null || lef.point == null) {
//				if (mid == null || mid.point == null)
//					return right==null ? null : right.getIntersect(line);
//				else
//					return mid;
//			}
//			else {
//				if (mid == null || mid.point == null)
//					return lef;
//				else {
//					return lef.t < mid.t ? lef : mid;
//				}
//			}
//		}
//		else {
//			rig = right==null ? null : right.getIntersect(line);
//			if (rig == null || rig.point == null) {
//				if (mid == null || mid.point == null)
//					return left==null ? null : left.getIntersect(line);
//				else
//					return mid;
//			}
//			else {
//				if (mid == null || mid.point == null)
//					return rig;
//				else {
//					return rig.t < mid.t ? rig : mid;
//				}
//			}
//		}
//	}
	public static T_Point_Obj_Normal nearestIntersect(Collection<Triangle> list, Line line) {
		T_Point_Obj_Normal tmp=null, ret=null;
		double tmp2=Double.MAX_VALUE;
		for (Iterator<Triangle> it = list.iterator();it.hasNext();) {
			Triangle obj = it.next();
			tmp = obj.intersect_Everyone(line);
			if (tmp!=null && tmp.point!=null) {
				if (tmp.t < tmp2) {
					tmp2=tmp.t;
					ret=tmp;
				}
			}
		}
//		if (ret!=null && ret.normal==null) System.out.println("Jieshi");
		return ret;
	}
	public static boolean isInside(Point lo, Point hi, Point point) {
		if (point==null || lo==null || hi==null) return false;
		boolean ret = true;
		for (int i=0;i<3;i++) {
			if (point.get(i) < lo.get(i)-Options.DOUBLE_EPS || point.get(i) > hi.get(i)+Options.DOUBLE_EPS) {
				ret = false;break;
			}
		}
		return ret;
	}
	public static double findmax(double d1,double d2,double d3) {
		if (d1>d2) {
			if (d1>d3) return d1;
			else return d3;
		}
		else {
			if (d2>d3) return d2;
			else return d3;
		}
	}
	public static double findmin(double d1,double d2,double d3) {
		if (d1<d2) {
			if (d1<d3) return d1;
			else return d3;
		}
		else {
			if (d2<d3) return d2;
			else return d3;
		}
	}
	
	public static boolean haines (Point lo, Point hi, Line line, double t0, double t1) {
		double tmin[],tmax[];
		tmin=new double [3];tmax=new double [3];
		if (1.0 / line.w.x>=0) {
			tmin[0]=(lo.x-line.x) / line.w.x;
			tmax[0]=(hi.x-line.x) / line.w.x;
		}
		else {
			tmin[0]=(hi.x-line.x) / line.w.x;
			tmax[0]=(lo.x-line.x) / line.w.x;
		}
		if (1.0 / line.w.y>=0) {
			tmin[1]=(lo.y-line.y) / line.w.y;
			tmax[1]=(hi.y-line.y) / line.w.y;
		}
		else {
			tmin[1]=(hi.y-line.y) / line.w.y;
			tmax[1]=(lo.y-line.y) / line.w.y;
		}
		if ( (tmin[0]>tmax[1]) || (tmin[1]>tmax[0]))
			return false;
		if (tmin[1]>tmin[0]) tmin[0]=tmin[1];
		if (tmax[1]<tmax[0]) tmax[0]=tmax[1];
		if (1.0 / line.w.z>=0) {
			tmin[2]=(lo.z-line.z) / line.w.z;
			tmax[2]=(hi.z-line.z) / line.w.z;
		}
		else {
			tmin[2]=(hi.z-line.z) / line.w.z;
			tmax[2]=(lo.z-line.z) / line.w.z;
		}
		if ( (tmin[0]>tmax[2]) || (tmin[2]>tmax[0]))
			return false;
		if (tmin[2]>tmin[0]) tmin[0]=tmin[2];
		if (tmax[2]<tmax[0]) tmax[0]=tmax[2];
		return ( (tmin[0] < t1 ) && (tmax[0] > t0));
	}
	
	public static boolean haines (Point lo, Point hi, Line line) {
		return haines(lo, hi, line, 0, Double.POSITIVE_INFINITY);
	}
//		T_Point_Obj_Normal tmp2;
//		Plane plane;
//		for (int i=0;i<3;i++) {
//			plane = new Plane(Options.AXIS(i), -lo.get(i));
//			tmp2 = plane.intersect_Everyone(line);
//			if (tmp2==null || tmp2.point==null) tmin[i]=Double.NEGATIVE_INFINITY;
//			else tmin[i]=tmp2.t;
//			plane = new Plane(Options.AXIS(i), -hi.get(i));
//			tmp2 = plane.intersect_Everyone(line);
//			if (tmp2==null || tmp2.point==null) tmax[i]=Double.NEGATIVE_INFINITY;
//			else tmax[i]=tmp2.t;
//		}
//	}
	/*
	 * public static boolean woo (Point lo, Point hi, Line line) {
		if (line==null || lo==null || hi==null) return false;
		if (isInside(lo, hi, line)) return true;
		double use[] = new double [3];
		double t = Double.NEGATIVE_INFINITY;
		Point ret=null;
		for (int i=0;i<3;i++) {
			if (Math.abs(lo.get(i)-line.get(i))<Math.abs(hi.get(i)-line.get(i)))
				use[i]=lo.get(i);
			else
				use[i]=hi.get(i);
			Plane tmp = new Plane(Options.AXIS(i), -use[i]);
			T_Point_Obj_Normal tmp2 = tmp.intersect_Everyone(line);
			if (tmp2==null || tmp2.point==null) continue;
			if (tmp2.t > t ) {
				t=tmp2.t;
				ret=tmp2.point;
			}
		}
		return t<0?false : isInside(lo, hi, ret);
	}
	*/
}
