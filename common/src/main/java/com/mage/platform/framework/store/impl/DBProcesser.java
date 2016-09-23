package com.mage.platform.framework.store.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.utils.UploadUtils;
import com.mage.platform.service.dfs.IDfsManager;

/**
 * 数据库大文本字段处理
 * @author pzh
 * @date 2016年6月13日 下午4:18:00
 */
public class DBProcesser implements IStoreProcesser {

	@Resource
	IDfsManager dfsManager;
	
	public String getFileUrl(String content){
		return content;
	}
	
	@Override
	public void del(String fileId) {
		dfsManager.delete(fileId);
	}
	
	@Override
	public void delLocaoFile(String path) {
		
	}

	public static final DBProcesser inst = new DBProcesser();
	
	public static IStoreProcesser getNetBlog(){
		return inst;
	}

	@Override
	public String[] upload(File file, String fileFileName, String subFolder,
			int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(File file, String fileFileName, String subFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(InputStream in, String fileFileName, String subFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(byte[] in, String fileFileName, String subFolder) {
		try {
			return new String(in,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String replaceUrl(String path) {
		return path.replaceAll(EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_DOMAIN + UploadUtils.getEopContextPath());
	}

	@Override
	public String getStroeType() {
		return "DB";
	}
	
}
