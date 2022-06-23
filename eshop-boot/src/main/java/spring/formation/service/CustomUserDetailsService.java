package spring.formation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.formation.model.Client;
import spring.formation.model.Fournisseur;
import spring.formation.repo.IClientRepository;
import spring.formation.repo.IFournisseurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IClientRepository clientRepo;
	@Autowired
	private IFournisseurRepository fournisseurRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Client> optClient = clientRepo.findByLogin(username);

		if (optClient.isPresent()) {
			return new CustomUserDetails(optClient.get());
		} else {
			Optional<Fournisseur> optFournisseur = fournisseurRepo.findByLogin(username);

			if (optFournisseur.isPresent()) {
				return new CustomUserDetails(optFournisseur.get());
			} else {
				throw new UsernameNotFoundException(username + " Inconnu");
			}
		}
	}

}
