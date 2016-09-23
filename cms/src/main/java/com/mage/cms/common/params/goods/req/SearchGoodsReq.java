package com.mage.cms.common.params.goods.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.goods.resp.SearchGoodsResp;
import com.mage.cms.common.params.goods.vo.SearchGoodsOption;
import com.mage.cms.common.params.goods.vo.SimpleGoods;
/**
 * 
 * @author pzh
 * 查询商品入参对象
 * 
 */
public class SearchGoodsReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SearchGoodsOption option;
	
	public SearchGoodsOption getOption() {
		return option;
	}

	public void setOption(SearchGoodsOption option) {
		this.option = option;
	}
	
	public SearchGoodsResp get(){
		SearchGoodsResp resp = new SearchGoodsResp();
		try{
			List<SimpleGoods> list = getGoodsListForCms();
			resp.setSimple_goods(list);
			resp.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			resp.setSuccess(false);
		}
		return resp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 查询所有商品列表
	 * @return
	 */
	public List<SimpleGoods> getGoodsListForCms(){
		List<SimpleGoods> list = new ArrayList<SimpleGoods>();
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
