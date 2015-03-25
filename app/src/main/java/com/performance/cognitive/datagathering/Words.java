package com.performance.cognitive.datagathering;

import android.media.MediaRecorder;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.os.Environment;
import android.widget.Button;
import android.view.View;
import android.util.Log;

import java.io.IOException;
import android.widget.TextView;

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
    String[] WORDS = {"table", "sun", "toast", "justice", "car", "yogurt", "red", "bag", "flag", "spoon", "absence","kid",
            "music", "go", "watch", "balloon", "plane", "secret", "boss", "energy" };
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private Button mRecordButton;
    private MediaRecorder mRecorder = null;
    LongTermMemoryTask longTermMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        longTermMemory=new LongTermMemoryTask();
        Scheduler.getInstance().activityStart(longTermMemory);
        setContentView(R.layout.activity_words);
        word = (TextView)findViewById(R.id.word);

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
        mHandler = new Handler();
        mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 1000, 1000);
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
        Scheduler.getInstance().activityStop(longTermMemory,true);
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (i<10 ) {
                        word.setText(WORDS[i]);
                        i++;

                    }else {
                        mTimer.cancel();
                        mRecordButton.setVisibility(View.VISIBLE);
                        word.setVisibility(View.INVISIBLE);
                        i = 0;
                        //Scheduler.getInstance().activityStop(mAttentionTask, true);
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
                        word.setText(WORDS[i]);
                        i++;

                    }else {
                        sTimer.cancel();
                        Words.this.finish();
                        //Scheduler.getInstance().activityStop(mAttentionTask, true);
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