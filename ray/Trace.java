package ray;

import java.util.Scanner;

import bmp.Bmp;
import geometry.Base;
import geometry.Camera;
import geometry.Cube;
import geometry.GManager;
import geometry.Line;
import geometry.Options;
//import geometry.Plane;
import geometry.Point;
import geometry.Sphere;
import geometry.T_Point_Obj_Normal;

public class Trace {
	GManager manager;
	Camera camera;
	public Trace(GManager manager, Camera camera) {
		// TODO Auto-generated constructor stub
		this.manager = manager;this.camera=camera;
	}
	public void workOutPicture(Bmp pic)
	{
		for (int ix=0;ix<Options.WIDTH;ix++){
			for (int iy=0;iy<Options.HEIGHT;iy++) {
				Point intclr;
				intclr = rayTracer(camera.getLineOfPixel(ix, iy), 0);
				pic.setPixelReal(ix, iy, intclr.x, intclr.y, intclr.z);
			}
			System.out.println(100*ix/(Options.WIDTH-1)+" %");
		}
	}
	public void workOutPictureAA(Bmp pic)
	{
		Scanner sc = new Scanner(System.in);
		int s1,s2;
		System.out.println("Range (2 numbers):");
		s1=sc.nextInt();s2=sc.nextInt();
		final int num=4;
		final int num2 =1;
//		final int num3 =128;
		final double del = (double)1/num;
		final double del2 = (double)1/num2;
//		final double del3 = (double)1/num3;
		for (int ix=s1;ix<s2;ix++){
			for (int iy=0;iy<Options.HEIGHT;iy++){
				Point intclr=new Point();
				for (int id=0;id<num;id++)
					for (int jd=0;jd<num;jd++)
						for (int n=0;n<num2;n++) {
							double dx=del*id+del*Math.random(),dy=del*jd+del*Math.random();
							//for (int m=0;m<num3;m++)
							intclr.plus( rayTracer(camera.getLineOfPixelWithDelta(ix, iy, dx, dy), 0));
						}
				intclr.times(del*del*del2);//don't del2
				pic.setPixelReal(ix, iy, intclr.x, intclr.y, intclr.z);
			}
			System.out.println(100*ix/(Options.WIDTH-1)+" %");
		}
		sc.close();
	}
//	public void workOutPictureFocus(Bmp pic)
//	{
//		final int num=128;
//		final double del = (double)1/num;
//		for (int ix=0;ix<Options.WIDTH;ix++){
//			for (int iy=0;iy<Options.HEIGHT;iy++){
//				Point intclr=new Point();
//				for (int n=0;n<num;n++){
//					intclr.plus( rayTracer(camera.getLineOfPixel(ix, iy) ,0));
//				}
//				intclr.times(del);
//				pic.setPixelReal(ix, iy, intclr.x, intclr.y, intclr.z);
//			}
//			System.out.println(100*ix/(Options.WIDTH-1)+" %");
//		}
//	}
	
	public Point rayTracer(Line line, int depth){//, Integer color) {
		if (depth>Options.MAXDEPTH) {
			return Options.BACKGROUND();
		} //background color, over depth and no intersection
		T_Point_Obj_Normal ret = manager.getIntersect(line,0);
		if (ret == null || ret.point == null) {return Options.BACKGROUND();}//background color, no intersection at all
		else {
			if (ret.obj.isLighted()) {
				return Options.colorTimes(ret.obj.getColor(ret.point),ret.obj.getLight());
//				color = Options.colorPlus(color , Options.colorTimes(ret.obj.getColor(),ret.obj.getLight()));
			}
			if (ret.obj instanceof Cube) System.out.println("qi le guai le"+ret.point);
			Point color = Options.colorTimes(ret.obj.getColor(ret.point), Options.AMBIENT);
			Point norm = ret.normal; // normal vec
			Point tmpcolor = new Point();//ji suan soft shadow
			for (int soft=0;soft<Options.SOFT;soft++) {
				for (Base baselight : manager.getLights(0)) 
					if (baselight instanceof Sphere){
						Sphere light = (Sphere) baselight;
						Point lc = new Point(light.getCenter());
						lc.minus(ret.point);
						Point tmp = new Point(norm);
						tmp.times(Options.DOUBLE_EPS);
						tmp.plus(ret.point);
						Line tolight = new Line(tmp, lc);
						T_Point_Obj_Normal shed = manager.getIntersect(tolight,0);
						//				double rhos=ret.obj.getRhos();
						//				double s=ret.obj.getS();
						if (shed != null && shed.obj!=null && shed.obj.equals(light)) {
							//					System.out.println("Mei you dang guang!!");
//							color = Options.colorPlus(color , 
//									Options.colorInner( ret.obj.getColor(ret.point),
									tmpcolor.plus(Options.colorTimes(shed.obj.getColor(shed.point),
													//									Math.abs(
													tolight.w.inner(norm)
													*
													ret.obj.getRhod()*
													shed.obj.getLight()));
//							color = Options.colorPlus(color , 
//									Options.colorInner( ret.obj.getColor(ret.point),
									tmpcolor.plus(Options.colorTimes(shed.obj.getColor(shed.point),
													//									Math.abs(
													tolight.w.inner(norm)
													*
													Model.S(tolight.w, line.w, norm, ret.obj.getS(), ret.obj.getRhos())*
													//									Math.pow(norm.inner(tolight.w.normalize()), s)*rhos/Math.abs(norm.inner(line.w))*
													shed.obj.getLight())
											
									);
						}
					}
			}
			tmpcolor.times(Options.DSOFT);
			color = Options.colorPlus(color , 
					Options.colorInner( ret.obj.getColor(ret.point), tmpcolor ));
			//			SphCo coordinate = new SphCo(ret.obj.getNormal(ret.point));
			//			ArrayList<Point> list = coordinate.smallAng();
			//				System.out.println(list.size());
			//			for (Point ww : list) {
			//				v = new Line(line, ww);
			//				color = Options.colorPlus(color , 
			//						Options.colorTimes(rayTracer(v, depth+1) , 
			//								ww.modulo()*Model.Phong(v, line, ret.obj, ret.point, r)));
			if (ret.obj.isReflection()) {
				Line r = ret.obj.reflect(line, ret.point, norm); // reflect vec
				color = Options.colorPlus(color , 
						Options.colorTimes(
								rayTracer(r, depth+1), ret.obj.getRhoreflect()));
			}
			if (ret.obj.isRefraction() ) {
				Line r = ret.obj.refract(line, ret.point, norm); // refract vec
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

