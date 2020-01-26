package view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import application.MainApp;


/**
 * The Class RootLayoutController.
 */
public class RootLayoutController {
	
	/** Product view menuitem*/
	@FXML
	private MenuItem pview;
	
	/** User view menuitem*/
	@FXML
	private MenuItem uview;

    /** The main app. */
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp the new main app
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Opens Products from menu item
     * 
     * */
    @FXML
    private void handleOpenProduct() {
        	mainApp.showProductOverview();
    }
    
    /**
     * Opens Users from menu item
     * 
     * */
    @FXML
    private void handleOpenUser() {
        	mainApp.showUserOverview();
    	}

    /**
     * Opens About pop-up from menu item
     * 
     * */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Logiikka ohjelmisto");
        alert.setHeaderText("About");
        alert.setContentText("");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
    	//Lisaa tahan ENTITY MANAGERIN sulkeminen!!!!
        System.exit(0);
    }
    /**
     * Switches the language to Finnish.
     */
    @FXML
    private void suomi() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Suomi");
        alert.setHeaderText("Virhe");
        alert.setContentText("T�m� ominaisuus ei ole viel� k�yt�ss�");

        alert.showAndWait();    }
    /**
     * Switches the language to English.
     */
    @FXML
    private void english() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("English");
        alert.setHeaderText("Error");
        alert.setContentText("This feature is not yet enabled");

        alert.showAndWait();    }
}