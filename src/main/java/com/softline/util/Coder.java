
package com.softline.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import sun.misc.BASE64Decoder;

public class Coder {

	// 将 s 进行 BASE64 编码 
	public static String getBASE64(byte[] s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s);
	}

	// 将 s 进行 BASE64 编码 
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码 
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static String fileCode(String fileurl) throws Exception {
		String result = null;
		// 第1步、使用File类找到一个文件
		File f = new File(fileurl); // 声明File对象
		// 第2步、通过子类实例化父类对象
		InputStream input = null; // 准备好一个输入的对象
		input = new FileInputStream(f); // 通过对象多态性，进行实例化
		// 第3步、进行读操作
		// byte b[] = new byte[input..available()] ;  跟使用下面的代码是一样的
		byte b[] = new byte[(int) f.length()]; // 数组大小由文件决定
		int len = input.read(b); // 读取内容
		// 第4步、关闭输出流
		input.close(); // 关闭输出流\
		//System.out.println("读入数据的长度：" + len);
		result = Coder.getBASE64(b);
		//System.out.println("内容为：" + result); // 把byte数组变为字符串输出
		return result;
	}

	public static String fileStringCode(String fileurl) throws Exception {
		String result = "";
		String encoding = "GBK";
		File file = new File(fileurl);
		if (file.isFile() && file.exists()) { //判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				result += lineTxt;
			}
			System.out.println("内容为：" + result); 
			read.close();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
	
	}
	

}
