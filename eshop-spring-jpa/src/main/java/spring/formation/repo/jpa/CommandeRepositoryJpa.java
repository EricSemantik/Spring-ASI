package spring.formation.repo.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.formation.model.Commande;
import spring.formation.repo.ICommandeRepository;

@Repository
@Transactional(readOnly = true)
public class CommandeRepositoryJpa implements ICommandeRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Commande> findAll() {
		return em.createQuery("select e from Commande e", Commande.class).getResultList();
	}

	@Override
	public Optional<Commande> findById(Long id) {
		return Optional.of(em.createQuery("select e from Commande e where e.id = ?1", Commande.class)
				.setParameter(1, id).getSingleResult());
	}

	@Override
	@Transactional(readOnly = false)
	public Commande save(Commande entity) {
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
		em.createQuery("delete from Commande e where e.id = ?1").setParameter(1, id).executeUpdate();
	}

}
