package carbon;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Popup {
    public Label weekLabel;
    public Label dateLabel;
    public Label activityLabel;
    public Label pointsLabel;
    public Button closeButton;

    public void displayActivity(Activity activity){
        weekLabel.setText("Week: " + Integer.toString(activity.getWeek()));
        dateLabel.setText("Date: " + activity.getDate().toString());
        activityLabel.setText("Activity: " + activity.getActivityName());
        pointsLabel.setText("Points: " + Integer.toString(activity.getPoints()));
    }

}
