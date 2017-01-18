package com.mage.cms.common.params.nav.vo;

import com.mage.cms.common.params.base.resp.CmsLineResp;

public class Navigation extends CmsLineResp{
	private String ExtensionData;
	private Integer ClassId;
	private Integer ClassLevel;
	private Content Content;
	private String ContentJson;
	private String NavId;
	private Integer ShowTitle;
	private String StoreId;
	private Integer Style;
	private String Title;
	private Integer Type;
	
	//静态模式使用
	private StoreNav storeNav;

	public Navigation() {

	}

	public Navigation(String ExtensionData, Integer ClassId, Integer ClassLevel,
			Content Content, String ContentJson, String NavId,
			Integer ShowTitle, String StoreId, Integer Style, String Title,
			Integer Type) {
		this.ExtensionData = ExtensionData;
		this.ClassId = ClassId;
		this.ClassLevel = ClassLevel;
		this.Content = Content;
		this.ContentJson = ContentJson;
		this.NavId = NavId;
		this.ShowTitle = ShowTitle;
		this.StoreId = StoreId;
		this.Style = Style;
		this.Title = Title;
		this.Type = Type;
	}

	public String getExtensionData() {
		return ExtensionData;
	}

	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}

	public Integer getClassId() {
		return ClassId;
	}

	public void setClassId(Integer classId) {
		ClassId = classId;
	}

	public Integer getClassLevel() {
		return ClassLevel;
	}

	public void setClassLevel(Integer classLevel) {
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

	public Integer getStyle() {
		return Style;
	}

	public void setStyle(Integer style) {
		Style = style;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public StoreNav getStoreNav() {
		return storeNav;
	}

	public void setStoreNav(StoreNav storeNav) {
		this.storeNav = storeNav;
	}	

}
