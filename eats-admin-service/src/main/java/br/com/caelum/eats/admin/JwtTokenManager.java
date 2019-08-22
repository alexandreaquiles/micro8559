package br.com.caelum.eats.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.caelum.eats.admin.Role.ROLES;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
class JwtTokenManager {

	private String secret;

	public JwtTokenManager(	@Value("${jwt.secret}") String secret) {
		this.secret = secret;
	}

	public boolean isValid(String jwt) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public User getUserFromToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt).getBody();
		User user = new User();
		user.setName(claims.get("username", String.class));
		user.setId(Long.parseLong(claims.getSubject()));
		List<String> roles = claims.get("roles", List.class);
		roles.stream().forEach(role -> user.addRole(ROLES.valueOf(role)));
		return user;
	}

}
