package com.spring.login.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.spring.login.exceptions.HandlerExceptions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date dateCurrent = new Date();
		Date dateExpiration = new Date(dateCurrent.getTime() + jwtExpirationInMs);
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return token;
	}
	
	public String getUsernameOfToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new HandlerExceptions("Firma JWT no valida", HttpStatus.BAD_REQUEST);
		} catch (MalformedJwtException e) {
			throw new HandlerExceptions("Token JWT no valido", HttpStatus.BAD_REQUEST);
		} catch (ExpiredJwtException e) {
			throw new HandlerExceptions("Token Caducado", HttpStatus.BAD_REQUEST);
		} catch (UnsupportedJwtException e) {
			throw new HandlerExceptions("Token no compatible", HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			throw new HandlerExceptions("Cadena Claims Vacia", HttpStatus.BAD_REQUEST);
		}
	}
}
