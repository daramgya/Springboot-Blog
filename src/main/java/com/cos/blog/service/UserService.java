package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IoC를 해준다.
// 메모리에 대신 띄워준다는 것이다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional // 오류 발생 시 롤백한다.
	public void 회원가입(User user) {
		userRepository.save(user);
	}
}
