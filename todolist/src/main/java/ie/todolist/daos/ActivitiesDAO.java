package ie.todolist.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.todolist.models.Activities;

@Repository
@Transactional
public class ActivitiesDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Activities activitie) {
		
		if(activitie.getId() != 0) {
			manager.merge(activitie);
		}else {
			manager.persist(activitie);
		}
		
	}
	
	public void delete(Integer id) {
		manager.createQuery("delete from Activities a where a.id=:id")
	            .setParameter("id", id)
	            .executeUpdate();
	}

	public List<Activities> list() {
		return manager.createQuery("select a from Activities a", Activities.class).getResultList();
	}
	
	public List<Activities> findByUser(Integer userId) {
		return manager.createQuery("select a from Activities a join fetch a.user u where u.id=:id", Activities.class)
				.setParameter("id", userId)
				.getResultList();
	}
	
	public Activities findById(Integer id) {
		return manager.createQuery("select a from Activities a where a.id=:id", Activities.class)
        		.setParameter("id", id)
        		.getSingleResult();
	}
}
