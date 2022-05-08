package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
// 해당 JpaRepository는 User테이블을 관리하는 Repository이다.
// 그리고 User테이블의 Primary key는 Integer이다.
}