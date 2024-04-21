package com.thecommerce.dao;

import com.thecommerce.entity.User;

public interface UserDao {
	public User insert(User user);
	public User update(String userName, User user);
	public boolean existedByUserName(String userName);
	public boolean existedByEmail(String email);
	public boolean existedByNickName(String nickname);
	public boolean existedByPhoneNumber(String phoneNumber);
	
	
	
}
