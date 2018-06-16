package com.damionew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.damionew.security.CustomUserService;
import com.damionew.security.MyPasswordEncoder;

@Configuration
@EnableWebSecurity	//注解开启Spring Security功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 自定义用户登录验证逻辑
	 */
	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}
	/**
	 * security添加自定义登录验证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserService())
		.passwordEncoder(new MyPasswordEncoder())
		.and()
		.eraseCredentials(true);
	}
	/**
	 * 继承WebSecurityConfigurerAdapter并重写configure方法设置web安全细节
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
				.antMatchers("/assets/**","/css/**","/js/**").permitAll()	//定义不需要被保护的URL
				.anyRequest().authenticated()	//定义需要被保护的URL，即需要通过身份验证
				.and()
			.formLogin()	//定义当用户需要登录时，跳转的登录页面
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/index")
				.and()
			.logout()
				.permitAll();
		
	}
	
//	/**
//	 * 内存中创建一个用户
//	 */
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		@SuppressWarnings("deprecation")
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("sa")
//				.password("1")
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
}
