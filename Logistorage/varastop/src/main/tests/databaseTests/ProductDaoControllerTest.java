package databaseTests;

import databaseControllers.ProductDaoController;
import model.Product;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** 
* ProductDaoController Tester. 
* 
* @author <Joonas Lehikoinen>
* @since <pre>12? 13, 2019</pre> 
* @version 1.0 
*/ 
public class ProductDaoControllerTest {

    private ProductDaoController testProductDB;
    Product p;
    ArrayList<Product> products;
    int id;
    int id2=2;

@Before
public void before() throws Exception {
    testProductDB =new ProductDaoController();
    p = new Product("Junit", "TEST",99, 99, 99);
    products = new ArrayList();
    testProductDB.addProduct(p);
    products = (ArrayList<Product>) testProductDB.getAllProducts();
    for (Product p : products){
        if(p.getCategory().equals("TEST")){
            this.id=p.getID();
        }
    }
} 

@After
public void after() throws Exception {
    testProductDB.deleteProduct(testProductDB.getProduct(id));
    try{
        testProductDB.deleteProduct(testProductDB.getProduct(id2));
    }catch (Exception e){
        System.out.println("after failed in product test");
    }
} 

/** 
* 
* Method: getProduct(int id) 
* 
*/ 
@Test
public void testGetProduct() throws Exception {

   // u.setProductValues();;
    Product test;
    test=testProductDB.getProduct(id);
    test.setProductValues();
    assertEquals(p.toString(),test.toString());
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAllProducts() 
* 
*/ 
@Test
public void testGetAllProducts() throws Exception {
    ArrayList<Integer>prdctIdList=new ArrayList<>();
    ArrayList<Integer>testPrdctIdList=new ArrayList<>();
    for(Product p: testProductDB.getAllProducts()){
        testPrdctIdList.add(p.getID());
    }

    for(Product p: products){
        prdctIdList.add(p.getID());
    }

    assertEquals(prdctIdList,testPrdctIdList);
//TODO: Test goes here... 
} 

/** 
* 
* Method: editProduct(Product product, String[] params) 
* 
*/ 
@Test
public void testEditProduct() throws Exception {
    String name="newJunit";
    testProductDB.editProduct(testProductDB.getProduct(id),new String[]{name, p.getCategory(), String.valueOf(p.getRow()), String.valueOf(p.getShelf()), String.valueOf(p.getAmount())});
    assertEquals(name,testProductDB.getProduct(id).getName());
//TODO: Test goes here... 
} 

/** 
* 
* Method: addProduct(Product product) 
* 
*/ 
@Test
public void testAddProduct() throws Exception {

    Product u2=new Product("Junit2", "2",88, 88, 88);
    testProductDB.addProduct(u2);
    for(Product u: testProductDB.getAllProducts()){
        if(u.getCategory().equals("2")){
            id2=u.getID();
        }
    }
    u2.setProductValues();
    assertEquals(u2.toString(),testProductDB.getProduct(id2).toString());
    products = (ArrayList<Product>) testProductDB.getAllProducts();

//TODO: Test goes here...
} 

/** 
* 
* Method: deleteProduct(Product product) 
* 
*/
@Test(expected=NullPointerException.class)
public void testDeleteProduct() throws Exception {
    testProductDB.deleteProduct(testProductDB.getProduct(id2));
    assertEquals(NullPointerException.class , p.getClass());
//TODO: Test goes here... 
} 

} 
