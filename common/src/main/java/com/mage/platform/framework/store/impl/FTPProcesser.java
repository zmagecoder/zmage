package com.mage.platform.framework.store.impl;

import java.io.File;
import java.io.InputStream;

import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.plugin.image.IThumbnailCreator;
import com.mage.platform.framework.store.plugin.image.ThumbnailCreatorFactory;
import com.mage.platform.utils.FileBaseUtil;
import com.mage.platform.utils.UploadUtil;

/**
 * FTP存储处理器
 * @author pzh
 * @date 2016年6月13日 下午4:19:13
 */
public class FTPProcesser implements IStoreProcesser {

	/**
	 * 获取文件内容
	 */
	public String getFileUrl(String fileId){
		File file = new File(fileId);
		byte[] bytes = FileBaseUtil.getFileBuffer(file);
		String content = null;
		if(null != bytes){
			new String(bytes);
		}
		return content;
	}
	
	public static final FTPProcesser inst = new FTPProcesser();
		
	public static IStoreProcesser getNetBlog(){
		return inst;
	}
	
	/**
	 * 内容上传
	 */
	public  String[] upload(File in,String fileFileName,String subFolder,int width,int height){
		String [] path = new String[2];
		path[0] = this.upload(in, fileFileName, subFolder);
		String thumbName= UploadUtil.getThumbPath(path[0],"_thumbnail");
		IThumbnailCreator thumbnailCreator = ThumbnailCreatorFactory.getCreator( path[0], thumbName);
		thumbnailCreator.resize(width, height);	
		path[1] = UploadUtil.getThumbPath(path[0], "_thumbnail");
		return path;
	}
	
	public String upload(File in,String fileFileName,String subFolder) {
		return this.upload(FileBaseUtil.getFileBuffer(in), fileFileName, subFolder);
		
	}

	@Override
	/**
	 * 内容删除
	 */
	public void del(String fileId) {
		FileBaseUtil.delete(fileId);//文件删除
	}
	
	public void delLocaoFile(String path){
		FileBaseUtil.delete(path);//文件删除
	}
	
	public String upload(InputStream in, String fileFileName, String subFolder) {
		return this.upload(FileBaseUtil.getInputStreamBuffer(in), fileFileName, subFolder);
	}

	public String upload(byte [] in, String fileFileName, String subFolder) { //attachments passlog
		return UploadUtil.upload(in, fileFileName, subFolder);
	}

	@Override
	public String replaceUrl(String path) {
		return path;
	}

	@Override
	public String getStroeType() {
		return "FTP";
	}
}
