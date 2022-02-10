package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.UserService;

@RestController 
public class UserApiController {
	
	@Autowired 
	private UserService userService;

	@PostMapping ("/api/user")
	public ResponseDto<Integer> saveUser(@RequestBody User user) {
		user.setRole(RoleType.USER);
		int result = userService.join(user);
		System.out.println("save호출 ");		
		return new ResponseDto<Integer>(HttpStatus.OK, result);//브라우저 응답값, db리턴 결과값
	}
}
