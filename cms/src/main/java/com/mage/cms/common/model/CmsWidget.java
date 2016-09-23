package com.mage.cms.common.model;

import com.mage.database.NotDbField;

/**
 * 插件桩实体
 */
public class CmsWidget {

	private String source_from;
	private String widget_id;
	private String code;		//编码
	private String type;		//前台bean
	private String cms_type;	//后台bean
	private String visible;
	private String edit;
	private String move;
	private String widget_url;
	private String cache;
	private String state;
	private String create_time;
	
	
	private String template_name;
	private String start_time;
	private String end_time;
	private String widget_name;
	private String params;
	private String widget_image;
	
	
	public String getWidget_image() {
		
		return widget_image;
	}
	public void setWidget_image(String widget_image) {
		this.widget_image = widget_image;
	}
	public String getSource_from() {
		return source_from;
	}
	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}
	public String getWidget_id() {
		return widget_id;
	}
	public void setWidget_id(String widget_id) {
		this.widget_id = widget_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCms_type() {
		return cms_type;
	}
	public void setCms_type(String cms_type) {
		this.cms_type = cms_type;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getMove() {
		return move;
	}
	public void setMove(String move) {
		this.move = move;
	}
	public String getWidget_url() {
		return widget_url;
	}
	public void setWidget_url(String widget_url) {
		this.widget_url = widget_url;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@NotDbField
	public String getTemplate_name() {
		return template_name;
	}
	@NotDbField
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	@NotDbField
	public String getStart_time() {
		return start_time;
	}
	@NotDbField
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	@NotDbField
	public String getEnd_time() {
		return end_time;
	}
	@NotDbField
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getWidget_name() {
		return widget_name;
	}
	public void setWidget_name(String widget_name) {
		this.widget_name = widget_name;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
