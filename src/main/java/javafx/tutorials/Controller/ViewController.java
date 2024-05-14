package javafx.tutorials.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.checkerframework.checker.units.qual.t;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class ViewController implements Initializable {
    private String title;


    @FXML
    private Text titleText;

    @FXML
    private Text contentText;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        //String data = new GetContent().getContentByTitle(titleText.getText());
        //System.out.println(data);

        //contentText.setText(data);
    }

    public void setText(String text) { 
        System.out.println(text);
        titleText.setText(text);
        String data = new GetContent().getContentByTitle(text);
        contentText.setText(data);
    }

}
