package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController // html파일이 아니라 데이터를 리턴해주는 controller = RestController
public class DummyControllerTest {
	
	// @Autowired 전에는 null값이다.
	// ↓ 의존성 주입 // Autowired로 UserRepository를 메모리에 띄워준다.
	@Autowired // UserRepository 타입으로 스프링이 관리하고 있는 객체가 있다면 userRepository로 넣어라
	private UserRepository userRepository;
	
	// http://localhost:9090/blog/dummy/user/3
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id : " + id;
	}
	
	// http://localhost:9090/blog/dummy/user/3
	// email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
	// json 데이터를 받으려면 @RequestBody라는 어노테이션이 필요하다.
	// json 데이터를 요청했는데 스프링이(MessageConverter의 Jackson 라이브러리가)
	// 자바 오브젝트로 변환해서 받아준다.
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		// 원래 자바는 파라미터에 함수를 집어넣을 수가 없다. 객체를 넣어야 한다.
		// 하지만 1.8부터 람다식을 이용해 함수를 넣을 수가 있다.
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);
		
		// 더티 체킹
		return null;
	}
	
	
	//  http://localhost:9090/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//  http://localhost:9090/blog/dummy/user
	//  ?page=id
	// 한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터를 전달받을 수 있다.
	// http://localhost:9090/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
	// 함수의 파라미터에서 처리를 해야한다. 이를 통해 id에 매핑되서 쏙 들어온다.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 사용자가 없습니다!");
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 웹브라우저는 html, js 이해하지만 자바 오브젝트는 이해하지 못한다.
		// 따라서 자바 오브젝트를 json으로 변환해서 데이터를 전송한다.
		// 스프링부트는 MessageConverter라는 애가 응답 시 자동 작동한다!
		// 만약 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson이라는 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다. (json타입의 데이터로 변환)
		return user;
	}
	
	// 람다식 사용
	//User user = userRepository.findById(id).orElseThrow(()->{
	//	return new IllegalArgumentException("해당 유저는 없습니다. id : "+ id);
	//});
	
	//   http://localhost:9090/blog/dummy/join (요청)
	//   http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join") // 회원가입(insert) 해야하니 PostMapping
	public String join(User user) {
		// @RequestParam("username") String u 이렇게해도 username이 변수 u에 딱 들어옴
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}