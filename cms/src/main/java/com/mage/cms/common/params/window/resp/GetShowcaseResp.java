package com.mage.cms.common.params.window.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.window.vo.Showcase;

/**
 * 
 * @author pzh
 * 读取橱窗入出参对象
 *
 */
public class GetShowcaseResp extends CmsLineResp implements Serializable{
	private Showcase showcase;

	public Showcase getShowcase() {
		return showcase;
	}

	public void setShowcase(Showcase showcase) {
		this.showcase = showcase;
	}
	
}
