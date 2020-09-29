package carbon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public Label weekLabel;
    public Label dateLabel;
    public Label activityLabel;
    public Label pointsLabel;
    public GridPane mainPane;
    public TextField weekEntry;
    public DatePicker datePicker;
    public TextField activityEntry;
    public TextField pointsEntry;
    public HBox buttonsBox;
    public Button listButton;
    public TextArea mainText;
    public Button summaryButton;
    public Button removeButton;
    public Button addButton;

    public CarbonModel model;



    public void formatLayout() {
        mainPane.setVgap(3);
        buttonsBox.setSpacing(5);
        mainText.setEditable(false);
    }

    public void addActivity(MouseEvent mouseEvent)
    {
        try {
            int week = Integer.parseInt(weekEntry.getText());
            String test = pointsEntry.getText();
            int points = Integer.parseInt(pointsEntry.getText());

            if(points < -10 || points > 10 || points == 0){
                System.out.println("Invalid activity input");
                return;
            }

            String activityName = activityEntry.getText();
            if(activityName.length()==0){
                System.out.println("Invalid activity input");
                return;
            }

            Activity activity = new Activity(week, datePicker.getValue(), points, activityName);
            model.addActivity(activity);

        }catch (Exception e) {
            System.out.println("Invalid activity input");
        }
        listActivities(null);
    }

    public void listActivities(MouseEvent mouseEvent)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < model.getActivities().size(); i++) {
            Activity activity = model.getActivities().get(i);

            if(activity.getPoints()>0) {
                builder.append(String.format("%s +%d points",activity.getActivityName(), activity.getPoints()));
            }else{
                builder.append(String.format("%s %d points",activity.getActivityName(), activity.getPoints()));
            }

            builder.append(System.getProperty("line.separator"));
        }
        mainText.setText(builder.toString());
    }

    public void showSummary(MouseEvent mouseEvent)
    {
        model.showSummary();

    }

    public void removeActivity(MouseEvent mouseEvent){
        model.removeActivity(activityEntry.getText());
        listActivities(null);
    }

    public void saveActivities(MouseEvent mouseEvent) {
        model.saveActivities();

    }

    public void loadActivities(MouseEvent mouseEvent) {
        model.loadActivities();
        listActivities(null);
    }
}
