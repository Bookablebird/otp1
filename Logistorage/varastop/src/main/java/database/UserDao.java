package database;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.User;
/**
 *JPA User-specific implementation of the DAO interface
 * */
public class UserDao implements DBInterface <User> {
	private  EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("varastp");
	private  EntityManager em=ENTITY_MANAGER_FACTORY.createEntityManager();

	/**
	 *Get user by id from the database
	 *
	 * @return User object
	 * */
	@Override
	public Optional<User> getById(int id) {

		User user= em.find(User.class, id);
		user.setUserValues();
		return Optional.ofNullable(user);
	}

	/**
	 *Get all Users from the database
	 *
	 * @return User Object List from the database
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {

		String strQuery = "SELECT c FROM User c WHERE c.id IS NOT NULL";
		Query query = em.createQuery(strQuery,User.class);
		List<User> userList = query.getResultList();
		userList.forEach(user->user.setUserValues());

		return userList;//query.getResultList()
	}

	/**
	 *Add User object to the database
	 * */
	@Override
	public void add(User t) {
		executeInsideTransaction(em -> em.persist(t));
	}

	/**
	 *Update User information in the database
	 * */
	@Override
	public void edit(User t, String[] params) {
        t.setFirstName(Objects.requireNonNull(params[0], "Firstname cannot be null"));
        t.setLastName(Objects.requireNonNull(params[1], "Lastname cannot be null"));
        t.setUsername(Objects.requireNonNull(params[2], "Username cannot be null"));
        t.setEmail(Objects.requireNonNull(params[3], "Email cannot be null"));
        t.setType(Objects.requireNonNull(params[4], "Type cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	/**
	 *Delete User from the database
	 * */
	@Override
	public void delete(User t) {
		 executeInsideTransaction(entityManager -> entityManager.remove(t));
	}

	/**
	 *Helper method to execute inside transaction
	 * */
    private void executeInsideTransaction(Consumer<EntityManager> action) {

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }finally {
        	em.clear();
        }
    }


}
