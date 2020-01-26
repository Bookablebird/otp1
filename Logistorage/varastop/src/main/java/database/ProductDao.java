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

import model.Product;
/**
 *JPA Product-specific implementation of the Dao interface
 * */
public class ProductDao implements DBInterface <Product> {
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("varastp");
	private EntityManager em=ENTITY_MANAGER_FACTORY.createEntityManager();

	/**
	 *Get Product by id from the database
	 * */
	@Override
	public Optional<Product> getById(int id) {

		Product product= em.find(Product.class, id);
		product.setProductValues();
		return Optional.ofNullable(product);
	}
	/**
	 *Get Products from the database
	 *
	 * @return Product Object List
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {

		String strQuery = "SELECT c FROM Product c WHERE c.id IS NOT NULL";
		Query query = em.createQuery(strQuery);
		List<Product> prdctList = query.getResultList();
		prdctList.forEach(prdct->prdct.setProductValues());

		return prdctList;
	}
	/**
	 *Add Product to the database
	 * */
	@Override
	public void add(Product t) {
		executeInsideTransaction(em -> em.persist(t));
	}
	/**
	 *Update Products information in the database
	 * */
	@Override
	public void edit(Product t, String[] params) {

        t.setName(Objects.requireNonNull(params[0],"Product name cannot be null"));
        t.setCategory(Objects.requireNonNull(params[1],"category cannot be null"));
        t.setRow(Objects.requireNonNull(Integer.parseInt(params[2]), "Row cannot be null"));
        t.setShelf(Objects.requireNonNull(Integer.parseInt(params[3]), "Shelf cannot be null"));
        t.setAmount(Objects.requireNonNull(Integer.parseInt(params[4]), "Amount cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(t));

	}
	/**
	 **Delete Product from the database
	 * */
	@Override
	public void delete(Product t) {
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

