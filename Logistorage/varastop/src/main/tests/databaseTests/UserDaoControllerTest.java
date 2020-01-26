package databaseTests;


import databaseControllers.UserDaoController;
import model.User;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** 
* UserDaoController Tester. 
* 
* @author <Joonas Lehikoinen>
* @since <pre>12? 13, 2019</pre> 
* @version 1.0 
*/ 
public class UserDaoControllerTest { 

    private UserDaoController testUserDB;
    User u;
    ArrayList<User> users;
    int id;
    int id2=0;
@Before
public void before() throws Exception {
    testUserDB=new UserDaoController();
    u=new User("Junit", "Uniter","UnitBoy", "TEST", "junit@gmail.com");
    users = new ArrayList();
    testUserDB.addUser(u);
    users= (ArrayList<User>) testUserDB.getAllUsers();
    for (User u : users){
        if(u.getType().equals("TEST")){
            this.id=u.getID();
        }
}
}

@After
public void after() throws Exception {
  testUserDB.deleteUser(testUserDB.getUser(id));
  try{
       testUserDB.deleteUser(testUserDB.getUser(id2));
  }catch (Exception e){
      System.out.println("after failed in usertest");
  }
 // testUserDB.deleteUser(testUserDB.getUser(id2));
} 

/** 
* 
* Method: getUser(int id) 
* 
*/ 
@Test
public void testGetUser() throws Exception {

    u.setUserValues();
    User test;
    test=testUserDB.getUser(id);
    test.setUserValues();
    assertEquals(u.toString(),test.toString());


//TODO: Test goes here... 
} 

/** 
* 
* Method: getAllUsers() 
* 
*/ 
@Test
public void testGetAllUsers() throws Exception {
    ArrayList<Integer>userIdList=new ArrayList<>();
    ArrayList<Integer>testUserIdList=new ArrayList<>();
    for(User u: testUserDB.getAllUsers()){
        testUserIdList.add(u.getID());
    }

    for(User u: users){
        userIdList.add(u.getID());
    }

    assertEquals(userIdList,testUserIdList);
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateUser(User user, String[] params) 
* 
*/ 
@Test
public void testUpdateUser() throws Exception {
    String name="newJunit";
    testUserDB.editUser(testUserDB.getUser(id),new String[]{name,u.getLastName(),u.getUsername(),u.getEmail(),u.getType()});
    assertEquals(name,testUserDB.getUser(id).getFirstName());
//TODO: Test goes here... 
} 

/** 
* 
* Method: addUser(User user) 
* 
*/ 
@Test
public void testAddUser() throws Exception {

    User u2=new User("Junit2", "Uniter2","UnitBoy2", "2", "2junit@gmail.com");
    testUserDB.addUser(u2);
    for(User u: testUserDB.getAllUsers()){
        if(u.getType().equals("2")){
            id2=u.getID();
        }
    }
    u2.setUserValues();
    assertEquals(u2.toString(),testUserDB.getUser(id2).toString());
    users= (ArrayList<User>) testUserDB.getAllUsers();

//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteUser(User user) 
* 
*/ 
@Test(expected=NullPointerException.class)
public void testDeleteUser() throws Exception {
    testUserDB.deleteUser(testUserDB.getUser(id2));


    assertEquals(NullPointerException.class ,u.getClass());

//TODO: Test goes here... 
} 


} 
