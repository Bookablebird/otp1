package databaseControllers;

import java.util.List;
import java.util.Optional;

import database.DBInterface;
import database.ProductDao;
import model.Product;

public class ProductDaoController {
	/**
	 * Interface connection
	 * */
	private DBInterface productDao = new ProductDao();
	/**
	 * Get product by id
     * @param id of product
	 * */
    public Product getProduct(int id) {
        Optional<Product> product = productDao.getById(id);

        return product.orElseGet(
          () -> new Product("No product","No category",0,0,0));
    }
    /**
     * Get all products as a list
     * */
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }
    /**
     * Edit product
     * @param product object
     * @param params product parameters
     * */
    public void editProduct(Product product, String[] params) {productDao.edit(product,params);
    }
    /**
     * Add products
     * @param product object
     * */
    public void addProduct(Product product) {
        productDao.add(product);
    }

    /**
     * Delete products
     * @param product object
     * */
    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

}
