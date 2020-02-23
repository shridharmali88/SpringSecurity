package com.nit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource ds;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("SecurityConfig.configure()");
		//set configuration to Authentication Provider
		auth.jdbcAuthentication()
			.dataSource(ds)
			.usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERLIST WHERE UNAME=?")
			.authoritiesByUsernameQuery("SELECT UNAME,ROLE FROM USER_ROLES WHERE UNAME=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("SecurityConfig.configure()");
		//set Configuration To Http
		http.authorizeRequests()
			.antMatchers("/home.htm").access("permitAll")
			.antMatchers("/wish.htm").access("hasAnyRole('ROLE_ADMIN','ROLE_FACULTY')")
			.and()
			.formLogin()
			.and()
			.logout().logoutSuccessUrl("/logout_success.jsp")
			.and()
            .exceptionHandling().accessDeniedPage("/access_denied.jsp")
			.and()
			.rememberMe()
			.and()
			.sessionManagement().maximumSessions(3).maxSessionsPreventsLogin(true);
	}
}
