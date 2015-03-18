package com.performance.cognitive.datagathering;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import datastructure.AttentionTask;
import scheduler.Scheduler;


public class DigitOrder extends ActionBarActivity {

    TextView digits;
    int[] NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    List term = new ArrayList();
    int j = 3;
    int x = 0;
    int level;
    InputMethodManager imm;
    boolean inputSet;
    Timer mTimer;
    Handler mHandler;
    EditText editText;
    boolean buttonPressed;
    Button submitButton;
    Button startButton;
    boolean taskStarted;
    AttentionTask mAttentionTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_order);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        level=1;
        mAttentionTask = new AttentionTask();
        Scheduler.getInstance().activityStart(mAttentionTask);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        editText = (EditText) findViewById(R.id.edit);
        editText.setVisibility(View.INVISIBLE);
        digits = (TextView) findViewById(R.id.numbers);
        digits.setText("");
        submitButton = (Button) findViewById(R.id.submit_answer);
        startButton = (Button) findViewById(R.id.start_button);
        buttonPressed = false;
        taskStarted = false;
        inputSet = false;


        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }

        mHandler = new Handler();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonPressed = true;
                level++;
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskStarted = true;
                mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 1000, 1000);
                digits.setText(String.valueOf(getListToShow(NUMBERS)));
            }
        });

    }

    private void setEditTextForInput() {
        if (inputSet == true && buttonPressed == false) {
            editText.setVisibility(View.VISIBLE);
            editText.setText("");
            submitButton.setVisibility(View.VISIBLE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
        if (buttonPressed == true) {
            editText.setVisibility(View.INVISIBLE);
            digits.setVisibility(View.VISIBLE);
            buttonPressed = false;
            inputSet = false;
            submitButton.setVisibility(View.INVISIBLE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {


                    if (j < 8 && inputSet == false) {
                        digits.setText(String.valueOf(getListToShow(NUMBERS)));
                        digits.setVisibility(View.INVISIBLE);
                        buttonPressed = false;
                        if (digits.getVisibility() != View.VISIBLE) {
                            inputSet = true;
                            setEditTextForInput();
                        }


                    } else if (inputSet  && buttonPressed) {
                        setEditTextForInput();


                    }
                    if (level == 11) {
                        mTimer.cancel();
                        Scheduler.getInstance().activityStop(mAttentionTask, true);
                        DigitOrder.this.finish();

                    }


                }
            });
        }
    }

    private List<Integer> getListToShow(int[] intArray) {

        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            intList.add(intArray[i]);
        }
        Collections.shuffle(intList);
        x += 1;

        if (x > 2) {
            if (j < 8) {
                j++;
                x = 1;
            }
        }

        return intList.subList(0, j);
    }


}
