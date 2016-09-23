package com.mage.cms.common.params.goods.vo;
/**
 * 
 * @author pzh
 * 查询商品入参对象
 * 
 */
public class SearchGoodsOption{
	
	private String status;
	private String key;
	private String first;
	private String max;
	private String storeId;
	private String order;
	private String priceFrom;
	private String priceTo;
	private String class1Id;
	private String class2Id;
	private String class3Id;
	private boolean stockOnly;
	private boolean isGetTag;
	private boolean isGetClass;
	private boolean isGetDynamic;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(String priceFrom) {
		this.priceFrom = priceFrom;
	}
	public String getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(String priceTo) {
		this.priceTo = priceTo;
	}
	public String getClass1Id() {
		return class1Id;
	}
	public void setClass1Id(String class1Id) {
		this.class1Id = class1Id;
	}
	public String getClass2Id() {
		return class2Id;
	}
	public void setClass2Id(String class2Id) {
		this.class2Id = class2Id;
	}
	public String getClass3Id() {
		return class3Id;
	}
	public void setClass3Id(String class3Id) {
		this.class3Id = class3Id;
	}
	public boolean isStockOnly() {
		return stockOnly;
	}
	public void setStockOnly(boolean stockOnly) {
		this.stockOnly = stockOnly;
	}
	public boolean isGetTag() {
		return isGetTag;
	}
	public void setGetTag(boolean isGetTag) {
		this.isGetTag = isGetTag;
	}
	public boolean isGetClass() {
		return isGetClass;
	}
	public void setGetClass(boolean isGetClass) {
		this.isGetClass = isGetClass;
	}
	public boolean isGetDynamic() {
		return isGetDynamic;
	}
	public void setGetDynamic(boolean isGetDynamic) {
		this.isGetDynamic = isGetDynamic;
	}	
}
