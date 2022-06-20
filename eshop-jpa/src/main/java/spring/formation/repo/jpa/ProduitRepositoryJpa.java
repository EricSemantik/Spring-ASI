package spring.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

public class ProduitRepositoryJpa extends AbstractRepositoryJpa<Produit> implements IProduitRepository {
	protected final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");
	
	public ProduitRepositoryJpa() {
		super(Produit.class);
	}
	
	public List<Produit> findAll() {
		EntityManager em = emf.createEntityManager();
		
		try {
			return em.createQuery("select p from Produit p", Produit.class).getResultList();
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
	public List<Produit> findByPrixBetween(Double a, Double b) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return em	.createQuery("select p from Produit p where p.prixVente between ?1 and ?2", Produit.class)
						.setParameter(1, a)
						.setParameter(2, b)
						.getResultList();
		}
		
		catch (Exception ex) {
			return new ArrayList<>();
		}
		
		finally {
			em.close();
		}
	}
}