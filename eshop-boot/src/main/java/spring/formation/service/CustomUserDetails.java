package spring.formation.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import spring.formation.model.Client;
import spring.formation.model.Personne;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Personne personne;

	public CustomUserDetails(Personne personne) {
		super();
		this.personne = personne;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (personne instanceof Client) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CLIENT");
		} else {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_FOURNISSEUR");
		}
	}

	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(personne.getMotDePasse());
	}

	@Override
	public String getUsername() {
		return personne.getLogin();
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

}
