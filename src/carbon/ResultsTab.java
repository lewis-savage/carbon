package carbon;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ResultsTab {
    public CarbonModel model;
    public GridPane activitiesGrid;
    public HBox header;
    public void setupLayout(){
        header.setSpacing(5);
    }

    @FXML
    private void orderActivitiesByDate(){
        populateHeaders();
        ArrayList<Activity> activities = (ArrayList<Activity>) model.getActivities().clone();
        activities.sort(Comparator.comparing(Activity::getDate));
        displayActivities(activities);
    }

    @FXML
    private void orderActivitiesByName(){
        populateHeaders();
        ArrayList<Activity> activities = (ArrayList<Activity>) model.getActivities().clone();
        activities.sort(Comparator.comparing(Activity::getActivityName));
        displayActivities(activities);
    }

    private void displayActivities(ArrayList<Activity> activities){
        for (int i = 0; i < activities.size(); i++) {
            activitiesGrid.add(new Text(activities.get(i).getActivityName()),0,i+1,1,1);
            activitiesGrid.add(new Text(activities.get(i).getDate().toString()),1,i+1,1,1);
            activitiesGrid.add(new Text(Integer.toString(activities.get(i).getWeek())),2,i+1,1,1);
            activitiesGrid.add(new Text(Integer.toString(activities.get(i).getPoints())),3,i+1,1,1);
        }
    }

    public void populateHeaders(){
        activitiesGrid.setHgap(10);
        activitiesGrid.setVgap(10);
        activitiesGrid.setPadding(new Insets(10, 10, 10, 10));
        activitiesGrid.getChildren().clear();
        activitiesGrid.add(new Text("Name"),0,0,1,1);
        activitiesGrid.add(new Text("Date"),1,0,1,1);
        activitiesGrid.add(new Text("Week"),2,0,1,1);
        activitiesGrid.add(new Text("Points"),3,0,1,1);

    }
}
