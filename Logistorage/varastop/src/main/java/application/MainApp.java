package application;

import java.io.IOException;

import database.LoggedUser;
import model.Product;
import model.User;
import view.UserController;
import view.LoginController;
import view.ProductController;
import view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Class MainApp.
 */
public class MainApp extends Application {

    //Login
    private LoggedUser loggedUser;

    public void setLoggedUser(User user) {
        this.loggedUser = new LoggedUser(user);
    }
    public LoggedUser getLoggedUser(){
        return loggedUser;
    }

    /** The login stage. */
    private Stage primaryStage;

    /** The view question stage. */
    private Stage dialogStage;

    /** The mainapp stage. */
    private Stage mainStage;

    /** The root layout. */
    private BorderPane rootLayout;

    /** yhteys. */
    //MainAppissa naytetty data


    /** The user data. */
    private ObservableList<User> userData = FXCollections.observableArrayList();

    /** The product data. */
    private ObservableList<Product> productData = FXCollections.observableArrayList();
    /**
     * User data list update.
     *
     * @return the observable list
     */
    public ObservableList<User> userDataListUpdate() {
    	return userData;
    }
    
    public ObservableList<Product> productDataListUpdate() {
    	return productData;
    }

    /**
     * Instantiates a new main app.
     */
    public MainApp() {

    }

	/**
	 * Gets the user data.
	 *
	 * @return the user data
	 */
	public ObservableList<User> getUserData() {
		return userData;
	}
	
	/**
	 * Gets the product data.
	 *
	 * @return the product data
	 */
	public ObservableList<Product> getProductData() {
		return productData;
	}
    //tasta alas pain olevat avaa ikkunat

    /**
     * Start.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Logistorage");

        this.primaryStage.getIcons().add(new Image("file:src/main/java/view/Logistorage_white.png"));

        loginScreen();
    }



    /**
     * Inits the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/menubar.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Stage mainStage = new Stage();
            //mainStage.initOwner(primaryStage);
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            mainStage.setTitle("Logistorage");

            mainStage.getIcons().add(new Image("file:src/main/java/view/Logistorage_white.png"));

            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    /**
     * Show user overview.
     */
    public void showUserOverview() {
        try {
            // Load user overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/MainGui.fxml"));
            AnchorPane userOverview = (AnchorPane) loader.load();

            // Set user overview into the center of root layout.
            rootLayout.setCenter(userOverview);

            // Give the controller access to the main app.
            UserController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Show product overview.
     */
    public void showProductOverview() {
        try {
            // Load user overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/MainGuiProduct.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            // Set user overview into the center of root layout.
            rootLayout.setCenter(productOverview);

            // Give the controller access to the main app.
            ProductController controller = loader.getController();
            controller.setMainApp(this);


            primaryStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show user overview.
     */
    public void initMain(){
    	initRootLayout();
    	showUserOverview();
    }

    /**
     * Show product overview.
     */
    public void initMainProduct(){
    	initRootLayout();
    	showProductOverview();
    }
    
    /**
     * inits primaryStage
     * (first screen)
     * 
     * */
    public void loginScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(login);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setResizable(false);

            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /***
     * Shows view question screen
     * 
     * */
    public void viewQScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/ViewQuestion.fxml"));
            AnchorPane viewQ = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Stage dialogStage = new Stage();
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(viewQ);
            dialogStage.setScene(scene);


            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);

            dialogStage.setTitle("Logistorage");
            dialogStage.getIcons().add(new Image("file:src/main/java/view/Logistorage_white.png"));
            dialogStage.setResizable(false);

            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    /**
     * Logs out, not working because stage.close() for some unknown reason doesn't work
     * */
    public void logout(){
        loginScreen();
    }


    /**
     * Gets the primary stage.
     *
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
