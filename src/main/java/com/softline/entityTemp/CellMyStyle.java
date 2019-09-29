package com.softline.entityTemp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
public class CellMyStyle {
	
	private String backgroundColor;
	
	private String foregroundColor;
	
	private String alignment;

	//private String fontColor;
	
	private int heightInPoints;
	
	private String fontName;
	
	private int boldweight;

	
	
	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(String foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	/*public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}*/

	public int getHeightInPoints() {
		return heightInPoints;
	}

	public void setHeightInPoints(int heightInPoints) {
		this.heightInPoints = heightInPoints;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getBoldweight() {
		return boldweight;
	}

	public void setBoldweight(int boldweight) {
		this.boldweight = boldweight;
	}

	/** 
	 * excel(2007)中颜色转化为uof颜色 
	 *  
	 * @param color 
	 *            颜色序号 
	 * @return 颜色或者null 
	 */  
	private static ColorInfo excelColor2007UOF(XSSFColor color) {
		return null;  
	   /* if (color == null) {  
	        return null;  
	    }  
	    ColorInfo ci = null;  
        byte[] b = color.getRGBWithTint();
        if(b==null)
        	b=color.getRGB();
        if (b != null) {// 一定是argb  
            ci = ColorInfo.fromARGB(change(b[0]), change(b[1]), change(b[2]));  
            
        }  
	    return ci;  */
	}  
	
	
	private static int change(int number){
		return ((number & 0x0f0) >> 4)*16 +(number & 0x0f);
	}
	/** 
	 * excel(包含97)中颜色转化为uof颜色 
	 *  
	 * @param color 
	 *            颜色序号 
	 * @return 颜色或者null 
	 */  
	private  ColorInfo excelColor2UOF(HSSFColor color) {  
	    if (color == null) {  
	        return null;  
	    }  
	    ColorInfo ci = null;  

	        HSSFColor hc = color;  
	        short[] s = hc.getTriplet();// 一定是rgb  
	        if (s != null) {  
	            ci = ColorInfo.fromARGB(s[0], s[1], s[2]);  
	        }  
	    
	    return ci;  
	}  

	public CellMyStyle(Workbook book,CellStyle style,Font font)
	{
		
		if(style!=null)
		{
		
			int c1=0;
			int c2=0;
			ColorInfo ci=new ColorInfo();
			ColorInfo ci2=new ColorInfo();
			try{//2003
				HSSFWorkbook hb = (HSSFWorkbook) book;  
			    HSSFColor hc = hb.getCustomPalette().getColor(style.getFillBackgroundColor());  
			    ci = excelColor2UOF(hc);  
			    
				
				HSSFColor hc2 = hb.getCustomPalette().getColor(style.getFillForegroundColor());  
				ci2 = excelColor2UOF(hc2);  
			}
			catch(Exception ex)//2007
			{
				//单元格样式
				XSSFCellStyle xs = (XSSFCellStyle) style;
				//单元格背景颜色
				XSSFColor color=xs.getFillBackgroundXSSFColor();
				if(color!=null)
					ci=excelColor2007UOF(color);
				
				XSSFColor color2=xs.getFillForegroundXSSFColor();
				if(color2!=null)
					ci2=excelColor2007UOF(color2);
			}
			if(ci!=null)
			{
				c1=(ci.getR() << 16 | ci.getG() << 8 | ci.getB());
				backgroundColor = "#" +String.format("%06x",c1);
			}
			else
				backgroundColor = "#000000";
			if(ci2!=null)
			{
				c2=(ci2.getR() << 16 | ci2.getG() << 8 | ci2.getB());
				
				foregroundColor = "#" +String.format("%06x",c2);
			}
			else
				foregroundColor = "#000000";
			
			switch (style.getAlignment())
			{
				case 0x2:
					alignment="center";
					break;
				case 0x1:
					alignment="left";
					break;
				case 0x3:
					alignment="right";
					break;
				default:
					alignment="left";
					break;
			}
		}
		if(font!=null)
		{	
			heightInPoints = font.getFontHeightInPoints();
			fontName = font.getFontName();
			boldweight = font.getBoldweight();
		}
	}

	public CellMyStyle(String backgroundColor, String foregroundColor,
			String alignment, short heightInPoints,
			String fontName, short boldweight) {
		super();
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
		this.alignment = alignment;
		//this.fontColor = fontColor;
		this.heightInPoints = heightInPoints;
		this.fontName = fontName;
		this.boldweight = boldweight;
	}

	public CellMyStyle() {
		super();
	}

	public CellMyStyle(String a) {
		String regex = "(\"alignment\":\"(.*)\",\"backgroundColor\":\"(.*)\",\"boldweight\":(.*),\"fontName\":\"(.*)\",\"foregroundColor\":\"(.*)\",\"heightInPoints\":(.*)})";
		 Pattern pattern = Pattern.compile(regex);
         Matcher m = pattern.matcher(a);
         if(m.find())
         {
        	this.alignment=m.group(2);
			this.backgroundColor = m.group(3).equals("#000000")? "#ffffff":m.group(3);
			this.boldweight =Integer.parseInt(m.group(4)==null ? null :m.group(4))   ;
			this.fontName = m.group(5);
			this.foregroundColor = m.group(6).equals("#000000")? "#ffffff":m.group(6);
			this.heightInPoints =Integer.parseInt(m.group(7)==null ? null :m.group(7))   ;
         }
		
	}
}
