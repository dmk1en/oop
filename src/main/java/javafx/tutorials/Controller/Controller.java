package javafx.tutorials.Controller;

import java.io.IOException;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Controller {


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

    @FXML
    public void changeToTrend() throws IOException {
        changeTo("/resources/fxml/trend.fxml");
        //System.out.println("c");
    }
    

}
