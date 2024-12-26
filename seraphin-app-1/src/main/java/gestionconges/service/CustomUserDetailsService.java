package gestionconges.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gestionconges.model.User;
import gestionconges.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	 @Autowired
	 private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUserDetail(user);
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


}
