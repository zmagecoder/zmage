package com.mage.cms.common.params.photo.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.photo.resp.GetFolderResp;
import com.mage.cms.common.params.photo.vo.Folder;

public class GetFolderReq extends CmsLineReq implements Serializable{
	
	public GetFolderResp edit() throws Exception{
		GetFolderResp resp = new GetFolderResp();
		List list = new ArrayList();
		Folder f1 = new Folder();
		f1.setExtensionData(null);
		f1.setAlbumId("16502");
		f1.setCreateTime("\\/Date(1403141037000)\\/");
		f1.setCurrentSize("0");
		f1.setFolderName("商品图片(系统)");
		f1.setId("38756");
		f1.setPhotoSum("0");
		f1.setStoreId("16528");
		f1.setType("1");
		list.add(f1);
		Folder f2 = new Folder();
		f2.setExtensionData(null);
		f2.setAlbumId("16502");
		f2.setCreateTime("\\/Date(1403141037000)\\/");
		f2.setCurrentSize("2782122");
		f2.setFolderName("商品图片(系统)");
		f2.setId("38758");
		f2.setPhotoSum("0");
		f2.setStoreId("16528");
		f2.setType("2");
		list.add(f2);	
		Folder f3 = new Folder();
		f3.setExtensionData(null);
		f3.setAlbumId("16502");
		f3.setCreateTime("\\/Date(1403141037000)\\/");
		f3.setCurrentSize("2782122");
		f3.setFolderName("图片文件夹");
		f3.setId("38758");
		f3.setPhotoSum("0");
		f3.setStoreId("16528");
		f3.setType("0");
		list.add(f3);		
		resp.setList(list);
		return resp;
	}
}
