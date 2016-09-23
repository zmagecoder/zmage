package com.mage.platform.framework.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mage.platform.framework.utils.UploadUtils;

public class EopSetting {
	
	//是否为测试模式
	public static boolean TEST_MODE =false; 
	
	
	//EOP服器根
	public static String EOP_PATH ="";
	
	
	/*
	 * 图片服务器域名
	 */
	public static String IMG_SERVER_DOMAIN = "localhost";

	/*
	 * 
	 * 图片服务器地址
	 */
	public static String IMG_SERVER_PATH="";
	
	/*
	 * 应用数据存储地址
	 */
	public static String APP_DATA_STORAGE_PATH ="e:/eop";
	
	/*
	 * 产品存储目录
	 */	
	public static String PRODUCTS_STORAGE_PATH ="E:/workspace/eop3/eop/src/products";
	
	
	/*
	 * 服务器域名
	 */
	public static String APP_DOMAIN_NAME = "eop.com";
	
	public static String SERVICE_DOMAIN_NAME="";
	
	/*
	 * 用户数据存储路根径
	 */
	public static String USERDATA_STORAGE_PATH = "/user";
	
	
	public static String SOURCE_FROM ="";
	
	public static String DB_SOURCE_FROM ="";
	
	
	
	/*
	 * 后台主题存储路径
	 * 包括公共资源和用户资源的
	 * '/'代表当时的上下文
	 */
	public static String ADMINTHEMES_STORAGE_PATH = "/mgWebthemes";
	
	
	
	
	/*
	 * 前台主题存储路径
	 * 包括公共资源和用户资源的
	 * '/'代表当时的上下文
	 */	
	public static String THEMES_STORAGE_PATH = "/themes";
	
	
	public static String THEMES_STORAGE_PATH_F = "/themes";
	
	/*
	 * EOP虚拟目录
	 */
	public static String CONTEXT_PATH ="/";
	
	//资源模式
	public static String RESOURCEMODE="1";
	
	//运行模式
	public static String RUNMODE ="1";
	
	//数据库类型
	public static String DBTYPE ="2" ;
	
	//CLOB|BLOG用数据库存储
	public static String DBBLOGTYPE = "1";
	
	//扩展名
	public static String EXTENSION ="do";
	
	//是否使用默认eop的引擎,on为使用，off为不使用
	public static String TEMPLATEENGINE="on";
	
	public static String  THUMBNAILCREATOR ="javaimageio";
	
	public static String  FILE_STORE_PREFIX ="fs:"; //本地文件存储前缀
	
	public static String VERSION =""; //版本
	public static String PRODUCTID ="";
	
	public static String INSTALL_LOCK ="NO"; //是否已经安装
	
	public static List<String> safeUrlList;
	public static String BACKEND_PAGESIZE = "10";
	public static  String ENCODING;
	public static  String ROP_VALI ="no";
	
	public static String DEFAULT_IMG_URL =IMG_SERVER_DOMAIN+"/images/no_picture.jpg";
	
	/*
	 * 从配置文件中读取相关配置<br/>
	 * 如果没有相关配置则使用默认
	 */
//	static {
//		try{
//			InputStream in  = UploadUtils.getResourceAsStream("eop.properties");
//			Properties props = new Properties();
//			props.load(in);
//			init(props);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	 
	
