package com.mage.cms.common.params.template.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author pzh
 * 页面模板对象
 */
public class StoreTemplate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object extensionData;
	private String adPhone;
	private String adPhoto;
	private Integer adPhotoId;
	private String adPhotoTitle;
	private String adPhotoUrl;
	private String adText;
	private Integer adType;
	private String adUrl;
	private Integer borderStyle;
	private String description;
	private Integer device;
	private String footer;
	private String header;
	private Integer headerStyle;
	private List<HelpTitle> helpTitle;
	private List<NavClass> storeNavClass1;
	
	private String helpTitleJson;
	
	private Long id;
	private String keyword;
	private String logo;
	private Long logoPhotoId;
	private Integer logoType;
	private Integer mainStyle;
	private Integer majorColor;
	private Integer minorColor;
	private String name;
	private Integer saveLevel;
	private String searchKey;
	private boolean showHeader;
	private boolean showHeaderAd;
	private boolean showHelp;
	private boolean showNav;
	private boolean showZhuyun;
	private Long storeId;
	private Integer templateId;
	private String title;
	private List<String> searchKeyDisplay = null;
	
	public Object getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(Object extensionData) {
		this.extensionData = extensionData;
	}

	public String getAdPhone() {
		return adPhone;
	}

	public void setAdPhone(String adPhone) {
		this.adPhone = adPhone;
	}

	public String getAdPhoto() {
		return adPhoto;
	}

	public void setAdPhoto(String adPhoto) {
		this.adPhoto = adPhoto;
	}

	public Integer getAdPhotoId() {
		return adPhotoId;
	}

	public void setAdPhotoId(Integer adPhotoId) {
		this.adPhotoId = adPhotoId;
	}

	public String getAdPhotoTitle() {
		return adPhotoTitle;
	}

	public void setAdPhotoTitle(String adPhotoTitle) {
		this.adPhotoTitle = adPhotoTitle;
	}

	public String getAdPhotoUrl() {
		return adPhotoUrl;
	}

	public void setAdPhotoUrl(String adPhotoUrl) {
		this.adPhotoUrl = adPhotoUrl;
	}

	public String getAdText() {
		return adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public Integer getBorderStyle() {
		return borderStyle;
	}

	public void setBorderStyle(Integer borderStyle) {
		this.borderStyle = borderStyle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDevice() {
		return device;
	}

	public void setDevice(Integer device) {
		this.device = device;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Integer getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(Integer headerStyle) {
		this.headerStyle = headerStyle;
	}

	public List<HelpTitle> getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(List<HelpTitle> helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getHelpTitleJson() {
		return helpTitleJson;
	}

	public void setHelpTitleJson(String helpTitleJson) {
		this.helpTitleJson = helpTitleJson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getLogoPhotoId() {
		return logoPhotoId;
	}

	public void setLogoPhotoId(Long logoPhotoId) {
		this.logoPhotoId = logoPhotoId;
	}

	public Integer getLogoType() {
		return logoType;
	}

	public void setLogoType(Integer logoType) {
		this.logoType = logoType;
	}

	public Integer getMainStyle() {
		return mainStyle;
	}

	public void setMainStyle(Integer mainStyle) {
		this.mainStyle = mainStyle;
	}

	public Integer getMajorColor() {
		return majorColor;
	}

	public void setMajorColor(Integer majorColor) {
		this.majorColor = majorColor;
	}

	public Integer getMinorColor() {
		return minorColor;
	}

	public void setMinorColor(Integer minorColor) {
		this.minorColor = minorColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSaveLevel() {
		return saveLevel;
	}

	public void setSaveLevel(Integer saveLevel) {
		this.saveLevel = saveLevel;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public boolean isShowHeader() {
		return showHeader;
	}

	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}

	public boolean isShowHeaderAd() {
		return showHeaderAd;
	}

	public void setShowHeaderAd(boolean showHeaderAd) {
		this.showHeaderAd = showHeaderAd;
	}

	public boolean isShowHelp() {
		return showHelp;
	}

	public void setShowHelp(boolean showHelp) {
		this.showHelp = showHelp;
	}

	public boolean isShowNav() {
		return showNav;
	}

	public void setShowNav(boolean showNav) {
		this.showNav = showNav;
	}

	public boolean isShowZhuyun() {
		return showZhuyun;
	}

	public void setShowZhuyun(boolean showZhuyun) {
		this.showZhuyun = showZhuyun;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getSearchKeyDisplay() {
		return searchKeyDisplay;
	}

	public void setSearchKeyDisplay(List<String> searchKeyDisplay) {
		this.searchKeyDisplay = searchKeyDisplay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<NavClass> getStoreNavClass1() {
		return storeNavClass1;
	}

	public void setStoreNavClass1(List<NavClass> storeNavClass1) {
		this.storeNavClass1 = storeNavClass1;
	}

}
