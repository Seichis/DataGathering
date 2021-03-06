package datastructure;

import java.util.GregorianCalendar;

/**
 * Created by User1 on 8/3/2015.
 */
public abstract class Task {

    String taskType;
    GregorianCalendar startTimestamp;
    GregorianCalendar endTimestamp;
    boolean completion;
    String taskLocation;
    int score;

    public <T extends Task> T createTask() {
        this.startTimestamp = new GregorianCalendar();
        return null;
    }

    public <T extends Task> void concludeTask(boolean completion) {
        startTimestamp = getStartTimestamp();
        this.endTimestamp = new GregorianCalendar();
        this.completion = completion;

    }

    public <T extends Task> GregorianCalendar getStartTimestamp() {
        return this.startTimestamp;
    }

    public <T extends Task> GregorianCalendar getEndTimestamp() {
        return this.endTimestamp;
    }

    public <T extends Task> String getTaskType() {
        return this.taskType;
    }

    public <T extends Task> String getTaskLocation() {
        return this.taskLocation;
    }

    public <T extends Task> void setTaskLocation(String loc) {
        this.taskLocation = loc;
    }

    public <T extends Task> int getScore() {
        return this.score;
    }

    public <T extends Task> void setScore(int sc) {
        this.score = sc;
    }


}
