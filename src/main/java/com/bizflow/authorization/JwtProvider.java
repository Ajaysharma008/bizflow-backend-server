package com.bizflow.authorization;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.JWT_SECRET.getBytes());
	
	public String generateToken(Authentication authentication) {
		String email = authentication.getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String roles = populateAuthorities(authorities);
		return Jwts.builder()
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() + 24*60*60*1000))
				.claim("email", email)
				.claim("authorities", roles)
				.signWith(key)
				.compact();
	}
	
	public String getEmailFromToken(String jwt) {
		 jwt = jwt.substring(7);
		 
		 Claims claims = Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(jwt)
					.getPayload();
		 
		 
		 return String.valueOf(claims.get("email"));
		 
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated method stub
		Set<String> auths = new HashSet<>();
		
		for(GrantedAuthority authority : authorities) {
			auths.add(authority.getAuthority());
			
		}
		return String.join(",",auths);
	}
}
