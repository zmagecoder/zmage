package com.mage.maven;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mage.maven.model.Attachment;
import com.mage.platform.service.db.IDaoSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
									"classpath:spring-mybatis.xml",
									"classpath:spring/common-spring.xml",
									"classpath:spring/jdbc-spring.xml"
								  })

public class MavenTest{
	private static Logger logger = Logger.getLogger(MavenTest.class);
	
//	private IUserService userService;
	
	@Resource
	private IDaoSupport<?> oracleDBSupport;
	
	public void hello(){
//		User user = this.userService.getUserById("31");
		String temp = oracleDBSupport.queryForString("select a.func_name from sy_func a  where a.func_code = 'SY_MODULE'");
		logger.info("=============>>>>" + temp);
	}
	
	public void insert(){
		
		Attachment attachment = new Attachment();
		attachment.setAttachmentid(1314);
		attachment.setAttachmentname("搬砖");
		attachment.setCreatedate("1989-06-19 00:00:01");
		
		oracleDBSupport.insert("bdc_attachment", attachment);
		logger.info("=============>>>>");
	}
	
	public void mapTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("/", "首页");
		map.put("/user", "用户");
		Set<String> set = map.keySet();
		for(String key : set){
			System.out.println("页面：" + key + "-----> 功能：" + map.get(key));
		}
	}
	
	@Test
	public void getRootPath(){
		System.out.println(System.getProperty("CONFIG"));
		
		System.out.println(this.getClass().getResource("/").getPath());
		
		System.out.println(System.getProperty("user.dir"));
	}
}