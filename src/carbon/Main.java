package carbon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    ArrayList<Activity> activities;

    @Override
    public void start(Stage primaryStage) throws Exception{
        activities = new ArrayList();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("My Carbon awareness effort");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        MainWindow controller = loader.getController();
        controller.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
