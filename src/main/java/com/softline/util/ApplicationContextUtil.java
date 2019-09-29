package com.softline.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
	
	public static ApplicationContext getContext() {
        return context;
    }
	
}
