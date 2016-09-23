package com.mage.cms.common.params.news.vo;

import java.util.List;

public class TagClass {
	private List<TagClassInfo> tag_class_info;
	private String tag_class_sum;private String tag_item_sum;
	private String ExtensionData;
	private String error;
	private String result;
	public List<TagClassInfo> getTag_class_info() {
		return tag_class_info;
	}
	public void setTag_class_info(List<TagClassInfo> tag_class_info) {
		this.tag_class_info = tag_class_info;
	}
	public String getTag_class_sum() {
		return tag_class_sum;
	}
	public void setTag_class_sum(String tag_class_sum) {
		this.tag_class_sum = tag_class_sum;
	}
	public String getTag_item_sum() {
		return tag_item_sum;
	}
	public void setTag_item_sum(String tag_item_sum) {
		this.tag_item_sum = tag_item_sum;
	}
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
