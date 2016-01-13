package geometry;

import java.util.ArrayList;

public class GManager {
	public ArrayList<Base> list;
	public ArrayList<Base> lights;
	public GManager() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<Base>();
		lights = new ArrayList<Base>();
	}
	public void add(Base base) {
		list.add(base);
	}
	public void addLight(Base base) {
		list.add(base);
		lights.add(base);
	}
	public T_Point_Obj_Normal getIntersect(Line line) {
		T_Point_Obj_Normal tmp=null, ret=null;
		double tmp2=Double.MAX_VALUE;
		for (Base obj : list) {
			tmp = obj.intersect_Everyone(line);
			if (tmp!=null && tmp.point!=null) {
				if (tmp.t < tmp2) {
					tmp2=tmp.t;
					ret=tmp;
				}
			}
		}
		return ret;
	}
	/*public int getIntersectColor(Line line) {
		Point tmp;
		double tmp2=Double.MAX_VALUE;
		int ret = 0;
		for (Base obj : list) {
			tmp = obj.intersect(line);
			if (tmp!=null) {
				tmp.minus(line);
				double t= tmp.modulo2();
				if (t <tmp2) {
					tmp2=t;
					ret = obj.color;
				}
			}
		}
		return ret;
	}*/
}
