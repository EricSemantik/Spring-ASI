package spring.formation.repo;

import spring.formation.repo.jpa.CommandeRepositoryJpa;
import spring.formation.repo.jpa.FournisseurRepositoryJpa;
import spring.formation.repo.jpa.ProduitRepositoryJpa;

// Responsable de la création de toutes les repositories
public class RepositoryFactory {
	
	public static ICommandeRepository creerCommandeRepository() {
		return new CommandeRepositoryJpa();
	}
	
	// Créer la ProduitRepository ... en utilisant son interface (couplage faible)
	public static IProduitRepository creerProduitRepository() {
		return new ProduitRepositoryJpa();
	}
	
	public static IFournisseurRepository creerFournisseurRepository() {
		return new FournisseurRepositoryJpa();
	}
	
}
