package com.mage.platform.framework.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mage.consts.CoreConsts;
import com.mage.database.Page;
import com.mage.platform.framework.database.IDaoSupport;
import com.mage.platform.framework.exception.DBRuntimeException;
import com.mage.platform.utils.ManagerUtils;
import com.mage.platform.utils.MapUtils;
import com.mage.platform.utils.ReflectionUtil;

/**
 * jdbc数据库操作支撑
 * @author modify by pzh 2015-05-27
 * @param <T>
 */
@Service
public class JdbcDaoSupport<T> implements IDaoSupport<T> {

	@Resource
	protected JdbcTemplate jdbcTemplate;
	
	@Resource
	protected NamedParameterJdbcTemplate parameterJdbcTemplate;
	
	protected final Logger logger = Logger.getLogger(getClass());
	//日期格式
	public static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//关键字
	private static List<String> keywords = new ArrayList<String>();
	static {
		keywords.add("COMMENT");
	}
	
	@Override
	public Connection getConnection(){
		Connection conn = DataSourceUtils.getConnection(this.jdbcTemplate.getDataSource());
		return conn;
	}
	
	@Override
	public void closeConnection(Connection conn){
		try {
			DataSourceUtils.doReleaseConnection(conn, this.jdbcTemplate.getDataSource());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(String sql, Object... args) {
		try {
//			String sqlReg = "(?i)insert.*";
//			if(sql.matches(sqlReg)){
//				if(sql.indexOf("source_from") == -1 && sql.indexOf("SOURCE_FROM") == -1 ){
//					sql = sql.replaceFirst("\\)", ", source_from)");
//					int index = sql.lastIndexOf(")");
//                    if(index!=-1){
//                        sql = sql.substring(0, index) + ",'" + ManagerUtils.getSourceFrom() + "')";
//                    }
//				}
//			}
			this.jdbcTemplate.update(sql, args);
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		}
	}
	
	@Override
	public void batchExecute(String sql, List<Object[]> batchArgs){
		try {
			if(null != batchArgs && !batchArgs.isEmpty()){
				this.jdbcTemplate.batchUpdate(sql, batchArgs);
			}else {
				Connection conn = this.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				try {					
					pst.addBatch();
					pst.executeBatch();
				} catch (Exception f) {
					f.printStackTrace();
					throw new DBRuntimeException(f, sql);
				}finally{
					if (pst != null)
						pst.close();
					if (conn != null)
						this.closeConnection(conn);
				}
			}
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		}
	}
	
	
	@Override
	@SuppressWarnings({ "unchecked"})
	public void batchExecuteByListMap(String sql, List<Map<String, Object>> batchArgs) {
		try {
			Map<String, Object>[] pm = new Map[batchArgs.size()] ;
			int i = 0 ; 
			for(Map<String, Object> m : batchArgs){
				pm[i++] = m ;
			}
			SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(pm);				
			this.parameterJdbcTemplate.batchUpdate(sql, batch);
		} catch (Exception e) {
			throw new DBRuntimeException(e, sql);
		}
	}
	
	@Override
	public void insert(String table, Object po) {
		//断言
		Assert.hasText(table, "表名不能为空");
		Map<String, Object> fields = ReflectionUtil.getDbFields(po);
		Assert.notEmpty(fields, "字段不能为空");
		String sqlInsert = "insert into " + table + "(";
		String sqlVal = "values(";
		try {
			Object[] vals = new Object[fields.size()];
			Set<Entry<String, Object>> entry =  fields.entrySet();
			int count = 0;
			for(Entry<String, Object> field : entry){										//拼装sql字段
				String field_name = field.getKey();
				sqlInsert += field_name + ",";
				sqlVal += "?,";
				vals[count++] = field.getValue();
			}
			sqlInsert = sqlInsert.substring(0, sqlInsert.length() - 1) + ")";
			sqlVal = sqlVal.substring(0, sqlVal.length() - 1) + ")";
			jdbcTemplate.update(sqlInsert + sqlVal, vals);				//减少sql拼装，防止sql注入
		} catch (Exception e) {
			throw new DBRuntimeException(e, sqlInsert + sqlVal);
		}
	}
	
	@Override
	public void update(String table, Map<String, Object> fields, 
			Map<String, Object> where) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int update(String table, Map<String, Object> fields, String where,
			Object... args) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void update(String table, T po, Map<String, Object> where) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(String table, T po, String where) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(String sql, Map<String, Object> args) {
		this.jdbcTemplate.update(sql, args) ;
	}
	
	@Override
	public String getSequences(String tableName, String strSeqType, int len) {
		//子类实现
		return "";
	}
	
	@Override
	public String queryForString(String sql, Object... args) {
		String s = "";
		try {
			sql = this.addSourceFrom(sql);
			s  = (String)this.jdbcTemplate.queryForObject(sql, String.class);
		} catch (RuntimeException e) {}
		return s;
	}
	
	@Override
	public int queryForInt(String sql, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			return this.jdbcTemplate.queryForObject(sql, Integer.class, args);
		} catch (RuntimeException e) {
			this.logger.error(e.getMessage(), e);
			throw new DBRuntimeException(e, sql);
		}
	}
	
	@Override
	public int queryForIntByMap(String sql, Map<String, Object> args) {
		try {
			sql = this.addSourceFrom(sql);
			return this.jdbcTemplate.queryForObject(sql, Integer.class, args);
		} catch (RuntimeException e) {
			this.logger.error(e.getMessage(), e);
			throw new DBRuntimeException(e, sql);
		}
	}
	
	@Override
	public long queryForLong(String sql, Object... args) {
		sql = this.addSourceFrom(sql);
		return this.jdbcTemplate.queryForObject(sql, Long.class, args);
	}
	
	@Override
	public Map<String, Object> queryForMap(String sql, Object... args) {
		sql = this.addSourceFrom(sql);
		try {
			Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, args);
			Map<String, Object> newMap = new HashMap<String, Object>();
			Iterator<?> keyItr = map.keySet().iterator();
			while (keyItr.hasNext()) {
				String key = (String) keyItr.next();
				Object value = map.get(key);
				newMap.put(key.toLowerCase(), value);
			}
			return newMap;
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public T queryForObject(String sql, Class<T> clazz, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			return this.jdbcTemplate.queryForObject(sql, 
					ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);
		} catch (Exception ex) {
			this.logger.error("查询出错", ex);
		}
		return null;
	}
	
	@Override
	public T queryForObjectByMap(String sql, Class<T> clazz, Map<String, Object> args) {
		try {
			sql = this.addSourceFrom(sql);
			return jdbcTemplate.queryForObject(sql, 
					ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);
		} catch (Exception ex) {
			this.logger.error("查询出错", ex);
		}
		return null;
	}
	
	@Override
	public T queryForObject(String sql, ParameterizedRowMapper<T> mapper, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			return this.jdbcTemplate.queryForObject(sql, mapper, args);
		} catch (RuntimeException e) {
			this.logger.error("查询出错", e);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			return this.jdbcTemplate.queryForList(sql, args);
		} catch (RuntimeException e) {
			this.logger.error("查询出错", e);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> queryForListByMap(String sql,
			Map<String, Object> args) {
		sql = this.addSourceFrom(sql);
		return this.jdbcTemplate.queryForList(sql, args);
	}
	
	@Override
	public List<T> queryForList(String sql, RowMapper<T> mapper, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			return this.jdbcTemplate.query(sql, args, mapper);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public List<T> queryForList(String sql, Class<T> clazz, Object... args) {
		sql = this.addSourceFrom(sql);
		return this.jdbcTemplate.query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);
	}
	
	@Override
	public List<T> queryForListByMap(String sql, Class<T> clazz, Map<String, Object> args) {
		sql = this.addSourceFrom(sql);
		return this.jdbcTemplate.query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(clazz), args);
	}
	
	@Override
	public List<Map<String, Object>> queryForListPage(String sql, int pageNo,
			int pageSize, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = this.buildPageSql(sql, pageNo, pageSize);
			return queryForList(listSql, args);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public List<T> queryForList(String sql, int pageNo, int pageSize,
			RowMapper<T> mapper) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = this.buildPageSql(sql, pageNo, pageSize);
			return this.queryForList(listSql, mapper);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	@Override
	public Page queryForPage(String sql, String countSql, int pageNo,
			int pageSize, RowMapper<T> rowMapper, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			countSql = this.addSourceFrom(countSql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			List<T> list = this.queryForList(listSql, rowMapper, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DBRuntimeException(ex, sql);
		}
	}
	@Override
	public Page queryForPage(String sql, int pageNo, int pageSize,
			Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) " + removeSelect(removeOrders(sql));
			List<Map<String, Object>> list = queryForList(listSql, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	@Override
	public Page queryForPageByMap(String sql, int pageNo, int pageSize,
			Map<String, Object> args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) FROM ("
				+ removeOrders(sql) + " )";
			List<Map<String, Object>> list = queryForListByMap(listSql, args);
			int totalCount = queryForIntByMap(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public Page queryForPage(String sql, int pageNo, int pageSize,
			RowMapper<T> rowMapper, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, rowMapper, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public Page queryForPage(String sql, int pageNo, int pageSize,
			Class<T> clazz, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, clazz, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public Page qryForPage(String sql, int pageNo, int pageSize,
			Class<T> clazz, Object... args) {
		try {
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) "
					+ removeSelect(removeOrders(sql));
			List<T> list = this.queryForList(listSql, clazz, args);
			int totalCount = this.queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public Page queryForPageByMap(String sql, int pageNo, int pageSize,
			Class<T> clazz, Map<String, Object> args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			String countSql = "SELECT COUNT(*) FROM ("
				+ removeOrders(sql) + " )";
			List<T> list = queryForListByMap(listSql, clazz, args);
			int totalCount = queryForIntByMap(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public Page queryForPage(String sql, String countSql, int pageNo,
			int pageSize, Class<T> clazz, Object... args) {
		try {
			sql = this.addSourceFrom(sql);
			Assert.hasText(sql, "SQL语句不能为空");
			Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
			String listSql = buildPageSql(sql, pageNo, pageSize);
			
			List<T> list = this.queryForList(listSql, clazz, args);
			int totalCount = queryForInt(countSql, args);
			return new Page(0, totalCount, pageSize, list);
		} catch (Exception ex) {
			throw new DBRuntimeException(ex, sql);
		}
	}
	
	@Override
	public String queryForSingleResult(String sql, String key, Object... args) {
		Map<String, Object> map =null;
		sql = this.addSourceFrom(sql);
		try {
			 map = jdbcTemplate.queryForMap(sql,args);
		} catch (Exception e) {
			return "";
		}
		return MapUtils.getString(map, key, "");
	}
	
	@Override
	public String getLastId(String table) {
		//由子类实现
		return null;
	}
	
	@Override
	public String buildPageSql(String sql, int page, int pageSize) {
		//由子类实现
		return null;
	}
	
	
	/**************************核心表可以没有来源****************************/
	private static List<String> filterTables = new ArrayList<String>();
	
	static{
		filterTables.add("PM_APP");
	}
	
	private String addSourceFrom(String sql){
//		//检查是否含有source_from(where之后是否含有source_from，有则全部过滤掉)
//		for(String table : filterTables){
//			if(sql.indexOf(table)>-1 || sql.indexOf(table.toLowerCase())>-1)
//				return sql;
//		}
//		String regCheck = ".*(?i)\\bwhere\\b.*[\\s|.]source_from[\\s|=].*";
//		//替换所有where(where前后都有间隔符，不区分大小写)
//		String regReplace = "\\s+(?i)where\\s+";
//		
//		if(!sql.matches(regCheck)){				//如果where之后没有source_from,则替换所有where
//			sql = sql.replaceAll(regReplace, " where source_from = '" + ManagerUtils.getSourceFrom() + "' and ");
//		}
		return sql;
	}
	
	/**
	 * 去掉order by 加快速度
	 * @param hql
	 * @return
	 */
	private String removeOrders(String sql) {
		Assert.hasText(sql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 去除sql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 */
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = 0;
		if(hql.indexOf("dc_public")>-1)
		{
			//目前只替换了source_from
			beginPos = hql.toLowerCase().replaceAll("(?i)source_from", "SOURCE_FROM").lastIndexOf("from");
		}else{
			 beginPos = hql.toLowerCase().replaceAll("(?i)source_from", "SOURCE_FROM").indexOf("from");
		}
		return hql.substring(beginPos);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getParameterJdbcTemplate() {
		return parameterJdbcTemplate;
	}

	public void setParameterJdbcTemplate(
			NamedParameterJdbcTemplate parameterJdbcTemplate) {
		this.parameterJdbcTemplate = parameterJdbcTemplate;
	}
}
