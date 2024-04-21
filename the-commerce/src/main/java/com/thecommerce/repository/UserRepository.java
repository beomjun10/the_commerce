package com.thecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByUserName(String userName);
	public Optional<User> findByEmail(String email);
	public Optional<User> findByNickname(String nickName);
	public Optional<User> findByPhoneNo(String phoneNo);
}
