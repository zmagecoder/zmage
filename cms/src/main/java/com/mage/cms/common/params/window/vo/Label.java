package com.mage.cms.common.params.window.vo;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.params.goods.vo.SimpleGoods;
/**
 * 
 * @author pzh
 * 橱窗对象
 * 
 */
public class Label implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String extensionData;
	private List<SimpleGoods> goods;
	private List<String> id;
	private String name;
	private String index;
	
	public String getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(String extensionData) {
		this.extensionData = extensionData;
	}

	public List<SimpleGoods> getGoods() {
		return goods;
	}

	public void setGoods(List<SimpleGoods> goods) {
		this.goods = goods;
	}

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
