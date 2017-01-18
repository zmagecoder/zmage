package com.mage.cms.common.service.impl;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.common.params.template.req.EditStoreTemplateReq;
import com.mage.cms.common.params.template.resp.GetStoreTemplateResp;
import com.mage.cms.common.service.IApplicationManager;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.database.BaseSupport;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.platform.framework.utils.MapUtils;

@Service
public class ApplicationManager extends BaseSupport<Object> implements IApplicationManager{

	@Override
	public GetStoreTemplateResp getTemplateByPageId(String page_id) {
		GetStoreTemplateResp resp = new GetStoreTemplateResp();
		//TODO 根据页面获取模板
		resp.setSuccess(false);
		return resp;
	}

	@Override
	public GetStoreTemplateResp getAppTemplate() {
		GetStoreTemplateResp resp = new GetStoreTemplateResp();
		try{
			String sql = "select a.app_name, a.style_tpl from cms_application a where a.app_code = 'ZMAGE' "
					+ "and a.device = " + CmsConst.DEVICE_0 + " and a.disabled = " + CmsConst.DISABLE_0;
			Map<String, Object> appTpl = this.getExecutor().queryForMap(sql);
			if(null != appTpl){
				String templateStr = MapUtils.getString(appTpl, "style_tpl", "");
				EditStoreTemplateReq temp = BeanUtils.jsonToBean(templateStr, EditStoreTemplateReq.class); 
				resp.setStore_template(temp.getTemplate());
				resp.setSuccess(true);
			}
		}catch(Exception e){
			resp.setSuccess(false);
			e.printStackTrace();
		}
		return resp;
	}
	
}
