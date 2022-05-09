package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController // 데이터만 리턴해줄 것이기 때문에 RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
	// json 데이터를 받으려면 @RequestBody라는 어노테이션이 필요하다.
		System.out.println("UserApiController : save 호출됨");
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user); // 1이면 회원가입 성공, -1이면 실패
		return new ResponseDto<Integer>(HttpStatus.OK, 1); // (status, data)
		// 자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}
}
