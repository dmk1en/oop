package javafx.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public abstract class abstractController implements Initializable{
    private String connectionString = "mongodb+srv://tuannamle256:k3lGLGcBHFvYgto9@phannam.t93kh0q.mongodb.net/?retryWrites=true&w=majority&appName=PhanNam";
    public String getConnectionString() {
        return connectionString;
    }
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    public void changeToView(String title){

    }
}
