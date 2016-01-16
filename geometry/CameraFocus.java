package geometry;

public class CameraFocus extends Camera {
	double ape=.02;//光圈
	@Override
	Point getView() {
		// TODO Auto-generated method stub
		double theta=Math.random()*Math.PI*2;
		double rad=Math.random()*ape;
		Point ret =new Point(view);
		ret.plus(new Point(0,rad*Math.cos(theta),rad*Math.sin(theta)));
		return ret;
	}
}
