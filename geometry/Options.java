package geometry;

public final class Options {
	public static final int WIDTH= 400;
	public static final int HEIGHT= 400;
	public static final int CMAX= 256;
	public static final int MAXDEPTH= 4;
	public static final double SMALLANG = Math.PI/4;
	public static final double AMBIENT = .1;
	public static final int WHITE = 16777215;
	public static final int BACKGROUND = 0;
	public static final double DOUBLE_EPS = 1e-6;
	public static int colorInner(int a,int b){
		int a1,a2,a3;
		a1=(a>>16)%CMAX;
		a2=(a>>8)%CMAX;
		a3=(a)%CMAX;
		int b1,b2,b3;
		b1=(b>>16)%CMAX;
		b2=(b>>8)%CMAX;
		b3=(b)%CMAX;
		int c1,c2,c3;
		c1=(int)((double)b1/CMAX*a1);
		c2=(int)((double)b2/CMAX*a2);
		c3=(int)((double)b3/CMAX*a3);
		return (c1<<16)+(c2<<8)+c3;
	}
public static int colorPlus(int a,int b){
	int a1,a2,a3;
	a1=(a>>16)%CMAX;
	a2=(a>>8)%CMAX;
	a3=(a)%CMAX;
	int b1,b2,b3;
	b1=(b>>16)%CMAX;
	b2=(b>>8)%CMAX;
	b3=(b)%CMAX;
	int c1,c2,c3;
	c1=a1+b1;
	c2=a2+b2;
	c3=a3+b3;
	if (c1>=CMAX) c1=CMAX-1;
	if (c2>=CMAX) c2=CMAX-1;
	if (c3>=CMAX) c3=CMAX-1;
	return (c1<<16)+(c2<<8)+c3;
}
	public static int colorTimes(int a,double b){
		if (b<0) b=-b;
		double a1,a2,a3;
		a1=(a>>16)%CMAX;
		a2=(a>>8)%CMAX;
		a3=(a)%CMAX;
		a1*=b;a2*=b;a3*=b;
		int c1,c2,c3;
		c1=(int)a1;
		c2=(int)a2;
		c3=(int)a3;
		return (c1<<16)+(c2<<8)+c3;
	}
	
	public static double deterministic (Point a, Point b, Point c) {
		return a.x*b.y*c.z + a.y*b.z*c.x + a.z*b.x*c.y
				- c.x*b.y*a.z - c.y*b.z*a.x - c.z*b.x*a.y;
	}
}
