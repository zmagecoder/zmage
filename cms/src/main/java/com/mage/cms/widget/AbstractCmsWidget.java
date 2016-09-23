package com.mage.cms.widget;

import java.util.Map;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.platform.framework.widget.processor.impl.AbstractWidget;

/**
 * cms 挂件基类
 * @author pzh
 * @date 2016年6月20日 下午1:44:30
 */
abstract public class AbstractCmsWidget extends AbstractWidget{

	public StoreLayOutResp storeLay;				//cms 模板存储类
	
	/**
	 * 转出content里面包含的实体对象 即content的入参包含对象
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public CmsLineResp getRespByContent(String jsonReqStr) throws Exception;
	
	/**
	 * 静态模式构造静态参数对象
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp) throws Exception;
	
	/**
	 * 挂件配置处理方法
	 * 
	 * @param params
	 *            挂件参数
	 */
	abstract protected  void config(Map<String, String> params);
	
	/**
	 * 新增插件
	 * @author pzh
	 * @date 2016年6月20日 下午2:50:57
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public CmsLineResp add(String jsonReqStr) throws Exception;

	/**
	 * 删除插件
	 * @author pzh
	 * @date 2016年6月20日 下午2:50:57
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public CmsLineResp delete(String jsonReqStr) throws Exception;
	
	/**
	 * 编辑插件
	 * @author pzh
	 * @date 2016年6月20日 下午2:50:57
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public CmsLineResp edit(String jsonReqStr) throws Exception;
	
	/**
	 * 查询插件
	 * @author pzh
	 * @date 2016年6月20日 下午2:51:45
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public CmsLineResp get(String jsonReqStr) throws Exception;
	
	abstract public CmsLineResp update(String jsonReqStr) throws Exception;
	
	/**
	 * 发布插件
	 * @author pzh
	 * @date 2016年6月20日 下午2:52:01
	 * @param jsonReqStr
	 * @return
	 * @throws Exception
	 */
	abstract public CmsLineResp publish(String jsonReqStr) throws Exception;

}
