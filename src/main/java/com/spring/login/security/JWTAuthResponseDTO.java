package com.spring.login.security;

public class JWTAuthResponseDTO {

	private String accessToken;
	private String typeToken = "Bearer";
	
	
	
	public JWTAuthResponseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	public JWTAuthResponseDTO(String accessToken, String typeToken) {
		super();
		this.accessToken = accessToken;
		this.typeToken = typeToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTypeToken() {
		return typeToken;
	}
	public void setTypeToken(String typeToken) {
		this.typeToken = typeToken;
	}
	
	
}
