package com.performance.cognitive.datagathering;

import android.media.MediaRecorder;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.os.Environment;
import android.widget.Button;
import android.view.View;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import datastructure.LongTermMemoryTask;
import scheduler.Scheduler;


public class Words extends ActionBarActivity {
    Timer mTimer;
    Timer sTimer;
    Handler mHandler;
    Handler sHandler;
    TextView word;
    int i;
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private Button mRecordButton;
    Button playButton;
    TextView info;
    private MediaRecorder mRecorder = null;
    LongTermMemoryTask longTermMemory;
    ArrayList<String> words= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        longTermMemory=new LongTermMemoryTask();
        Scheduler.getInstance().activityStart(longTermMemory);
        setContentView(R.layout.activity_words);
        word = (TextView)findViewById(R.id.word);
        info = (TextView)findViewById(R.id.info);
        info.setText("Memorize the words shown on screen. Repeat the words on microphone and memorize the words one more time.");
        playButton = (Button) findViewById(R.id.playbutton);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("mappe.txt")));

            String mLine = reader.readLine();
            while (mLine != null) {
                words.add(mLine);
                mLine = reader.readLine();

            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        playButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                playButton.setVisibility(View.INVISIBLE);
                info.setVisibility(View.INVISIBLE);
                mHandler = new Handler();
                mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 1000, 1000);
            }
        });
        mRecordButton = (Button) findViewById(R.id.record);
        i=0;
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        if (sTimer != null) {
            sTimer.cancel();
        } else {
            sTimer = new Timer();
        }

        mRecordButton.setVisibility(View.INVISIBLE);
        mRecordButton.setOnClickListener(new Button.OnClickListener() {
            boolean mStartRecording = true;
            public void onClick(View v) {
                onRecord(mStartRecording);

                if (mStartRecording) {
                    mRecordButton.setText("Stop recording");

                } else {
                    mRecordButton.setVisibility(View.INVISIBLE);
                    i=0;
                    sHandler = new Handler();
                    sTimer.scheduleAtFixedRate(new SecondTimerTask(), 1000, 1000);

                }
                mStartRecording = !mStartRecording;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        longTermMemory.setTaskLocation(MainActivity.taskLocation);
        Scheduler.getInstance().activityStop(longTermMemory,true);
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (i<10 ) {
                        word.setText(words.get(i));
                        i++;

                    }else {
                        mTimer.cancel();
                        mRecordButton.setVisibility(View.VISIBLE);
                        word.setVisibility(View.INVISIBLE);
                        i = 0;

                    }
                }
            });
        }
    }

    class SecondTimerTask extends TimerTask {

        @Override
        public void run() {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (i<10 ) {

                        word.setVisibility(View.VISIBLE);
                        word.setText(words.get(i));
                        i++;

                    }else {
                        sTimer.cancel();
                        Words.this.finish();

                    }
                }
            });
        }
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

    }


}