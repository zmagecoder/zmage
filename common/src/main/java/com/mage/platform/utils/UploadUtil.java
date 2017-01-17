package com.mage.platform.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.mage.param.req.MageRequest;
import com.mage.param.resp.MageResponse;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.impl.StoreProcesser;
import com.mage.platform.framework.thread.Task;
import com.mage.platform.framework.thread.TaskThreadPool;
import com.mage.platform.framework.thread.ThreadPoolFactory;

public class UploadUtil {
	
	private UploadUtil(){}
	/**
	 * 上传图片<br/>
	 * 图片会被上传至用户上下文的attacment文件夹的subFolder子文件夹中<br/>
	 * 
	 * @param file  图片file对象
	 * @param fileFileName 上传的图片原名
	 * @param subFolder  子文件夹名
	 * @return 
	 * @since 上传后的本地路径，如:fs:/attachment/goods/2001010101030.jpg<br/>
	 * 
	 */
	public static String upload(File file,String fileFileName,String subFolder ) {
		IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
		String path =netBlog.upload(file, fileFileName, subFolder);
		return path;
	}
	
	
	/**
	 * 
	 * 文件上传<br/>
	 * 图片会被上传至用户上下文的attacment文件夹的subFolder子文件夹中<br/>
	 * 
	 * @param file  图片file对象
	 * @param fileFileName 上传的图片原名
	 * @param subFolder  子文件夹名
	 * @return 
	 * @since 上传后的本地路径，如:fs:/attachment/goods/2001010101030.jpg<br/>
	 * 
	 */
	public static String uploadFiles(File file,String fileFileName,String subFolder,String rootFolderName) {
		IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
		return netBlog.upload(file, fileFileName, subFolder);
	}
	
	
	public static String replacePath(String path){
		if(StringUtils.isEmpty(path)) return path;
		IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
		return  netBlog.replaceUrl(path);
	}
	
	/**
	 * 上传图片并生成缩略图
	 * 图片会被上传至用户上下文的attacment文件夹的subFolder子文件夹中<br/>
	 * 
	 * @param file  图片file对象
	 * @param fileFileName 上传的图片原名
	 * @param subFolder  子文件夹名
	 * @param width 缩略图的宽
	 * @param height 缩略图的高
	 * @return 上传后的图版全路径，如:http://static.eop.com/user/1/1/attachment/goods/2001010101030.jpg<br/>
	 * 返回值为大小为2的String数组，第一个元素为上传后的原图全路径，第二个为缩略图全路径
	 */
	public static String[] upload(File file,String fileFileName,String subFolder,int width,int height ){
		
		IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
		return netBlog.upload(file, fileFileName, subFolder, width, height);
	}
	
	public static String upload(byte [] in, String fileFileName, String subFolder) {
		String path=""; 
		//同步FTP
		ThreadPoolExecutor executor = ThreadPoolFactory.getExector(ThreadPoolFactory.EXECTOR_NEW, 2);
		TaskThreadPool taskThreadPool = new TaskThreadPool(new Task(null) {
			public MageResponse execute(MageRequest mageRequest) {
				//TODO 同步到文件服务器
				return new MageResponse();
			}
		});
		ThreadPoolFactory.submit(taskThreadPool, executor);
		return path;
	}
	
	/**
	 * 删除某个上传的文件
	 * @param filePath 文件全路径如：http://static.eop.com/user/1/1/attachment/goods/2001010101030.jpg
	 */
	public static void deleteFile(String filePath){
		IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
		netBlog.del(filePath);		
	}
	
	/**
	 * 文件路径转换
	 * @param key 文件路径的字段名
	 * @param mapList list对象
	 */
	public static void convertFilePath(String key, List<Map> mapList) {
		if (mapList == null) return;
		
		for (Map map: mapList) {
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String keyTmp = (String) it.next();
				if (key.equalsIgnoreCase(keyTmp)) {
					String attUrl = replacePath((String)map.get(keyTmp));
					map.put(keyTmp, attUrl);
					break;
				}
			}
		}
	}
	
	/**
	 * 转换图片的名称
	 * @param filePath
	 * @param shortName 
	 * @return
	 */
	public static  String getThumbPath(String filePath, String shortName) {
		String pattern = "(.*)([\\.])(.*)";
		String thumbPath = "$1" + shortName + "$2$3";

		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(filePath);
		if (m.find()) {
			String s = m.replaceAll(thumbPath);

			return s;
		}
		return null;
	}
}
