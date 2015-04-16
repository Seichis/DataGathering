package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import adapters.TextAdapter;
import datastructure.SelectiveAttentionTask;
import scheduler.Scheduler;


public class SelectiveActivity extends ActionBarActivity implements View.OnTouchListener {
    public static int tPosition;
    GridView myGridView;
    TextAdapter mTextAdapter;
    static int score;
    SelectiveAttentionTask mSelectiveAttentionTask;
    float tapX=0, tapY=0;
    TextView timer;
    CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selective);
        myGridView = (GridView) findViewById(R.id.gridView);
        mTextAdapter = new TextAdapter(this);
        mTextAdapter.fillArray();
        mSelectiveAttentionTask = new SelectiveAttentionTask();
        Scheduler.getInstance().activityStart(mSelectiveAttentionTask);
        score = 0;
        tPosition = 0;
        myGridView.setAdapter(mTextAdapter);
        timer = (TextView) findViewById(R.id.timer);

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                if(mTextAdapter.mylist.get(position).getText().equals("T")){
                    score++;
                    mTextAdapter.clearList();
                    mTextAdapter.fillArray();
                    mTextAdapter.notifyDataSetChanged();
                }
            }

        });
        mCountDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) ((millisUntilFinished / 1000));
                timer.setText(seconds + "  seconds ");
            }

            public void onFinish() {
                mSelectiveAttentionTask.setScore(score);
                Scheduler.getInstance().activityStop(mSelectiveAttentionTask,true);
                SelectiveActivity.this.finish();
            }
        };

        mCountDownTimer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tapX = event.getX();
                tapY = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }

        return false;
    }
}
