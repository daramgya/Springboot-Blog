package com.cos.blog.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 즉, MySQL일 경우 auto_increment를 사용한다는 것이다.
	private int id; // 시퀀스(오라클), auto_increment로 넘버링(MySQL)
	
	@Column(nullable = false, length = 30) // null이 될 수 없고, 30자 이상이 될 수 없다.
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 비밀번호 암호화한 해쉬 넣어볼 예정이므로 길게 설정
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role;
	// admin, user, manager ...
	// 정확히는 Enum을 사용하는 것이 좋다. Enum을 사용하면 어떤 데이터의 도메인(범위)을 만들 수 있다.
	// role이 String이므로 adminn과 같은 오타를 낼 수가 있다. Enum을 사용하면 이를 방지할 수 있다.
	
	@CreationTimestamp // 데이터가 insert나 update 될 때 시간이 자동으로 입력된다.
	private Timestamp createDate;
}