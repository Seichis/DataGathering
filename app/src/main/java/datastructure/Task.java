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
    int completionNumber;

    public <T extends Task> T createTask(){
        this.startTimestamp = new GregorianCalendar();
        return null;
    }

    public <T extends Task> void concludeTask(boolean completion){
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

    public <T extends Task> int getCompletionNumber() {
        return this.completionNumber;
    }

}
