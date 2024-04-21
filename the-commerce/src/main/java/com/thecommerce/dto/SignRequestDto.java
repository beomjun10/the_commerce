package com.thecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.thecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SignRequestDto { 
	// 서버내 유효성 검사
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Pattern(regexp = "^[a-z0-9]{4,20}$" , message = "아이디는 영문 소문자와 숫자 4~12자리여야 합니다.")
	private String userName;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
			message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;
	private String name;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
	private String nickName;
	
	@NotBlank(message = "휴대폰 번호를 입력해주세요.")
	@Pattern(regexp = "(01[016789])-(\\d{3,4})-(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String phoneNo;
	
	@NotBlank(message = "이메일 주소를 입력해주세요.")
	@Email(message = "올바른 이메일 주소를 입력해주세요.")
	private String email;
	
	
	public static User toEntity(SignRequestDto signRequestDto) {   // 클라이언트에서 받은 SignRequestDto 값을 Entity 로 변환
		return User.builder()
					.userName(signRequestDto.getUserName())
					.password(signRequestDto.getPassword())
					.name(signRequestDto.getName())
					.nickname(signRequestDto.getNickName())
					.phoneNo(signRequestDto.getPhoneNo())
					.email(signRequestDto.getEmail())
					.build();
	}
	
	
}
