package com.mage.cms.common.model;

import java.io.Serializable;

import com.mage.database.NotDbField;

public class Application implements Serializable{
	
	private static final long serialVersionUID = 7635976043188753020L;
	
	private String app_id;//应用id
	private String app_name;//应用名称
	private String app_code;//应用编码
	private String description;//描述
	private String disabled;//状态
	private String outer_app_code;//外系统行业编码
	private String app_img_path;//应用图片路径
	private String create_time;
	private String source_from;
	private String app_package;
	private String style_tpl;			//应用默认模板数据
	

	private Long rel_id;
	private String user_id;
	
	//add by wui 
	private Long save_level; //cms在线编辑使用
	private Long device;
	private Long type;
	
	private AppPage appPage;
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getApp_code() {
		return app_code;
	}
	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getOuter_app_code() {
		return outer_app_code;
	}
	public void setOuter_app_code(String outer_app_code) {
		this.outer_app_code = outer_app_code;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getSource_from() {
		return source_from;
	}
	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}
	@NotDbField
	public Long getRel_id() {
		return rel_id;
	}
	@NotDbField
	public void setRel_id(Long rel_id) {
		this.rel_id = rel_id;
	}
	public Long getSave_level() {
		return save_level;
	}
	public void setSave_level(Long save_level) {
		this.save_level = save_level;
	}
	public Long getDevice() {
		return device;
	}
	public void setDevice(Long device) {
		this.device = device;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	
	@NotDbField
	public String getUser_id() {
		return user_id;
	}
	@NotDbField
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@NotDbField
	public AppPage getAppPage() {
		return appPage;
	}
	@NotDbField
	public void setAppPage(AppPage appPage) {
		this.appPage = appPage;
	}
	public String getApp_img_path() {
		return app_img_path;
	}
	public void setApp_img_path(String app_img_path) {
		this.app_img_path = app_img_path;
	}
	public String getApp_package() {
		return app_package;
	}
	public void setApp_package(String app_package) {
		this.app_package = app_package;
	}
	public String getStyle_tpl() {
		return style_tpl;
	}
	public void setStyle_tpl(String style_tpl) {
		this.style_tpl = style_tpl;
	}
	
}
