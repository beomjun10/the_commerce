package com.thecommerce.dto;

import java.time.LocalDateTime;

import com.thecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
	private Long id;
	private String userName;
	private String password;
	private String name;
	private String nickname;
	private String phoneNo;
	private String email;
	private LocalDateTime createTime;
	
	public static UserDto toDto(User entity) {  // DB의 값을 Dto값들로 변환하여 클라이언트에 응답
		return UserDto.builder()
				.id(entity.getId())
				.userName(entity.getUserName())
				.password(entity.getPassword())
				.name(entity.getName())
				.nickname(entity.getNickname())
				.phoneNo(entity.getPhoneNo())
				.email(entity.getEmail())
				.createTime(entity.getCreateTime())
				.build();
	}
	
	
	
}
