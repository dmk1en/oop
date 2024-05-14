package javafx.tutorials.Controller;

import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

public class Controller {

    private BrowseController browseController;

    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
	private Button submitButton;

    @FXML
    private VBox navigateBox;

    @FXML
    private VBox mainBox;


    @FXML
	public void onButtonClicked(ActionEvent event) {
		System.out.println("clicked");
	}

    @FXML
    public void changeTo(String fxml) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent newContent = loader.load();    
            ObservableList<Node> children = newContent.getChildrenUnmodifiable();
            mainBox.getChildren().setAll(children);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
 
    @FXML
    public void changeToBrowse() throws IOException {
        changeTo("/resources/fxml/browse.fxml");
    }

    @FXML
    public void changeToSearch() throws IOException {
        changeTo("/resources/fxml/search.fxml");
    }

}
