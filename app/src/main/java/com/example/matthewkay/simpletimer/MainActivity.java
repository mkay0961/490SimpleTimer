package com.example.matthewkay.simpletimer;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }


    @Override
    public void onClick(View view) {
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText(R.string.StartString);
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText(R.string.ResetString);
        }
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisiInFuture, long countDownInterval){
            super(millisiInFuture,countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText(R.string.TimeRemainingString + String.valueOf(millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(R.string.TimeElapsedString + String.valueOf(timeElapsed));
        }

        @Override
        public void onFinish() {
            text.setText(R.string.TimesUpString);
            timeElapsedView.setText(R.string.TimeElapsedString + String.valueOf(startTime));

        }
    }





}
