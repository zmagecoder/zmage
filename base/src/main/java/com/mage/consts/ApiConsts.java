package com.mage.consts;

/**
 * 
 * 通用API常量
 * @author pzh
 */
public class ApiConsts {

    public static final String ZTE_CLIENT_DUBBO = "dubbo"; // dubbo调用
    public static final String ZTE_CLIENT_REMOTE = "remote"; // 远程调用
    public static final String ZTE_CLIENT_HTTP = "http"; // http调用
    public static final String ZTE_CLIENT_MQ = "mq"; // mq调用

    public final static String EXP_BUSS="buss";//业务异常
    public final static String EXP_RUNTIME="runtime";//运行时异常

    public static final String ERROR_SUCC ="0";
    public static final String ERROR_FAIL ="-1";
    
    public static String MQ_PRODUCER_POOL = "normalSendController"; // 生产者池，
	public static String MQ_TX_PRODUCER_POOL = "txSendController"; // 事务发送生产者池，
	
}
