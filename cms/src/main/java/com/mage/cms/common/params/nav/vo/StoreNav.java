package com.mage.cms.common.params.nav.vo;

import java.util.List;
import java.util.Map;

import com.mage.cms.common.params.base.resp.CmsLineResp;

/**
 * 快捷导航
 */
public class StoreNav extends CmsLineResp{
	private static final long serialVersionUID = 1L;
	
	private Object extensionData;
	private Integer class1Sum;
	private Integer class2Sum;
	private Integer class3Sum;
	private List<Map<String, Object>> StoreNavClass1;
	
	public Object getExtensionData() {
		return extensionData;
	}
	public void setExtensionData(Object extensionData) {
		this.extensionData = extensionData;
	}
	public Integer getClass1Sum() {
		return class1Sum;
	}
	public void setClass1Sum(Integer class1Sum) {
		this.class1Sum = class1Sum;
	}
	public Integer getClass2Sum() {
		return class2Sum;
	}
	public void setClass2Sum(Integer class2Sum) {
		this.class2Sum = class2Sum;
	}
	public Integer getClass3Sum() {
		return class3Sum;
	}
	public void setClass3Sum(Integer class3Sum) {
		this.class3Sum = class3Sum;
	}
	public List<Map<String, Object>> getStoreNavClass1() {
		return StoreNavClass1;
	}
	
	public void setStoreNavClass1(List<Map<String, Object>> storeNavClass1) {
		StoreNavClass1 = storeNavClass1;
	}
}
