package spring.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

public class FournisseurRepositoryJpa extends AbstractRepositoryJpa<Fournisseur> implements IFournisseurRepository {
	protected final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");
	
	public FournisseurRepositoryJpa() {
		super(Fournisseur.class);
	}
	
	public List<Fournisseur> findAll() {
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.createQuery("select f from Fournisseur f", Fournisseur.class).getResultList();
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
		
		finally {
			em.close();
		}
	}
	
	@Override
	public Optional<Fournisseur> findById(Long id) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return Optional.of(
				em
					.createQuery("select f from Fournisseur f left join fetch f.produits p where f.id = ?1", Fournisseur.class)
					.setParameter(1, id)
					.getSingleResult()
			);
		}
		
		catch (Exception ex) {
			return Optional.empty();
		}
		
		finally {
			em.close();
		}
	}


}
