package com.mage.cms.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mage.cms.common.model.CmsWidget;
import com.mage.cms.common.model.Modual;
import com.mage.cms.common.model.Template;
import com.mage.cms.common.params.news.vo.Page;


/**
 * 模板操作公共类
 * @author pzh
 * @date 2016年6月3日 下午2:51:28
 */
public interface ICmsBaseManager {

	/**
	 * 保存、修改
	 * 如果是修改 页面上需要传入主键  命名为column_content_id
	 * 此时状态为待审核
	 * @param request
	 */
	public void saveColumnContent(HttpServletRequest request,Map<String, Object> map);
	
	/**
	 * 插件桩列表
	 * @param pageNo
	 * @param pageSize
	 * @param cmsWidget
	 * @return
	 */
	public Page listWidget(int pageNo,int pageSize,CmsWidget cmsWidget);
	
	/**
	 * 根据id查询插件桩实体
	 * @param widget_id
	 * @return
	 */
	public CmsWidget getCmsWidgetById(String widget_id);
	
	/**
	 * 保存、修改插件桩
	 * @param template
	 * @param cmsWidget
	 */
	public String saveWidegetInfo(Template template,CmsWidget cmsWidget);
	
	
	/**
	 * 根据模板id查询相应的处理bean
	 * @param tpl_id
	 * @return
	 */
	public String getOwnBean(String tpl_id);
	
	
	/**
	 * 审核模块
	 * @param modual
	 */
	public void auditColumnContent(Modual modual);
	
	
	/**
	 * CMS在线编辑解决方案--根据pluginId查询modual
	 * @param storeId
	 * @return
	 */
	public List<Modual> getModualByPluginId(String pluginId);
	
	
	/**
	 * CMS在线编辑解决方案--根据storeLayoutId查询modual
	 * @param storeLayoutId
	 * @return
	 */
	public List<Modual> getModualByStoreLayoutId(String storeLayoutId);
	
	
	/**
	 * CMS在线编辑解决方案--根据storeLayoutId获取pluginType
	 * @param storeLayoutId
	 * @return
	 */
	public String getPluginTypeByStoreLayoutId(String storeLayoutId);
	
	
	/**
	 * CMS在线编辑解决方案--根据pluginId获取pluginType
	 * @param pluginId
	 * @return
	 */
	public String getPluginTypeByPluginId(String pluginId);
	
	
	/**
	 * CMS在线编辑解决方案--根据pageId获取发布页面的基本编辑信息
	 * @param pageId
	 * @return
	 */
	public List<Map<String, Object>> getModualBaseByPageId(String pageId);
	
	
	/**
	 * CMS在线编辑解决方案--发布时物理删除数据
	 * @param pluginId
	 */
	public void deleteModualByPluginId(String pluginId);
	
	
	/**
	 * CMS在线编辑解决方案--恢复时物理删除未发布数据
	 */
	public void recoverPage(String pageId);
	
	
	/**
	 * CMS在线编辑解决方案--保存时加上保存标志位
	 * @param pluginId
	 */
	public void savePageModual(String pluginId);
}
