package com.thecommerce.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
	private int code;
	private HttpStatus httpStatus;
	private String message;
}
