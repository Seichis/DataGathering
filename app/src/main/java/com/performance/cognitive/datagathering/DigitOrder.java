package com.performance.cognitive.datagathering;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import datastructure.AttentionTaskDigitOrder;
import scheduler.Scheduler;


public class DigitOrder extends ActionBarActivity {
    String TAG = "digits2";
    String GAT = "digits";
    TextView digits;
    ImageView result;
    TextView info;
    Button playButton;
    int[] NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    List term = new ArrayList();
    int j = 3;
    int x = 0;
    int ind=0;
    static int level;
    InputMethodManager imm;
    boolean inputSet;
    Timer mTimer;
    Handler mHandler;
    EditText editText;
    boolean buttonPressed;
   // Button submitButton;
    Button startButton;
    boolean taskStarted;
    AttentionTaskDigitOrder mAttentionTaskDigitOrder;
    public static List<String> digitsResults;
    public static List<String> userResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_order);
        info = (TextView)findViewById(R.id.info);
        playButton = (Button)findViewById(R.id.playbutton);
        result = (ImageView) findViewById(R.id.result);
        info.setText("Write the digits in right order (lower to higher)");
        editText = (EditText) findViewById(R.id.edit);
        editText.setVisibility(View.INVISIBLE);
        digits = (TextView) findViewById(R.id.numbers);
        digits.setText("");

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setVisibility(View.INVISIBLE);

        playButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                info.setVisibility(View.INVISIBLE);
                playButton.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    String userAnswer = editText.getText().toString();

                    userAnswer = userAnswer.replaceAll("\\s+", "");
                    userResults.add(userAnswer);
                    Log.i(TAG, String.valueOf(userResults));
                    final String finalUserAnswer = userAnswer;

                    new CountDownTimer(2000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            result.setVisibility(View.VISIBLE);
                            if (userResults.get(ind).equals(DigitOrder.digitsResults.get(ind))) {
                                Resources res = getResources();
                                result.setImageDrawable(res.getDrawable(R.drawable.tickgreen));
                            }else {
                                Resources res = getResources();
                                result.setImageDrawable(res.getDrawable(R.drawable.wrong));
                            }
                        }

                        public void onFinish() {
                            result.setVisibility(View.INVISIBLE);
                            buttonPressed = true;
                            level++;
                            ind++;
                        }
                    }.start();

                }
                return false;
            }
        });



        level = 1;
        mAttentionTaskDigitOrder = new AttentionTaskDigitOrder();
        Scheduler.getInstance().activityStart(mAttentionTaskDigitOrder);
        digitsResults = new ArrayList<>();
        userResults = new ArrayList<>();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        buttonPressed = false;
        taskStarted = false;
        inputSet = false;


        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }

        mHandler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskStarted = true;
                mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 1000, 1000);
                term = getListToShow(NUMBERS);
                String numberList = String.valueOf(term);
                String[] newList = numberList.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                String digitsComp = "";
                for (int x = 0; x < newList.length; x++) {
                    digitsComp = digitsComp + newList[x];
                }
                digits.setText(digitsComp);
                digitsResults.add(sortDigits(term));
                startButton.setVisibility(View.INVISIBLE);
                Log.i(GAT, String.valueOf(digitsResults));
            }
        });


    }

    private void setEditTextForInput() {
        if (inputSet == true && buttonPressed == false) {
            editText.setVisibility(View.VISIBLE);
            editText.setText("");

            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
        if (buttonPressed == true) {
            editText.setVisibility(View.INVISIBLE);
            digits.setVisibility(View.VISIBLE);
            buttonPressed = false;
            inputSet = false;


        }
    }

    private String sortDigits(List<Integer> term) {
        String termStr;
        String digitsNew = "";
        Collections.sort(term);
        termStr = String.valueOf(term);
        String[] termNew = termStr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        for (int x = 0; x < termNew.length; x++) {
            digitsNew = digitsNew + termNew[x];
        }
        digitsNew = digitsNew.replaceAll("\\s+", "");

        return digitsNew;
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {


                    if (j < 8 && inputSet == false) {
                        term = getListToShow(NUMBERS);
                        String numberList = String.valueOf(term);
                        String[] newList = numberList.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                        String digitsComp = "";
                        for (int x = 0; x < newList.length; x++) {
                            digitsComp = digitsComp + newList[x];
                        }
                        digits.setText(digitsComp);
                        digitsResults.add(sortDigits(term));
                        Log.i(GAT, String.valueOf(digitsResults));
                        digits.setVisibility(View.INVISIBLE);
                        buttonPressed = false;
                        if (digits.getVisibility() != View.VISIBLE) {
                            inputSet = true;
                            setEditTextForInput();
                        }


                    } else if (inputSet && buttonPressed) {
                        setEditTextForInput();


                    }
                    if (level > 10) {
                        digits.setVisibility(View.INVISIBLE);

                        int counter=0;
                        for (int x=0; x<10; x++){
                            if (userResults.get(x).equals( DigitOrder.digitsResults.get(x))) {
                                counter+=1;
                            }
                        }

                        mAttentionTaskDigitOrder.setScore(counter);
                        Scheduler.getInstance().activityStop(mAttentionTaskDigitOrder, true);
//                        Intent intent = new Intent(DigitOrder.this, DigitOrderResults.class);
//                        startActivity(intent);
                        mTimer.cancel();
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