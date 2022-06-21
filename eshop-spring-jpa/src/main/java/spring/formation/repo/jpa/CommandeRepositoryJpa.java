package spring.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.formation.model.Commande;
import spring.formation.repo.ICommandeRepository;

@Component
public class CommandeRepositoryJpa implements ICommandeRepository {
	@Autowired
	private EntityManagerFactory emf;

	public List<Commande> findAll() {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("select e from Commande e", Commande.class).getResultList();
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
	public Optional<Commande> findById(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			return Optional.of(em.createQuery("select e from Commande e where e.id = ?1", Commande.class)
					.setParameter(1, id).getSingleResult());
		}

		catch (Exception ex) {
			return Optional.empty();
		}

		finally {
			em.close();
		}
	}

	public Commande save(Commande entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		try {
			if (em.contains(entity)) {
				em.persist(entity);
			}

			else { // UPDATE
				entity = em.merge(entity);
			}

			em.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}

		finally {
			em.close();
		}

		return entity;
	}

	public void deleteById(Long id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		try {
			em.createQuery("delete from Commande e where e.id = ?1").setParameter(1, id).executeUpdate();
			em.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}

		finally {
			em.close();
		}
	}

}
