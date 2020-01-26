package databaseControllers;

import java.util.List;
import java.util.Optional;

import database.DBInterface;
import database.UserDao;
import model.User;

public class UserDaoController {
	/**
	 * Interface connection
	 * */
	private static DBInterface userDao = new UserDao();

	/**
	 * Get user by id
     *
     * @param id from user
     *
	 * */
    public User getUser(int id) {
        Optional<User> user = userDao.getById(id);
        System.out.println(user);
        return user.orElseGet(
          () -> new User("non-existing user", "non-existing user","non-existing user", "non-existing user", "non-existing user"));
    }
    /**
     * Get all users as a list
     * */
    public List<User> getAllUsers() {
        return userDao.getAll();
    }
    /**
     * Update user info
     * @param user object
     * @params user parameters
     * */
    public  void editUser(User user, String[] params) {
        userDao.edit(user, params);
    }
    /**
     * Add user
     * @param user object
     * */
    public void addUser(User user) {
        userDao.add(user);
    }
    /**
     * Delete user
     * @param user object
     * */
    public void deleteUser(User user) {
        userDao.delete(user);
    }

}
