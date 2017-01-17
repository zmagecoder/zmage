package com.mage.platform.framework.database;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.mage.database.Page;

/**
 * 数据库操作支撑接口
 * @author modify by pzh  2016-05-27
 * @param <T>
 */
public interface IDaoSupport<T> {
	
	/**
	 * 获取连接
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	public void closeConnection(Connection conn);
	
	/**
	 * 执行sql语句
	 */
	public void execute(String sql, Object... args) ;
	
	/**
	 * 批量执行sql语句
	 * @param sql
	 * @param args
	 */
	public void batchExecute(String sql, List<Object[]> batchArgs);
	
	/**
	 * 批量操作
	 * @param sql
	 * @param batchArgs
	 */
	public void batchExecuteByListMap(String sql, List<Map<String, Object>> batchArgs) ;
	
	/**
	 * 新增数据
	 * @param table 表名
	 * @param po 要新增的对象，保证对象的属性名和字段名对应
	 */
	public void insert(String table, Object po);
	
	/**
	 * 更新数据
	 * @param table 表名
	 * @param fields 字段-值Map
	 * @param where 更新条件(字段-值Map)
	 */
	public void update(String table, Map<String, Object> fields, Map<String, Object> where);
	
	/**
	 * 更新数据
	 * @param table  表名
	 * @param fields 字段-值Map
	 * @param where 更新条件,如"a='1' AND b='2'"
	 */
	public int update(String table, Map<String, Object> fields, String where, Object ...args);
	
	/**
	 * 更新数据
	 * @param table 表名
	 * @param po 要更新的对象，保证对象的属性名和字段名对应
	 * @param where 更新条件(字段-值Map)
	 */
	public void update(String table, T po, Map<String, Object> where);
	
	/**
	 * 更新数据
	 * @param table 表名
	 * @param po 要更新的对象，保证对象的属性名和字段名对应
	 * @param where 更新条件,如"a='1' AND b='2'"
	 */
	public void update(String table, T po, String where) ;
	
	/**
	 * 更新数据
	 * @param sql
	 * @param args
	 */
	public void update(String sql, Map<String, Object> args) ;
	
	
	/**
	 * 获取Oracle  Sequence
	 * @param tableName
	 * @param len序列长度
	 * @param strSeqType 获取类型
	 * @return
	 */
	public String getSequences(String tableName, String strSeqType, int len);
	
	/**
	 * 查询单个值
	 * @param sql
	 * @param args
	 * @return
	 */
    public String queryForString(String sql,Object...args);
    
    /**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>int</code>型返回
	 * @param sql 查询的sql语句，确定结果为一行一列，且为数字型
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public int queryForInt(String sql, Object... args);	
	
	/**
	 * 查询单一结果集<br/>  
	 * 并将结果转为<code>int</code>型返回
	 * @param sql 查询的sql语句，使用:name占位符  确定结果为一行一列，且为数字型
	 * @param args K-V 键值对
	 * @return
	 */
	public int queryForIntByMap(String sql, Map<String, Object> args);
	
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>long</code>型返回
	 * @param sql 查询的sql语句，确保结果为一行一列，且为数字型
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public long queryForLong(String sql, Object... args);
	
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>Map</code>对象返回
	 * @param sql 查询的sql语句
	 * @param args 对应sql语句中的参数值
	 * @return 以结果集中的列为key，值为value的<code>Map</code>
	 */
	public Map<String, Object> queryForMap(String sql, Object... args) ;
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>T</code>对象返回
	 * @param sql 查询的sql语句,确保结果列名和对象属性对应
	 * @param clazz  <code>T</code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public T queryForObject(String sql, Class<T> clazz, Object... args);
	
	/**
	 * 查询单一结果集<br/>
	 * 并将结果转为<code>T</code>对象返回
	 * @param sql 查询的sql语句,确保结果列名和对象属性对应
	 * @param clazz  <code>T</code>的Class对象
	 * @param args 对应sql语句中的name占位符
	 * @return
	 */
	public T queryForObjectByMap(String sql, Class<T> clazz, Map<String, Object> args);
 
