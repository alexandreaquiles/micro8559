package br.com.caelum.apigateway;

import lombok.Data;

@Data
class UserInfoDto {

	private String username;
	private String password;
	
	public User toUser() {
		return new User(username, password);
	}

}
