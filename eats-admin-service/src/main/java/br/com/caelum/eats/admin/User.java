package br.com.caelum.eats.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String password;

	private List<Role> authorities = new ArrayList<>();
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public List<String> getRoles() {
		return authorities.stream().map(Role::getRole).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean isInRole(Role.ROLES role) {
		return getRoles().contains(role.name());
	}

	public void addRole(Role.ROLES role) {
		this.authorities.add(new Role(role.asAuthority()));
	}

}
