package com.mage.test.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mage.test.dao.UserMapper;
import com.mage.test.model.User;
import com.mage.test.service.IUserService;

@Service
public class UserService implements IUserService {

	private UserMapper userMapper;
	
	private static Logger logger = Logger.getLogger(UserService.class);
	
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		logger.info("开始查询数据");
		return userMapper.selectByPrimaryKey(1l);
	}

}
