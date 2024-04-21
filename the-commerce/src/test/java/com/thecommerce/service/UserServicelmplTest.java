package com.thecommerce.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thecommerce.dto.SignRequestDto;
import com.thecommerce.entity.User;
import com.thecommerce.repository.UserRepository;

@SpringBootTest
class UserServicelmplTest {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	@Disabled
	void testGetUsers() {
	}

	@Test
	@Disabled
	void testSignUser() throws Exception {
		userService.signUser(SignRequestDto.builder()
									.userName("User1")
									.password("password1")
									.nickName("하하1")
									.name("홍길동")
									.phoneNo("010-1111-1111")
									.email("User1@naver.com")
									.build());
		
	}

	@Test
	@Disabled
	void testUpdateUser() throws Exception {
		userService.updateUser("User1", User.builder()
									.password("password2")
									.nickname("호랑나비")
									.build());
	}

	

}
