package com.mage.cms.common.params.photo.vo;

public class Crop {
	private String ExtensionData;
	private String x1;
	private String y1;
	private String x2;
	private String y2;
	private String quality;
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public String getX1() {
		return x1;
	}
	public void setX1(String x1) {
		this.x1 = x1;
	}
	public String getY1() {
		return y1;
	}
	public void setY1(String y1) {
		this.y1 = y1;
	}
	public String getX2() {
		return x2;
	}
	public void setX2(String x2) {
		this.x2 = x2;
	}
	public String getY2() {
		return y2;
	}
	public void setY2(String y2) {
		this.y2 = y2;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
}
