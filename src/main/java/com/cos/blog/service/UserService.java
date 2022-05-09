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
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace(); // 에러의 발생근원지를 찾아서 단계별로 에러를 출력한다.
			System.out.println("UserService : 회원가입() : "+e.getMessage());
		}
		return -1;
	}
}
