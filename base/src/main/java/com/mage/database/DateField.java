package com.mage.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日期字段
 * @author pzh
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD) 
public @interface DateField {
	
	String format() default "yyyy-MM-dd HH:mm:ss";			//日期格式
	
}
