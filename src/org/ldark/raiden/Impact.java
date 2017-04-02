package org.ldark.raiden;

public class Impact {
	public double Rect(int x1,int y1,int x2,int y2,int x3,int y3){
		 	double space = 0;
	        double a, b, c;
	        a=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	        b=Math.sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));
	        c=Math.sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3));
	        // �߶εĳ���
	        // (x1,y1)����ľ���
	        // (x2,y2)����ľ���
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
	        double p = (a + b + c) / 2;// ���ܳ�
	        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// ���׹�ʽ�����
	        space = 2 * s / a;// ���ص㵽�ߵľ��루���������������ʽ��ߣ�
	        return space;
	}
}
