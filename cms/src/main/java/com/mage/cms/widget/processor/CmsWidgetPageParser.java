package com.mage.cms.widget.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.params.template.resp.GetStoreTemplateResp;
import com.mage.cms.common.params.template.vo.StoreTemplate;
import com.mage.cms.common.service.IApplicationManager;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.cms.consts.CmsConst;
import com.mage.cms.utils.CmsUtil;
import com.mage.platform.framework.context.SessionContextHolder;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.platform.framework.widget.processor.IWidget;
import com.mage.platform.framework.widget.processor.IWidgetPageParser;
import com.mage.platform.framework.widget.processor.IWidgetParamParser;

/**
 * 挂件界面解析器
 * @author pzh
 * @date 2016年6月14日 下午2:34:20
 */
@Service
public class CmsWidgetPageParser implements IWidgetPageParser {
	
	private static final Log log = LogFactory.getLog(CmsWidgetPageParser.class);
	
	@Resource
	private IWidgetParamParser widgetParamParser;
	
	@Override
	public void parse(String path, Model model) {
		Map<String, Map<String, Map<String,String>>> pages = widgetParamParser.parse();
		if(null == pages || pages.isEmpty()){
			log.error("================>>界面[" + path + "]，没有定义插件");
			return;
		}
		
		Map<String, Map<String,String>> widgets = pages.get(path);				//获取当前页面用到的插件
		if(null != widgets && !widgets.isEmpty()){
			Set<String> widgetSet = widgets.keySet();
			for (String key : widgetSet) {
				IWidget widgetBean = SpringContextHolder.getBean(key);
				if(widgetBean == null)
					throw new RuntimeException("=====================>>插件[" + key + "]未定义");
				Map<String,String> widget = widgets.get(key);					//插件配置
				String widgetContent = widgetBean.process(widget);				//解析
				model.addAttribute("widget_" + key, widgetContent);
			}
		}
		
		model.addAllAttributes(this.getCmsParams());
	}
	
	/**
	 * 获取cms 特有的变量
	 * @author pzh
	 * @date 2016年6月20日 下午5:20:28
	 */
	private Map<String,Object> getCmsParams(){
		Map<String, Object> cmsParams = new HashMap<String, Object>();
		cmsParams.put("page_device", CmsConst.STORE_PAGE_TYPE_0);
		cmsParams.put("is_edit", true);
		cmsParams.put("bar_type", CmsConst.BAR_TYPE_ADMIN_DESIGN);
		//获取页面模板
		StoreTemplate store_template = this.getStoreTemplate();
		String tmp_style = "";
		if(null != store_template)
			tmp_style = CmsUtil.firstToUpperCase(BeanUtils.beanToJsonNCls(store_template));
		cmsParams.put("tmp_style", tmp_style);
		cmsParams.put("store_template", store_template);
		cmsParams.put("isLogin", false);
		cmsParams.put("is_preview", false);
		return cmsParams;
	}
	
	
	/**
	 * 页面模板：每个页面的模板不同，
	 * 每次都要重新加载模板数据，
	 * 没有则取默认模板
	 * @return StoreTemplate
	 */
	private StoreTemplate getStoreTemplate(){
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		GetStoreTemplateResp tmpResp = null;
		StoreTemplate store_template = (StoreTemplate)SessionContextHolder.getInstance().
				getAttribute(CmsConst.CMS_STORE_TEMPLATE_ + this.getPageId());
		if(null == store_template){	//session 中不存在则查询
			IApplicationManager applicationManager = SpringContextHolder.getBean("applicationManager");
			tmpResp = applicationManager.getTemplateByPageId(this.getPageId());
			if(tmpResp.isSuccess())
				store_template = tmpResp.getStore_template();
			if(null == store_template){
				if(cmsService.getCmsContext().getStore_template() != null){
					store_template = cmsService.getCmsContext().getStore_template();
				}else{
					//查询默认模板数据
					store_template  = applicationManager.getAppTemplate().getStore_template();
					cmsService.getCmsContext().setStore_template(store_template);
				}
			}
			if(null !=store_template)
				SessionContextHolder.getInstance().setAttribute(CmsConst.CMS_STORE_TEMPLATE_ + this.getPageId(), store_template);
		}
		if(null != store_template)
			store_template.getFooter().replace("'", "\\'");
		return store_template;
	}
	
	private String getPageId(){
		
		//TODO 从前端参数中获取
		
		return "";
	}
}
