package com.mage.cms.common.model;

import java.io.Serializable;

/**
 * widget模块
 * @作者 MoChunrun
 * @创建日期 2014-7-15 
 * @版本 V 1.0
 */
public class CmsWidgetModule implements Serializable {

	private String module_id;
	private String module_name;
	private String hint;
	private String source_from;
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getSource_from() {
		return source_from;
	}
	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}
	
}
