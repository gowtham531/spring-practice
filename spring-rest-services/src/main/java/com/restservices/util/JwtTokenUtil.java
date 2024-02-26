package com.restservices.util;



import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	private String secretKey = "jhgfjsjmhsdkbvksjhfgsbgjkisdnhbkljsdffnbhkjcvsdifvljknvlhijvfihuhnvi"
			+ "jdbhsfjkyhfkjdbdkmjdhikjsdghksu";
		
	public String createToken(String username) {
		String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5000000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
		return token;
	}

		//todo
		//validating token
		public boolean validateToken(String token , String loginUserName) {
			try {
			String userName = extractUsername(token);
			 boolean isValid = isTokenNotExpired(token);
			return isValid && userName.equalsIgnoreCase(loginUserName);
		}catch(Exception e) {
			System.out.println("token is expired");
			return false;}
		}
		//extracting user name from token
		public String extractUsername(String token) {
			
			try {
			String userName = Jwts.parser()
					              .setSigningKey(secretKey)
					              .parseClaimsJws(token)
					              .getBody()
					              .getSubject();
			return userName;
		}catch(Exception e) {
			System.out.println("token is expired right now");
			return null;
		}
		}
	public boolean isTokenNotExpired(String token) {
	
		Date expiryDate = null;
		try {
		     expiryDate = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
		return expiryDate.after(new Date());
		
	} catch (Exception e) {
		System.out.println("token is expired");
		return false;
	}
	
		}

	}


