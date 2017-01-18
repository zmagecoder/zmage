package com.mage.cms.common.service;

import java.util.List;
import java.util.Map;

import com.mage.cms.common.model.Template;
import com.mage.cms.common.model.Widget;
import com.mage.cms.common.params.news.vo.Page;

/**
 * @author hu.yinghao
 *
 */
public interface ITemplateManager {
	
	/**行业用户查询可用的模版
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryList(Map<String,String> map, int pageNo, int pageSize);
	
	/**用户查询对应的APP
	 * @param param
	 * @return
	 */
	public List queryUserApp();
	
	public Template getTpl(String tpl_id);
	public void insertTpl(Template template);
	public void updateTpl(Template template);
	public void insertTplColumnRel(String column_id,String template_id,int sort,String c_code);
	public List<Map> queryDefaultColumn();
	
	public Template getTplByPluginType(String plugin_type);
	//模板对应栏目详情
	public Page columnRelateList(Map<String,String> param,int pageNo, int pageSize);
	public  void  addTemplateColumn(Map<String,Object>param);
	public Widget getWidgetByWidgetId(String widget_id);
	
	public Page queryPageTmplForPage(int pageNo,int pageSize,String tmpl_name,String tmpl_type);
	
	public int countTmplColumns(String tmpl_id);
	
	public Page getPicMapperPage(String tpl_id, Integer pageNo, Integer pageSize);
}
