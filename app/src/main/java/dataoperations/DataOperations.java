package dataoperations;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import datastructure.StaticTaskTypes;
import datastructure.Task;


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

    public List<Task> getTaskListFromJSON(String taskType) {
        List<String> mTaskStringList = readFromTaskFile();
        Gson gson = new GsonBuilder().create();
        List<Task> mTaskList = new ArrayList<>();
        switch (taskType) {
            case StaticTaskTypes.digitOrder:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, AttentionTaskDigitOrder.class));
                }
                break;
            case StaticTaskTypes.digitSpan:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, AttentionDigitSpanTask.class));
                }
                break;
            case StaticTaskTypes.fluency:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, FluencyTask.class));
                }
                break;
            case StaticTaskTypes.coord:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, CoordinationTask.class));
                }
                break;
            case StaticTaskTypes.longterm:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, LongTermMemoryTask.class));
                }
                break;
            case StaticTaskTypes.speedtap:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, SpeedTapTask.class));
                }
                break;
            case StaticTaskTypes.selective:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, SelectiveAttentionTask.class));
                }
                break;
            case StaticTaskTypes.reaction:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, ReactionTimeTask.class));
                }
                break;
            case StaticTaskTypes.speednum:
                for (String tp : mTaskStringList) {
                    if (tp.contains(taskType))
                        mTaskList.add(gson.fromJson(tp, SpeedNumberTask.class));
                }
                break;
        }


        return mTaskList;
    }


    public String javascriptFeedbackToJson(String[] data) {


        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            String d = data[i];
            if (i > 0)
                sb.append(",");
            sb.append(d);
        }
        sb.append("]");
        return sb.toString();

    }

    public String[] prepareTaskLocationForJavascript(String taskType) {

        List<Task> DL = getTaskListFromJSON(taskType);

        String[] mLocationStrings = new String[DL.size()];

        if (!DL.isEmpty())
            for (Task ex : DL) {
                mLocationStrings[DL.indexOf(ex)] = ex.getTaskLocation();
            }

        return mLocationStrings;
    }

    public String[] prepareTaskBestScoreForJavascript(String taskType) {
        int max =  0;
        List<Task> DL = getTaskListFromJSON(taskType);

        String[] mBestScoreStrings = new String[DL.size()];

        if (!DL.isEmpty()) {
            max = DL.get(0).getScore();
            for (Task ex : DL) {
                if(max>ex.getScore()){
                    max=ex.getScore();
                }
            }
        }
        mBestScoreStrings[0] = Integer.toString(max);
        return mBestScoreStrings;
    }
      public String[] prepareTaskWorstScoreForJavascript(String taskType) {
        int min =  0;
        List<Task> DL = getTaskListFromJSON(taskType);

        String[] mWorstScoreStrings = new String[DL.size()];

        if (!DL.isEmpty()) {
            min = DL.get(0).getScore();
            for (Task ex : DL) {
                if(min<ex.getScore()){
                    min=ex.getScore();
                }
            }
        }
        mWorstScoreStrings[0] = Integer.toString(min);
        return mWorstScoreStrings;
    }

    public String[] prepareTaskScoreForJavascript(String taskType) {

        List<Task> DL = getTaskListFromJSON(taskType);

        String[] mScoreStrings = new String[DL.size()];

        if (!DL.isEmpty())
            for (Task ex : DL) {
                mScoreStrings[DL.indexOf(ex)] = Integer.toString(ex.getScore());
            }

        return mScoreStrings;
    }
}
