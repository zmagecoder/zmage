package com.mage.platform.framework.database;


import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.mage.exception.PermssionException;

public abstract class BaseSupport<T> {

	protected final Logger logger = Logger.getLogger(getClass());
	
	@Resource
	protected IDaoSupport<T> mySqlDBSupport;					//mysql处理器
	
	@Resource
	protected IDaoSupport<T> oracleDBSupport;   			//oracle处理器


	/**
	 * 检测操作的“属主”合法性
	 * @param userid
	 */
	protected void checkIsOwner( final Integer userid){
		if(userid==null){
			throw new PermssionException();
		}
		
		String suserid = "";			//EopContext.getContext().getCurrentSite().getUserid();
		
		if(!suserid.equals(userid)){
			throw new PermssionException();
		}
	}
	
	/**
	 * 获取默认sql执行器
	 * @return
	 */
	protected  IDaoSupport<T> getExecutor(){
		
		return oracleDBSupport;
	}
	
	/**
	 * 根据数据源名称获取执行器
	 * @param dbName
	 * @return
	 */
	protected  IDaoSupport<T> getExecutor(String dbName){
		
		return oracleDBSupport;
	}
}
