package javafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ViewController {

    @FXML
    private Text titleText;

    @FXML
    private Text contentText;

    @FXML
    private Text authorText;

    public void setText(String title,String author) { 
        
        titleText.setText(title);
        authorText.setText(author);
        String data = new GetContent().getContentByTitle(title);
        contentText.setText(data);
    }

}
