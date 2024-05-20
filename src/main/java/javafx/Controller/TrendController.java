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
        ShowTrend ShowTrend = new ShowTrend();
        List<Map.Entry<String, Integer>> entryList = ShowTrend.getWordFrequency();
        top1.setText(entryList.get(0).getKey() + " : " + entryList.get(0).getValue());
        top2.setText(entryList.get(1).getKey() + " : " + entryList.get(1).getValue());
        top3.setText(entryList.get(2).getKey() + " : " + entryList.get(2).getValue());
        top4.setText(entryList.get(3).getKey() + " : " + entryList.get(3).getValue());
        top5.setText(entryList.get(4).getKey() + " : " + entryList.get(4).getValue());


    }
    


    
}
