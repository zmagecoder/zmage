package com.mage.platform.model;

import com.mage.platform.consts.Constant;

/**
 * 组装JSON对象
 * @author pzh
 * @date 2017年2月9日 上午9:37:46
 */
public class AssembleJSON {
	
	private Integer retStatus;
	private String message;
	private Object data;
	
	/**
	 * 获得返回状态号
	 * @return 状态号
	 */
	public Integer getRetStatus(){
		return this.retStatus;
	}
	
	/**
	 * 返回状态信息
	 * @return 信息
	 */
	public String getMessage(){
		return this.message;
	}
	
	/**
	 * 获得返回数据
	 * @return 数据
	 */
	public Object getData(){
		return this.data;
	}
	
	private AssembleJSON(Integer _retStatus, String _message, Object _data){
		this.retStatus = _retStatus;
		this.message = _message;
		this.data = _data;
	}
	
	/**
	 * 返回成功(默认)
	 */
	public static AssembleJSON SUCCESS = new AssembleJSON(Constant.SUCCESS, "请求成功", null);
	
	/**
	 * 返回成功JSON
	 * @param _data 数据
	 * @return JSON对象
	 */
	public static AssembleJSON SUCCESS(Object _data){
		return new AssembleJSON(Constant.SUCCESS, "请求成功", _data);
	}
	
	/**
	 * 返回失败JSON
	 * @param _message 失败信息
	 * @return JSON对象
	 */
	public static AssembleJSON FAILURE(String _message){
		return new AssembleJSON(Constant.FAILED, _message, null);
	}
	
	/**
	 * 返回失败JSON,传入data
	 * @param _message 失败信息
	 * @return JSON对象
	 */
	public static AssembleJSON FAILURE(String _message,Object _data){
		return new AssembleJSON(Constant.FAILED, _message, _data);
	}
}
