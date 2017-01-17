package com.mage.platform.framework.database.impl;

import org.springframework.stereotype.Service;

/**
 * oracle 数据操作
 * @author add by pzh 2015-05-27
 * @param <T>
 */
@Service
public class OracleDBSupport<T> extends JdbcDaoSupport<T> {
	
	public String buildPageSql(String sql, int page, int pageSize) {
		StringBuffer local_sql = new StringBuffer("SELECT * FROM (SELECT t1.*,rownum sn1 FROM (");
		local_sql.append(sql);
		local_sql.append(") t1) t2 WHERE t2.sn1 BETWEEN ");
		local_sql.append((page - 1) * pageSize + 1);
		local_sql.append(" AND ");
		local_sql.append(page * pageSize);
		return local_sql.toString();
	}
	
	@Override
	public String getSequences(String tableName, String strSeqType, int len) {
		String sql="";
		if(strSeqType.trim().equals("0")){		//按长度取序列值
			sql = "SELECT LPAD(getseq(?),"+len+",'0') SEQ FROM DUAL";
		}else if(strSeqType.trim().equals("1")){   //在前面加8位年月日
			sql="SELECT to_char2(getdate(),'yyyymmdd')|| round(dbms_random.value(1000,9999)) ||LPAD(getseq(?),"+len+"-12,'0')  seq  FROM DUAL";
		}else if(strSeqType.trim().equals("2")){   //在前面加14位年月日时分秒
			sql="select to_char2(getdate(),'yyyymmddhh24miss')||LPAD(getseq(?),"+len+"-14,'0') seq from dual ";
		}else if(strSeqType.trim().equals("3")){		//按长度取序列值
			len = len-8;
			sql = "SELECT to_char2(getdate(),'yyyymmdd') || LPAD(getseq(?),"+len+",'0') SEQ FROM DUAL";
		}else if(strSeqType.trim().equals("4")){
			sql="SELECT '69'||to_char2(getdate(),'yyyymmdd')||LPAD(getseq(?),"+len+"-12,'0')  seq  FROM DUAL";
		}else{		//直接取序列
			sql="select getseq(?) from dual";
		}
		String ret = this.jdbcTemplate.queryForObject(sql, String.class, this.getSeqByTableName(tableName));
		return ret;
	}
	
	/**
	 * 获取序列
	 * @param table_name
	 * @return
	 */
	private String getSeqByTableName(String table_name) {
		return "SEQ_" + table_name;
	}
	
	@Override
	public void insert(String table, Object po) {
		super.insert(table, po); 			//调用父类方法,通用字段的处理
		//TODO 对特殊字段进行处理， 比如大文本字段操作
	}
}
