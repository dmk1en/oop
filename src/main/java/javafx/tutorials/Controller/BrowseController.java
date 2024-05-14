package javafx.tutorials.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

public class BrowseController implements Initializable{
    @FXML
    private Button loadMoreButton;

    @FXML
    private VBox root;

    @FXML
    private HBox HBox1;

    @FXML
    private GridPane gridPane;

    private int currentArticleId = 0;

    private List<String> possibleTitle = new ArrayList<>();
    private List<String> possibleTag = new ArrayList<>();
    private List<String> possibleAuthor = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
            try {
                InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
                for (CSVRecord record : records) {
                    possibleTitle.add(record.get(1));
                    possibleAuthor.add(record.get(2));
                    possibleTag.add(record.get(4));
                }
                in.close();
                loadMore();
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    public void loadMore() {
        for (int i = currentArticleId; i < currentArticleId + 10; i++) {
            String title = possibleTitle.get(i);
            Text text = new Text(title);
            text.setWrappingWidth(600);
            text.setStyle("-fx-font-size: 20px;");
            text.setOnMouseClicked(event -> {
                changeToView(title);
            });

            text.setOnMouseEntered(event -> {
                text.setStyle("-fx-font-size: 24px;");
            });

            text.setOnMouseExited(event -> {
                text.setStyle("-fx-font-size: 20px;");
            });



            Text num = new Text(String.valueOf(i));
            gridPane.add(text, 1, i);
            gridPane.add(num, 0, i); 
        }
        currentArticleId += 10;
        
    }

    public void changeToView(String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/view.fxml"));
            Parent newContent = loader.load();
            ViewController controller = loader.getController();
            controller.setText(title);
            ObservableList<Node> children = newContent.getChildrenUnmodifiable();
            HBox1.getChildren().setAll(children);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
