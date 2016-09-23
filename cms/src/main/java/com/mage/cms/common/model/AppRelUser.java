package com.mage.cms.common.model;

import com.mage.database.NotDbField;

/**
 * 用户应用关联表
 * @author pzh
 */
public class AppRelUser {

	private String rel_id;
	private String user_id;
	private String app_id;
	private String user_type;
	private String user_name;
	private String app_update;
	private String app_name;
	private String source_from;
	private String partner_id;
	
	
	public String getRel_id() {
		return rel_id;
	}
	public void setRel_id(String rel_id) {
		this.rel_id = rel_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	@NotDbField
	public String getUser_name() {
		return user_name;
	}
	@NotDbField
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSource_from() {
		return source_from;
	}
	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}
	public String getApp_update() {
		return app_update;
	}
	public void setApp_update(String app_update) {
		this.app_update = app_update;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
}
