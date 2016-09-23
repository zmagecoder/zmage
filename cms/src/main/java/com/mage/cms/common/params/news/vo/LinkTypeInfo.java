package com.mage.cms.common.params.news.vo;

import java.util.List;

public class LinkTypeInfo {
	private String ExtensionData;
	private List<Domain> StoreDomain;
	private List<Page> StoreNews;
	private List<Page> StorePage;
	private List<SalesRule> StoreSalesRule;
	private List<TagClass> TagClass;
	public String getExtensionData() {
		return ExtensionData;
	}
	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}
	public List<Domain> getStoreDomain() {
		return StoreDomain;
	}
	public void setStoreDomain(List<Domain> storeDomain) {
		StoreDomain = storeDomain;
	}
	public List<Page> getStoreNews() {
		return StoreNews;
	}
	public void setStoreNews(List<Page> storeNews) {
		StoreNews = storeNews;
	}
	public List<Page> getStorePage() {
		return StorePage;
	}
	public void setStorePage(List<Page> storePage) {
		StorePage = storePage;
	}
	public List<SalesRule> getStoreSalesRule() {
		return StoreSalesRule;
	}
	public void setStoreSalesRule(List<SalesRule> storeSalesRule) {
		StoreSalesRule = storeSalesRule;
	}
	public List<TagClass> getTagClass() {
		return TagClass;
	}
	public void setTagClass(List<TagClass> tagClass) {
		TagClass = tagClass;
	}
	
}
