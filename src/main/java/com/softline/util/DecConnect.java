package com.softline.util;

import java.security.Security;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class DecConnect extends PropertyPlaceholderConfigurer {
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactory,
			Properties props) throws BeansException {
		
		/*try {
			// jdbc----------------------
			String jdbc = props.getProperty("jdbcUrl");
			if (jdbc != null) {
				String srcBytes = ThreeDes.Decrypt(jdbc);  
				props.setProperty("jdbcUrl", srcBytes);
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}*/
	}

}
