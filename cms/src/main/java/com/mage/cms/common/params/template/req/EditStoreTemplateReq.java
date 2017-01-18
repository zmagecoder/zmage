package com.mage.cms.common.params.template.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.template.resp.EditStoreTemplateResp;
import com.mage.cms.common.params.template.vo.StoreTemplate;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.context.ThreadContextHolder;
/**
 * 
 * @author pzh
 * 切换页面风格
 */
public class EditStoreTemplateReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	private StoreTemplate template;
	private String page_id;
	
	/**
	 * 切换页面风格
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EditStoreTemplateResp edit(){
		EditStoreTemplateResp resp = new EditStoreTemplateResp();
		try {
//			String sql = SF.cmsSql("QRY_MODUAL_BY_PAGE_BY_PAGE_ID");
//			this.baseDaoSupport = SpringContextHolder.getBean("baseDaoSupport");
//			List<Modual> modualList = this.baseDaoSupport.queryForList(sql, Modual.class, page_id);
//			if(null == modualList || modualList.isEmpty()){		//页面没有对应的模板,新增模板
//				//构建模板数据
//				Long pluginId = new Long(this.baseDaoSupport.nextVal("SEQ_CMS_PAGE_REL_TPL_PLUGINID"));
//				Long storeLayoutId = new Long(this.baseDaoSupport.nextVal("SEQ_CMS_PAGE_REL_TPL_STROUTID"));
//				Modual modual = CmsUtil.getBaseModual();
//				modual.setModual_id(String.valueOf(this.baseDaoSupport.nextVal("S_ES_CMS_MODUAL")));
//				modual.setPluginId(pluginId);
//				modual.setStoreLayoutId(storeLayoutId);
//				modual.setType(CmsConst.CMS_MODUAL_TYPE_8888);
//				modual.setGridCx(1);
//				modual.setGridCy(4);
//				modual.setGridX(1);
//				modual.setGridY(0);
//				//构建content字段对象
//				String content = BeanUtils.beanToJson(this);//getBeanUtils.beanToJson(getStoreFreeResp);
//				modual.setContent(content);
//				this.baseDaoSupport.insert("es_cms_modual", modual);
//				
//				//关联页面
//				ModualRel userModual  =new ModualRel();
//				userModual.setPage_id(this.page_id);
//				userModual.setPartner_type(CmsConst.CMS_USER_MODUAL_PARTNER_TYPE_2);
//				userModual.setPatner_id(ManagerUtils.getAdminUser().getUserid());
//				userModual.setModual_id(modual.getModual_id());
//				this.baseDaoSupport.insert("es_cms_user_rel_modual", userModual);
				
//			}else {
				//获取编辑模式下的模块内容
//				Modual modual = getSingleModual(modualList);
//				modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
//				this.getTemplate().getTemplateId();
//				editModual(modual.getPluginId() + "", null, BeanUtils.beanToJson(this), modual);
//			}
			//设置缓存
			ThreadContextHolder.getSessionContext().setAttribute(CmsConst.CMS_STORE_TEMPLATE_ + this.page_id, this.template);
			resp.setSuccess(true);
		} catch (Exception e) {
			resp.setSuccess(false);
			e.printStackTrace();
		}
		return resp;
	}
	
	public StoreTemplate getTemplate() {
		return template;
	}

	public void setTemplate(StoreTemplate template) {
		this.template = template;
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
