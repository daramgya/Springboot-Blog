package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

// @AuthenticationPrincipal PrincipalDetail principal // 컨트롤러에서 세션 찾는법
// System.out.println("로그인 유저 아이디 : " + principal.getUsername());
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) { 
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index";
		//   /WEB-INF/views/index.jsp
		//   prefix: /WEB-INF/views/ , suffix: .jsp
	}
	
	@GetMapping("/board/{id}") // @Controller에서 이와 같은 파라미터 받는 방법 : @PathVariable
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() { 
		return "board/saveForm";
	}
}