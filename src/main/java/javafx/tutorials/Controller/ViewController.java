package javafx.tutorials.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class ViewController implements Initializable {



    @FXML
    private Text titleText;

    @FXML
    private Text contentText;

    @FXML
    private Text authorText;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        //String data = new GetContent().getContentByTitle(titleText.getText());
        //System.out.println(data);

        //contentText.setText(data);
    }

    public void setText(String title,String author) { 
        
        titleText.setText(title);
        authorText.setText(author);
        String data = new GetContent().getContentByTitle(title);
        contentText.setText(data);
    }

}
