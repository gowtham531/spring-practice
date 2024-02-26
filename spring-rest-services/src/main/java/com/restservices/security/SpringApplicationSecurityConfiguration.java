package com.restservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restservices.services.RestServicesService;

@Configuration
@EnableWebSecurity
public class SpringApplicationSecurityConfiguration {

	@Autowired
	JwtTokenSecurity jwtTokenSecurity;
	@Autowired
	RestServicesService service;
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public BCryptPasswordEncoder createPasswordEncrypter() {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity security) {
		System.out.println("Configuring security end points...");
		try {
			security.csrf().disable()
			               .authorizeHttpRequests()
			               .antMatchers("/user/create","/user/login")
			               .permitAll()
			               .anyRequest()
			               .authenticated()
			               .and()
			               .addFilterBefore(jwtTokenSecurity, UsernamePasswordAuthenticationFilter.class);
			return security.build();
		} catch (Exception e) {
		    System.out.println("exception while configuring security of endpoints...");
			System.err.println(e.getMessage());
		}
		
		return null;
	}
}
