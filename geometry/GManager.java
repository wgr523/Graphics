package geometry;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class GManager {
	ArrayList<Base> list;
	ArrayList<Base> lights;
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
	public T_Point_Obj_Normal getIntersect(Line line, int i) {
		T_Point_Obj_Normal tmp=null, ret=null;
		double tmp2=Double.MAX_VALUE;
		for (Iterator<Base> it = list.iterator();it.hasNext();) {
			Base obj = it.next();
			tmp = obj.intersect_Everyone(line);
			if (tmp!=null && tmp.point!=null) {
				if (tmp2>Options.DOUBLE_EPS && tmp.t < tmp2) {
					tmp2=tmp.t;
					ret=tmp;
				}
			}
		}
		return ret;
	}
	public List<Base> getList(int i){
		return list;
	}
	public List<Base> getLights(int i){
		return lights;
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
