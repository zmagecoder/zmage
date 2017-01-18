package com.mage.cms.common.params.news.vo;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.config.EopSetting;


public class News {
	private String GroupId;
	private String Id;
	private String Title;
	private String Photo;
	private Integer LinkType;
	private String LinkParam;
	private String Abstract;
	private String Description;
	private String Order;
	private String IndexId;
	private String Time;
	
	private String Link_url;	//生产的时候用到
	
	public String getIndexId() {
		return IndexId;
	}
	public void setIndexId(String indexId) {
		IndexId = indexId;
	}
	public String getGroupId() {
		return GroupId;
	}
	public void setGroupId(String groupId) {
		GroupId = groupId;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public Integer getLinkType() {
		return LinkType;
	}
	public void setLinkType(Integer linkType) {
		LinkType = linkType;
	}
	public String getLinkParam() {
		return LinkParam;
	}
	public void setLinkParam(String linkParam) {
		LinkParam = linkParam;
	}
	public String getAbstract() {
		return Abstract;
	}
	public void setAbstract(String abstract1) {
		Abstract = abstract1;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	
	public String getLink_url() {
		CmsContext cmsContext = CmsServiceFactory.getCmsLineService().getCmsContext();
		String linkUrl = "";
		if(this.LinkType == CmsConst.CMS_LINK_TYPE_2){//url地址
			linkUrl = this.LinkParam;
		}else {
			linkUrl = EopSetting.CONTEXT_PATH + "/sshop-page_id-" + this.IndexId + ".html"; //门户页面;
			linkUrl = linkUrl.replaceAll("//", "/");
		}
		return linkUrl;
	}
	
	public void setLink_url(String link_url) {
		Link_url = link_url;
	}
	
}
