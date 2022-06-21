package spring.formation.repo.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

@Repository
@Transactional(readOnly = true)
public class ProduitRepositoryJpa implements IProduitRepository {
	@PersistenceContext
	private EntityManager em;

	public ProduitRepositoryJpa() {
	}

	@Override
	public List<Produit> findAll() {
		return em.createQuery("select p from Produit p", Produit.class).getResultList();
	}

	@Override
	public Optional<Produit> findById(Long id) {
		Produit entity = em.find(Produit.class, id);

		return entity != null ? Optional.of(entity) : Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Produit save(Produit entity) {
		if (em.contains(entity)) {
			em.persist(entity);
		}

		else { // UPDATE
			entity = em.merge(entity);
		}

		return entity;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		em.createQuery("delete from Produit e where e.id = ?1").setParameter(1, id).executeUpdate();
	}

	@Override
	public List<Produit> findByPrixBetween(Double a, Double b) {
		return em.createQuery("select p from Produit p where p.prixVente between ?1 and ?2", Produit.class)
				.setParameter(1, a).setParameter(2, b).getResultList();
	}
}