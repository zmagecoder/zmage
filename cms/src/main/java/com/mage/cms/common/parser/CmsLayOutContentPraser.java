package com.mage.cms.common.parser;

import com.mage.cms.common.model.Modual;

/**
 * @author pzh
 * 添加页面模板，内容解析器
 * 
 */
public  abstract class CmsLayOutContentPraser{
	public CmsLayOutContentPraser(){}
	public abstract String genModualCnt(Modual modual);
}
