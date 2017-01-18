package com.mage.cms.common.model;

import java.io.Serializable;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.database.NotDbField;

public class AppPage implements Serializable{
	private static final long serialVersionUID = -8136105416564997065L;

	private String id;// appPage ID;
	private String page_name;// 页面名称
	private String description;// 页面描述
	private String page_code;// 页面编码
	private String disable;// 状态
	private String page_type;// 页面类型 'header','second','third','info','ament
	private String page_url;// 页面url
	private String source_from;
	private String keyword;
	private String isshow;
	private String title;
	private String sort;
	private Integer indexId;
	private String device;
	
	
	//add by wui CMS在线编辑,查询扩充字段
	private String page_id;
	private String record_state;
	private String nrecord_state;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage_code() {
		return page_code;
	}

	public void setPage_code(String page_code) {
		this.page_code = page_code;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getPage_type() {
		return page_type;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public String getSource_from() {
		return source_from;
	}

	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}
	
	
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	@NotDbField
	public String getPage_id() {
		return page_id;
	}
	@NotDbField
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	@NotDbField
	public String getRecord_state() {
		return record_state;
	}
	@NotDbField
	public void setRecord_state(String record_state) {
		this.record_state = record_state;
	}
	@NotDbField
	public String getNrecord_state() {
		return nrecord_state;
	}
	@NotDbField
	public void setNrecord_state(String nrecord_state) {
		this.nrecord_state = nrecord_state;
	}

	public String getDevice() {
		if(null == device || device.equals("")){
			CmsContext cmsContext = CmsServiceFactory.getCmsLineService().getCmsContext();
			device = cmsContext.getDevice();
		}
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
	
}
