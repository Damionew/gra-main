package com.damionew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity	//注解开启Spring Security功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 继承WebSecurityConfigurerAdapter并重写configure方法设置web安全细节
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
				.antMatchers("/index","/css/**","/js/**").permitAll()	//定义不需要被保护的URL
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
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("sa")
				.password("1")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
