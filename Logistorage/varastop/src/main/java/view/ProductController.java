package view;

import databaseControllers.ProductDaoController;
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
import model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ProductController_test.
 */
public class ProductController {
    private ProductDaoController productDB = new ProductDaoController();
    ObservableList<Product> productList;
    /** The product table. */
    @FXML
    private TableView<Product> productTable;

    /** The id column. */
    @FXML
    private TableColumn<Product, Integer> idColumn;

    /** The name column. */
    @FXML
    private TableColumn<Product, String> nameColumn;

    /** The first name column. */
    @FXML
    private TableColumn<Product, String> categoryColumn;

    /** The last name column. */
    @FXML
    private TableColumn<Product, Integer> rowColumn;

    /** The shelf column. */
    @FXML
    private TableColumn<Product, Integer> shelfColumn;

    /** The amount column. */
    @FXML
    private TableColumn<Product, Integer> amountColumn;


    /** The category field. */
    @FXML
    private ComboBox<String> categoryField;

	String[] category = { "Tools", "Electronics", "Computers", "Peripherals", "Other", "NaN" };


    /** The row field. */
    @FXML
    private TextField rowField;

    /** The shelf field. */
    @FXML
    private TextField shelfField;

    /** The id field. */
    @FXML
    private TextField idField;

    /** The name field. */
    @FXML
    private TextField nameField;

    /** The amount field. */
    @FXML
    private TextField amountField;


    /** The search. */
    @FXML
    private TextField search;

    /** The product. */
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
    public ProductController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the product table with the two columns.
        categoryColumn.setCellValueFactory(
                cellData -> cellData.getValue().categoryProperty());
        rowColumn.setCellValueFactory(
                cellData -> cellData.getValue().rowProperty().asObject());
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        amountColumn.setCellValueFactory(
                cellData -> cellData.getValue().amountProperty().asObject());
        shelfColumn.setCellValueFactory(
                cellData -> cellData.getValue().shelfProperty().asObject());
        idColumn.setCellValueFactory(
                cellData -> cellData.getValue().idProperty().asObject());

        // Clear product details.
        showProductDetails(null);

        // Listen for selection changes and show the product details when changed.
        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));

        categoryField.setItems(FXCollections.observableArrayList(category));
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp the new main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        search.textProperty().addListener((observable,oldValue,newValue) ->{handleSearchProduct(); });

        productList = FXCollections.observableArrayList((productDB.getAllProducts()));
        // Add observable list data to the table
        productTable.setItems(productList);

        idField.setDisable(true);

        disabled(true);

       // If logged in as a guest
        if (mainApp.getLoggedUser()==null){
        	disabled(true);

        	neww.setVisible(false);
        	edit.setVisible(false);
        	delete.setVisible(false);
            user.setVisible(false);
            login.setVisible(false);
            }
        else{
        	user.setText(mainApp.getLoggedUser().getUserName());
        	login.setText("Log out");
        	}
    }
    /**
     * Fills all text fields to show details about the product.
     * If the specified product is null, all text fields are cleared.
     *
     * @param product the product or null
     */
    private void showProductDetails(Product product) {
        if (product != null) {
            // Fill the labels with info from the product object.
            categoryField.setPromptText(product.getCategory());
            rowField.setText(Integer.toString(product.getRow()));
            shelfField.setText(Integer.toString(product.getShelf()));
            idField.setText(Integer.toString(product.getID()));
            nameField.setText(product.getName());
            amountField.setText(Integer.toString(product.getAmount()));

        } else {
            // Product is null, remove all the text.

            categoryField.getItems();
            rowField.setText("");
            shelfField.setText("");
            idField.setText("");
            idField.setDisable(true);
            nameField.setText("");
            amountField.setText("");

        }
    }

    /**
     * Disabled.
     *
     * @param b the b
     */
    //Makes the person details field editable/non-editable
    private void disabled(boolean b){
        categoryField.setDisable(b);
        rowField.setDisable(b);
        shelfField.setDisable(b);
        nameField.setDisable(b);
        amountField.setDisable(b);
    }

    /**
     * Called when the product clicks on the delete button.
     */
    @FXML
    private void handleDeleteProduct() {
    	int uId = Integer.parseInt(idField.getText());;

    	try {
            productDB.deleteProduct(productDB.getProduct(uId));
            productList = FXCollections.observableArrayList((productDB.getAllProducts()));
            productTable.setItems(productList);
        System.out.println("Product Deleted");
    	}catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
    	}
    }

    /**
     * Called when the user clicks the save button.
     * Saves product details
     */
    @FXML
    private void handleSaveProduct() {

    	String id = idField.getText();
        String name = nameField.getText();
        String category = categoryField.getPromptText();
        String row= rowField.getText();
        String shelf = amountField.getText();
        String amount = shelfField.getText();

        if(id.isEmpty()){
            productDB.addProduct(new Product(name, category,Integer.parseInt(row), Integer.parseInt(shelf),Integer.parseInt(amount)));
        }
        else{
            productDB.editProduct(productDB.getProduct(Integer.parseInt(id)),new String[]{name,category,row,shelf,amount});
        }

        //Update GUI with Changes
        productList = FXCollections.observableArrayList((productDB.getAllProducts()));
        productTable.setItems(productList);

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
     * Handle new product.
     */
    @FXML
    private void handleNewProduct(){

    	disabled(false);
    	save.setVisible(true);
    	cancel.setVisible(true);

    	edit.setDisable(true);
    	delete.setDisable(true);
    	neww.setDisable(true);

        rowField.setText("");
        shelfField.setText("");
        idField.setText("");
        idField.setDisable(true);
        nameField.setText("");
        amountField.setText("");
        categoryField.setPromptText("");
    }

    /**
     * Called when the product clicks the edit button. Opens a dialog to edit
     * details for the selected product.
     */
    @FXML
    private void handleEditProduct() {


        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

            showProductDetails(selectedProduct);
        	disabled(false);
        	save.setVisible(true);
        	cancel.setVisible(true);

        	neww.setDisable(true);
        	delete.setDisable(true);
    }

    /**
     * Handles search
     *
     * */
    @FXML
    private void handleSearchProduct() {
        ArrayList <Product> products = new ArrayList <Product>(productDB.getAllProducts());
        List<Product> searchedProducts=new ArrayList<>();
        for(Product p : products){
            if (p.getName().toLowerCase().contains(search.getText().toLowerCase())){
                System.out.println(p.getName());
                searchedProducts.add(p);
            }
        }
        productList=FXCollections.observableArrayList(searchedProducts);
        productTable.setItems(productList);
    }

    @FXML
    private void handleLogout(){
    	mainApp.logout();
    	mainApp.setLoggedUser(null);
    	}

    /** Highlights the search field when clicked*/
    @FXML
    private void handleClear(){
    	search.selectAll();
    }
}