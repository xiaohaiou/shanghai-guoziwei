package com.softline.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadProperties {
	private Properties rd;
	private String path;

	static Logger log = Logger.getLogger(ReadProperties.class.getName());

	public static ReadProperties CreateReadProperties(String pathName) {
		return new ReadProperties(pathName);
	}

	private ReadProperties(String pathName) {

		Properties p = new Properties();
		try {
			if (pathName.startsWith("/")) {
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(pathName);
				p.load(inputStream);
			} else {
				FileInputStream fis = new FileInputStream(pathName);
				p.load(fis);
			}

		} catch (Exception e1) {
			log.error(e1);
		}
		path = pathName;
		rd = p;
	}

	public String GetProperty(String key) {
		if (rd.containsKey(key))
			return rd.getProperty(key);
		return "";
	}

	public void SetProperty(String key, String value) {
		rd.setProperty(key, value);
		if (path.startsWith("/")) {

			try {
				OutputStream fos = new FileOutputStream(this.getClass().getClassLoader().getResource("/").getPath() + path);
				// rd.store(fos, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			OutputStream fos;
			try {
				fos = new FileOutputStream(path);
				// rd.store(fos, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
