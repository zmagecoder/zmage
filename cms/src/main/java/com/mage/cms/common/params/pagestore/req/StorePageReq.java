package com.mage.cms.common.params.pagestore.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
/**
 * 
 * @author pzh
 * 页面添加入参对象
 * 
 */
public class StorePageReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object extensionData;
	private String description;
	private String device;
	private String name;
	private String title;                                            
	private String keyword;
	private String type;
	private String isShow;
	private String order;
	private String saveLevel;
	private String storeId;
	private String indexId;
	private String pageId;
	private String page_url;
	
	private String store_page_type;	//编辑的时候才会有的属性
	
	
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public Object getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(Object extensionData) {
		this.extensionData = extensionData;
	}

	public String getSaveLevel() {
		return saveLevel;
	}

	public void setSaveLevel(String saveLevel) {
		this.saveLevel = saveLevel;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
	public String getStore_page_type() {
		return store_page_type;
	}

	public void setStore_page_type(String store_page_type) {
		this.store_page_type = store_page_type;
	}
	

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
