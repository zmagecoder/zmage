package com.mage.cms.common.params.img.vo;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.config.EopSetting;

public class Adv {
	private String ExtensionData;
	private String LinkParam;
	private Integer LinkType;
	private String PhotoId;
	private String Title;
	private String Url;
	private String LinkUrl;

	public Adv(String ExtensionData, String LinkParam, Integer LinkType,
			String PhotoId, String Title, String Url) {
		this.ExtensionData = ExtensionData;
		this.LinkParam = LinkParam;
		this.LinkType = LinkType;
		this.PhotoId = PhotoId;
		this.Title = Title;
		this.Url = Url;
	}

	public Adv() {

	}

	public String getExtensionData() {
		return ExtensionData;
	}

	public void setExtensionData(String extensionData) {
		ExtensionData = extensionData;
	}

	public String getLinkParam() {
		return LinkParam;
	}

	public void setLinkParam(String linkParam) {
		LinkParam = linkParam;
	}

	public Integer getLinkType() {
		return LinkType;
	}

	public void setLinkType(Integer linkType) {
		LinkType = linkType;
	}

	public String getPhotoId() {
		return PhotoId;
	}

	public void setPhotoId(String photoId) {
		PhotoId = photoId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getLinkUrl() {
		String linkUrl = "";
		CmsContext cmsContext = CmsServiceFactory.getCmsLineService().getCmsContext();
		if(this.LinkType == CmsConst.CMS_LINK_TYPE_2){//url地址
			linkUrl = this.LinkParam;
		}else {
			linkUrl = EopSetting.CONTEXT_PATH + "/sshop-page_id-" + this.LinkParam + ".html"; //门户页面;
			linkUrl = linkUrl.replaceAll("//", "/");
		}
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		LinkUrl = linkUrl;
	}
}
