package scheduler;

import datastructure.Task;

/**
 * Created by User1 on 13/3/2015.
 */
public interface IScheduler {
    /**
     * @param task
     * @return exercise
     * This method receives an exercise instance and adds it to active exercise list
     */
    public  <T extends Task> T activityStart( T task);
    /**
     * @param task
     * @param completion
     * This method completes the exercise, removes from active list, converts to JSON and writes it in the database
     */
    public  <T extends Task> void activityStop( T task, boolean completion);
}
