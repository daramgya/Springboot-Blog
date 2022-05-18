package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController // 데이터만 리턴해줄 것이기 때문에 RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		// json 데이터를 받으려면 @RequestBody라는 어노테이션이 필요하다.
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user); // 1이면 회원가입 성공, -1이면 실패
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // (status, data)
		// 자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
	// @RequestBody가 없으면 key=value 형태의 데이터만 받을 수 있으며, 이것은 x-www-form-urlencoded이다.
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 되었지만,
		// 세션값이 변경되지 않기 때문에 직접 세션값을 변경하자
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// 세션값 변경
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	

	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session) {
	 * System.out.println("UserApiController : save 호출됨"); User principal =
	 * userService.로그인(user); // principal (접근주체)
	 * 
	 * if (principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */

}
