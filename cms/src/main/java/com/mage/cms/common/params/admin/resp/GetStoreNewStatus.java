package com.mage.cms.common.params.admin.resp;


import java.util.ArrayList;

import com.mage.cms.common.params.admin.req.StatusInfoReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;

/**
 * 登录鉴权处理
 */
@SuppressWarnings("serial")
public class GetStoreNewStatus extends CmsLineResp{
	
	private String id;
	private String type ="admin";
	private String name;
	private ArrayList<Integer> permissions;
	private StatusInfoReq status_info;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public StatusInfoReq getStatus_info() {
		return status_info;
	}
	
	public void setStatus_info(StatusInfoReq status_info) {
		this.status_info = status_info;
	}
	
	//登录鉴权处理
	public String vertify(){
		
		return null;
	}
	
	public ArrayList<Integer> getPermissions() {
		return permissions;
	}
	public void setPermissions(ArrayList<Integer> permissions) {
		this.permissions = permissions;
	}
	public static void main(String[] args) {
		GetStoreNewStatus getStoreNewStatus = new GetStoreNewStatus();
		getStoreNewStatus.vertify();
	}
}
