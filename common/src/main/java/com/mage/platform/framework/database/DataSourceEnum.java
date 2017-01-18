package com.mage.platform.framework.database;

public enum DataSourceEnum {

	MYSQL("jdbc/mySql", "mySql 数据源"), 
	ORACLE("jdbc/oracle", "oralce 数据源"),
	SQLSERVER("jdbc/sqlServer", "sqlServer 数据源") ;
	
	private final String jdbc;
	private final String name;
	
	private DataSourceEnum(String jdbc, String name){
		this.jdbc = jdbc;
		this.name = name;
	}

	public String getJdbc() {
		return jdbc;
	}

	public String getName() {
		return name;
	}
	
}
