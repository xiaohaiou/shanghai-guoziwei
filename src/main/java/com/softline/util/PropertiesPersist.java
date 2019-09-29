package com.softline.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.util.DefaultPropertiesPersister;

public class PropertiesPersist extends DefaultPropertiesPersister{

    public void load(Properties props, InputStream is) throws IOException{
    
    Properties properties = new Properties(); 
    properties.load(is); 
    
    if ( (properties.get("jdbcUrl") != null) ){
    	   String url=	ThreeDes.Decrypt(properties.get("jdbcUrl").toString());
    	   System.out.println(url);
    		properties.setProperty("jdbcUrl" , "jdbc:mysql://10.123.0.64:3306/shanghai-gzw?useUnicode=true&amp;characterEncoding=UTF-8");   
        /*这里通过解密算法，得到你的真实密码，然后写入到properties中*/
//        String password = getRealPassword( decrypter , properties.getProperty("password") );    
//        properties.setProperty("password" , password);     
    }    
    OutputStream outputStream = null; 
    try {
        outputStream = new ByteArrayOutputStream(); 
        properties.store(outputStream, ""); 
        is = outStream2InputStream(outputStream); 
        super.load(props, is);
    }catch(IOException e) { 
        throw e;
    }finally {
        outputStream.close();
    }
    }
    
    
    private InputStream outStream2InputStream(OutputStream out){
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
        bos = (ByteArrayOutputStream) out ;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(bos.toByteArray()); 
        return swapStream;
    }
}
