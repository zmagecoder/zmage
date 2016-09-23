package com.mage.cms.common.params.base.resp;

/**
 * app类型实体
 */
public class AppType extends CmsLineResp{

	private String extensionData;
	private String id;
	private String name;
	private String remark;
	
	
	public String getExtensionData() {
		return extensionData;
	}
	public void setExtensionData(String extensionData) {
		this.extensionData = extensionData;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
