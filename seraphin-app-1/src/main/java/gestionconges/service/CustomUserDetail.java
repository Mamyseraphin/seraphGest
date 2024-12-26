package gestionconges.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import gestionconges.model.User;

public class CustomUserDetail implements UserDetails {
	
	private User user;
	
	public CustomUserDetail(User user) {
		this.user = user;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(() -> user.getRole());
	}
	
	public String getFullname() {
		return user.getFullname();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getEmailOfCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		if (principal instanceof CustomUserDetail) {
			// Si principal est une instance de CustomUserDetail, récupérez l'email
			return ((CustomUserDetail) principal).getUsername();
		} else if (principal instanceof String) {
			// Si principal est une chaîne (rare), elle représente généralement un nom d'utilisateur
			return (String) principal;
		}

		throw new IllegalStateException("Type de principal inattendu : " + principal.getClass().getName());
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
