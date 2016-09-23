package com.mage.cms.common.params.template.req;

import java.io.Serializable;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.template.resp.GetStoreTemplateResp;
import com.mage.cms.common.params.template.vo.StoreTemplate;
import com.mage.cms.common.service.IApplicationManager;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.platform.framework.context.SpringContextHolder;
/**
 * @author pzh
 * 切换页面风格
 */
public class GetStoreTemplateReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String device;
	private String save_level;
	private String page_id;
	
	public GetStoreTemplateResp get(){
		IApplicationManager applicationManager = SpringContextHolder.getBean("applicationManager");
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		GetStoreTemplateResp resp = applicationManager.getTemplateByPageId(this.page_id);
		StoreTemplate store_template = resp.getStore_template();
		try{
			if(store_template == null){
				if(cmsService.getCmsContext().getStore_template() != null){
					store_template = cmsService.getCmsContext().getStore_template();
				}else{
					//查询默认模板数据
					store_template  = applicationManager.getAppTemplate().getStore_template();
					cmsService.getCmsContext().setStore_template(store_template);
				}
				resp.setStore_template(store_template);
			}
			resp.setSuccess(true);
		}catch(Exception e){
			resp.setSuccess(false);
		}
		return resp;
	}
	
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getSave_level() {
		return save_level;
	}

	public void setSave_level(String save_level) {
		this.save_level = save_level;
	}
	
	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
