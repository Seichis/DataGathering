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
import datastructure.SpeedNumberTask;
import datastructure.SpeedTapTask;
import datastructure.Task;
import settings.SettingsObj;


public class DataOperations {
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
    public void writeToTaskFile(String content) {
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

    public void writeToSettingsFile(String content) {
        try {
            File file = new File(Environment.getExternalStorageDirectory()
                    .getPath() + "/settings.json");
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
    public List<String> readFromTaskFile() {
//        File file = new File(Environment.getExternalStorageDirectory()
//                .getPath() + "/task.json");
//        if (!file.exists())
//            try {
//                file.createNewFile();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }

        BufferedReader br;
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


    public List<String> readFromSettingsFile() {


        BufferedReader br;
        List<String> JSONlist = new ArrayList<>();
        String line;
        try {
            String fpath = Environment.getExternalStorageDirectory().getPath()
                    + "/settings.json";
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
    public <T extends Task> String taskToJSON(T EX) {
        Gson gson = new GsonBuilder().create();
        String JSONtemp = (gson.toJson(EX));
        return JSONtemp;
    }

    /**
     * @param mSettings
     * @return JSON
     * Converts an SettingsObj object to a JSON string
     */
    public String settingsToJSON(SettingsObj mSettings) {
        Gson gson = new GsonBuilder().create();
        String JSONtemp = (gson.toJson(mSettings));
        return JSONtemp;
    }

    /**
     * @param JSONlist
     * @return OBJlist
     * Converts a list of task objects to a JSON list
     */
    public List<Task> JSONlistToTaskList(List<String> JSONlist) {
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
    public SettingsObj JSONToSettings(String JSON) {
        Gson gson = new GsonBuilder().create();
        SettingsObj mSet = gson.fromJson(JSON, SettingsObj.class);
        return mSet;
    }


    /**
     * Clears the data from task file
     */
    public void clearSettingsFile() {
        try {
            new FileOutputStream(Environment.getExternalStorageDirectory()
                    .getPath() + "/settings.json", false).close();
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
    public List<AttentionTaskDigitOrder> getTodaysAttentionDigitOrderTasks() {

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
    public List<AttentionDigitSpanTask> getTodaysAttentionDigitSpanTasks() {

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
    public List<FluencyTask> getTodaysFluencyTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<FluencyTask> SL = new ArrayList<>();
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
    public List<CoordinationTask> getTodaysCoordinationTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<CoordinationTask> WL = new ArrayList<>();
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
    public List<LongTermMemoryTask> getTodaysLongTermMemoryTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<LongTermMemoryTask> AL = new ArrayList<>();
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
    public List<ReactionTimeTask> getTodaysReactionTimeTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<ReactionTimeTask> AL = new ArrayList<>();
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
    public List<SelectiveAttentionTask> getTodaysSelectiveAttentionTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<SelectiveAttentionTask> AL = new ArrayList<>();
        for (String json : JSONlist) {
            if (json.contains("selectiveattention"))
                AL.add(gson.fromJson(json, SelectiveAttentionTask.class));
        }
        return AL;
    }

    /**
     * @return a list of Speed Tap objects
     * Converts tasks from today JSON -> Objects
     */
    public List<SpeedTapTask> getTodaysSpeedTapTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<SpeedTapTask> AL = new ArrayList<>();
        for (String json : JSONlist) {
            if (json.contains("speedtap"))
                AL.add(gson.fromJson(json, SpeedTapTask.class));
        }
        return AL;
    }

    /**
     * @return a list of Speed Tap objects
     * Converts tasks from today JSON -> Objects
     */
    public List<SpeedNumberTask> getTodaysSpeedNumberTasks() {
        List<String> JSONlist = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<SpeedNumberTask> AL = new ArrayList<>();
        for (String json : JSONlist) {
            if (json.contains("speednumber"))
                AL.add(gson.fromJson(json, SpeedNumberTask.class));
        }
        return AL;
    }
}
