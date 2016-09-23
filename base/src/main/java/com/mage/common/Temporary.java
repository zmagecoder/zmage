package com.mage.common;

import java.lang.annotation.*;

/**
 * 默认情况下，请求对象的所有field都会作为请求参数提交，
 * 如果希望某个field不作为参数提交，可以打上{@Temporary}注解
 *
 * @author pzh
 * @version 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Temporary {

}

