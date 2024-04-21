package com.thecommerce.service;



import com.thecommerce.dto.PageResponse;
import com.thecommerce.dto.SignRequestDto;
import com.thecommerce.entity.User;

public interface UserService {
	public PageResponse userListAll(int pageNo, int pageSize, String sortBy);
	public void signUser(SignRequestDto signRequestDto) throws Exception;
	public void updateUser(String userName, User user) throws Exception;
	public boolean isDuplicateByUserName(String userName);
	public boolean isDuplicateByEmail(String email);
	public boolean isDuplicateByNickName(String nickName);
	public boolean isDuplicateByPhoneNumber(String phoneNumber);
	
	
}
