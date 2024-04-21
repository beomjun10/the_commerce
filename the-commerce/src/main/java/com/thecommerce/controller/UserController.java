package com.thecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thecommerce.entity.User;
import com.thecommerce.repository.UserRepository;
import com.thecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@GetMapping("/list")
//	public String userList(Model model, @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable,
//			@RequestParam(value = "sortBy", defaultValue = "createTime") String sortBy) {
//		Sort sorts;
//	    if (sortBy.equals("name")) {
//	    	sorts = Sort.by("name").ascending();
//	    } else {
//	    	sorts = Sort.by("createTime").descending();
//	    }
//	    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorts);
//		
//		Page<User> list = userRepository.findAll(pageable);
//		int nowPage = list.getPageable().getPageNumber() + 1;
//		int startPage = Math.max(nowPage -4, 1);
//		int endPage = Math.min(nowPage + 5, list.getTotalPages());
//		model.addAttribute("list",list);
//		model.addAttribute("nowPage", nowPage);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//		return "user_list";
//	}

}
