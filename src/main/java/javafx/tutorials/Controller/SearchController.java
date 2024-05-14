package javafx.tutorials.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.csv.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import org.controlsfx.control.textfield.TextFields;


public class SearchController implements Initializable{
    @FXML
    private TextField titleInput;
    @FXML
    private TextField authorInput;
    @FXML
    private TextField tagInput;
    @FXML
    private TextField yearInput;
    @FXML
    private TextField keywordInput;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button searchButton;

    @FXML
    private HBox HBox1;

    private List<String> possibleTitle = new ArrayList<>();
    private List<String> possibleTag = new ArrayList<>();
    private List<String> possibleAuthor = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                String columnOne = record.get(1);
                possibleTitle.add(columnOne);
                if (!possibleAuthor.contains(record.get(2))){
                    possibleAuthor.add(record.get(2));
                }

                if (!possibleTag.contains(record.get(4))){
                    possibleTag.add(record.get(4));
                }

            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }

        TextFields.bindAutoCompletion(titleInput, possibleTitle);
        TextFields.bindAutoCompletion(tagInput, possibleTag);
        TextFields.bindAutoCompletion(authorInput, possibleAuthor);

    }

    public void searchButtonClicked(){

        String title = titleInput.getText();
        String author = authorInput.getText();
        String tag = tagInput.getText();
        String year = yearInput.getText();
        String keyword = keywordInput.getText();


        //System.out.println("search button clicked");
        List<String> ListTitles = new ArrayList<>();



        if (!title.isEmpty()) {
            List<String> titles1 = getTitleByTitle(title);
            for (int i = 0; i < titles1.size(); i++) {
                ListTitles.add(titles1.get(i));
            }
        }

        if (!author.isEmpty()) {
            if (ListTitles.isEmpty()) {
                ListTitles = getTitleByAuthor(author);
            }else{
                List<String> titles2 = getTitleByAuthor(author);
                List<String> intersection1 = new ArrayList<>(ListTitles);
                intersection1.retainAll(titles2);
                ListTitles = intersection1;
            }
        }
        
        if (!tag.isEmpty()) {
            if (ListTitles.isEmpty()) {
                ListTitles = getTitleByTag(tag);
            }else{
                List<String> titles3 = getTitleByTag(tag);
                List<String> intersection2 = new ArrayList<>(ListTitles);
                intersection2.retainAll(titles3);
                ListTitles = intersection2;
            }
        }

        if (!year.isEmpty()) {
            if (ListTitles.isEmpty()) {
                ListTitles = getTitleByYear(year);
            }else{
                List<String> titles4 = getTitleByYear(year);
                List<String> intersection3 = new ArrayList<>(ListTitles);
                intersection3.retainAll(titles4);
                ListTitles = intersection3;
            }
        }

        if (!keyword.isEmpty()){
            if (ListTitles.isEmpty()) {
                ListTitles = getTitleByKeyword(keyword);
            }else{
                List<String> titles5 = getTitleByKeyword(keyword);
                List<String> intersection4 = new ArrayList<>(ListTitles);
                intersection4.retainAll(titles5);
                ListTitles = intersection4;
            }
        }


        if (!ListTitles.isEmpty()){
            load(ListTitles);
        }else{
            gridPane.getChildren().clear();
            gridPane.add(new Text("No article found"), 1, 0);
        }
    }

    public void load(List<String> ListTitles) {
        gridPane.getChildren().clear();

        for (int i = 0; i < ListTitles.size(); i++) {
            String title = ListTitles.get(i);
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

            Text num = new Text(String.valueOf(i+1));
            num.setStyle("-fx-font-size: 16px;");
            gridPane.add(text, 1, i);
            gridPane.add(num, 0, i); 
        }
        
    }

    private void changeToView(String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/view.fxml"));
            Parent newContent = loader.load();
            ViewController controller = loader.getController();
            controller.setText(title);
            ObservableList<Node> children = newContent.getChildrenUnmodifiable();
            HBox1.getChildren().setAll(children);
            System.out.println(gridPane.getParent()==null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getTitleByAuthor(String author) {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            List<String> titles = new ArrayList<>();
            for (CSVRecord record : records) {
                if (record.get(2).contains(author)) {
                    titles.add(record.get(1)); // return the title
                }
            }
            return titles;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // return null if no title found for the author
    }

    private List<String> getTitleByTag(String tag) {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            List<String> titles = new ArrayList<>();
            for (CSVRecord record : records) {
                if (record.get(4).contains(tag)) {
                    titles.add(record.get(1)); // return the title
                }
            }
            return titles;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // return null if no title found for the author
    }

    private List<String> getTitleByTitle(String title) {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            List<String> titles = new ArrayList<>();
            for (CSVRecord record : records) {
                if (record.get(1).contains(title)) {
                    titles.add(record.get(1)); // return the title
                }
            }
            return titles;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // return null if no title found for the author
    }

    private List<String> getTitleByYear(String year) {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            List<String> titles = new ArrayList<>();
            for (CSVRecord record : records) {
                if (record.get(3).contains(year)) {
                    titles.add(record.get(1)); // return the title
                }
            }
            return titles;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // return null if no title found for the author
    }
    
    private List<String> getTitleByKeyword(String keyword){
        List<List<String>> titleanddata = new GetContent().getDocumentByFieldName(keyword);
        List<String> titles = new ArrayList<>();
        for (List<String> title : titleanddata){
            titles.add(title.get(0));
            System.out.println(title.get(0));
        }
        return titles;
    }




}
