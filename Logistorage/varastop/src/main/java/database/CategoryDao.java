package database;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Category;
/**
 *JPA Category-specific implementation of the Dao interface
 * */
public class CategoryDao implements DBInterface<Category> {
	protected static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("varastp");
	protected static EntityManager em=ENTITY_MANAGER_FACTORY.createEntityManager();

	/**
	 *Get Category by id from database
	 *
	 * @return Category object from database
	 * */
	@Override
	public Optional<Category> getById(int id) {

		Category user= em.find(Category.class, id);
		return Optional.ofNullable(user);
	}
	/**
	 *Get all Categories from database
	 *
	 * @return Category Object List from database
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {

		String strQuery = "SELECT c FROM Category c WHERE c.id IS NOT NULL";
		Query query = em.createQuery(strQuery);
		
		return query.getResultList();
	}

	/**
	 *Add Category object to database
	 * */
	@Override
	public void add(Category t) {				
		executeInsideTransaction(em -> em.persist(t));				
	}

	/**
	 *Update Categorys information in database
	 * */
	@Override
	public void edit(Category t, String[] params) {
		/**
        t.setFirstName(Objects.requireNonNull(params[0], "Firstname cannot be null"));
        t.setLastName(Objects.requireNonNull(params[1], "Lastname cannot be null"));
        t.setUsername(Objects.requireNonNull(params[2], "Username cannot be null"));
        t.setEmail(Objects.requireNonNull(params[3], "Email cannot be null"));
        t.setType(Objects.requireNonNull(params[4], "Type cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(t));	
        **/	
	}

	/**
	 *Delete Category from database
	 * */
	@Override
	public void delete(Category t) {
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
        	em.close();
        }
    }

}
