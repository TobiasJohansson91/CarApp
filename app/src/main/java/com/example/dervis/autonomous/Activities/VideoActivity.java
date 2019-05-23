package com.example.dervis.autonomous.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dervis.autonomous.CarRest;
import com.example.dervis.autonomous.Constants.Filters;
import com.example.dervis.autonomous.R;
import com.example.dervis.autonomous.ViewModels.MainViewModel;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import io.github.controlwear.virtual.joystick.android.JoystickView;

/**
 * this class handles the camera display settings and steering
 */
public class VideoActivity extends AppCompatActivity {
    CarRest car = new CarRest();
    ExecutorService pool = Executors.newCachedThreadPool();
    ImageView carStream;
    MainViewModel viewModel;

    /**
     * turn rate
     */
    public int turnRate;

    /**
     * speed
     */
    public int speed;

    public boolean carOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        JoystickView joystick = findViewById(R.id.joyStick);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            long lastCommandTime = 0;
            @Override
            public void onMove(int angle, int strength) {
                long time = System.currentTimeMillis();
                long deltaTime = time - lastCommandTime;
                if(deltaTime > 250 || (angle == 0 && strength ==0)){
                    int str = strength * 2; //maxspeed 200
                    turnRate = (int) (str * (0-Math.cos(Math.toRadians(angle))));
                    speed = (int) (str * Math.sin(Math.toRadians(angle)));
                    viewModel.addCommand(new byte[] {(byte) Filters.CMD_SPEED, (byte) Filters.CAR_SPD, (byte) speed});
                    viewModel.addCommand(new byte[] {(byte) Filters.CMD_SPEED, (byte) Filters.TURN_SPD, (byte) turnRate});
                    lastCommandTime = time;
                }
            }
        });

        carStream = findViewById(R.id.carGoggle);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.startDataGathering(Filters.FLAG_VIDEO);
        viewModel.getImage().observe(this, imageObserver);
        viewModel.startCommandThread();
    }

    /**
     * goes back to previous activity and closes this activity.
     *
     * @param view this view
     */
    public void clickBackArrow(View view) {
        finish();
        overridePendingTransition(R.anim.enter_back_anim, R.anim.exit_back_anim);
    }

    public void clickedOnOff(View view){
        if (!carOn){
            viewModel.addCommand(new byte[] {(byte) Filters.CMD_SET_PARAMS, (byte) Filters.ARM_MOTORS});
            carOn = true;
        }else{
            viewModel.addCommand(new byte[] {(byte) Filters.CMD_SET_PARAMS, (byte) Filters.DISARM_MOTORS});
            carOn = false;
        }
    }

    /**
     * stops everything in this class, calls on stopRepeatingTask.
     */
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * called on when entered this activity, calls on startRepeatingTask.
     */
    protected void onStart() {
        super.onStart();

    }

    /**
     * called on when application is in background
     */
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Create the observer which updates the UI.
    final Observer<Bitmap> imageObserver = new Observer<Bitmap>() {
        @Override
        public void onChanged(@Nullable final Bitmap bitmap) {
            // Update the UI, in this case, a TextView.
            carStream.setImageBitmap(bitmap);
        }
    };
}