	public static void init(Properties props ){
		
		String encoding =  props.getProperty("encoding");
		ENCODING  = StringUtils.isEmpty(encoding) ? "": encoding;
		
		String rop_val =  props.getProperty("rop_val");
		ROP_VALI  = StringUtils.isEmpty(rop_val) ? "no": rop_val; //默认不需要做rop功能验证
		
		
		SOURCE_FROM = props.getProperty("source_from");
		String theme_source_from =props.getProperty("theme_source_from");
		if(SOURCE_FROM==null)SOURCE_FROM = "";
		if(StringUtils.isEmpty(theme_source_from))
			theme_source_from = SOURCE_FROM;
		
		if(!SOURCE_FROM.equals(theme_source_from) && !StringUtils.isEmpty(theme_source_from))
			SOURCE_FROM = theme_source_from;
		
		DB_SOURCE_FROM = props.getProperty("db_source_from");
		if(StringUtils.isEmpty(DB_SOURCE_FROM))
			DB_SOURCE_FROM = SOURCE_FROM;
		
		
		String domain = props.getProperty("domain.imageserver");
		IMG_SERVER_DOMAIN = StringUtils.isEmpty(domain) ? IMG_SERVER_DOMAIN: domain;
		IMG_SERVER_DOMAIN = IMG_SERVER_DOMAIN;//IMG_SERVER_DOMAIN.startsWith("http://") ? IMG_SERVER_DOMAIN: "http://" + IMG_SERVER_DOMAIN;
		
		

		String userdata_storage_path = props.getProperty("storage.userdata");
		USERDATA_STORAGE_PATH = StringUtils.isEmpty(userdata_storage_path) ? USERDATA_STORAGE_PATH: userdata_storage_path;
		
		
		String adminthemes_storage_path = props.getProperty("storage.adminthemes");
		ADMINTHEMES_STORAGE_PATH = StringUtils.isEmpty(adminthemes_storage_path) ? "/mgWebthemes" + theme_source_from: adminthemes_storage_path;
			
		String themes_storage_path = props.getProperty("storage.themes");
		THEMES_STORAGE_PATH = StringUtils.isEmpty(themes_storage_path) ? THEMES_STORAGE_PATH: themes_storage_path;
		
		THEMES_STORAGE_PATH_F = StringUtils.isEmpty(themes_storage_path) ? "/themes" + SOURCE_FROM: themes_storage_path; //add by wui
		
		
		String eop_path = props.getProperty("storage.EOPServer");
		EOP_PATH = StringUtils.isEmpty(eop_path) ? EOP_PATH: eop_path;
		

		String imageserver_path = props.getProperty("path.imageserver");
		IMG_SERVER_PATH = StringUtils.isEmpty(imageserver_path) ? IMG_SERVER_PATH: imageserver_path;
 
		
		String app_data = props.getProperty("storage.app_data");
		APP_DATA_STORAGE_PATH = StringUtils.isEmpty(app_data) ? APP_DATA_STORAGE_PATH: app_data;
 		
		
		String context_path = props.getProperty("path.context_path");
		CONTEXT_PATH = StringUtils.isEmpty(context_path) ? CONTEXT_PATH: context_path;	
		

		String products_path = props.getProperty("storage.products");
		PRODUCTS_STORAGE_PATH = StringUtils.isEmpty(products_path) ? PRODUCTS_STORAGE_PATH: products_path;	
		
		//资源模式
		String resoucemode = props.getProperty("resourcemode");
		RESOURCEMODE=  StringUtils.isEmpty(resoucemode)?RESOURCEMODE:resoucemode;
		
		//运行模式
		String runmode = props.getProperty("runmode");
		RUNMODE=  StringUtils.isEmpty(runmode)?RUNMODE:runmode;

		//数据库类型
		String dbtype = props.getProperty("dbtype");
		DBTYPE=  StringUtils.isEmpty(dbtype)?DBTYPE:dbtype;

		//大字段存储方式
		String dbblogtype = props.getProperty("dbblogtype");
		DBBLOGTYPE=  StringUtils.isEmpty(dbblogtype)?DBBLOGTYPE:dbblogtype;
		
		//扩展名
		String extension = props.getProperty("extension");
		EXTENSION=  StringUtils.isEmpty(extension)?EXTENSION:extension;
		
		
		String templateengine = props.getProperty("templateengine");
		TEMPLATEENGINE=  StringUtils.isEmpty(templateengine)?TEMPLATEENGINE:templateengine;		

		String thumbnailcreator = props.getProperty("thumbnailcreator");
		THUMBNAILCREATOR=  StringUtils.isEmpty(thumbnailcreator)?THUMBNAILCREATOR:thumbnailcreator;
//		ThumbnailCreatorFactory.CREATORTYPE = THUMBNAILCREATOR;

		VERSION = props.getProperty("version");
		if(VERSION==null) VERSION="";
		
		PRODUCTID = props.getProperty("productid");
		if(PRODUCTID==null) PRODUCTID="";
		
		File installLockFile = null;//new File(StringUtils.getRootPath()+"/install/install.lock");
		if( installLockFile.exists() ){
			INSTALL_LOCK = "YES"; //如果存在则不能安装
		}else{
			INSTALL_LOCK = "NO"; //如果不存在，则认为是全新的，跳到install页
		}
		
		String servicedomain = props.getProperty("domain.service");
		SERVICE_DOMAIN_NAME = StringUtils.isEmpty(servicedomain)?SERVICE_DOMAIN_NAME:servicedomain;
		
		
		
		String backend_pagesize = props.getProperty("backend.pagesize");
		BACKEND_PAGESIZE = StringUtils.isEmpty(backend_pagesize)?BACKEND_PAGESIZE:backend_pagesize;
		initSafeUrl();
	}
	
	
	
	/**
	 * 初始化安全url
	 * 这些url不用包装 safeRequestWrapper
	 */
	private static void initSafeUrl(){
		
		try{
			//加载url xml 配置文档
			DocumentBuilderFactory factory = 
		    DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(UploadUtils.getResourceAsStream("safeurl.xml"));
		    NodeList urlNodeList = document.getElementsByTagName("urls").item(0).getChildNodes();
		    safeUrlList = new ArrayList<String>();
		    for( int i=0;i<urlNodeList.getLength();i++){
		    	Node node =urlNodeList.item(i); 
		    	//safeUrlList.add(node.getTextContent() );
		    }
		    
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
