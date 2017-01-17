package com.mage.platform.framework.store.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.plugin.dfs.IDfsManager;
import com.mage.platform.framework.store.plugin.image.IThumbnailCreator;
import com.mage.platform.framework.store.plugin.image.ThumbnailCreatorFactory;
import com.mage.platform.utils.FileBaseUtil;
import com.mage.platform.utils.UploadUtil;

/**
 * FastDfs存储处理器
 * @author pzh
 * @date 2016年6月13日 下午4:18:52
 */
public class DFSProcesser implements IStoreProcesser {

	@Resource
	IDfsManager dfsManager;
	
	public String getFileUrl(String fileId){
		if("".equals(fileId)||null==fileId){
			return "";
		}
		if(null == dfsManager){
			dfsManager = SpringContextHolder.getBean("dfsManager");
		}
		if(fileId.endsWith(".txt")){
			try {
				byte [] buff = dfsManager.getFileById(fileId);
//				System.out.println(buff);
				if(null == buff){
					return "";
				}else{
					return new String(buff,"utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
		}else{
			return dfsManager.getFileUrl(fileId);
		}
	}
	
	@Override
	public void del(String fileId) {
		dfsManager.delete(fileId);
	}

	public static final DFSProcesser inst = new DFSProcesser();
	
	public static IStoreProcesser getNetBlog(){
		return inst;
	}

	@Override
	public String upload(File file, String fileFileName, String subFolder) {
		String ext = FileBaseUtil.getFileExt(fileFileName);
		if(null == dfsManager){
			dfsManager = SpringContextHolder.getBean("dfsManager");
		}
		return dfsManager.upload(file, ext);
	}

	@Override
	public String[] upload(File file, String fileFileName, String subFolder, int width, int height) {
		String [] path = new String[2];
		String ext = FileBaseUtil.getFileExt(fileFileName);
		path[0]= dfsManager.upload(file, ext);
		
		String thumbName= UploadUtil.getThumbPath(path[0],"_thumbnail");
		IThumbnailCreator thumbnailCreator = ThumbnailCreatorFactory.getCreator(path[0], thumbName);
		thumbnailCreator.resize(width, height);	
		path[1] = UploadUtil.getThumbPath(path[0], "_thumbnail");
		
		return null;
	}

	@Override
	public String upload(InputStream in, String fileFileName, String subFolder) {
		String ext = FileBaseUtil.getFileExt(fileFileName);
		if(null == dfsManager){
			dfsManager = SpringContextHolder.getBean("dfsManager");
		}
		return dfsManager.upload(FileBaseUtil.getInputStreamBuffer(in), ext);
	}

	@Override
	public String upload(byte[] in, String fileFileName, String subFolder) {
		String ext = FileBaseUtil.getFileExt(fileFileName);
		if(null == dfsManager){
			dfsManager = SpringContextHolder.getBean("dfsManager");
		}
		return dfsManager.upload(in, ext);
	}

	@Override
	public String replaceUrl(String path) {
		return path;
	}

	@Override
	public String getStroeType() {
		return "DFS";
	}

	@Override
	public void delLocaoFile(String path) {
		// TODO Auto-generated method stub
	}
}
