package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IoC를 해준다.
// 메모리에 대신 띄워준다는 것이다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired // encode가 DI가 되어서 주입이 된다.
	private BCryptPasswordEncoder encoder;

	@Transactional // 오류 발생 시 롤백한다.
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	/*
	 * @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료 (정합성)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
