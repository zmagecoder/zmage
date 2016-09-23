package com.mage.cms.common.params.img.vo;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.params.base.resp.CmsLineResp;

public class Advertising extends CmsLineResp implements Serializable{
	private String ExtensionData;
	private List<Adv> Advertising;
	private Integer Direction;
	private String Id;
	private Integer Interval;
	private Setting Setting;
	private Integer ShowTitle;
	private String StoreId;
	private Integer Style;
	private String Title;
	private Integer Type;
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public List<Adv> getAdvertising() {
		return Advertising;
	}
	public void setAdvertising(List<Adv> advertising) {
		Advertising = advertising;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Setting getSetting() {
		return Setting;
	}
	public void setSetting(Setting setting) {
		Setting = setting;
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
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Integer getStyle() {
		return Style;
	}
	public void setStyle(Integer style) {
		Style = style;
	}
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}
	public Integer getDirection() {
		return Direction;
	}
	public void setDirection(Integer direction) {
		Direction = direction;
	}
	public Integer getInterval() {
		return Interval;
	}
	public void setInterval(Integer interval) {
		Interval = interval;
	}
	
}
