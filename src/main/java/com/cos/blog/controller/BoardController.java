package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

// @AuthenticationPrincipal PrincipalDetail principal // 컨트롤러에서 세션 찾는법
// System.out.println("로그인 유저 아이디 : " + principal.getUsername());
@Controller
public class BoardController {
	
	@GetMapping({"", "/"})
	public String index() { 
		return "index";
		//   /WEB-INF/views/index.jsp
		//   prefix: /WEB-INF/views/ , suffix: .jsp
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() { 
		return "board/saveForm";
	}
}