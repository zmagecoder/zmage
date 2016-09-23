package com.mage.platform.service.dfs.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mage.platform.framework.config.FileConfigSetting;
import com.mage.platform.service.dfs.IDfsManager;

@Service
public class DfsManager implements IDfsManager {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DfsManager(){
		
	}
	
	
	private FileServerImpl s =null;
/*	{
		try {
			s=new FileServerImpl(FileConfigSetting.FAST_DFS_HOSTNAME,
					Integer.parseInt(FileConfigSetting.FAST_DFS_PORT),
					Integer.parseInt(FileConfigSetting.FAST_DFS_POOL_SIZE),
					Integer.parseInt(FileConfigSetting.FAST_DFS_HEART_BEAT_TIME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static DfsManager inst = new DfsManager() ;
	
	public static  DfsManager getInst(){
		return inst ;
	}*/
	
	private FileServerImpl getFileSer(){
		
		if(null == s){
			try {
				s=new FileServerImpl(FileConfigSetting.FAST_DFS_HOSTNAME,
						Integer.parseInt(FileConfigSetting.FAST_DFS_PORT),
						Integer.parseInt(FileConfigSetting.FAST_DFS_POOL_SIZE),
						Integer.parseInt(FileConfigSetting.FAST_DFS_HEART_BEAT_TIME),
						FileConfigSetting.FAST_DFS_SAVE_DIR);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return s;
	}
	
	/**
	 * 
	 * @param fileBuff
	 * @param suffix 
	 * @return
	 */
	public String upload(byte[] fileBuff , String suffix){
//		System.out.println("upload--------->") ;
		logger.info("upload--------->")  ;
		try {
			String fileId = this.getFileSer().uploadFile(fileBuff, suffix) ;
//			System.out.println("upload--------->"+fileId) ;
			logger.info("upload--------->"+fileId)  ;
			return fileId ;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
	}
	
	/**
	 * 
	 * @param fileBuff
	 * @param suffix 
	 * @return
	 */
	public String upload(File fileBuff, String suffix){
//		System.out.println("upload--------->") ;
		logger.info("upload--------->")  ;
		try {
			String fileId = this.getFileSer().uploadFile(fileBuff, suffix) ;
//			System.out.println("upload--------->"+fileId) ;
			logger.info("upload--------->"+fileId)  ;
			return fileId ;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
	}
	
	
	public String getFileUrl(String fileId){
		
		if(StringUtils.isEmpty(fileId))
			return "" ;
		
//		group1/M00/00/57/Ci0vqFLCJ8Xy11F-ADA66_jzVpA608.pdf
		String address ="";// CrmParamsConfig.getInstance().getParamValue("FAST_DFS_NGINX_ADDR")  ;
		fileId = fileId.startsWith("group") ? "/"+fileId : fileId ;
		fileId = address+fileId ;
		logger.info("getFileUrl--------->"+fileId)  ;
//		System.out.println("getFileUrl--------->"+fileId) ;
		return fileId ;
	}
	public static void main(String[] a ){
//		System.out.println(getFileUrl("group1/M00/00/57/Ci0vqFLCJ8Xy11F-ADA66_jzVpA608.pdf")) ;
		File f = new File("C:\\1.txt");
		String suffix = "txt";
		//上传配置
		String FAST_DFS_HOSTNAME = "10.45.47.170";//上传地址
		int FAST_DFS_PORT = 22122;//上传端口
		int FAST_DFS_POOL_SIZE = 10;
		int FAST_DFS_HEART_BEAT_TIME = 15; 
		String FAST_DFS_SAVE_DIR = "C:\\tmp\\";//本地缓存地址
		FileServerImpl s;
		String uploadpath = "";
		//下载配置数据
		String fast_dfs_down_hostname = "218.107.6.142";//下载地址
		int fast_dfs_hostport = 8000;//下载端口
		try {
			s = new FileServerImpl(FAST_DFS_HOSTNAME,
					FAST_DFS_PORT,
					FAST_DFS_POOL_SIZE,
					FAST_DFS_HEART_BEAT_TIME,
					FAST_DFS_SAVE_DIR);
			uploadpath = s.uploadFile(f, suffix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("http://"+fast_dfs_down_hostname+":"+fast_dfs_hostport+"/"+uploadpath);
	}
	
	/**
	 * 文件删除
	 * @param fileId 文件标识
	 * @return
	 */
	public boolean delete(String fileId){
//		System.out.println("delete--------->"+fileId) ;
		logger.info("delete--------->"+fileId)  ;
		try {
			if(fileId.startsWith("http://"+FileConfigSetting.FAST_DFS_HOSTNAME+"/")){
				fileId = fileId.replace("http://"+FileConfigSetting.FAST_DFS_HOSTNAME+"/","");
			}
			boolean result = this.getFileSer().deleteFile(fileId) ;
			logger.info("delete--------->"+result)  ;
//			System.out.println("delete--------->"+result) ;
			return result ;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
	}
	
	/**
	 * 读取/下载文件
	 * @param fileId
	 * @return
	 */
	public byte[] getFileById(String fileId){
//		System.out.println("getFileById--------->"+fileId) ;
		logger.info("getFileById--------->"+fileId)  ;
		try {
			FileServerImpl fi = this.getFileSer();
			return fi.getFileByID(fileId) ;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
	}
	
	public byte[] downFileById(String fileId){
//		System.out.println("getFileById--------->"+fileId) ;
		logger.info("downFileById--------->"+fileId)  ;
		try {
			return this.getFileSer().downFileByID(fileId) ;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}		
	}
	
}
