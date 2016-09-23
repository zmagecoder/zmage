package com.mage.platform.framework.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import oracle.sql.CLOB;



/**
 * 小写key的columnrowmapper
 *  本类覆写了spring 的ColumnMapRowMapper，将字段名全部小写
 * @author modify by pzh 2016-05-27 
 */
public class LowerCaseColumnMapRowMapper extends  ColumnMapRowMapper {

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Map<String, Object> mapOfColValues = createColumnMap(columnCount);
		for (int i = 1; i <= columnCount; i++) {
			String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
			key = key.toLowerCase();
			Object obj = null;
			String typename= rsmd.getColumnTypeName(i);
			
			if("NUMBER".equals(typename)){
				int scale = rsmd.getScale(i);
				int precision = rsmd.getPrecision(i);
				if(scale == 0){
					if(precision<10)
						obj = rs.getInt(i);
					else
						obj = rs.getLong(i);
				}else if(scale>0){
					obj = rs.getDouble(i);
				}else{
					obj =rs.getObject(i);
				}
			}else if("CLOB".equals(typename)){
				obj = clobToString((CLOB) rs.getClob(i));
			}else if("BLOG".equals(typename)){
				obj = blobToString(rs.getBlob(i));
			}else {
				obj = getColumnValue(rs, i);
			}
			if(obj==null)
				obj = "";
			mapOfColValues.put(key, obj);
		}
		return mapOfColValues;
	}
	
	/**
	 * 将blob转为String
	 * @param blob
	 * @return
	 */
	public String blobToString(Blob blob){  
		StringBuffer str = new StringBuffer();
		InputStream in = null;
		try {  
			in = blob.getBinaryStream();  
			byte[] buff=new byte[(int) blob.length()];  
			while(in.read(buff) > 0){  
				str=str.append(new String(buff));  
			}
			return str.toString();
		}catch (Exception e) {  
			e.printStackTrace();  
		} finally{
			try{
				if(null != in)
					in.close();
			}catch(Exception e){}
		} 
		return null;
	}
	
	/**
	 * 将clob 转为string 
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	private String clobToString(CLOB clob){
		StringBuffer sb = new StringBuffer();   
        try{
        	Reader is = clob.getCharacterStream();// 得到流   
            BufferedReader br = new BufferedReader(is);   
            String s = br.readLine();   
            while (s != null) {  
    	        sb.append(s);   
    	        s = br.readLine();   
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return sb.toString();   
    }   
}
