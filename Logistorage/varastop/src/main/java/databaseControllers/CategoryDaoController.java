package databaseControllers;

import database.DBInterface;
import model.Category;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class CategoryDaoController {


    private static DBInterface categoryDao;// = new CategoryDao();

    /**
     * Get Category by id
     * @param id from category
     * */
    public Category getCategory(int id) {
        Optional<Category> ctgry = categoryDao.getById(id);
        return ctgry.orElseGet(
                () -> new Category(0, "non-existing Category"));
    }

    /**
     * Get all Categories as a list
     * */
    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }

    /**
     * Edit Category
     * @param category object
     * @param params category parameters
     *
     * */
    public  void updateCategory(Category category, String[] params) {
        categoryDao.edit(category, params);
    }

    /**
     * Add Category
     * @param category object
     * */
    public void addCategory(Category category) {
        categoryDao.add(category);
    }

    /**
     * Delete Category
     * @param category object
     * */
    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

}
