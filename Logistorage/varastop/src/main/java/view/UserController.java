package view;

import databaseControllers.UserDaoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import application.MainApp;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserController.
 */
public class UserController {

    UserDaoController userDB = new UserDaoController();
    //List parameter for setMainApp
    ObservableList<User> usersList = FXCollections.observableArrayList((userDB.getAllUsers()));

    /** The user table. */
    @FXML
    private TableView<User> userTable;

    /** The id column. */
    @FXML
    private TableColumn<User, Integer> idColumn;

    /** The username column. */
    @FXML
    private TableColumn<User, String> usernameColumn;

    /** The first name column. */
    @FXML
    private TableColumn<User, String> firstNameColumn;

    /** The last name column. */
    @FXML
    private TableColumn<User, String> lastNameColumn;

    /** The email column. */
    @FXML
    private TableColumn<User, String> emailColumn;

    /** The type column. */
    @FXML
    private TableColumn<User, String> typeColumn;


    /** The first name field. */
    @FXML
    private TextField firstNameField;

    /** The last name field. */
    @FXML
    private TextField lastNameField;

    /** The email field. */
    @FXML
    private TextField emailField;

    /** The id field. */
    @FXML
    private TextField idField;

    /** The username field. */
    @FXML
    private TextField usernameField;

    /** The type field. */
    @FXML
    private ComboBox<String> typeField;

	String[] Usertype = { "ADMIN", "User" };

    /** The search. */
    @FXML
    private TextField search;

    /** The user. */
    @FXML
    private Label user;

    /** The login. */
    @FXML
    private Button login;

    /** The save. */
    @FXML
    private Button save;

    /** The neww. */
    @FXML
    private Button neww;

    /** The edit. */
    @FXML
    private Button edit;

    /** The delete. */
    @FXML
    private Button delete;

    /** The cancel. */
    @FXML
    private Button cancel;

    /** The main app. */
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the user table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        usernameColumn.setCellValueFactory(
                cellData -> cellData.getValue().usernameProperty());
        typeColumn.setCellValueFactory(
                cellData -> cellData.getValue().typeProperty());
        emailColumn.setCellValueFactory(
                cellData -> cellData.getValue().emailProperty());
        idColumn.setCellValueFactory(
                cellData -> cellData.getValue().idProperty().asObject());


        //historyColumn.setGraphic(new Button("History"));
        // Clear user details.
        showUserDetails(null);

        // Listen for selection changes and show the user details when changed.
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));

        typeField.setItems(FXCollections.observableArrayList(Usertype));

        disabled(true);
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp the new main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        search.textProperty().addListener((observable,oldValue,newValue) ->{handleSearchUsers(); });
        // Add observable list data to the table
        userTable.setItems(usersList);

        //Sets visible and editable screen items according to user type
        user.setVisible(false);
        login.setVisible(false);
        idField.setDisable(true);
        neww.setDisable(true);

        //Käyttöoikeus lisätä käyttäjiä vain ADMIN
        if (mainApp.getLoggedUser().getType().equals("ADMIN".toUpperCase())){
            neww.setDisable(false);
        }
        if(mainApp.getLoggedUser().getType()!=null){
            user.setVisible(true);
            login.setVisible(true);
        	user.setText(mainApp.getLoggedUser().getUserName());
        	login.setText("Log out");
        }
    }
    /**
     * Fills all text fields to show details about the user.
     * If the specified user is null, all text fields are cleared.
     *
     * @param user the user or null
     */
    private void showUserDetails(User user) {
        if (user != null) {
            // Fill the labels with info from the user object.
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            emailField.setText(user.getEmail());
            idField.setText(Integer.toString(user.getID()));
            usernameField.setText(user.getUsername());
            typeField.setPromptText(user.getType());

        } else {
            // User is null, remove all the text.
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            idField.setText("");
            idField.setDisable(true);
            usernameField.setText("");
            typeField.getItems();
        }
    }


    /**
     * Disabled.
     *
     * @param b the b
     */
    //Makes the person details field editable/non-editable
    private void disabled(boolean b){
        firstNameField.setDisable(b);
        lastNameField.setDisable(b);
        emailField.setDisable(b);
        //idField.setDisable(b);
        usernameField.setDisable(b);
        typeField.setDisable(b);
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteUser() {
    	int uId = Integer.parseInt(idField.getText());;

    	try {
    	    userDB.deleteUser(userDB.getUser(uId));
            usersList=FXCollections.observableArrayList((userDB.getAllUsers()));
            userTable.setItems(usersList);
            System.out.println("User Deleted");
    	}catch (Exception e){

            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a user in the table.");

            alert.showAndWait();

    	}
    }

    /**
     * Called when the user clicks the save button.
     * Saves user details
     */
    @FXML
    private void handleSaveUser() {

    	String id = idField.getText();
        String fname = firstNameField.getText();
        String lname= lastNameField.getText();
        String email = emailField.getText();
        String username=usernameField.getText();
        String utype = typeField.getPromptText();
        System.out.println(id);

        if(id.isEmpty()){
        	userDB.addUser (new User(fname,lname,username,utype,email));
        }
        else{
            userDB.editUser(userDB.getUser(Integer.parseInt(id)),new String[]{fname, lname, username, email, utype});
        }

        usersList=FXCollections.observableArrayList((userDB.getAllUsers()));
        userTable.setItems(usersList);

        handleCancel();

    }

    /**
     * Handle cancel.
     */
    @FXML
    private void handleCancel(){
    	disabled(true);
    	save.setVisible(false);
    	cancel.setVisible(false);

    	delete.setDisable(false);
    	neww.setDisable(false);
    	edit.setDisable(false);
    }

    /**
     * Called when the user clicks the new button.
     * Makes user detail text fields editable.
     */
    @FXML
    private void handleNewUser(){

    	disabled(false);
    	save.setVisible(true);
    	cancel.setVisible(true);

    	edit.setDisable(true);
    	delete.setDisable(true);


        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        idField.setText("");
        idField.setDisable(true);
        usernameField.setText("");
        typeField.setPromptText("");
    }
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected user.
     */

    @FXML
    private void handleEditUser() {


        User selectedUser = userTable.getSelectionModel().getSelectedItem();

            showUserDetails(selectedUser);
        	disabled(false);
        	save.setVisible(true);
        	cancel.setVisible(true);

        	neww.setDisable(true);
        	delete.setDisable(true);
    }
    /**
     * Handles user search
     *
     * */
    @FXML
    private void handleSearchUsers() {

        ArrayList <User> users = new ArrayList <User>(userDB.getAllUsers());
        List<User> searchedUsers=new ArrayList<>();
        for(User u : users){
            if (u.getUsername().toLowerCase().contains(search.getText().toLowerCase())){
                searchedUsers.add(u);
            }else if(u.getFirstName().toLowerCase().contains(search.getText().toLowerCase())){
                searchedUsers.add(u);
            }else if(u.getLastName().toLowerCase().contains(search.getText().toLowerCase())) {
                searchedUsers.add(u);
            }else if(u.getType().toLowerCase().contains(search.getText().toLowerCase())) {
                searchedUsers.add(u);
            }
        }
        usersList=FXCollections.observableArrayList(searchedUsers);
        userTable.setItems(usersList);
    }

    /** Highlights the search field when clicked*/
    @FXML
    private void handleClear(){
    	search.selectAll();
    }
}