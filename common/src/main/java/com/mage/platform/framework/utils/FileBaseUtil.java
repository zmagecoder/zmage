package com.mage.platform.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;

import com.mage.param.req.ZteRequest;
import com.mage.param.resp.ZteResponse;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.config.EopSite;
import com.mage.platform.framework.config.FileConfigSetting;
import com.mage.platform.framework.context.EopContext;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.impl.StoreProcesser;
import com.mage.platform.framework.thread.Task;
import com.mage.platform.framework.thread.TaskThreadPool;
import com.mage.platform.framework.thread.ThreadPoolFactory;

/**
 * 文件工具类
 * @author pzh
 */
public class FileBaseUtil {

	private FileBaseUtil() {
	}

	public static void createFile(InputStream in, String filePath) {
		if(in==null) throw new RuntimeException("create file error: inputstream is null");
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			byte[] by = new byte[1024];
			int c;
			while ((c = in.read(by)) != -1) {
				outputStream.write(by, 0, c);
			}
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createFile(byte  [] bytes, String filePath) {
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			outputStream.write(bytes);
			
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 是否是允许上传文件
	 * 
	 * @param ex
	 * @return
	 */
	public static boolean isAllowUp(String logoFileName) {
		String allowTYpe = "GIF,JPG,BMP,SWF,JPEG,PNG";
		if (!logoFileName.trim().equals("") && logoFileName.length() > 0) {
			String ex = logoFileName.substring(logoFileName.lastIndexOf(".") + 1, logoFileName.length());
//			return allowTYpe.toString().indexOf(ex) >= 0;
			//lzf edit 20110717
			//解决只认小写问题
			//同时加入jpeg扩展名/png扩展名
			return allowTYpe.indexOf(ex.toUpperCase()) >= 0;
		} else {
			return false;
		}
	}
	
	/**
	 * 是否是允许上传文件
	 * 
	 * @param ex
	 * @return
	 */
	public static boolean isAllowUpXls(String logoFileName) {
		String allowTYpe = "XLSX,XLS";
		if (!logoFileName.trim().equals("") && logoFileName.length() > 0) {
			String ex = logoFileName.substring(logoFileName.lastIndexOf(".") + 1, logoFileName.length());
//			return allowTYpe.toString().indexOf(ex) >= 0;
			//lzf edit 20110717
			//解决只认小写问题
			//同时加入jpeg扩展名/png扩展名
			return allowTYpe.indexOf(ex.toUpperCase()) >= 0;
		} else {
			return false;
		}
	}
	
	/**
	 * 把内容写入文件
	 * 
	 * @param filePath
	 * @param fileContent
	 */
	public static void write(String filePath, String fileContent) {

		try {
			FileOutputStream fo = new FileOutputStream(filePath);
			OutputStreamWriter out = new OutputStreamWriter(fo, "UTF-8");

			out.write(fileContent);

			out.close();
		} catch (IOException ex) {

			System.err.println("Create File Error!");
			ex.printStackTrace();
		}
	}

	/**
	 * 读取文件内容 默认是UTF-8编码
	 * 
	 * @param filePath
	 * @return
	 */
	public static String read(String filePath, String code) {
		if (code == null || code.equals("")) {
			code = "UTF-8";
		}
		String fileContent = "";
		File file = new File(filePath);
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), code);
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + "\n";
			}
			read.close();
			read = null;
			reader.close();
			read = null;
		} catch (Exception ex) {
			ex.printStackTrace();
			fileContent = "";
		}
		return fileContent;
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param filePath
	 */
	public static void delete(String filePath) {
		
		String store_type = FileConfigSetting.FILE_STORE_TYPE;
		if("FTP".equals(store_type.toUpperCase())){ //FTP文件
			try {
				File file = new File(filePath);
				if (file.exists()){
					if(file.isDirectory()){
						FileUtils.deleteDirectory(file);
					}else{
						file.delete();
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else{
			IStoreProcesser netBlog = StoreProcesser.getFileProcesser();
			netBlog.del(filePath);
		}
	}

	public static String upload(byte [] in, String fileFileName, String subFolder) {
		String path=""; 
		if(subFolder == null)throw new IllegalArgumentException("subFolder is null");
		String fileName = null;
		String filePath = "";
		
		String ext = FileBaseUtil.getFileExt(fileFileName);
		fileName = DateUtil.toString(new Date(), "yyyyMMddHHmmss") + "." + ext;
		EopContext context = EopContext.getContext();
		if(context==null){
			EopSite site = new EopSite();
			site.setUserid(1 + "");
			site.setId(1 + "");
			site.setThemeid(1 + "");
			context = new EopContext();
//			context.setCurrentSite(site);
		}
		filePath =  EopSetting.IMG_SERVER_PATH +context.getContextPath()+  "/attachment/";
		path= EopSetting.IMG_SERVER_DOMAIN + context.getContextPath()+ "/attachment/"+subFolder+"/"+fileName;
		if(subFolder!=null){
			filePath+=subFolder +"/";
		}
		if(!"goods".equals(subFolder)) //非商品的直接返回相对路径
			path  = EopSetting.FILE_STORE_PREFIX+ "/attachment/" +(subFolder==null?"":subFolder)+ "/"+fileName;
		filePath += fileName;
		FileBaseUtil.createFile(in, filePath);
		
		//同步FTP
		ThreadPoolExecutor executor = ThreadPoolFactory.getExector(ThreadPoolFactory.EXECTOR_NEW, 2);
		TaskThreadPool taskThreadPool = new TaskThreadPool(new Task(null) {
			public ZteResponse execute(ZteRequest zteRequest) {
				//TODO 同步到文件服务器
				return new ZteResponse();
			}
		});
		
		ThreadPoolFactory.submit(taskThreadPool, executor);
		return path;
	}
	
	public static boolean exist(String filepath) {
		File file = new File(filepath);

		return file.exists();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createFolder(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception ex) {
			System.err.println("Make Folder Error:" + ex.getMessage());
		}
	}

	/**
	 * 得到文件的扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {

		int potPos = fileName.lastIndexOf('.') + 1;
		String type = fileName.substring(potPos, fileName.length());
		return type;
	}
	
	public  static byte[] getStringBuffer(String content) {
		byte[] fileByte = null;
		try {
			fileByte = content.getBytes("utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
		return fileByte;
	}
	
	public  static byte[] getFileBuffer(File file) {
		byte[] fileByte = null;
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
			fileByte = new byte[fis.available()];
			fis.read(fileByte);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (IOException e) {
				fis=null;
			}
		}
		return fileByte;
	}
	
	public static byte[] getInputStreamBuffer(InputStream in) {
		byte[] fileByte = null;
		try {
			fileByte = new byte[in.available()];
			in.read(fileByte);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				in=null;
			}
		}
		return fileByte;
	}

	/**
	 * 通过File对象创建文件
	 * 
	 * @param file
	 * @param filePath
	 */
	public static void createFile(File file, String filePath) {
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			fileInputStream = new FileInputStream(file);
			byte[] by = new byte[1024];
			int c;
			while ((c = fileInputStream.read(by)) != -1) {
				outputStream.write(by, 0, c);
			}
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String resource) {
		InputStream stream = getResourceAsStream(resource);
		String content = readStreamToString(stream);
		return content;

	}

	public static InputStream getResourceAsStream(String resource) {
		try {
			File file = new File(System.getProperty("CONFIG")+resource);
			InputStream stream = null;
			if(file.exists()){
				FileInputStream fileIS  = new FileInputStream(file);
				stream = fileIS;
			}
			if(null == stream){
				String stripped = resource.startsWith("/") ? resource.substring(1)
						: resource;
				stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(stripped);
			}
			return stream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readStreamToString(InputStream stream) {
		String fileContent = "";

		try {
			InputStreamReader read = new InputStreamReader(stream, "utf-8");
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + "\n";
			}
			read.close();
			read = null;
			reader.close();
			read = null;
		} catch (Exception ex) {
			fileContent = "";
		}
		return fileContent;
	}

	/**
	 * delete file folder
	 * 
	 * @param path
	 */
	public static void removeFile(File path) {
		
		if (path.isDirectory()) {
			try {
				FileUtils.deleteDirectory(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
	}

	public static void copyFile(String srcFile,String destFile){
		try {
			if(FileBaseUtil.exist(srcFile)){
				FileUtils.copyFile(new File(srcFile),new File( destFile ));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void copyFolder(String sourceFolder, String destinationFolder)
		 {
		//System.out.println("copy " + sourceFolder + " to " + destinationFolder);
		try{
			File sourceF = new File(sourceFolder);
			if (sourceF.exists())
				FileUtils.copyDirectory(new File(sourceFolder), new File(
						destinationFolder));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("copy file error");
		}
		
		
	}
	
	
	public static void unZip(String zipPath,String targetFolder,boolean cleanZip){
		File folderFile =new File(targetFolder);
		File zipFile = new File(zipPath);
		Project prj = new Project();
		Expand expand = new Expand();
		expand.setEncoding("UTF-8");
		expand.setProject(prj);
		expand.setSrc(zipFile);
		expand.setOverwrite(true);
		expand.setDest(folderFile);
		expand.execute();
		
		if(cleanZip){
			//清除zip包
			Delete delete = new Delete();
			delete.setProject(prj);
			delete.setDir(zipFile);
			delete.execute();
		}
	}

}
