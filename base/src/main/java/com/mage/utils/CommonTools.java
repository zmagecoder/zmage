package com.mage.utils;

import com.mage.common.ZteError;
import com.mage.consts.ApiConsts;
import com.mage.consts.ErrorConsts;
import com.mage.context.CoreThreadLocalHolder;
import com.mage.context.GlobalThreadLocalHolder;
import com.mage.exception.ApiBusiException;
import com.mage.exception.ApiRunTimeException;
import com.mage.param.resp.ZteResponse;

public class CommonTools {
	
	private static final ThreadLocal<ZteResponse> outEntryLocal = new ThreadLocal<ZteResponse>(); //外围参数
	
	public static String getUUID(){
		String session_id = GlobalThreadLocalHolder.getInstance().getUUID();
		return session_id;
	}
	
	
	/**
	 * 异常类
	 * @param errorEntity
	 */
	public static void addFailError(String message){
		if(CoreThreadLocalHolder.getInstance().getZteCommonData().getZteError() ==null)
			CoreThreadLocalHolder.getInstance().getZteCommonData().setZteError(new ZteError(ErrorConsts.ERROR_FAIL,message));
		ZteError zteError = new ZteError(ErrorConsts.ERROR_FAIL,message);
		throw new ApiRunTimeException(ErrorConsts.ERROR_FAIL,message);
	}
	

	/**
	 * 业务异常类
	 * @param errorEntity
	 * @throws BusiException 
	 */
	public static void addBusiError(ZteError zteError) throws ApiBusiException{
		CoreThreadLocalHolder.getInstance().getZteCommonData().setZteError(zteError);
		throw new ApiBusiException(zteError.getError_msg());
	}

	 /**
     * 业务异常类
     * @param errorEntity
	 * @throws BusiException 
     */
    public static void addBusiError(String errCode,String msg) throws ApiBusiException{
        ZteError zteError=new ZteError(errCode,msg);
        zteError.setType_code(ApiConsts.EXP_BUSS);
        ZteError zteErrorn= CoreThreadLocalHolder.getInstance().getZteCommonData().getZteError();
        //add by wui追加处理
        if(zteErrorn !=null)
        	zteError.setError_msg(zteError.getError_msg()+","+zteErrorn.getError_msg());
        CoreThreadLocalHolder.getInstance().getZteCommonData().setZteError(zteError);
        throw new ApiBusiException(zteError.getError_msg());
    }
    
    
	
	
	/**
	 * 异常类
	 * @param errorEntity
	 */
	public static void addError(ZteError zteError){
		if(CoreThreadLocalHolder.getInstance().getZteCommonData().getZteError() ==null)
			CoreThreadLocalHolder.getInstance().getZteCommonData().setZteError(zteError);
		//错误信息写到入参变量中，解决dubbo、rop调用问题
		throw new ApiRunTimeException(zteError.getError_code(),zteError.getError_msg());
	}

	/**
     * 异常类
     * @param errorEntity
     */
    public static void addError(String errCode,String msg){
        ZteError zteError=new ZteError(errCode,msg);
        if(CoreThreadLocalHolder.getInstance().getZteCommonData().getZteError() ==null)
        	CoreThreadLocalHolder.getInstance().getZteCommonData().setZteError(zteError);
        throw new ApiRunTimeException(errCode,zteError.getError_msg());
    }
    
	public static ZteError getZteError(){
		return CoreThreadLocalHolder.getInstance().getZteCommonData().getZteError();
	}
	
	public static ZteResponse getZteResponse(){
		return CoreThreadLocalHolder.getInstance().getZteCommonData().getZteResponse();
	}
	
	/**
	 * 创建sessionId
	 * @作者 MoChunrun 
	 * @创建日期 2013-9-23 
	 * @return
	 */
	public static String createSessionId(){
		return GlobalThreadLocalHolder.getInstance().getUUID();
	}
	
	public static String getUserSessionId() {
		String session_id =getUUID();
		return session_id;
	}
	
	
	
	/**
	 * 设置外系统订单
	 * @作者 MoChunrun 
	 * @创建日期 2013-10-12 
	 * @param order
	 */
	public static void setOutEntryLocal(ZteResponse order){
		outEntryLocal.set(order);
	}
	
	
	public static<T> T genOutThreadVO(Class<T> cla){
		
		T t = null;
		try {
			t = cla.newInstance();
			setOutEntryLocal((ZteResponse) t);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
		return t;

	}
	
	public static ZteResponse  getOutEntryLocal(){
		return outEntryLocal.get();
	}
	
	/**
	 * 
	 * @param cla
	 * @return
	 */
	public static<T> T getOutThreadVO(Class<T> cla){
		T t = (T)getOutEntryLocal();
		return t;

	}
	
	public static void  setOutThreadVO(ZteResponse ZteResponse){
		setOutEntryLocal(ZteResponse);

	}
	
}