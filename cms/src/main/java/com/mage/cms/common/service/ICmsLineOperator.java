package com.mage.cms.common.service;

import java.util.List;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.AddStoreLayOutReq;
import com.mage.cms.common.params.base.req.DeleteStoreLayOutReq;
import com.mage.cms.common.params.base.req.GetStoreLayOutReq;
import com.mage.cms.common.params.base.req.UpdateStoreLayoutReq;
import com.mage.cms.common.params.base.resp.AddStoreLayOutResp;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.DeleteStoreLayoutResp;
import com.mage.cms.common.params.base.resp.GetStoreLayOutResp;
import com.mage.cms.common.params.base.resp.PublishrStoreLayOutResp;
import com.mage.cms.common.params.base.resp.UpdateStoreLayoutResp;
import com.mage.cms.common.params.pagestore.req.AddStorePageReq;
import com.mage.cms.common.params.pagestore.req.DeleteStorePageReq;
import com.mage.cms.common.params.pagestore.req.GetStorePageReq;
import com.mage.cms.common.params.pagestore.req.RecoverStorePageReq;
import com.mage.cms.common.params.pagestore.req.SaveStorePageReq;
import com.mage.cms.common.params.pagestore.req.UpdateStorePageReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
import com.mage.cms.common.params.pagestore.resp.DeleteStorePageResp;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.pagestore.resp.RecoverStorePageResp;
import com.mage.cms.common.params.pagestore.resp.SaveStorePageResp;
import com.mage.cms.common.params.pagestore.resp.UpdateStorePageResp;
import com.mage.cms.common.parser.CmsLayOutContentPraser;

/**
 * cms 核心处理器
 * @author pzh
 * @date 2016年6月3日 下午2:45:03
 */
public interface ICmsLineOperator {
	
	/**
	 * 获取页面列表
	 * @author pzh
	 */
	public GetStorePageResp getStorePage(GetStorePageReq req)throws Exception;
	
	/**
	 * 获取页面模块数据
	 * @param cmsReq
	 * @return
	 * @throws Exception 
	 */
	public GetStoreLayOutResp getPageStoreLayout(GetStoreLayOutReq cmsReq) throws Exception;
	
	
	
	
	
	
	
	

	/**
	 * 页面拖拽添加模板
	 * @param pzh
	 */
	public AddStoreLayOutResp addStoreLayOut(CmsLayOutContentPraser cntParser,AddStoreLayOutReq addStoreLayOutReq) throws Exception;
	
	/**
	 * 页面新增模板
	 * @author pzh
	 */
	public AddStorePageResp addStorePage(AddStorePageReq req)throws Exception;
	
	/**
	 * 更新菜单导航
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public UpdateStorePageResp updateStorePage(UpdateStorePageReq req)throws Exception;
	
	/**
	 * 删除导航菜单
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public DeleteStorePageResp deleteStorePage(DeleteStorePageReq req)throws Exception;
	
	/**
	 * 保存操作
	 * @author pzh
	 * @date 2016年6月3日 下午2:46:31
	 * @param req
	 * @return
	 */
	public SaveStorePageResp saveStorePage(SaveStorePageReq req);
	
	/**
	 * 恢复操作
	 * @author pzh
	 * @date 2016年6月3日 下午2:45:55
	 * @param req
	 * @return
	 */
	public RecoverStorePageResp recoverStorePage(RecoverStorePageReq req);
	
	/**
	 * 更新页面布局
	 * @param cmsReq
	 * @return
	 * @throws Exception 
	 */
	public UpdateStoreLayoutResp updateStoreLayout(UpdateStoreLayoutReq cmsReq) throws Exception;
	
	/**
	 * 获取编辑时的modual对象
	 * @param pluginid
	 * @return
	 */
	public Modual getEditModualByPluginId(String pluginid);
	
	/**
	 * 获取编辑时的modual对象
	 * @param storeLayoutId
	 * @return
	 */
	public Modual getEditModualByStoreLayoutId(String storeLayoutId);
	
	/**
	 * 从modual列表中取出当前需要操作的modual对象
	 * @param list
	 * @return
	 */
	public Modual getSingleModual(List<Modual> list);
	
	/**
	 * 页面拖拽修改模板
	 * @param editStoreLayOutReq
	 * @param modual
	 */
	public void editModual(String pluginId,String storeLayoutId,String content,Modual modual) throws Exception;
	
	/**
	 * 查询最新的返回对象
	 * @param cmsReq
	 * @return 
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	public CmsLineResp getRecentStoreLayout(String pluginId, Class<?> clazz) throws Exception;
	
	/**
	 * 删除当前模块
	 * @param cmsReq
	 * @return
	 * @throws Exception
	 */
	public DeleteStoreLayoutResp deleteStoreLayOut(DeleteStoreLayOutReq cmsReq) throws Exception;
	
	/**
	 * 发布页面
	 * @return
	 * @throws Exception
	 */
	public PublishrStoreLayOutResp publishStoreLayOut() throws Exception;
	
	/**
	 * 发布当前页面集合
	 * @param pageIds
	 */
	public void publishStoreModuals(List<String> pageIds) throws Exception;
	
	/**
	 * 恢复页面模块,将非正式数据全部删除
	 * @param pageIds
	 */
	public void recoverStoreModual(List<String> pageIds);
	
	/**
	 * 保存页面模板数据
	 * @param pageIds
	 */
	public void saveStoreModual(List<String> pageIds);
}
