package com.mage.platform.service.db;

import java.util.List;
import java.util.Map;

import com.mage.platform.framework.database.DStoreConfig;
import com.mage.platform.framework.database.DStoreInst;

public interface IBlogManager {
	public List<DStoreConfig> getDStoreConfigList();
	public Map getDstoreInst(String file_path);
	public void insertDstoreInst(DStoreInst inst);
}
