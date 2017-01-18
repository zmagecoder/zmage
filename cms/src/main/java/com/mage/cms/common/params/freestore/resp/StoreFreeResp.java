package com.mage.cms.common.params.freestore.resp;

import com.mage.cms.common.params.base.resp.CmsLineResp;


/**
 * get方法内含对象 与EditStoreLayOutEeq 内容相同，数据类型不同
 * @author hu.yi
 * @date 2014.06.19
 */
public class StoreFreeResp extends CmsLineResp{

	private String content;
	private String extensionData;
	private String freeId;
	private Integer showTitle;
	private String storeId;
	private String style;
	private String title;
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExtensionData() {
		return extensionData;
	}
	public void setExtensionData(String extensionData) {
		this.extensionData = extensionData;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFreeId() {
		return freeId;
	}
	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Integer getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(Integer showTitle) {
		this.showTitle = showTitle;
	}
}
