package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter와 Setter를 동시에 만든다.
//@AllArgsConstructor // 생성자를 만든다.
@NoArgsConstructor // 빈 생성자
public class Member {
	// 여기다 private를 사용하는 이유는 바로 직접적으로 접근하는 것을 막기 위함이다.
	// 바로 접근해서 수정해버리는 것은 객체지향이랑 맞지 않는다.
	// 함수를 통해서 이 값들에 접근하게된다.
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}