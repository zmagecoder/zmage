package com.mage.cms.common.params.template.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.template.vo.StoreTemplate;

/**
 * 
 * @author pzh
 * 编辑橱窗 出参
 *
 */
public class GetStoreTemplateResp extends CmsLineResp implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private StoreTemplate store_template;

	public StoreTemplate getStore_template() {
		return store_template;
	}

	public void setStore_template(StoreTemplate store_template) {
		this.store_template = store_template;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
