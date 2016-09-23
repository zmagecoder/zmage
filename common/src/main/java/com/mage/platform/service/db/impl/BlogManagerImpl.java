package com.mage.platform.service.db.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.platform.framework.database.BaseSupport;
import com.mage.platform.framework.database.DStoreConfig;
import com.mage.platform.framework.database.DStoreInst;
import com.mage.platform.service.db.IBlogManager;

@Service
public class BlogManagerImpl extends BaseSupport<DStoreConfig> implements IBlogManager {

	@Override
	public List<DStoreConfig> getDStoreConfigList() {
		String sql = "select a.* from es_blob_config a where 1=1" ;
		return this.getExecutor().queryForList(sql, DStoreConfig.class);
	}
	
	@Override
	public Map<String,Object> getDstoreInst(String file_path) {
		String sql = "select * from es_dstore_inst where file_path=?";
		List<Map<String,Object>> ret = this.getExecutor().queryForList(sql, file_path);
		return (ret==null ||ret.size()==0) ? null : ret.get(0);
	}
	
	@Override
	public void insertDstoreInst(DStoreInst inst) {
		this.getExecutor().insert("es_dstore_inst", inst);
	}
	
}
