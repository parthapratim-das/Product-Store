package com.partha.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SpringConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception
	{
		auth.userDetailsService(userDetailsService);
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http)throws Exception
	{
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/mvc/admin/**").hasRole("ADMIN")
		.antMatchers("/mvc/products/").permitAll()
		.antMatchers("/mvc/home/").permitAll()
		.antMatchers("webjars/**").permitAll()
		.and()
		.formLogin()
		.and().logout()
		.logoutUrl("/logout")
        .logoutSuccessUrl("/mvc/home/")
		.invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .clearAuthentication(true);
	}
	

}
