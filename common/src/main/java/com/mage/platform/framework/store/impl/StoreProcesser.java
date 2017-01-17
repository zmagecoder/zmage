package com.mage.platform.framework.store.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.mage.platform.framework.cache.CacheFactory;
import com.mage.platform.framework.cache.ICache;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.database.DStoreConfig;
import com.mage.platform.framework.store.IBlogManager;
import com.mage.platform.framework.store.IStoreProcesser;
import com.mage.platform.framework.store.config.FileConfigSetting;

/**
 * 数据存储处理器
 * @author pzh
 * @date 2016年6月13日 下午4:21:28
 */
public class StoreProcesser {

	private final String CORE_CACHE_TYPE = "";
	
	public static final String LIST_DSTORE_CONFIG ="es_dstore_config_list";
	
	private IBlogManager blogManager;
	
	private ICache cache = CacheFactory.getCacheByType(CORE_CACHE_TYPE);
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	private List dStoreConfigList(){

		return null;
	}
	
	public List<Map<String,Object>> getTabClobName(String table_name){
		List<Map<String,Object>> clob_names = new ArrayList<Map<String,Object>>();
		List list = dStoreConfigList();
		for (int i = 0; i < list.size(); i++) {
			DStoreConfig dStoreConfig = (DStoreConfig)list.get(i);
			if(dStoreConfig.getTable_name().equals(table_name)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("field_name",dStoreConfig.getField_name());
				map.put("store_type", dStoreConfig.getStore_type());
				if(StringUtils.isEmpty(dStoreConfig.getClob_type())){
					map.put("clos_type", "clob");
				}else{
					map.put("clos_type", dStoreConfig.getClob_type());
				}
				clob_names.add(map);
			}
		}
		return clob_names;
	}
	
	/**
	 * 获取es_blob_config配置信息
	 * @param table
	 */
	public String getConfig(String table_name, String table_field, String source_from){
		String cfValue = "";
		//首先获取列表
		List list = dStoreConfigList();
		for (int i = 0; i < list.size(); i++) {
			DStoreConfig dStoreConfig = (DStoreConfig)list.get(i);
			if(dStoreConfig.getTable_name().equals(table_name) && dStoreConfig.getField_name().equals(table_field)){
				cfValue=dStoreConfig.getStore_type();
				break;
			}
		}
		return cfValue;
	}
	
	public String getStoreType(String table_name, String table_field, String file_path, String source_from){
//		System.out.println("getStoreType-->>table_name:"+table_name+" table_field:"+table_field+" file_path:"+file_path+" source_from:"+source_from);
		if("".equals(table_name) || null==table_name){
			if(null == blogManager){
				blogManager = SpringContextHolder.getBean("blogManager");
			}
			try{
				HashMap inst = (HashMap)blogManager.getDstoreInst(file_path);
				if(null == inst){
					return "";
				}else{
					return (String)inst.get("store_type");
				}
			}catch(Exception e){
				logger.warn(e.getMessage());
			}
			return "";			
		}else{
			return getConfig(table_name, table_field, source_from);	
		}
	}
	
	/**
	 * 设置文件按分布式还是按FTP方式存储
	 * @return
	 */
	public static IStoreProcesser getFileProcesser(){
		String store_type = FileConfigSetting.FILE_STORE_TYPE;
	 	IStoreProcesser netBlog = null;
		if("DFS".equals(store_type.toUpperCase())){ //分布式文件 
			netBlog = DFSProcesser.getNetBlog();
		}else if("FTP".equals(store_type.toUpperCase())){ //FTP文件
			netBlog = FTPProcesser.getNetBlog();
		}
		return netBlog;
	}
	
	public static IStoreProcesser getProcesser(String table_name, String table_field, String source_from ,Object obj){
		IStoreProcesser netBlog = null;
		//如果通过es_dstore_config查找缺少table字段，则判断大字段存储的是否是路径相关信息，如果是则是文件式存储，否则数据库存储
		StoreProcesser factory = new StoreProcesser();
//		String store_type = factory.getConfig(table_name.toUpperCase(), table_field.toUpperCase(),source_from);
		String store_type = factory.getStoreType(table_name.toUpperCase(), table_field.toUpperCase(),(String)obj,source_from);
		if(null!=store_type && !"".equals(store_type)){
			return getProcesser(store_type.toUpperCase());			
		}else{
			return getProcesser("");
		}
	}
	
	/**
	 * 根据存储类型选择处理器
	 * @author pzh
	 * @date 2016年6月13日 下午4:20:59
	 * @param store_type
	 * @return
	 */
	public static IStoreProcesser getProcesser(String store_type){
		IStoreProcesser netBlog = null;
		if(StringUtils.isEmpty(store_type)){
			if("DFS".equals(store_type.toUpperCase())){ //分布式文件 
				netBlog = DFSProcesser.getNetBlog();
			}else if("FTP".equals(store_type.toUpperCase())){ //FTP文件
				netBlog = FTPProcesser.getNetBlog();
			}else if("DB".equals(store_type.toUpperCase())){ 
				netBlog = DBProcesser.getNetBlog();
			}else{
				netBlog = DBProcesser.getNetBlog();
			}
		}else{
			netBlog = DBProcesser.getNetBlog();
		}
		return netBlog;
	}	

	public IBlogManager getBlogManager() {
		return blogManager;
	}

	public void setBlogManager(IBlogManager blogManager) {
		this.blogManager = blogManager;
	}
	
}
