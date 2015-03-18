package scheduler;

import android.util.Log;

import dataoperations.DataOperations;
import datastructure.Task;

/**
 * Created by User1 on 13/3/2015.
 */
public class Scheduler implements IScheduler {
    private static Scheduler scheduler = new Scheduler();

    public Scheduler() {
    }

    /**
     * @return Scheduler returns the singleton instance of Scheduler
     */
    public static Scheduler getInstance() {
        return scheduler;
    }

    @Override
    public <T extends Task> T activityStart(T task) {
        task.createTask();
        return task;
    }

    @Override
    public <T extends Task> void activityStop(T task, boolean completion) {
        task.concludeTask(completion);

        Log.i("Scheduler", "writing to json");
        String JSONstring = DataOperations.taskToJSON(task);

        DataOperations.writeToTaskFile(JSONstring);
        Log.i("Scheduler", "writing to json completed");
    }
}