	/**
	 * 查询单一结果集
	 * 映射字段与对象属性不一致
	 * @param sql
	 * @param mapper
	 * @param args
	 * @return
	 */
	public T queryForObject(String sql, ParameterizedRowMapper<T> mapper, Object... args) ;
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List<Map></code>
	 * @param sql 查询的sql语句
	 * @param args 对应sql语句中的参数值
	 * @return  列表中元素为<code>Map</code>的<code>List</code>,<br/>Map结构：以结果集中的列为key，值为value,
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... args);
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List<Map></code>
	 * @param sql 查询的sql语句 使用:name占位符
	 * @param args K-V 键值对
	 * @return  列表中元素为<code>Map</code>的<code>List</code>,<br/>Map结构：以结果集中的列为key，值为value,
	 */
	public List<Map<String, Object>> queryForListByMap(String sql, Map<String, Object> args);
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List</code>
	 * @param sql  查询的sql语句
	 * @param mapper 列和对象属性的Mapper
	 * @param args 对应sql语句中的参数值
	 * @return 列表中元素为<code>T</code>的<code>List</code>
	 */
	public List<T> queryForList(String sql, RowMapper<T> mapper, Object... args) ;
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List</code>
	 * @param sql 查询的sql语句
	 * @param clazz <code><T></code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return  列表中元素为<code>T</code>的<code>List</code>
	 */
	public List<T> queryForList(String sql, Class<T> clazz, Object... args);
	
	/**
	 * 查询多行结果集<br/>
	 * 并将结果转为<code>List</code>
	 * @param sql 查询的sql语句 使用named占位符
	 * @param clazz <code><T></code>的Class对象
	 * @param args K-V 键值对  对应sql语句中的name参数
	 * @return  列表中元素为<code>T</code>的<code>List</code>
	 */
	public List<T> queryForListByMap(String sql, Class<T> clazz, Map<String, Object> args);
	
	
	/**
	 * 分页查询多行结果集<br/>
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize  每页数量
	 * @param args  对应sql语句中的参数值
	 * @return 列表中元素为<code>Map</code>的<code>List</code>,<br/>Map结构：以结果集中的列为key，值为value,
	 */
	public List<Map<String, Object>> queryForListPage(String sql, int pageNo, int pageSize, Object... args);
	
	/**
	 * 分页查询多行结果集<br/>
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param mapper 列和对象属性的Mapper
	 * @return 列表中元素为<code>T</code>的<code>List</code>
	 */ 
	public List<T> queryForList(String sql,  int pageNo, int pageSize, RowMapper<T> mapper);
	
	
	/**
	 * 分页查询，并单独统计总数
	 * @param sql
	 * @param countSql
	 * @param pageNo
	 * @param pageSize
	 * @param rowMapper
	 * @param args
	 * @return
	 */
	public Page queryForPage(String sql, String countSql,int pageNo, int pageSize,RowMapper<T> rowMapper, Object... args);
	
	
	/**
	 * 分页查询
	 * @param sql  查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize  每页数量
	 * @param args  对应sql语句中的参数值
	 * @return 分页结果集对象
	 */
	public Page queryForPage(String sql, int pageNo, int pageSize, Object... args) ;

	/**
	 * 分页查询
	 * @param sql  查询的sql语句 使用:name占位符
	 * @param pageNo 查询的起始页
	 * @param pageSize  每页数量
	 * @param args  k-v 键值对 对应sql语句中的name参数
	 * @return 分页结果集对象
	 */
	public Page queryForPageByMap(String sql, int pageNo, int pageSize, Map<String, Object> args) ;
	
	/**
	 *  分页查询
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param rowMapper 列和对象属性的Mapper
	 * @param args 对应sql语句中的参数值
	 * @return 分页结果集对象
	 */
	public Page queryForPage(String sql,int pageNo, int pageSize, RowMapper<T> rowMapper, 	Object... args);
	
	/**
	 * 分页查询
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param clazz  <code><T></code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public Page queryForPage(String sql, int pageNo, int pageSize, Class<T> clazz, Object... args);
	
	
	/**
	 * 分页查询:因不需es_前缀而加，如查inf_comm_client_calllog数据
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param clazz
	 * @param args
	 * @return
	 */
	public Page qryForPage(String sql, int pageNo, int pageSize, Class<T> clazz, Object... args);
	/**
	 * 分页查询
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param clazz  <code><T></code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public Page queryForPageByMap(String sql, int pageNo, int pageSize, Class<T> clazz, Map<String, Object> args);
	
	
	/**
	 * 分页查询，提供count语句
	 * @param sql 查询的sql语句
	 * @param pageNo 查询的起始页
	 * @param pageSize 每页数量
	 * @param clazz  <code><T></code>的Class对象
	 * @param args 对应sql语句中的参数值
	 * @return
	 */
	public Page queryForPage(String sql, String countSql, int pageNo, int pageSize, Class<T> clazz, Object... args);
	
	/**
	 * 根据键查询单个字段
	 * @param sql
	 * @param key
	 * @param args
	 * @return
	 */
	String queryForSingleResult(String sql ,String key, Object... args);
	
	/**
	 * 获取当前事务最后一次更新的主键值
	 * @return
	 */
	public String getLastId(String table);
	
	/**
	 * 构造分页查询语句
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public String buildPageSql(String sql, int page, int pageSize);
}
