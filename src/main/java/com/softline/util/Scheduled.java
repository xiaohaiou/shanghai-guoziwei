package com.softline.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.ANNOTATION_TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Scheduled  
{  
 public abstract String cron();  
 
 public abstract long fixedDelay();  
 
 public abstract long fixedRate();  
}  
