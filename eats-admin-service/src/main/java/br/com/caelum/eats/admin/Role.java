package br.com.caelum.eats.admin;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	public static enum ROLES {
		ADMIN, PARCEIRO;
		public String asAuthority() {
			return "ROLE_" + name();
		}
	}

	private String authority;

	public String getRole() {
		return authority.replace("ROLE_", "");
	}
}
