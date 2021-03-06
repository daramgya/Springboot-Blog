package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략이 가능하다.
//해당 JpaRepository는 User테이블을 관리하는 Repository이다.
//그리고 User테이블의 Primary key는 Integer이다.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// findByUsername 이것도 JPA 네이밍 쿼리이다. (findBy~)
	// SELETE * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}
	
	//JPA 네이밍 쿼리
	// SELECT * FROM user WHERE username = ?1 AND password = ?2;
	
	//User findByUsernameAndPassword(String username, String password);
	
	// @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	// User login(String username, String password);
