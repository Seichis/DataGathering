package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceToTextActivity extends Activity {
    private Timer mTimer;
    int countWords;
    private TextView showWordsTextView;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private Handler mHandler;
    private List<String> answersList;
    List<String> wordsToShow;
    String wordShown;
    boolean finishedShowWords;
    boolean speechInputActivated;
    boolean finishedVoiceInput;
    List<String> finalResults;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_to_text);
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        mHandler = new Handler();
        showWordsTextView = (TextView) findViewById(R.id.show_words);
        countWords = 0;
        answersList = new ArrayList<>();
        wordShown = "";
        finishedShowWords = false;
        speechInputActivated = false;
        finishedVoiceInput = false;
        wordsToShow = new ArrayList<>();
        wordsToShow.add("umbrella");
        wordsToShow.add("star");
        wordsToShow.add("obelisk");
        wordsToShow.add("banana");
        wordsToShow.add("shark");
        wordsToShow.add("monkey");
        wordsToShow.add("skirt");
        wordsToShow.add("zebra");
        wordsToShow.add("sword");
        wordsToShow.add("knife");
        wordsToShow.add("");
        finalResults = new ArrayList<>();
        score = 0;
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 1000, 1000);

    }

    private void showWords(TextView tv) {
        if (countWords < wordsToShow.size()) {
            tv.setText(wordsToShow.get(countWords));
            countWords++;
        }
    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    setResults(result);
                    finishedVoiceInput = true;
                }
                break;
            }

        }
    }

    public void setResults(ArrayList<String> results) {
        this.answersList = results;
        //Log.i("result list", "   " + answersList);
    }

    public void setAnswersToFinalResults(List<String> answersList) {
        String[] result = answersList.get(0).split("\\s");
        finalResults.clear();
        for (int i = 0; i < result.length; i++) {
            Log.i("result list", "   " + result[i]);
            finalResults.add(result[i]);
        }
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (!finishedShowWords) {
                        showWords(showWordsTextView);
                        blankTv(showWordsTextView);
                    } else if (finishedShowWords == true && !speechInputActivated) {
                        promptSpeechInput();
                        speechInputActivated = true;
                    } else if (speechInputActivated == true && finishedVoiceInput == true) {
                        setAnswersToFinalResults(answersList);
                        checkFinalResults(finalResults);
                        mTimer.cancel();
                    }

                }
            });
        }
    }

    private void checkFinalResults(List<String> finalResults) {
        for (String s2 : finalResults) {
            Log.i("score", "  outside loop ");
            for (String s1 : wordsToShow) {
                Log.i("score", "  inside loop ");
                if (s2.equals(s1)) {
                    wordsToShow.set(wordsToShow.indexOf(s1), "");
                    score++;
                    Log.i("score", "  inside if ");
                }
            }
        }
        Log.i("score", "   " + score);
    }


    private void blankTv(TextView tv) {
        if (countWords == wordsToShow.size()) {
            tv.setText("");
            finishedShowWords = true;
        }
    }
}

