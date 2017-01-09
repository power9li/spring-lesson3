package com.power.spring.lesson3.service;

import com.power.spring.lesson3.model.User;
import com.power.spring.lesson3.model.UserSession;

import java.util.List;
import java.util.Map;

public interface UserService {

	boolean createUser(User user);

	public boolean deleteUser(long userId);

	public boolean disableUser(long userId);

	public List<User> queryUsers(Map<String,Object> map);
	
	/**
	 * 如果密码不对，返回的UserSession对象里sessionId为空，客户端可以依次判断，参照UserSession.isValid方法
	 * @param userName
	 * @param md5EncodedPassword
	 * @return
	 */
	public UserSession login(String userName, String md5EncodedPassword);

	public List<User> queryAll();
}
