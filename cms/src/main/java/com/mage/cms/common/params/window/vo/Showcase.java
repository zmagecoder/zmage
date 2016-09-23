package com.mage.cms.common.params.window.vo;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.params.base.resp.CmsLineResp;
/**
 * 
 * @author pzh
 * 橱窗对象
 * 
 */
public class Showcase extends CmsLineResp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String extensionData;
	private Integer direction;
	private Integer goodsStyle;
	private Integer interval;
	private boolean roll;
	private String searchKey;
	private Integer showTitle;
	private Long showcaseId;
	private Long storeId;

	private Integer style;
	private String title;
	private Integer type;
	private List<Label> labels;
	private List<String> searchKeyDisplay;
	
	public String getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(String extensionData) {
		this.extensionData = extensionData;
	}	

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(Integer goodsStyle) {
		this.goodsStyle = goodsStyle;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public boolean getRoll() {
		return roll;
	}

	public void setRoll(boolean roll) {
		this.roll = roll;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(Integer showTitle) {
		this.showTitle = showTitle;
	}

	public Long getShowcaseId() {
		return showcaseId;
	}

	public void setShowcaseId(Long showcaseId) {
		this.showcaseId = showcaseId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	

	public List<String> getSearchKeyDisplay() {
		return searchKeyDisplay;
	}

	public void setSearchKeyDisplay(List<String> searchKeyDisplay) {
		this.searchKeyDisplay = searchKeyDisplay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
