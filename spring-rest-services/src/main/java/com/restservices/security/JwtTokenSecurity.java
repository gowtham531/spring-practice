package com.restservices.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restservices.services.RestServicesService;
import com.restservices.util.JwtTokenUtil;
@Component
public class JwtTokenSecurity extends OncePerRequestFilter{

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	RestServicesService service;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        
		//is token available or not in request
		String token = request.getHeader("Authorization");
		
		//validating token
		String userName = null;
		if( token != null) {
			userName = this.jwtTokenUtil.extractUsername(token);
	}else {
		System.out.println("given token is not valid");
	}
		//if token is available then validaate the token
		
		
     //userName from token is equal to username from database
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		 //loading data from DB by passing username from token
			System.out.println("validating username from token is available or not...");
			UserDetails user = service.loadUserByUsername(userName);
			
			//validating token by passing username received from DB
		    Boolean isValidToken = this.jwtTokenUtil.validateToken(token, user.getUsername());
		    if(isValidToken) {
		    	//setting authentication status in Security Context
		    	System.out.println("setting authentication status in Spring Security...");
		    	UsernamePasswordAuthenticationToken usernamePasswAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
		    	SecurityContextHolder.getContext().setAuthentication(usernamePasswAuthenticationToken);
		    	
		    }
		}else {
			System.out.println("invalid token.Please send valid token");
		}		filterChain.doFilter(request, response);

		}

}
