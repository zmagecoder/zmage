package com.mage.consts;

/**
 * 
 * 通用API常量
 * @author pzh
 */
public class ApiConsts {

	/******************api 调用方式**********************/
    public static final String MAGE_CLIENT_DUBBO = "dubbo"; // dubbo调用
    public static final String MAGE_CLIENT_REMOTE = "remote"; // 远程调用
    public static final String MAGE_CLIENT_HTTP = "http"; // http调用
    public static final String MAGE_CLIENT_MQ = "mq"; // mq调用

    public final static String EXP_BUSS="buss";//业务异常
    public final static String EXP_RUNTIME="runtime";//运行时异常
}
