package com.mage.common;

import java.util.Stack;

import com.mage.param.req.MageRequest;
import com.mage.param.resp.MageResponse;


/**
 * 
 * @author wui
 * 
 * dubbo,rop方式调用统一入参，出参处理对象
 * 
 * mageRequest为rop、dubbo调用的入参对象
 * 
 * mageResponse为rop、dubbo调用的出参对象
 * 
 * mageError为rop、dubbo调用的错误参数对象
 * 
 *
 */
public class MageCommonData {
	private MageRequest<MageResponse> mageRequest =null; //基础入参
    private MageResponse mageResponse = null;//基础出参
    private MageError mageError = null; //错误参数
    private Stack<String> appKeys = new Stack<String>();
    
    private Stack<DubboParam> dubboParams = new Stack<DubboParam>();

	public String getUserSession_id() {
        return getMageRequest()==null?null:getMageRequest().getUserSessionId();
    }

    public MageRequest getMageRequest() {
		return mageRequest;
	}

	public void setMageRequest(MageRequest mageRequest) {
		this.mageRequest = mageRequest;
	}
	
	public void setAppKeys(String appKey) {
		this.appKeys.push(appKey);
	}
	
	public String getAppKey() {
		String appKey = "";
		if(!appKeys.isEmpty())
			appKey = appKeys.lastElement();
		return appKey;
	}
	
	public void removeAppKey() {
		if(!appKeys.isEmpty())
			this.appKeys.pop();
	}

	
	
	public void setDubboParam(DubboParam dubboParam) {
		this.dubboParams.push(dubboParam);
	}
	
	public DubboParam getDubboParam() {
		DubboParam dubboParam = null;
		if(!dubboParams.isEmpty())
			dubboParam = dubboParams.lastElement();
		return dubboParam;
	}
	
	public void removeDubboParam() {
		if(!dubboParams.isEmpty())
			this.dubboParams.pop();
	}
	
	
	public void clearDubboParam() {
		if(!dubboParams.isEmpty())
			this.dubboParams = new  Stack<DubboParam>();
	}
	
	
	public MageResponse getMageResponse() {
		return mageResponse;
	}

	public void setMageResponse(MageResponse mageResponse) {
		this.mageResponse = mageResponse;
	}
    public MageError getMageError() {
		return mageError;
	}

	public void setMageError(MageError mageError) {
		this.mageError = mageError;
	}
	
	public static void main(String[] args) {
		MageCommonData mageCommonData = new MageCommonData();
		DubboParam dubboParam = new DubboParam();
		for (int i = 0; i < 3; i++) {
			dubboParam.setApp_key("appkey"+i);
			dubboParam.setSource_from("sourcefrom"+i);
			mageCommonData.setDubboParam(dubboParam);
		}
		mageCommonData.removeDubboParam();
		DubboParam dubboParam2 = mageCommonData.getDubboParam();
		System.out.println(dubboParam2.getApp_key()+":"+dubboParam2.getSource_from());
	}

	public Stack<DubboParam> getDubboParams() {
		return dubboParams;
	}

	public void setDubboParams(Stack<DubboParam> dubboParams) {
		this.dubboParams = dubboParams;
	}

	
	
}
