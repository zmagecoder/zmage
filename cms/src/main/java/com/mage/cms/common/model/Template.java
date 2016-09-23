package com.mage.cms.common.model;

import com.mage.database.NotDbField;

public class Template implements java.io.Serializable{
	
	private static final long serialVersionUID = 1311789274652822886L;
	
	private String template_id;// ID
	private String template_name;// 名称
	private String template_code;// 编码
	private String preview_img_url;// 图片快照预览
	private String preview_file_url;// 文件快照预览
	private String template_page_url;// 模板页面地址
	private String widget_id;// 挂件ID
	private String visible;// 是否可见
	private String edit;// 是否可编辑
	private String move;// 是否移动
	private String state;// 状态
	private String create_time;// 创建时间
	private String sequ;// 序号
	private String source_from;// 来源
	private String template_type;// 模板类型 001 酒店模板 002 娱乐模板 003社区
	private String disabled;// 是否有效
	
	private String hasColumn;
	private String type_name;
	
	private String partner_id;
	
	@NotDbField
	public String getHasColumn() {
		return hasColumn;
	}

	public void setHasColumn(String hasColumn) {
		this.hasColumn = hasColumn;
	}

	@NotDbField
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public String getTemplate_code() {
		return template_code;
	}

	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}

	public String getPreview_img_url() {
		return preview_img_url;
	}

	public void setPreview_img_url(String preview_img_url) {
		this.preview_img_url = preview_img_url;
	}

	public String getPreview_file_url() {
		return preview_file_url;
	}

	public void setPreview_file_url(String preview_file_url) {
		this.preview_file_url = preview_file_url;
	}

	public String getTemplate_page_url() {
		return template_page_url;
	}

	public void setTemplate_page_url(String template_page_url) {
		this.template_page_url = template_page_url;
	}

	public String getWidget_id() {
		return widget_id;
	}

	public void setWidget_id(String widget_id) {
		this.widget_id = widget_id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getSequ() {
		return sequ;
	}

	public void setSequ(String sequ) {
		this.sequ = sequ;
	}

	public String getSource_from() {
		return source_from;
	}

	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}

	public String getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@NotDbField
	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
	
}
