package com.mage.cms.common.params.base.resp;

import java.io.Serializable;

import com.mage.cms.common.params.vo.StaticLayOutExtVo;

/**
 * 
 * @author wui
 * 模板添加入出参对象
 *
 */
public class StoreLayOutResp extends CmsLineResp implements Serializable{

	private String extensionData;
	private Integer gridCx;
	private Integer gridCy;
	private Integer gridX;
	private Integer gridY;
	private Long pageId;
	private Long pluginId;
	private Integer pluginType;
	private String remark;
	private Long storeId;
	private Long storeLayoutId;
	private CmsLineResp data; //内存对象数据
	
	private StaticLayOutExtVo staticLayOutExtVo =new StaticLayOutExtVo(); //add by wui发布静态模式冗余字段
	
	
	public String getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(String extensionData) {
		this.extensionData = extensionData;
	}

	public Integer getGridCx() {
		return gridCx;
	}

	public void setGridCx(Integer gridCx) {
		this.gridCx = gridCx;
	}

	public Integer getGridCy() {
		return gridCy;
	}

	public void setGridCy(Integer gridCy) {
		this.gridCy = gridCy;
	}

	public Integer getGridX() {
		return gridX;
	}

	public void setGridX(Integer gridX) {
		this.gridX = gridX;
	}

	public Integer getGridY() {
		return gridY;
	}

	public void setGridY(Integer gridY) {
		this.gridY = gridY;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public Integer getPluginType() {
		return pluginType;
	}

	public void setPluginType(Integer pluginType) {
		this.pluginType = pluginType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getStoreLayoutId() {
		return storeLayoutId;
	}

	public void setStoreLayoutId(Long storeLayoutId) {
		this.storeLayoutId = storeLayoutId;
	}

	public CmsLineResp getData() {
		return data;
	}

	public void setData(CmsLineResp data) {
		this.data = data;
	}

	public StaticLayOutExtVo getStaticLayOutExtVo() {
		return staticLayOutExtVo;
	}

	public void setStaticLayOutExtVo(StaticLayOutExtVo staticLayOutExtVo) {
		this.staticLayOutExtVo = staticLayOutExtVo;
	}



}
