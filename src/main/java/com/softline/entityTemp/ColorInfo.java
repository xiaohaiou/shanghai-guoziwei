package com.softline.entityTemp;

public class ColorInfo {
	 /** 
     * 颜色的alpha值，此值控制了颜色的透明度 
     */  
    private int A;  
    /** 
     * 颜色的红分量值，Red 
     */  
    private int R;  
    /** 
     * 颜色的绿分量值，Green 
     */  
    private int G;  
    /** 
     * 颜色的蓝分量值，Blue 
     */  
    private int B;  
  
    public int getA() {
		return A;
	}

	public void setA(int a) {
		A = a;
	}

	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getB() {
		return B;
	}

	public void setB(int b) {
		B = b;
	}

	public int toRGB() {  
        return this.R << 16 | this.G << 8 | this.B;  
    }  
  
    public java.awt.Color toAWTColor(){  
        return new java.awt.Color(this.R,this.G,this.B,this.A);  
    }  
  
    public static ColorInfo fromARGB(int red, int green, int blue) {  
        return new ColorInfo((int) 0xff, (int) red, (int) green, (int) blue);  
    }  
    public static ColorInfo fromARGB(int alpha, int red, int green, int blue) {  
        return new ColorInfo(alpha, red, green, blue);  
    }  
    public ColorInfo(int a,int r, int g , int b ) {  
        this.A = a;  
        this.B = b;  
        this.R = r;  
        this.G = g;  
    }

	public ColorInfo() {
		// TODO Auto-generated constructor stub
	}  


}
