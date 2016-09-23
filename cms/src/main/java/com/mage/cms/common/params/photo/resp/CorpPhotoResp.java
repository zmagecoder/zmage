package com.mage.cms.common.params.photo.resp;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.photo.vo.Photo;


/**
 * 模板编辑出参对象
 * @author sguo
 * @date 2014.06.20
 */
public class CorpPhotoResp extends CmsLineResp{

	private boolean success;

	private Photo photo;
	
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	
	
}
