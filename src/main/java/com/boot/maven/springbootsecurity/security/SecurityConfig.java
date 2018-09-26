package com.boot.maven.springbootsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
//				.antMatchers("/dashboard/**").permitAll()
				.antMatchers("/dashboard/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.defaultSuccessUrl("/dashboard", true)
				.loginPage
				("/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll();
	}
	@Autowired
	public void configureGlobal(
			AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
//		.withUser("user").password("password").roles("USER");
		.withUser("user")
        .password("{noop}password")
        .roles("USER")
        .and()
        .withUser("sysuser")
        .password("password")
        .roles("SYSTEM");
		
//		auth.inMemoryAuthentication()
//		.withUser(User.class.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
	}
}
