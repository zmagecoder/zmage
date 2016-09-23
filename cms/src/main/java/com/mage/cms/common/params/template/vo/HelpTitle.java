package com.mage.cms.common.params.template.vo;

import java.io.Serializable;
/**
 * 
 * @author pzh
 * 页面模板对象
 */
public class HelpTitle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object extensionData;
	private String name;
	
	public Object getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(Object extensionData) {
		this.extensionData = extensionData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
