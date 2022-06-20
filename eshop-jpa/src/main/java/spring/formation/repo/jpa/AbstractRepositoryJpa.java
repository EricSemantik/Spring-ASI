package spring.formation.repo.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractRepositoryJpa<T> {
	protected final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");
	private Class<T> clz;
	private String entityName;
	
	public AbstractRepositoryJpa(Class<T> clz) {
		this.clz = clz;
		this.entityName = clz.getSimpleName();
	}

	public Optional<T> findById(Long id) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return Optional.of(
				em	.createQuery("select e from " + entityName + " e where e.id = ?1", clz)
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

	public T save(T entity) {
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
			em.createQuery("delete from " + entityName + " e where e.id = ?1").setParameter(1, id).executeUpdate();
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
