package ie.todolist.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.todolist.models.Users;

@Repository
@Transactional
public class UsersDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Users users) {
		manager.persist(users);
	}

	public Users findById(Integer userId) {
		return manager.createQuery("select u from Users u where u.id=:id", Users.class)
				.setParameter("id", userId)
				.getSingleResult();
	}
	
	public Users login(Users user) {
        Users returnUser = null;
        try {
        	returnUser = manager.createQuery("select u from Users u where u.user=:user and u.password=:password", Users.class)
            		.setParameter("user", user.getUser())
            		.setParameter("password", user.getPassword())
            		.getSingleResult();	
		} catch (NoResultException e) {
			
		} catch (Exception e) {

		}
        
        return returnUser;
	}
}
