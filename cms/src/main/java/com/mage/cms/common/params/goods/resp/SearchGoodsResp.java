package com.mage.cms.common.params.goods.resp;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.goods.vo.SimpleGoods;

/**
 * 
 * @author pzh
 * 查询商品出参
 *
 */
public class SearchGoodsResp extends CmsLineResp implements Serializable{
	
	private String goods_tag;
	private String goods_class;
	private Integer total;
	private List<SimpleGoods> simple_goods;
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getGoods_class() {
		return goods_class;
	}
	public void setGoods_class(String goods_class) {
		this.goods_class = goods_class;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<SimpleGoods> getSimple_goods() {
		return simple_goods;
	}
	public void setSimple_goods(List<SimpleGoods> simple_goods) {
		this.simple_goods = simple_goods;
	}
}
