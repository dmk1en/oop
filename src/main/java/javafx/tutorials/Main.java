package javafx.tutorials;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("/resources/fxml/main.fxml"));
        primaryStage.setTitle("main");
        primaryStage.setScene(new Scene(loader,1000,700));
        primaryStage.show();
    }
	 
    public static void main( String[] args )  
    {
    	launch();
    }
}
