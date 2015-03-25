package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

import datastructure.SelectiveAttentionTask;
import scheduler.Scheduler;


public class SelectiveActivity extends ActionBarActivity {

    ImageButton letter1;
    ImageButton letter2;
    ImageButton letter3;
    ImageButton letter4;
    ImageButton letter5;
    ImageButton letter6;
    ImageButton letter7;
    ImageButton letter8;
    ImageButton letter9;
    ImageButton letter10;
    ImageButton letter11;
    ImageButton letter12;
    ImageButton letter13;
    ImageButton letter14;
    ImageButton letter15;
    ImageButton letter16;
    ImageButton letter17;
    ImageButton letter18;
    ImageButton letter19;
    ImageButton letter20;
    ImageButton letter21;
    ImageButton letter22;
    ImageButton letter23;
    ImageButton letter24;
    ImageButton letter25;

    Chronometer mChronometer;
    SelectiveAttentionTask selectiveAttention;
    public int rnd;
    public int correct;
    public int wrong;

    //public static String CORRECT = "com.example.dell.selectiveattentiontask.CORRECT";
    //public static String WRONG = "com.example.dell.selectiveattentiontask.WRONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selective);

        selectiveAttention=new SelectiveAttentionTask();
        Scheduler.getInstance().activityStart(selectiveAttention);
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        letter1 = (ImageButton) findViewById(R.id.letter1);
        letter2 = (ImageButton) findViewById(R.id.letter2);
        letter3 = (ImageButton) findViewById(R.id.letter3);
        letter4 = (ImageButton) findViewById(R.id.letter4);
        letter5 = (ImageButton) findViewById(R.id.letter5);
        letter6 = (ImageButton) findViewById(R.id.letter6);
        letter7 = (ImageButton) findViewById(R.id.letter7);
        letter8 = (ImageButton) findViewById(R.id.letter8);
        letter9 = (ImageButton) findViewById(R.id.letter9);
        letter10 = (ImageButton) findViewById(R.id.letter10);
        letter11 = (ImageButton) findViewById(R.id.letter11);
        letter12 = (ImageButton) findViewById(R.id.letter12);
        letter13 = (ImageButton) findViewById(R.id.letter13);
        letter14 = (ImageButton) findViewById(R.id.letter14);
        letter15 = (ImageButton) findViewById(R.id.letter15);
        letter16 = (ImageButton) findViewById(R.id.letter16);
        letter17 = (ImageButton) findViewById(R.id.letter17);
        letter18 = (ImageButton) findViewById(R.id.letter18);
        letter19 = (ImageButton) findViewById(R.id.letter19);
        letter20 = (ImageButton) findViewById(R.id.letter20);
        letter21 = (ImageButton) findViewById(R.id.letter21);
        letter22 = (ImageButton) findViewById(R.id.letter22);
        letter23 = (ImageButton) findViewById(R.id.letter23);
        letter24 = (ImageButton) findViewById(R.id.letter24);
        letter25 = (ImageButton) findViewById(R.id.letter25);


    }

    @Override
    protected void onResume() {
        super.onResume();

        mChronometer.start();
        checkChronometer();

        getNextRound();

        correct=0;
        wrong=0;

        letter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 1) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 2) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 3) {correct += 1;} else {wrong += 1;}
                getNextRound();
            }
        });
        letter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 4) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 5) {correct += 1;} else {wrong += 1;}
                getNextRound();
            }
        });
        letter6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 6) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 7) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 8) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 9) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 10) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 11) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 12) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextRound();
                if (rnd == 13) {correct += 1;} else {wrong += 1;}
            }
        });
        letter14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 14) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 15) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 16) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 17) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 18) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 19) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 20) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 21) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 22) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 23) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 24) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });
        letter25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rnd == 25) {correct += 1;} else {wrong += 1;}
                getNextRound();

            }
        });

    }

    private void checkChronometer() {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long myElapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
                if (myElapsedMillis > 20000) {
                    mChronometer.stop();
                    SelectiveActivity.this.finish();

                }
            }
        });
    }

    private void getNextRound() {
        int id1 = getResources().getIdentifier( "l", "drawable", getPackageName());
        int id2 = getResources().getIdentifier( "t","drawable", getPackageName());

        rnd = (int)(Math.random()*25+1);

        if (rnd == 1){ letter1.setImageResource(id2);} else {letter1.setImageResource(id1);}
        if (rnd == 2){ letter2.setImageResource(id2);} else {letter2.setImageResource(id1);}
        if (rnd == 3){ letter3.setImageResource(id2);} else {letter3.setImageResource(id1);}
        if (rnd == 4){ letter4.setImageResource(id2);} else {letter4.setImageResource(id1);}
        if (rnd == 5){ letter5.setImageResource(id2);} else {letter5.setImageResource(id1);}
        if (rnd == 6){ letter6.setImageResource(id2);} else {letter6.setImageResource(id1);}
        if (rnd == 7){ letter7.setImageResource(id2);} else {letter7.setImageResource(id1);}
        if (rnd == 8){ letter8.setImageResource(id2);} else {letter8.setImageResource(id1);}
        if (rnd == 9){ letter9.setImageResource(id2);} else {letter9.setImageResource(id1);}
        if (rnd == 10){ letter10.setImageResource(id2);} else {letter10.setImageResource(id1);}
        if (rnd == 11){ letter11.setImageResource(id2);} else {letter11.setImageResource(id1);}
        if (rnd == 12){ letter12.setImageResource(id2);} else {letter12.setImageResource(id1);}
        if (rnd == 13){ letter13.setImageResource(id2);} else {letter13.setImageResource(id1);}
        if (rnd == 14){ letter14.setImageResource(id2);} else {letter14.setImageResource(id1);}
        if (rnd == 15){ letter15.setImageResource(id2);} else {letter15.setImageResource(id1);}
        if (rnd == 16){ letter16.setImageResource(id2);} else {letter16.setImageResource(id1);}
        if (rnd == 17){ letter17.setImageResource(id2);} else {letter17.setImageResource(id1);}
        if (rnd == 18){ letter18.setImageResource(id2);} else {letter18.setImageResource(id1);}
        if (rnd == 19){ letter19.setImageResource(id2);} else {letter19.setImageResource(id1);}
        if (rnd == 20){ letter20.setImageResource(id2);} else {letter20.setImageResource(id1);}
        if (rnd == 21){ letter21.setImageResource(id2);} else {letter21.setImageResource(id1);}
        if (rnd == 22){ letter22.setImageResource(id2);} else {letter22.setImageResource(id1);}
        if (rnd == 23){ letter23.setImageResource(id2);} else {letter23.setImageResource(id1);}
        if (rnd == 24){ letter24.setImageResource(id2);} else {letter24.setImageResource(id1);}
        if (rnd == 25){ letter25.setImageResource(id2);} else {letter25.setImageResource(id1);}
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectiveAttention.setScore(correct);
        Scheduler.getInstance().activityStop(selectiveAttention,true);

//        Intent intent = new Intent(MainActivity.this,Result.class);
//        Bundle bl =new Bundle();
//        bl.putInt("correct", correct);
//        bl.putInt("wrong", wrong);
//        intent.putExtras(bl);

        //String correction= Integer.toString(correct);
        //String error= "" + wrong;
        //intent.putExtra(CORRECT,"correction");
        //intent.putExtra(WRONG,error);
       // startActivityForResult(intent,0);
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
