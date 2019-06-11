package com.example.dervis.autonomous.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.CarRest;
import com.example.dervis.autonomous.Constants.Commands;
import com.example.dervis.autonomous.Constants.Filters;
import com.example.dervis.autonomous.Constants.SocketObjects;
import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.BatteryObj;
import com.example.dervis.autonomous.Objects.WheelSpeedObj;
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
    ImageView carStream;
    MainViewModel viewModel;
    ImageView batteryStatus;
    int maxWidthBattery;
    int currentWidthBattery;
    public short turnRate;
    public short speed;

    public boolean carOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        carStream = findViewById(R.id.carGoggle);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.startDataGathering(SocketObjects.VIDEO_SOCKETOBJ_LIST);
        viewModel.getImage().observe(this, imageObserver);
        viewModel.getSpeed().observe(this, speedObserver);
        viewModel.getBattery().observe(this, batteryObserver);
        viewModel.startCommandThread();

        batteryStatus = findViewById(R.id.batteryStatusVideo);
        maxWidthBattery = batteryStatus.getLayoutParams().width;
        currentWidthBattery = maxWidthBattery;

        JoystickView joystick = findViewById(R.id.joyStick);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            long lastCommandTime = 0;
            @Override
            public void onMove(int angle, int strength) {
                long time = System.currentTimeMillis();
                long deltaTime = time - lastCommandTime;
                if(deltaTime > 250 || (angle == 0 && strength ==0)){
                    int str = strength * 2; //maxspeed 200
                    turnRate = (short) (str * (0-Math.cos(Math.toRadians(angle))));
                    speed = (short) (str * Math.sin(Math.toRadians(angle)));
                    viewModel.addCommand(Commands.speedCommand(speed));
                    viewModel.addCommand(Commands.turnSpeedCommand(turnRate));
                    lastCommandTime = time;
                    Log.d("DELTATIME", "onMove: " + deltaTime +" speed: " + speed + " turnRate: " + turnRate);
                }
            }
        });
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
        String buttonText = carOn ? "ON" : "OFF";
        Drawable buttonDrawable = carOn ? ResourceGetter.getDrawable(R.drawable.round_button) : ResourceGetter.getDrawable(R.drawable.round_button_red);
        Button button = (Button) view;
        button.setText(buttonText);
        button.setBackground(buttonDrawable);

        if (!carOn){
            viewModel.addCommand(Commands.ARM_MOTORS);
            carOn = true;
        }else{
            viewModel.addCommand(Commands.DISARM_MOTORS);
            carOn = false;
        }
    }

    public void clickedHazard(View view){
        viewModel.addCommand(Commands.HAZARD_LIGHT);
    }

    public void clickedHorn(View view){
        viewModel.addCommand(Commands.HONK_HORN);
    }

    public void clickedRightLight(View view){
        viewModel.addCommand(Commands.RIGHT_TURN_LIGHT);
    }

    public void clickedLeftLight(View view){
        viewModel.addCommand(Commands.LEFT_TURN_LIGHT);
    }

    // Create the observer which updates the UI.
    final Observer<Bitmap> imageObserver = new Observer<Bitmap>() {
        @Override
        public void onChanged(@Nullable final Bitmap bitmap) {
            // Update the UI, in this case, a TextView.
            carStream.setImageBitmap(bitmap);
        }
    };

    final Observer<BatteryObj> batteryObserver = new Observer<BatteryObj>() {
        @Override
        public void onChanged(@Nullable BatteryObj batteryObj) {
            drawBattery(batteryObj);
        }
    };

    final Observer<WheelSpeedObj> speedObserver = new Observer<WheelSpeedObj>() {
        @Override
        public void onChanged(@Nullable WheelSpeedObj wheelSpeedObj) {
            ((TextView) findViewById(R.id.speedometerVideo)).setText("" + wheelSpeedObj.totalSpeed + "%");
        }
    };

    private void drawBattery(BatteryObj batteryObj) {
        double minVoltage = 0;
        double maxVoltage = 17000;

        double batteryLeft = (batteryObj.voltage/(maxVoltage-minVoltage));
        currentWidthBattery = (int) (maxWidthBattery * batteryLeft);

        batteryStatus.getLayoutParams().width = currentWidthBattery;
        //voltage.setText(batteryObj.voltage + "V");
        batteryStatus.requestLayout();
        float x = batteryStatus.getX();
        float y = batteryStatus.getY();
        float width = batteryStatus.getWidth();
        float height = batteryStatus.getHeight();

        if (batteryLeft < 0.5 && batteryLeft > 0.2) {
            batteryStatus.setBackgroundColor((Color.parseColor("#fffb1e")));
        } else if (batteryLeft < 0.2) {
            batteryStatus.setBackgroundColor((Color.parseColor("#ed3636")));
        } else {
            batteryStatus.setBackgroundColor((Color.parseColor("#90CC42")));
        }
        int procentBattery = (int) batteryLeft*100;
        TextView batteryProcent = findViewById(R.id.batteryProcentVideo);
        batteryProcent.setText("" + procentBattery + "%");
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.killSubscriberThreads();
        viewModel.killCommandThread();
    }

}