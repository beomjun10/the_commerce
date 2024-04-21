package com.thecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thecommerce.dao.UserDao;
import com.thecommerce.dto.PageResponse;
import com.thecommerce.dto.SignRequestDto;
import com.thecommerce.dto.UserDto;
import com.thecommerce.entity.User;
import com.thecommerce.exception.ExistedByEmailException;
import com.thecommerce.exception.ExistedByNickNameException;
import com.thecommerce.exception.ExistedByPhoneNumberException;
import com.thecommerce.exception.ExistedByUserNameException;
import com.thecommerce.exception.UserNotFoundException;
import com.thecommerce.repository.UserRepository;

@Service
public class UserServicelmpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public PageResponse userListAll(int pageNo, int pageSize, String sortBy) {
		Sort sorts;
        if (sortBy.equals("name")) {
        	sorts = Sort.by("name").ascending(); // 이름에 따라 오름차순 정렬
        } else {
        	sorts = Sort.by("createTime").descending(); // 기본은 가입일에 따라 내림차순 정렬
        }
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sorts);
		Page<User> page = userRepository.findAll(pageable);
		List<User> userList = page.getContent();
		List<UserDto> content = userList.stream().map(User -> UserDto.toDto(User)).collect(Collectors.toList()); // 스트림으로 user객채를 UserDto 객체로 변환하는 정적 메소드를 호출하여 새로운 리스트에 모으는 작업
		return PageResponse.builder()
				.userList(content)
				.pageNo(pageNo)
				.pageSize(pageSize)
				.totalElements(page.getTotalElements())
				.totalPages(page.getTotalPages())
				.last(page.isLast())
				.build();
	}
	
	@Transactional
	@Override
	public void signUser(SignRequestDto signRequestDto) throws Exception {  
		if(userDao.existedByUserName(signRequestDto.getUserName())) {		// SignRequestDto에서 받은 닉네임값이랑 existedByUserName메소드에서 userRepository를 통해 DB에 있는 닉네임이랑 비교
			throw new ExistedByUserNameException(signRequestDto.getUserName() + " 는 이미 존재하는 아이디 입니다.");  // 존재하면 해당예외를 발생시키며,  컨트롤러에서 이 예외를 받아서 클라이언트에게 적절한 응답을 반환함
		} else if (userDao.existedByEmail(signRequestDto.getEmail())) {
			throw new ExistedByEmailException(signRequestDto.getEmail() + " 는 이미 등록된 이메일 입니다."); // 존재하면 해당예외를 발생시키며,  컨트롤러에서 이 예외를 받아서 클라이언트에게 적절한 응답을 반환함
		} else if (userDao.existedByPhoneNumber(signRequestDto.getPhoneNo())) {
			throw new ExistedByPhoneNumberException(signRequestDto.getPhoneNo() + " 는 이미 등록된 번호 입니다."); // 존재하면 해당예외를 발생시키며,  컨트롤러에서 이 예외를 받아서 클라이언트에게 적절한 응답을 반환함
		} else if (userRepository.findByNickname(signRequestDto.getNickName()).isPresent()) {
			throw new ExistedByNickNameException(signRequestDto.getNickName() + " 는 사용중인 닉네임 입니다."); // 존재하면 해당예외를 발생시키며,  컨트롤러에서 이 예외를 받아서 클라이언트에게 적절한 응답을 반환함
		}
		
		userDao.insert(SignRequestDto.toEntity(signRequestDto)); // 클라이언트에서 SignRequestDto로 받은 값들을 Entity로 변환해서 repository를 통해 save시켜 DB에 저장
	}
	
	
	@Transactional
	@Override
	public void updateUser(String userName, User user) throws Exception{
		Optional<User> findUser = userRepository.findByUserName(userName);
		if(findUser.isPresent()) {
			User newUser = User.builder()
				.password(user.getPassword())
				.nickname(user.getNickname())
				.build();
			if(findUser.get().getNickname().equals(newUser.getNickname())) {  // DB에 저장되어있는 닉네임이랑 updateDto에서 받은 닉네임이랑 비교
				throw new ExistedByNickNameException(newUser.getNickname() + " 는 사용중인 닉네임 입니다."); // 동일하면 해당예외를 발생시키며,  컨트롤러에서 이 예외를 받아서 클라이언트에게 적절한 응답을 반환함
			}
			userDao.update(userName, newUser);
		} else {
			throw new UserNotFoundException(userName + " 사용자를 찾을 수 없습니다."); // 사용자를 찾지 못했을때 해당예외를 발생시키며, 비즈니스 로직에서 사용된다. 컨트롤러에서 이 예외를 받아서 클라이언트에게 적절한 응답을 반환함
		}
	}

	
	@Override
	public boolean isDuplicateByUserName(String userName) {
		return userDao.existedByUserName(userName);
	}

	@Override
	public boolean isDuplicateByEmail(String email) {
		return userDao.existedByEmail(email);
	}

	@Override
	public boolean isDuplicateByNickName(String nickName) {
		return userDao.existedByNickName(nickName);
	}

	@Override
	public boolean isDuplicateByPhoneNumber(String phoneNumber) {
		return userDao.existedByPhoneNumber(phoneNumber);
	}


	
	
}
