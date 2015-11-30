package geometry;

import java.util.ArrayList;
import java.util.Random;

public class SphCo {//球坐标
	Point normal; //horz
	Point horx,hory;
	public SphCo(Point n) {
		// TODO Auto-generated constructor stub
		normal=n;
		Random random = new Random();
		double x=random.nextDouble(),y=random.nextDouble();
		double z=-normal.inner(new Point(x,y,0))/normal.z;
		horx = new Point(x, y, z).normalize();
		hory = new Point(
				horx.z*normal.y-horx.y*normal.z, 
				horx.x*normal.z-horx.z*normal.x, 
				horx.x*normal.y-horx.y*normal.x);//行列式解法
//		origin=o;
	}
	public ArrayList<Point> smallAng() {
		final double ep = Math.PI/50;
		ArrayList<Point> ret = new ArrayList<Point>();
		for (double t=0;t<Options.SMALLANG;t=t+ep)
		{
			double sinn = Math.sin(t), coss = Math.cos(t);
			for (double p=0;p<2*Math.PI;p=p+ep) {
				Point tmp = new Point(normal);
				tmp.times(coss);
				Point tmpx,tmpy;
				tmpx = new Point(horx);
				tmpy = new Point(hory);
				tmpx.times(sinn*Math.cos(p));
				tmpy.times(sinn*Math.sin(p));
				tmp.plus(tmpx);
				tmp.plus(tmpy);
				tmp.times(coss);
				ret.add(tmp);
			}
		}
		return ret;
	}
}
