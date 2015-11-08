package bmp;

//import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

public class Bmp {
	public int height,width;
	BufferedImage tag;
	File showout;
	
	public Bmp(int h, int w) {
		// TODO Auto-generated constructor stub
		height=h;width=w;
		tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		tag.createGraphics();
	}
	public Bmp() {
		// TODO Auto-generated constructor stub
		this(64,64);
	}
	public void setFile(String tmp) {
		showout=new File(tmp);
	}
	public void setPixel(int x,int y,int r,int g,int b) {
		tag.setRGB(x, y, (r<<16)|(g<<8)|b);
	}
	public void setPixel(int x,int y,int rgb) {
		tag.setRGB(x, y, rgb);
	}
	public void setPixelDouble(double xx,double yy,int r,int g,int b) {
		int x,y;
		x=(int) (xx*width);
		y=(int) (yy*height);
		tag.setRGB(x, y, (r<<16)|(g<<8)|b);
	}
	public void setPixelDouble(double xx,double yy,int rgb) {
		if (xx>=1.0 || yy>=1.0 || xx<0.0 || yy<0.0) return;
		int x,y;
		x=(int) (xx*width);
		y=(int) (yy*height);
		tag.setRGB(x, y, rgb);
	}
	public double getRatio(int x, int dim) { // in ratio double [0,1]
		if (dim==1)
			return (double)x/width;
		else
			return (double)x/height;
	}
	public void write() {
		try {
			javax.imageio.ImageIO.write(tag, "bmp", showout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		Bmp x=new Bmp();
//		x.go();
//	}
//	public void go(){  
//		try{  
////			//读入文件    
////			File file = new File("1.bmp");    
////			// 构造Image对象    
////			BufferedImage src = javax.imageio.ImageIO.read(file);    
////			int width = src.getWidth();    
////			int height = src.getHeight();
////			System.out.println(width+"<>"+height);
////			// 放大边长  
////			//绘制放大后的图片  
//			tag.setRGB(1, 1, (0<<16)|(0<<8)|255);
//			tag.setRGB(1, 1, (0<<16)|(0<<8)|255);
//		}catch(Exception e){  
//			e.printStackTrace();  
//		}
//	}  
}
