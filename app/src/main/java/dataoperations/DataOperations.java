package dataoperations;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datastructure.AttentionDigitSpanTask;
import datastructure.AttentionTaskDigitOrder;
import datastructure.CoordinationTask;
import datastructure.FluencyTask;
import datastructure.LongTermMemoryTask;
import datastructure.ReactionTimeTask;
import datastructure.SelectiveAttentionTask;
import datastructure.SpeedTask;
import datastructure.Task;

/**
 * Created by User1 on 8/3/2015.
 */
public class DataOperations {
    public static final String[] tasks = {"Coordination", "Attention", "ReactionTime", "SelectiveAttention", "Speed", "LongTermMemory",
            "Fluency"};

    private static DataOperations dataOPS = new DataOperations();

    private DataOperations() {
    }

    /**
     * @return DataOperations
     * Returns the DataOperations singleton instance
     */
    public static DataOperations getInstance() {
        return dataOPS;
    }

    /**
     * @param content Writes JSON data to task file
     */
    public static void writeToTaskFile(String content) {
        try {
            File file = new File(Environment.getExternalStorageDirectory()
                    .getPath() + "/task.json");
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content + "\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    /**
     * @return JSONlist
     * @throws IOException Reads all the JSON data from the task file to a list of strings
     */
    public static List<String> readFromTaskFile() {
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + "/task.json");
//        if (!file.exists())
//            try {
//                file.createNewFile();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }

        BufferedReader br = null;
        List<String> JSONlist = new ArrayList<>();
        String line;
        try {
            String fpath = Environment.getExternalStorageDirectory().getPath()
                    + "/task.json";
            br = new BufferedReader(new FileReader(fpath));
            while ((line = br.readLine()) != null) {
                JSONlist.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONlist;
    }





    /**
     * @param EX
     * @return JSON
     * Converts an Task object to a JSON string
     */
    public static <T extends Task> String taskToJSON(T EX) {
        Gson gson = new GsonBuilder().create();
        String JSONtemp = (gson.toJson(EX));
        return JSONtemp;
    }

    /**
     * @param JSONlist
     * @return OBJlist
     * Converts a list of task objects to a JSON list
     */
    public static List<Task> JSONlistToTaskList(List<String> JSONlist) {
        Gson gson = new GsonBuilder().create();
        List<Task> EXL = new ArrayList<Task>();
        for (String json : JSONlist) {
            EXL.add(gson.fromJson(json, Task.class));
        }
        return EXL;
    }

    /**
     * @param JSON
     * @return EX
     * Converts a JSON string to an Task object
     */
    @SuppressWarnings("unchecked")
    public static <T extends Task> T JSONToTask(String JSON) {
        Gson gson = new GsonBuilder().create();
        T EX = (T) gson.fromJson(JSON, Task.class);
        return EX;
    }


    /**
     * Clears the data from task file
     */
    public static void clearTaskFile() {
        try {
            new FileOutputStream(Environment.getExternalStorageDirectory()
                    .getPath() + "/task.json", false).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return a list of Attention objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<AttentionTaskDigitOrder> getTodaysAttentionDigitOrderTasks() {

        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<AttentionTaskDigitOrder> DL = new ArrayList<>();
        for (String json : JSONlist) {
            if (json.contains("attentiondigitorder"))
                DL.add(gson.fromJson(json, AttentionTaskDigitOrder.class));
        }
        return DL;
    }
    /**
     * @return a list of Attention objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<AttentionDigitSpanTask> getTodaysAttentionDigitSpanTasks() {

        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<AttentionDigitSpanTask> DL = new ArrayList<>();
        for (String json : JSONlist) {
            if (json.contains("attentiondigitspan"))
                DL.add(gson.fromJson(json, AttentionDigitSpanTask.class));
        }
        return DL;
    }
    /**
     * @return a list of Fluency objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<FluencyTask> getTodaysFluencyTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<FluencyTask> SL = new ArrayList<FluencyTask>();
        for (String json : JSONlist) {
            if (json.contains("fluency"))
                SL.add(gson.fromJson(json, FluencyTask.class));
        }
        return SL;
    }

    /**
     * @return a list of Coordination objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<CoordinationTask> getTodaysCoordinationTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<CoordinationTask> WL = new ArrayList<CoordinationTask>();
        for (String json : JSONlist) {
            if (json.contains("coordination"))
                WL.add(gson.fromJson(json, CoordinationTask.class));
        }
        return WL;
    }


    /**
     * @return a list of LongTermMemory objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<LongTermMemoryTask> getTodaysLongTermMemoryTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<LongTermMemoryTask> AL = new ArrayList<LongTermMemoryTask>();
        for (String json : JSONlist) {
            if (json.contains("longtermmemory"))
                AL.add(gson.fromJson(json, LongTermMemoryTask.class));
        }
        return AL;
    }

    /**
     * @return a list of ReactionTime objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<ReactionTimeTask> getTodaysReactionTimeTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<ReactionTimeTask> AL = new ArrayList<ReactionTimeTask>();
        for (String json : JSONlist) {
            if (json.contains("reactiontime"))
                AL.add(gson.fromJson(json, ReactionTimeTask.class));
        }
        return AL;
    }

    /**
     * @return a list of SelectiveAttention objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<SelectiveAttentionTask> getTodaysSelectiveAttentionTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<SelectiveAttentionTask> AL = new ArrayList<SelectiveAttentionTask>();
        for (String json : JSONlist) {
            if (json.contains("selectiveattention"))
                AL.add(gson.fromJson(json, SelectiveAttentionTask.class));
        }
        return AL;
    }

    /**
     * @return a list of Speed objects
     * Converts tasks from today JSON -> Objects
     */
    public static List<SpeedTask> getTodaysSpeedTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<SpeedTask> AL = new ArrayList<SpeedTask>();
        for (String json : JSONlist) {
            if (json.contains("speed"))
                AL.add(gson.fromJson(json,SpeedTask.class));
        }
        return AL;
    }
}
