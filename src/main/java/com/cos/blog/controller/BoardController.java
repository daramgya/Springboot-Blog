package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 찾는법
		// /WEB-INF/views/index.jsp
		System.out.println("로그인 유저 아이디 : " + principal.getUsername());
		return "index";
	}
}