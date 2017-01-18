package com.mage.cms.common.params.news.vo;

import java.io.Serializable;

public class Page implements Serializable{
	private String ExtensionData;
	private String Description;
	private String Device;
	private String IndexId;
	private String IsShow;
	private String Keyword;
	private String Name;
	private String Order;
	private String PageId;
	private String SaveLevel;
	private String StoreId;
	private String Title;
	private String Type;
	public Page(){
		
	}
	
	public Page(String ExtensionData,String Description,String Device,String IndexId,String IsShow,String Keyword,String Name,String Order,String PageId,String SaveLevel,String StoreId,String Title,String Type ){
		this.ExtensionData=ExtensionData;
		this.Description=Description;
		this.Device=Device;
		this.IndexId=IndexId;
		this.IsShow=IsShow;
		this.Keyword=Keyword;
		this.Name=Name;
		this.Order=Order;
		this.PageId=PageId;
		this.SaveLevel=SaveLevel;
		this.StoreId=StoreId;
		this.Title=Title;
		this.Type=Type;
	}
	
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDevice() {
		return Device;
	}
	public void setDevice(String device) {
		Device = device;
	}
	public String getIndexId() {
		return IndexId;
	}
	public void setIndexId(String indexId) {
		IndexId = indexId;
	}

	public String getIsShow() {
		return IsShow;
	}

	public void setIsShow(String isShow) {
		IsShow = isShow;
	}

	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public String getPageId() {
		return PageId;
	}
	public void setPageId(String pageId) {
		PageId = pageId;
	}
	public String getSaveLevel() {
		return SaveLevel;
	}
	public void setSaveLevel(String saveLevel) {
		SaveLevel = saveLevel;
	}
	public String getStoreId() {
		return StoreId;
	}
	public void setStoreId(String storeId) {
		StoreId = storeId;
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
