package com.mage.cms.anniton;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pzh
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UrlPathMethod {
	/**
	 * 名称
	 * @return
	 */
	String name() default "";
	/**
	 * 类型
	 * @return
	 */
	String path() default "";
	
}
