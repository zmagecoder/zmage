package com.mage.cms.common.params.base.req;

import java.io.Serializable;

/**
 * 
 * @author wui
 * 模板添加入参对象
 *
 */
public class StoreLayOutReq extends CmsLineReq implements Serializable{

	//模板添加请求入参对象
	private static final long serialVersionUID = 1L;
	//add
	private String gridX;
	private String gridY;
	private String gridCx;
	private String gridCy;
	private String pluginType;
	private String pageId;
	
	//update
	private String storeLayoutId;
	
	
	public String getGridX() {
		return gridX;
	}
	public void setGridX(String gridX) {
		this.gridX = gridX;
	}
	public String getGridY() {
		return gridY;
	}
	public void setGridY(String gridY) {
		this.gridY = gridY;
	}
	public String getGridCx() {
		return gridCx;
	}
	public void setGridCx(String gridCx) {
		this.gridCx = gridCx;
	}
	public String getGridCy() {
		return gridCy;
	}
	public void setGridCy(String gridCy) {
		this.gridCy = gridCy;
	}
	public String getPluginType() {
		return pluginType;
	}
	public void setPluginType(String pluginType) {
		this.pluginType = pluginType;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getStoreLayoutId() {
		return storeLayoutId;
	}
	public void setStoreLayoutId(String storeLayoutId) {
		this.storeLayoutId = storeLayoutId;
	}
}
