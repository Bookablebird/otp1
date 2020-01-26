package view;

import application.MainApp;
import databaseControllers.UserDaoController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.User;

import java.util.ArrayList;

public class LoginController {

    /** MainApp. */
	private MainApp mainApp;

    /** UserDaoController. */
    UserDaoController userDB = new UserDaoController();

    /** username text field. */
    @FXML
    private TextField usernameText;

    /** password text field. */
    @FXML
    private PasswordField passwordText;

    /** QView hello user text. */
    @FXML
    private Label loggeduserlabel;

    /** test label. */
    @FXML
    private Label test;

    /** Logistorage image. */
    @FXML
    private ImageView imageview;

    /** The main app. */
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}

	/**
	 * Handles log in
	 * */
	@FXML
    private void handleLogin() {
        ArrayList<User> users = new ArrayList<User>(userDB.getAllUsers());
        boolean login = false;
        for(User u : users){
            if (u.getUsername().toLowerCase().equals(usernameText.getText().toLowerCase()) && u.getPassword().equals(passwordText.getText())){
                login=true;
                mainApp.setLoggedUser(u);
                if(u.getType().equals("ADMIN".toUpperCase())){
                    mainApp.viewQScreen();
                }
                else{
                	mainApp.initMainProduct();
                }
            }
        }

        if (login==false){

            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText("If you forgot your username or password");
            alert.setContentText("Please contact your system administrator");

            alert.showAndWait();
        }

        }


	/** Handles login as a guest*/
	@FXML
    private void handleLoginGuest() {
	    mainApp.initMainProduct();
	}

	/** Handles product view*/
	@FXML
    private void handleProducts() {
	    mainApp.initMainProduct();
	}

	/** Handles user view*/
	@FXML
    private void handleUsers() {
	    mainApp.initMain();
	}

	/** Handles forgot username*/
	@FXML
    private void handleForgotUser() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Forgot Username");
        alert.setHeaderText("If you forgot your username");
        alert.setContentText("Please contact your system administrator");

        alert.showAndWait();
        }

	/** Handles forgot password*/
	@FXML
    private void handleForgotPass() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Forgot Password");
        alert.setHeaderText("If you forgot your password");
        alert.setContentText("Please contact your system administrator");

        alert.showAndWait();
        }
}
