package com.mage.cms.common.params.dynamic.vo;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;
/**
 * 
 * @author pzh
 * 动态插件 对象
 */
public class DynamicPlugin extends CmsLineResp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object extensionData;
	
	private Integer checkType;
	
	private long id;
	
	private Integer inputType;
	
	private Integer showTitle;
	
	private String isWrapper;
	
	private long storeId;
	
	private Integer style;
	
	private String title;
	
	private String widget_id;
	private String widget_image;
	
	public String getWidget_image() {
		return widget_image;
	}

	public void setWidget_image(String widget_image) {
		this.widget_image = widget_image;
	}

	public Object getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(Object extensionData) {
		this.extensionData = extensionData;
	}

	public Integer getCheckType() {
		return checkType;
	}


	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public Integer getInputType() {
		return inputType;
	}

	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}

	public Integer getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(Integer showTitle) {
		this.showTitle = showTitle;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Integer getStyle() {
		return style;
	}


	public void setStyle(Integer style) {
		this.style = style;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWidget_id() {
		return widget_id;
	}

	public void setWidget_id(String widget_id) {
		this.widget_id = widget_id;
	}

	public String getIsWrapper() {
		return isWrapper;
	}

	public void setIsWrapper(String isWrapper) {
		this.isWrapper = isWrapper;
	}
}
