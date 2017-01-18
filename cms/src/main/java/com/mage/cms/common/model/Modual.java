package com.mage.cms.common.model;

public class Modual {
	private String modual_id;// 模块ID
	private String templete_id;// 模板ID
	private String modual_name;// 名称
	private String modual_code;// 编码
	private String create_time;// 创建时间
	private String state;// 状态 001 待审核 002 审核通过 003 审核失败
	private String source_from;
	private String seq;
	private String modify_time;
	private String audit_time;
	private String reason;
	private String img_path;
	private String html_path;

	
	//add by wui,CMS在线编辑解决方案
	private Integer gridX;
	private Integer gridY;
	private Integer gridCx;
	private Integer gridCy;
	private Long storeLayoutId; //主键生成
	private Long pluginId;//主键生成
	
	private String record_state;
	private String nrecord_state;
	private String save_state;
	
	private String content;
	
	
	private String direction;//滚动方向
	private String interval;//间隔
	private String setting;//设置
	private String showTitle;//显示标题 3
	private String style;//风格,有无边框
	private String title;//标题
	private String type;//类型
	
	private String user_id;
	private String app_id;
	
		
	public String getModual_id() {
		return modual_id;
	}

	public void setModual_id(String modual_id) {
		this.modual_id = modual_id;
	}

	public String getTemplete_id() {
		return templete_id;
	}

	public void setTemplete_id(String templete_id) {
		this.templete_id = templete_id;
	}

	public String getModual_name() {
		return modual_name;
	}

	public void setModual_name(String modual_name) {
		this.modual_name = modual_name;
	}

	public String getModual_code() {
		return modual_code;
	}

	public void setModual_code(String modual_code) {
		this.modual_code = modual_code;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSource_from() {
		return source_from;
	}

	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(String audit_time) {
		this.audit_time = audit_time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getHtml_path() {
		return html_path;
	}

	public void setHtml_path(String html_path) {
		this.html_path = html_path;
	}

	public Integer getGridX() {
		return gridX;
	}

	public void setGridX(Integer gridX) {
		this.gridX = gridX;
	}

	public Integer getGridY() {
		return gridY;
	}

	public void setGridY(Integer gridY) {
		this.gridY = gridY;
	}

	public Integer getGridCx() {
		return gridCx;
	}

	public void setGridCx(Integer gridCx) {
		this.gridCx = gridCx;
	}

	public Integer getGridCy() {
		return gridCy;
	}

	public void setGridCy(Integer gridCy) {
		this.gridCy = gridCy;
	}

	public Long getStoreLayoutId() {
		return storeLayoutId;
	}

	public void setStoreLayoutId(Long storeLayoutId) {
		this.storeLayoutId = storeLayoutId;
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public String getRecord_state() {
		return record_state;
	}

	public void setRecord_state(String record_state) {
		this.record_state = record_state;
	}

	public String getNrecord_state() {
		return nrecord_state;
	}

	public void setNrecord_state(String nrecord_state) {
		this.nrecord_state = nrecord_state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSave_state() {
		return save_state;
	}

	public void setSave_state(String save_state) {
		this.save_state = save_state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}