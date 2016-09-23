package com.mage.cms.common.params.window.req;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.window.resp.EditShowcaseResp;
import com.mage.cms.common.params.window.resp.GetShowcaseResp;
import com.mage.cms.common.params.window.vo.Label;
import com.mage.cms.common.params.window.vo.Showcase;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.utils.BeanUtils;
/**
 * 
 * @author pzh
 * 编辑橱窗入参对象
 * 
 */
public class EditShowcaseReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Showcase showcase;

	public Showcase getShowcase() {
		return showcase;
	}

	public void setShowcase(Showcase showcase) {
		this.showcase = showcase;
	}
	
	/**
	 * 编辑橱窗
	 * @return
	 */
	public EditShowcaseResp edit(){
		EditShowcaseResp resp = new EditShowcaseResp();
		Modual modual = this.getEditModualByPluginId(this.showcase.getShowcaseId() + "");
		try{
			modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
			GetShowcaseResp getShowcaseResp = new GetShowcaseResp();
			getShowcaseResp.setShowcase(this.showcase);
			List<Label> labels = this.showcase.getLabels();
			if(null != labels && !labels.isEmpty()){
				for(int i = 0; i < labels.size(); i++){
					String ids = "";
					List<String>idList = labels.get(i).getId();
					for(int j = 0; j < idList.size(); j++){
						if(j < idList.size() - 1){
							ids += idList.get(j) + ",";
						}else {
							ids += idList.get(j);
						}
					}
					if(ids != null && !ids.isEmpty()){
//						List<SimpleGoods> list = getGoodsListbyIds(ids);
//						labels.get(i).setGoods(list);
					}
				}
			}else {	//label为空，默认为修改标题
				String showContentStr = modual.getContent();
				GetShowcaseResp showContentOject = BeanUtils.jsonToBean(showContentStr, GetShowcaseResp.class);
				Showcase shoCaseTitle = showContentOject.getShowcase();
				shoCaseTitle.setTitle(this.showcase.getTitle());
				getShowcaseResp.setShowcase(shoCaseTitle);
			}
			editModual(this.showcase.getShowcaseId() + "",null, BeanUtils.beanToJson(getShowcaseResp),modual);
			resp.setSuccess(true);
		}catch(Exception e){
			resp.setSuccess(false);
		}
		return resp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
