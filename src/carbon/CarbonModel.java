package carbon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarbonModel {
    private ArrayList<Activity> activities;
    public CarbonModel(){
        activities = new ArrayList<>();
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
        try {
            Stage dialog = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("activityPopup.fxml"));
            Parent root = loader.load();
            dialog.setTitle("Added activity");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Scene dialogScene = new Scene(root, 300, 200);
            Popup popup = loader.<Popup>getController();
            popup.displayActivity(activity);

            popup.closeButton.setOnAction(e -> {
                dialog.close();
            });

            dialog.setScene(dialogScene);
            dialog.show();
        }catch(java.io.IOException exception){
            exception.printStackTrace();
        }
    }

    public void removeActivity(String text) {
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if(activity.getActivityName().equals(text)){
                activities.remove(activity);
                break;
            }
        }
    }

    public void saveActivities() {
        File file = new File("activities.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < activities.size(); i++) {
                Activity activity = activities.get(i);
                fr.write(String.format("[%d,%s,%d,%s]",activity.getWeek(),activity.getDate().toString(),activity.getPoints(),activity.getActivityName()));
            }

        } catch(java.io.IOException e){
            e.printStackTrace();
        }finally {
            try{
                fr.close();
            }catch(java.io.IOException e){
                e.printStackTrace();
            }
        }
    }

    public void loadActivities() {
        try {
            File file = new File("activities.txt");
            if (file.createNewFile()){
                System.out.println("File is created!");
            }else{
                System.out.println("File already exists.");
            }
            FileReader fr = null;
            try {
                fr = new FileReader(file);
                String fileContents = "";
                int i;
                while ((i = fr.read()) != -1) {
                    fileContents += (char) i;
                }
                String regex = "\\[(.*?)\\]";
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(fileContents);
                try {
                    ArrayList<Activity> activities = new ArrayList<>();
                    while (matcher.find()) {
                        for (int j = 1; j <= matcher.groupCount(); j++) {
                            String match = matcher.group(j);
                            String[] parts = match.split(",");
                            Activity activity = new Activity(Integer.parseInt(parts[0]), LocalDate.parse(parts[1]), Integer.parseInt(parts[2]), parts[3]);
                            activities.add(activity);
                        }
                    }
                    this.activities = activities;
                }catch (Exception e){
                    System.out.println("Invalid file format");
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fr.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private int getSummaryPoints(){
        int total = 0;
        for (int i = 0; i < activities.size(); i++) {
            total += activities.get(i).getPoints();
        }
        return total;
    }

    public void showSummary() {
        try {
            final Stage dialog = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("summaryPopup.fxml"));
            Parent root = loader.load();
            dialog.setTitle("Summary");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Scene dialogScene = new Scene(root, 300, 200);
            SummaryPopup popup = loader.getController();
            popup.closeButton.setOnAction(e -> {
                dialog.close();
            });
            popup.mainLabel.setText(String.format("You have a total of %d points",getSummaryPoints()));
            dialog.setScene(dialogScene);
            dialog.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
