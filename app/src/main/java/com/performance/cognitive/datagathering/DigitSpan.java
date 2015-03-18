package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import datastructure.AttentionTask;
import scheduler.Scheduler;


public class DigitSpan extends ActionBarActivity {

    ImageView number;
    Button button1;
    Button button2;
    EditText text1;
    TextView textView;
    public int correct;

    String use, use2, use3, use4, use5, use6, use7, use8, use9, use10;

    int round = 4;
    public int rnd;
    public int get;
    public int count;
    AttentionTask mAttentionTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_span);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mAttentionTask = new AttentionTask();
        number = (ImageView) findViewById(R.id.number);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        text1 = (EditText) findViewById(R.id.text1);
        textView = (TextView) findViewById(R.id.textView);

        Scheduler.getInstance().activityStart(mAttentionTask);

    }


    @Override
    protected void onResume() {
        super.onResume();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                count += 1;
                if (count == 16) {
                    DigitSpan.this.finish();
                }

                AnimationDrawable animation = new AnimationDrawable();
                rnd = getnum();
                use = "" + rnd;
                //num.add(use);
                animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                rnd = getnum();
                use2 = "" + rnd;
                //num.add(use2);
                animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                rnd = getnum();
                use3 = "" + rnd;
                //num.add(use3);
                animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                rnd = getnum();
                use4 = "" + rnd;
                //num.add(use4);
                animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                if (round >= 5) {
                    rnd = getnum();
                    use5 = "" + rnd;
                    //num.add(use5);
                    animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                }
                if (round >= 6) {
                    rnd = getnum();
                    use6 = "" + rnd;
                    //num.add(use6);
                    animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                }
                if (round >= 7) {

                    rnd = getnum();
                    use7 = "" + rnd;
                    //num.add(use7);
                    animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                }
                if (round >= 8) {

                    rnd = getnum();
                    use8 = "" + rnd;
                    // num.add(use8);
                    animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                }
                if (round >= 9) {

                    rnd = getnum();
                    use9 = "" + rnd;
                    //num.add(use9);
                    animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                }
                if (round >= 10) {

                    rnd = getnum();
                    use10 = "" + rnd;
                    //num.add(use10);
                    animation.addFrame(getResources().getDrawable(getid(rnd)), 1000);
                }
                animation.addFrame(getResources().getDrawable(R.drawable.end), 1000);
                animation.setOneShot(true);

                number.setBackgroundDrawable(animation);


                animation.start();


            }


        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1;
                str1 = text1.getText().toString();
                String str2 = "";
                if (round == 4) {
                    str2 = use + use2 + use3 + use4;
                }
                if (round == 5) {
                    str2 = use + use2 + use3 + use4 + use5;
                }
                if (round == 6) {
                    str2 = use + use2 + use3 + use4 + use5 + use6;
                }
                if (round == 7) {
                    str2 = use + use2 + use3 + use4 + use5 + use6 + use7;
                }
                if (round == 8) {
                    str2 = use + use2 + use3 + use4 + use5 + use6 + use7 + use8;
                }
                if (round == 9) {
                    str2 = use + use2 + use3 + use4 + use5 + use6 + use7 + use8 + use9;
                }
                if (round == 10) {
                    str2 = use + use2 + use3 + use4 + use5 + use6 + use7 + use8 + use9 + use10;
                }

                if (str1.equals(str2)) {
                    correct = 1;
                } else {
                    correct = 0;
                }
                if (correct == 1) {
                    round += 1;
                    text1.setText("1");
                } else {
                    round += 0;
                    text1.setText("0");
                }
                //round += 1;
                //num.clear();
                textView = setRoundView(textView);
                if (round == 11) {
                    DigitSpan.this.finish();
                }


            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Scheduler.getInstance().activityStop(mAttentionTask, true);
        Intent intent = new Intent(DigitSpan.this, DigitSpanResult.class);
        Bundle bl = new Bundle();
        bl.putInt("round", round - 1);
        intent.putExtras(bl);
        startActivityForResult(intent, 0);

    }

    private TextView setRoundView(TextView roundView1) {
        roundView1.setText(Integer.toString(round) + " Digits");
        return roundView1;
    }

    private int getnum() {
        get = (int) (Math.random() * 9);
        return get;
    }

    private int getid(int rnd) {
        //rnd = (int)(Math.random()*9);
        int id = getResources().getIdentifier("end", "drawable", getPackageName());
        //String name="end";
        if (rnd == 1) {
            id = getResources().getIdentifier("a", "drawable", getPackageName());
        }
        if (rnd == 2) {
            id = getResources().getIdentifier("b", "drawable", getPackageName());
        }
        if (rnd == 3) {
            id = getResources().getIdentifier("c", "drawable", getPackageName());
        }
        if (rnd == 4) {
            id = getResources().getIdentifier("d", "drawable", getPackageName());
        }
        if (rnd == 5) {
            id = getResources().getIdentifier("e", "drawable", getPackageName());
        }
        if (rnd == 6) {
            id = getResources().getIdentifier("f", "drawable", getPackageName());
        }
        if (rnd == 7) {
            id = getResources().getIdentifier("g", "drawable", getPackageName());
        }
        if (rnd == 8) {
            id = getResources().getIdentifier("h", "drawable", getPackageName());
        }
        if (rnd == 9) {
            id = getResources().getIdentifier("i", "drawable", getPackageName());
        }
        if (rnd == 0) {
            id = getResources().getIdentifier("j", "drawable", getPackageName());
        }

        return id;

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
}
