package carbon;

import java.time.LocalDate;

public class Activity {
    private int week;
    private LocalDate date;
    private int points;
    private String activityName;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Activity(int week, LocalDate date, int points, String activityName) {
        this.week = week;
        this.date = date;
        this.points = points;
        this.activityName = activityName;
    }
}
