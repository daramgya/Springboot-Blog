package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 된다.
	
	// @ColumnDefault("0")
	private int count; // 조회수
	
	//private int userId; // ORM에서는 이 방식을 사용하지 않는다.
	
	@ManyToOne(fetch = FetchType.EAGER) // 연관관계를 맺어줘야 한다. Many=Board, User=One
	@JoinColumn(name="userId")
	// 실제로 데이터베이스에 만들어질 때는 userId라는 이름으로 만들어진다.
	// 즉 테이블에 userId라는 필드값이 들어가게 된다.
	private User user;
	// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	// 자바 프로그램에서 데이터베이스의 자료형에 맞춰 테이블을 만들게 된다.
	// ORM을 사용하면 User user와 같이 오브젝트를 그대로 저장할 수 있다.
	// 그런데 이렇게 만들면 테이블이 어떻게 인식할까?
	// User 객체니 User.java 참조하고 Board.java에서 FK로 만들어 테이블에 저장한다.
	// 데이터베이스에 오브젝트를 저장할 수 없기 때문이다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	// 하나의 게시물에 여러 덧글이 달린다.
	// mappedBy : 연관관계의 주인이 아니다.(나는 FK가 아니다) DB에 칼럼 만들지 마십셔!
	// 나는 그냥 Board를 select할 때 join을 통해 값을 얻기 위해 필요한 것이다.
	private List<Reply> reply;
	// 즉, 얘는 테이블에 생성되는 FK가 아니다.
	// 나중에 select 하기 위해서 있는 코드
	
	@CreationTimestamp
	private Timestamp createDate;

}
