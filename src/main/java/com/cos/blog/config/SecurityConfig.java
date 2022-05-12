package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 설정파일인 이 클래스가 빈으로 등록이 되어야 한다.
// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 아래 어노테이션 3개는 시큐리티에서 세트이다!!
@Configuration // 빈 등록 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 것이다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() // Request가 들어오면
				.antMatchers("/auth/**") // 이쪽으로 들어오는건
				.permitAll() // 누구나 들어올 수 있음
				.anyRequest() // 이게 아닌 다른 모든 요청은
				.authenticated() // 인증이 되어야해
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}
