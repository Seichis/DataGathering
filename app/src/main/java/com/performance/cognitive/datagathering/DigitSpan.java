package com.performance.cognitive.datagathering;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import datastructure.AttentionDigitSpanTask;
import scheduler.Scheduler;


public class DigitSpan extends ActionBarActivity {

    TextView livesTextView, numberTextView, levelTextView;
    ProgressBar countDownProgressBar;
    Button startButton;
    EditText inputEditText;
    CountDownTimer mCountDownTimer;
    Random rand;
    int randomNum;
    int level;
    int life;
    InputMethodManager imm;
    Timer mTimer;
    Handler mHandler;
    boolean isNumber;
    boolean isCorrect;
    int countNumberShown;
    boolean isPadOn;
    boolean isNextLevel;
    long millisToWait;
    float x = 2;
    int j = 0;
    int count = 0;
    String answer;
    List<Integer> numberListShown;
    List<Integer> numberListAnswer;
    AttentionDigitSpanTask attention;
    int charCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_span);
        final ActionsTimerTask timetTask = new ActionsTimerTask();
        attention = new AttentionDigitSpanTask();
        numberListShown = new ArrayList<>();
        numberListAnswer = new ArrayList<>();
        answer = "";
        millisToWait = 500;
        rand = new Random();
        randomNum = 0;
        isNextLevel = false;
        x = 1;
        j = 0;
        life = 1;
        level = 1;
        isNumber = false;
        isCorrect = false;
        countNumberShown = 4;
        livesTextView = (TextView) findViewById(R.id.life_textView);
        numberTextView = (TextView) findViewById(R.id.number_textView);
        levelTextView = (TextView) findViewById(R.id.level_textView);
        countDownProgressBar = (ProgressBar) findViewById(R.id.countdown_progressBar);
        inputEditText = (EditText) findViewById(R.id.input_editText);
        startButton = (Button) findViewById(R.id.start_button);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        isPadOn = false;
        charCount = 0;
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        inputEditText.setVisibility(View.INVISIBLE);
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
                if (s.length() > 0 && s.length() <= numberListShown.size()) {
                    // TODO Auto-generated method stub

                    isCorrect = checkNumberInput(s.charAt(charCount), charCount);
                    if (!isCorrect) {

                        life--;
                        nextRound();
                        charCount = 0;

                    }
                    charCount++;
                    Log.i(" Text watcher ", " After  " + charCount + "   " + isCorrect);
                } else {

                    charCount = 0;
                    Log.i(" Text watcher ", " After  " + charCount);

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0 && s.length() == numberListShown.size()) {
                    nextRound();
                }
            }
        });

        mHandler = new Handler();
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (life >= 0) {
                    livesTextView.setText("Lives: " + life);
                    levelTextView.setText("Level: " + level);
                    countDownProgressBar.setProgress((int) Math.round(millisUntilFinished / 1000.0));
                } else {
                    DigitSpan.this.finish();
                }
            }


            @Override
            public void onFinish() {
                DigitSpan.this.finish();
            }
        };
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
                mCountDownTimer.start();
                mTimer.scheduleAtFixedRate(timetTask, 0, millisToWait);
                Scheduler.getInstance().activityStart(attention);
            }
        });
    }

    private boolean checkNumberInput(char c, int position) {
        char temp = numberListShown.get(position).toString().charAt(0);
        if (temp == c) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        attention.setScore(level);
        Scheduler.getInstance().activityStop(attention, true);
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (life < 0) {
                        DigitSpan.this.finish();
                    }
                    if (isPadOn == false) {
                        if (count < countNumberShown) {
                            if (isNumber == false) {
                                int temp = getNumber();
                                numberListShown.add(temp);
                                numberTextView.setText(Integer.toString(temp));
                                isNumber = true;
                                count++;
                                x = +1;

                            } else {
                                numberTextView.setText("");
                                isNumber = false;
                            }
                        } else if (count == countNumberShown) {
                            inputEditText.setText("");
                            inputEditText.setVisibility(View.VISIBLE);
                            imm.showSoftInput(inputEditText, InputMethodManager.SHOW_IMPLICIT);
                            numberTextView.setText("Go!!");
                            isPadOn = true;
                        }
                    }
                }
            });
        }

    }

    private void nextRound() {

        setNumbersFromInput(inputEditText.getText().toString().trim());

        if (numberListAnswer.equals(numberListShown)) {
            if ((level % 2) == 0) {
                countNumberShown++;
            }
            level++;
        }


        imm.hideSoftInputFromWindow(inputEditText.getWindowToken(), 0);
        inputEditText.setVisibility(View.INVISIBLE);
        isPadOn = false;
        inputEditText.clearFocus();


        count = 0;
        numberListShown.clear();
    }

    private void setNumbersFromInput(String answer) {
        numberListAnswer.clear();
        char[] chars = answer.toCharArray();
        for (Character ch : chars) {
            numberListAnswer.add(Character.getNumericValue(ch));
        }

    }

    private int getNumber() {
        return randomNum = rand.nextInt(10);
    }
}
