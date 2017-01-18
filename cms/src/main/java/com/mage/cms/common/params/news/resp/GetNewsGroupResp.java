package com.mage.cms.common.params.news.resp;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.news.vo.News;

public class GetNewsGroupResp extends CmsLineResp implements Serializable{
	private String ExtensionData;
	private String Direction;
	private String Id;
	private String Interval;
	private List<News> News;
	private boolean Roll;
	private Integer ShowTitle;
	private String StoreId;
	private Integer Style;
	private String Title;
	private String Type;
	
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public String getDirection() {
		return Direction;
	}
	public void setDirection(String direction) {
		Direction = direction;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getInterval() {
		return Interval;
	}
	public void setInterval(String interval) {
		Interval = interval;
	}

	public List<News> getNews() {
		return News;
	}
	public void setNews(List<News> news) {
		News = news;
	}
	public boolean isRoll() {
		return Roll;
	}
	public void setRoll(boolean roll) {
		Roll = roll;
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
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
}
