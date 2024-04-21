package com.thecommerce.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thecommerce.dto.PageResponse;
import com.thecommerce.dto.SignRequestDto;
import com.thecommerce.dto.UpdateDto;
import com.thecommerce.dto.UserResponse;
import com.thecommerce.exception.ExistedByEmailException;
import com.thecommerce.exception.ExistedByNickNameException;
import com.thecommerce.exception.ExistedByPhoneNumberException;
import com.thecommerce.exception.ExistedByUserNameException;
import com.thecommerce.exception.UserNotFoundException;
import com.thecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ApiController {
	
	@Autowired
	private final UserService userService;
	
	@GetMapping("/list")
	public PageResponse UserListPaging(
            @RequestParam(value = "pageNo", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createTime") String sort
    ) {
		if (!sort.equals("name") && !sort.equals("createTime")) {
            sort = "createTime"; // 잘못된 값이 들어올 경우 기본값으로 설정
        }
        return userService.userListAll(page -1, pageSize, sort); //pageable의 page는 0부터 시작인데 보통 1페이지부터 시작이니까 -1처리함
    }
	
	@PostMapping("/join")
	public ResponseEntity<UserResponse> Sign_action(@Valid @RequestBody SignRequestDto signRequestDto) {
	    try {
	        userService.signUser(signRequestDto);
	        UserResponse res = new UserResponse(201, HttpStatus.CREATED, "회원가입 완료"); // 회원 등록이 완료되면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (ExistedByUserNameException e) {
	        UserResponse res = new UserResponse(400, HttpStatus.BAD_REQUEST, e.getMessage()); // ExistedByUserNameException이 발생하면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (ExistedByEmailException e) {
	        UserResponse res = new UserResponse(400, HttpStatus.BAD_REQUEST, e.getMessage()); // ExistedByEmailException이 발생하면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (ExistedByPhoneNumberException e) {
	        UserResponse res = new UserResponse(400, HttpStatus.BAD_REQUEST, e.getMessage()); // ExistedByPhoneNumberException이 발생하면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (ExistedByNickNameException e) {
	        UserResponse res = new UserResponse(400, HttpStatus.BAD_REQUEST, e.getMessage()); // ExistedByNickNameException이 발생하면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (Exception e) {
	        UserResponse res = new UserResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다.");
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    }
	}
	
	
	@PutMapping("/{userName}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable("userName") String userName, @Valid @RequestBody UpdateDto updateDto) {
	    try {
	        userService.updateUser(userName, updateDto.toEntity());
	        UserResponse res = new UserResponse(200, HttpStatus.OK, "회원 정보 수정 완료"); // 회원 정보 수정이 완료되면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (ExistedByNickNameException e) {
	        UserResponse res = new UserResponse(400, HttpStatus.BAD_REQUEST, e.getMessage()); // ExistedByNickNameException이 발생하면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (UserNotFoundException e) {
	        UserResponse res = new UserResponse(404, HttpStatus.NOT_FOUND, e.getMessage());  // UserNotFoundException이 발생하면 클라이언트에게 응답
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    } catch (Exception e) {
	        UserResponse res = new UserResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다.");
	        return new ResponseEntity<>(res, res.getHttpStatus());
	    }
	}
	
	
	
}
