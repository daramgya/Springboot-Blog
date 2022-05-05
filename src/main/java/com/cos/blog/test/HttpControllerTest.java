package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @Controller : 사용자가 요청하면 HTML 파일을 응답해준다.
// @RestController : 사용자가 요청하면 Data를 응답해준다.
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		
		Member m = Member.builder().id(1).username("daramG").password("1234").email("daramG@gmail.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("Raichu");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료!";
	}
	
	//Member m = new Member(2, "daramG", "1234", "email");
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	//  http://localhost:9090/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) { // select
		return "get 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword();
	}
	
	//  http://localhost:9090/http/post
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) {
		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//  http://localhost:9090/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) { // update
		return "put 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//  http://localhost:9090/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() { // delete
		return "delete 요청";
	}
}
