package javafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TrendController implements Initializable {
    @FXML
    private Text top1;

    @FXML
    private Text top2;

    @FXML
    private Text top3;

    @FXML
    private Text top4;

    @FXML
    private Text top5;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Maturity maturity = new Maturity();
        List<Map.Entry<String, Integer>> entryList = maturity.getWordFrequency();
        top1.setText(entryList.get(0).getKey());
        top2.setText(entryList.get(1).getKey());
        top3.setText(entryList.get(2).getKey());
        top4.setText(entryList.get(3).getKey());
        top5.setText(entryList.get(4).getKey());


    }
    


    
}
