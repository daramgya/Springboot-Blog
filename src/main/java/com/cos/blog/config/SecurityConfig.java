package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 설정파일인 이 클래스가 빈으로 등록이 되어야 한다.
// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 아래 어노테이션 3개는 시큐리티에서 세트이다!!
@Configuration // 빈 등록 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 것이다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	// alt+shift+s 로 오버라이드
	
	
	@Bean // IoC가 된다!!  // 해당 클래스를 spring-container에서 관리
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 시큐리티가 대신 로그인, password를 가로채기 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입 되었는지를 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수가 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// passwordEncoder가 encodePWD()라고 알려줘야 한다.
		// principalDetailService를 통해서 로그인할 때 password를 encodePWD()로 인코드해서 아래 코드에서 알아서 비교해준다.
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 것이 좋다.)
			.authorizeRequests() // Request가 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // 이쪽으로 들어오는건
				.permitAll() // 누구나 들어올 수 있음
				.anyRequest() // 이게 아닌 다른 모든 요청은
				.authenticated() // 인증이 되어야해
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				// 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/");
				// 로그인 성공 시 해당 링크로 이동한다.
		
	}
}
