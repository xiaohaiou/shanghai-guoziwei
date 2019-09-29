package com.softline.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


/**
 * 未及时上报、审核公司提醒，sql加载配置类
 * @author zl
 *
 */
public class WarningCenterUtil {
	
	private SAXReader saxReader = null;
	private InputStream inputStream = null;
	private Document document = null;
	private static final Logger log= Logger.getLogger(WarningCenterUtil.class);
	
	private WarningCenterUtil(){
		saxReader = new SAXReader();
		try {
			//String classpath = WarningCenterUtil.class.getResource("/").getPath().replaceFirst("/", "");
//			String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
//			String url = webappRoot+"sql_update/warningcenter.xml";
//			String url = classpath+"warningcenter.xml";
//			inputStream = new FileInputStream(url);
			inputStream = WarningCenterUtil.class.getResourceAsStream("/warningcenter.xml");
			this.document = saxReader.read(inputStream);
		} catch (DocumentException e) {
			if(log.isInfoEnabled())
				log.info("未填报预警，加载配置文件(warningcenter.xml)失败！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			if(log.isInfoEnabled())
				log.info("未填报预警，加载配置文件(warningcenter.xml)失败！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				if(log.isInfoEnabled())
					log.info("未填报预警，加载配置文件(warningcenter.xml)资源关闭失败");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	private static class Innerclass{
		private static final WarningCenterUtil INSTANCE = new WarningCenterUtil();
	}
	
	public static final WarningCenterUtil getInstance(){
		return Innerclass.INSTANCE;
	}
		
	public Document getDocument(){
		return this.document;
	}
	
}
