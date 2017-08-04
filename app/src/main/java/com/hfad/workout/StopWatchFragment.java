package com.hfad.workout;


import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Button;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchFragment extends Fragment implements View.OnClickListener{

    private int seconds = 0;

    private boolean running;
    private boolean wasRunning;


    public StopWatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);
        Button startButton = (Button) layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return layout;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_button:
                onClickStart(v);
                break;

            case R.id.stop_button:
                onClickStop(v);
                break;

            case R.id.reset_button:
                onClickReset(v);
                break;
        }
    }
    public void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    public void onResume(){
        super.onResume();
        if(wasRunning){
            running = true ;
        }
    }

    public void onClickStart(View view){
        running = true;
    }
    public void onClickStop(View view){
        running = false;
    }
    public void onClickReset(View view){
        running = false;
        seconds=0;
    }


    private void runTimer(View view){
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             int hours =  seconds/3600;
                             int minutes = (seconds%3600)/60;
                             int secs = seconds%60;
                             String time = String.format("%d:%02d:%02d",
                                     hours,minutes,secs);
                             timeView.setText(time);
                             if(running){
                                 seconds++;
                             }
                             handler.postDelayed(this,1000);
                         }
                     }

        );
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);

    }




}
