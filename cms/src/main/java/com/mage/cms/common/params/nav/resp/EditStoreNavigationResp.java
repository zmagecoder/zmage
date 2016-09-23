package com.mage.cms.common.params.nav.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.nav.vo.Content;

public class EditStoreNavigationResp extends CmsLineResp implements Serializable{
	private String ExtensionData;
	private String ClassId;
	private String ClassLevel;
	private Content Content;
	private String ContentJson;
	private String NavId;
	private Integer ShowTitle;
	private String StoreId;
	private String Style;
	private String Title;
	private String Type;
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public String getClassId() {
		return ClassId;
	}
	public void setClassId(String classId) {
		ClassId = classId;
	}
	public String getClassLevel() {
		return ClassLevel;
	}
	public void setClassLevel(String classLevel) {
		ClassLevel = classLevel;
	}
	public Content getContent() {
		return Content;
	}
	public void setContent(Content content) {
		Content = content;
	}
	public String getContentJson() {
		return ContentJson;
	}
	public void setContentJson(String contentJson) {
		ContentJson = contentJson;
	}
	public String getNavId() {
		return NavId;
	}
	public void setNavId(String navId) {
		NavId = navId;
	}
	public Integer getShowTitle() {
		return ShowTitle;
	}
	public void setShowTitle(Integer showTitle) {
		ShowTitle = showTitle;
	}
	public String getStoreId() {
		return StoreId;
	}
	public void setStoreId(String storeId) {
		StoreId = storeId;
	}
	public String getStyle() {
		return Style;
	}
	public void setStyle(String style) {
		Style = style;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	

}
