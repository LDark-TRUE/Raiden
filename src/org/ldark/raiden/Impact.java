package org.ldark.raiden;

public class Impact {
	public double Rect(int x1,int y1,int x2,int y2,int x3,int y3){
		 	double space = 0;
	        double a, b, c;
	        a=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	        b=Math.sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));
	        c=Math.sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3));
	        // 线段的长度
	        // (x1,y1)到点的距离
	        // (x2,y2)到点的距离
	        if (c <= 0.000001 || b <= 0.000001) {
	            space = 0;
	            return space;
	        }
	        if (a <= 0.000001) {
	            space = b;
	            return space;
	        }
	        if (c * c >= a * a + b * b) {
	            space = b;
	            return space;
	        }
	        if (b * b >= a * a + c * c) {
	            space = c;
	            return space;
	        }
	        double p = (a + b + c) / 2;// 半周长
	        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
	        space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
	        return space;
	}
}
