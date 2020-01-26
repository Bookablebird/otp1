package database;

import databaseControllers.UserDaoController;
import model.User;

public class testifilu {
	private static DBInterface<User> userDao;
	static UserDao categoryDao = new UserDao();

    public static void main(String[] args) {
    	//EntityManagerFactory factory = Persistence.createEntityManagerFactory("varastp");
    	
    	User plaa = new User("final112323", "countdown12233", "boyqwe23", "girl123", "hors123e");
    	//UserController.addUser(plaa);



    	UserDaoController userc = new UserDaoController();

    	System.out.println(userc.getAllUsers());
	//	ProductDBCntrl prdct = new ProductDBCntrl();

	//	System.out.println(userc.getUser(22).getUsername()+" Sala: "+ userc.getUser(22).getPassword());
		//System.out.println(prdct.getProduct(3).toString());
		//userc.deleteUser(userc.getUser(2));
		//userc.addUser(plaa);
		//Product prdct1 = prdct.getProduct(10);
	//	prdct.deleteUser(prdct.getProduct(10));
    }

    
    public static void saveUser(User t) {
    	userDao.add(t);
    }
}
