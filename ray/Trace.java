package ray;

import geometry.Base;
import geometry.Base_Point;
import geometry.GManager;
import geometry.Line;
import geometry.Options;
import geometry.Point;
import geometry.Sphere;

public class Trace {
	GManager manager;
	public Trace(GManager manager) {
		// TODO Auto-generated constructor stub
		this.manager = manager;
	}
	public int rayTracer(Line line, int depth){//, Integer color) {
		if (depth>Options.MAXDEPTH) {
			return Options.BACKGROUND;
		} //background color, over depth and no intersection
		Base_Point ret = manager.getIntersect(line);
		if (ret == null || ret.point == null) {return Options.BACKGROUND;}//background color, no intersection at all
		else {
			if (ret.obj.isLighted()) {
				//				 乘以光强度(1 or other double)。
//				System.out.println("Guang!");
//				return Options.WHITE;
				return Options.colorTimes(ret.obj.getColor(),ret.obj.getLight());
//				color = Options.colorPlus(color , Options.colorTimes(ret.obj.getColor(),ret.obj.getLight()));
			}
			int color = Options.colorTimes(ret.obj.getColor(), Options.AMBIENT);
			Point norm = ret.obj.getNormal(ret.point); // normal vec
			for (Base baselight : manager.lights) 
			if (baselight instanceof Sphere){
				Sphere light = (Sphere) baselight;
				Point lc = new Point(light.getCenter());
				lc.minus(ret.point);
				Point tmp = new Point(norm);
				tmp.times(Options.DOUBLE_EPS);
				tmp.plus(ret.point);
				Line tolight = new Line(tmp, lc);
				Base_Point shed = manager.getIntersect(tolight);
//				double rhos=ret.obj.getRhos();
//				double s=ret.obj.getS();
				if (shed != null && shed.obj!=null && shed.obj.equals(light)) {
//					System.out.println("Mei you dang guang!!");
					color = Options.colorPlus(color , 
							Options.colorInner( ret.obj.getColor(),
							Options.colorTimes(shed.obj.getColor(),
									tolight.w.inner(norm)*
									ret.obj.getRhod()*
									shed.obj.getLight())));
//					if (tolight.w.inner(norm)*
//					Model.S(tolight.w, line.w, norm, ret.obj.getS(), ret.obj.getRhos())*
//					shed.obj.getLight()>1) System.out.println("OUt 1!!");
					color = Options.colorPlus(color , 
							Options.colorInner( ret.obj.getColor(),
							Options.colorTimes(shed.obj.getColor(),
									tolight.w.inner(norm)*
									Model.S(tolight.w, line.w, norm, ret.obj.getS(), ret.obj.getRhos())*
//									Math.pow(norm.inner(tolight.w.normalize()), s)*rhos/Math.abs(norm.inner(line.w))*
									shed.obj.getLight())));
				}
				
			}
			//			SphCo coordinate = new SphCo(ret.obj.getNormal(ret.point));
			//			ArrayList<Point> list = coordinate.smallAng();
			//				System.out.println(list.size());
			//			for (Point ww : list) {
			//				v = new Line(line, ww);
			//				color = Options.colorPlus(color , 
			//						Options.colorTimes(rayTracer(v, depth+1) , 
			//								ww.modulo()*Model.Phong(v, line, ret.obj, ret.point, r)));
			if (ret.obj.isReflection()) {
				Line r = ret.obj.reflect(line, ret.point); // reflect vec
				color = Options.colorPlus(color , 
						Options.colorTimes(
								rayTracer(r, depth+1), ret.obj.getRhoreflect()));
			}
			//&& norm.inner(line.w)<-.6
			if (ret.obj.isRefraction() ) {
				Line r = ret.obj.refract(line, ret.point); // refract vec
				color = Options.colorPlus(color , 
						Options.colorTimes(
								rayTracer(r, depth+1), ret.obj.getRhorefract()));
			}
			//				return ret.obj.getColor();
			//				return Options.colorInner(ret.obj.getColor() , color);
			
			return color;
		}
	}
}

