package com.mage.cms.common.model;

/**
 * 用户模块关系表
 * @author hu.yi
 * @date 2014.04.28
 */
public class ModualRel {

	private String patner_id;
	private String modual_id;
	private String partner_type;
	private String page_id;
	private String source_from;
	
	
	public String getPatner_id() {
		return patner_id;
	}
	public void setPatner_id(String patner_id) {
		this.patner_id = patner_id;
	}
	public String getModual_id() {
		return modual_id;
	}
	public void setModual_id(String modual_id) {
		this.modual_id = modual_id;
	}
	public String getPartner_type() {
		return partner_type;
	}
	public void setPartner_type(String partner_type) {
		this.partner_type = partner_type;
	}
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getSource_from() {
		return source_from;
	}
	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}
}
