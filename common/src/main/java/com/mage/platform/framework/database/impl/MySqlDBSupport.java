package com.mage.platform.framework.database.impl;

import org.springframework.stereotype.Service;

/**
 * mysql 数据操作
 * @author add by pzh 2015-05-27
 * @param <T>
 */
@Service
public class MySqlDBSupport<T> extends JdbcDaoSupport<T> {
	
	@Override
	public String buildPageSql(String sql, int page, int pageSize) {
		 String sql_str = sql + " LIMIT " + (page - 1) * pageSize + "," + pageSize;
		return sql_str.toString();
	}
	
	@Override
	public String getSequences(String tableName, String strSeqType, int len) {
		// TODO
		return "";
	}
	
}
