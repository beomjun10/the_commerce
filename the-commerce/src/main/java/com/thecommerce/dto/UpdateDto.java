package com.thecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.thecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateDto {
	// 서버 내 유효성 검사
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
			message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
	private String nickName;
	
	public User toEntity() {
		return User.builder()
					.password(password)
					.nickname(nickName)
					.build();
	}
}
