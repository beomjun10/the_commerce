package com.thecommerce.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thecommerce.entity.User;
import com.thecommerce.repository.UserRepository;

@Repository
public class UserDaolmpl implements UserDao{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User insert(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(String userName, User user) {
		Optional<User> findUser = userRepository.findByUserName(userName);
		User newUser = null;
		if(findUser.isPresent()) {
			User updateUser = findUser.get();
			updateUser.setUserName(userName);
			updateUser.setPassword(user.getPassword());
			updateUser.setNickname(user.getNickname());
			newUser = userRepository.save(updateUser);
		}
		return newUser;
	}

	@Override
	public boolean existedByUserName(String userName) {
		if(userRepository.findByUserName(userName).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existedByEmail(String email) {
		if(userRepository.findByEmail(email).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existedByNickName(String nickname) {
		if(userRepository.findByNickname(nickname).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existedByPhoneNumber(String phoneNumber) {
		if(userRepository.findByPhoneNo(phoneNumber).isPresent()) {
			return true;
		}
		return false;
	}


}
