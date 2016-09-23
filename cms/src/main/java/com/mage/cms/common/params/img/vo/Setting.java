package com.mage.cms.common.params.img.vo;

public class Setting {
	private String ExtensionData;
	private String Direction;
	private String Gap;
	private String Interval;
	private String ShowNumber;
	private String ShowText;

	public Setting() {

	}

	public Setting(String ExtensionData, String Direction, String Gap,
			String Interval, String ShowNumber, String ShowText) {
		this.ExtensionData = ExtensionData;
		this.Direction = Direction;
		this.Gap = Gap;
		this.Interval = Interval;
		this.ShowNumber = ShowNumber;
		this.ShowText = ShowText;
	}

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

	public String getGap() {
		return Gap;
	}

	public void setGap(String gap) {
		Gap = gap;
	}

	public String getInterval() {
		return Interval;
	}

	public void setInterval(String interval) {
		Interval = interval;
	}

	public String getShowNumber() {
		return ShowNumber;
	}

	public void setShowNumber(String showNumber) {
		ShowNumber = showNumber;
	}

	public String getShowText() {
		return ShowText;
	}

	public void setShowText(String showText) {
		ShowText = showText;
	}

}
