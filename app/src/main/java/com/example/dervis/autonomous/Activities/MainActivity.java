package com.example.dervis.autonomous.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Constants.Filters;
import com.example.dervis.autonomous.Constants.ListIDs;
import com.example.dervis.autonomous.Constants.ListItems;
import com.example.dervis.autonomous.Helpers.GsonConv;
import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.BatteryObj;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.Objects.WheelSpeedObj;
import com.example.dervis.autonomous.R;
import com.example.dervis.autonomous.RecyclerView.RecyclerListAdapter;
import com.example.dervis.autonomous.ViewModels.MainViewModel;

/**
 * this class shows the main menu for the app.
 * has battery, speed and a navigation menu.
 */
public class MainActivity extends AppCompatActivity implements RecyclerListAdapter.IOnClickListener {
    public static final String HEADER = "HEADER_TEXT";

    int maxWidthBattery;
    int currentWidthBattery;
    TextView currentSpeed;
    ImageView batteryStatus;
    ImageView lockImg;
    Boolean locked;
    Boolean lightsOn = false;
    ImageView stopImg;
    ImageView lightsImg;
    Button connectButton;
    Animation animAlpha;
    String ip;
    EditText ipNr;
    View connectionView;

    MainViewModel viewModel;
    RecyclerView listView;
    RecyclerListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        ImageButton backButton = findViewById(R.id.backArrowBtn);
        backButton.setEnabled(false);
        backButton.setVisibility(View.GONE);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        batteryStatus = findViewById(R.id.batteryStatus);
        maxWidthBattery = batteryStatus.getLayoutParams().width;
        currentWidthBattery = maxWidthBattery;

        listView = findViewById(R.id.recyclerList);
        listAdapter = new RecyclerListAdapter(this, ListItems.objListMain);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(listAdapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.startDataGathering(Filters.FLAG_MAIN);
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getSpeed().observe(this, speedObserver);
        viewModel.getBattery().observe(this, batteryObserver);
    }

    /**
     * opens diagnostics activity and calls on stopsRepeatingTask
     * @param view this view
     *
     */
    public void diagActivity(View view) {
        startActivity(new Intent(MainActivity.this, DiagnosticsActivity.class));
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }

    /**
     * opens Video activity and calls on stopsRepeatingTask
     * @param view this view
     *
     */
    public void remoteControlClick(View view) {
        startActivity(new Intent(MainActivity.this, VideoActivity.class));
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }

    /**
     * stops everything in this class
     */
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * called on when entered this activity, calls on startRepeatingTask
     */
    protected void onStart() {
        super.onStart();
    }

    protected void onPause(){
        super.onPause();
    }

    /**
     * changes the lock icon on clicked and calls server to change status of lock
     * @param view view
     */
    public void lockClicked(View view) {
        lockImg.startAnimation(animAlpha);
        if(locked) {
            lockImg.setImageResource(R.drawable.unlocked);
            locked = false;
        } else {
            lockImg.setImageResource(R.drawable.locked);
            locked = true;
        }
    }

    /**
     * opens location activity and calls on stopRepeatingTask
     * @param view view
     */
    public void locationClicked(View view) {
        startActivity(new Intent(MainActivity.this, LocationActivity.class));
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }

    /**
     * stops the "engine" to the car, and changes the lock icon
     */
    public void emergencyStopClick(View view) {
        stopImg.startAnimation(animAlpha);
        lockImg.setImageResource(R.drawable.locked);
        locked = true;
    }
    /**
     * calls the server and changes battery icon based on how much voltage is left
     */
    public void drawBattery(BatteryObj batteryObj) {
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
    }

    /**
     * calls server to change status of lights
     * @param view view
     */
    public void lightsClicked(View view) {
        lightsImg.startAnimation(animAlpha);

        if (lightsOn) {
            lightsImg.setAlpha(0.1f);
            lightsOn = false;
        } else {
            lightsImg.setAlpha(1f);
            lightsOn = true;
        }
    }

    /**
     * Opens a dialog view and ask to enter ip address if you get a response code 200 connection is
     * established else it fails.
     * @param view connect
     */
    public void connectOnClicked(View view) {
        connectionView = getLayoutInflater().inflate(R.layout.server_connect, null);
        AlertDialog.Builder connection = new AlertDialog.Builder(this);
        Button connectNowButton = connectionView.findViewById(R.id.connectNowButton);
        connection.setView(connectionView);
        final AlertDialog dialog = connection.create();
        ipNr = connectionView.findViewById(R.id.ipAddressEditText);

        if(true) {
            connectNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ip = ipNr.getText().toString();
                    dialog.cancel();
                    viewModel.connectToIp(ip);
                }
            });
            dialog.show();
        }else {
            connectButton.setText("Connect");
            currentSpeed.setText("0");
        }
    }

    // Create the observer which updates the UI.
    final Observer<WheelSpeedObj> speedObserver = new Observer<WheelSpeedObj>() {
        @Override
        public void onChanged(@Nullable final WheelSpeedObj wheelSpeedObj) {
            // Update the UI, in this case, a TextView.
            currentSpeed.setText(wheelSpeedObj.totalSpeed);
        }
    };

    // Create the observer which updates the UI.
    final Observer<BatteryObj> batteryObserver = new Observer<BatteryObj>() {
        @Override
        public void onChanged(@Nullable final BatteryObj batteryObj) {
            // Update the UI, in this case, a TextView.
            drawBattery(batteryObj);
        }
    };

    @Override
    public void onClick(ListObjIcon listObj) {
        Intent intent = null;
        switch (listObj.id) {
            case ListIDs.CAR_INFO_ID:
                intent = new Intent(this, CarDataActivity.class);
                break;
            case ListIDs.CLIMATE_ID:
                intent = new Intent(this, ClimateActivity.class);
                break;
            case ListIDs.BATTERY_ID:
                intent = new Intent(this, BatteryActivity.class);
                break;
            case ListIDs.LOCATION_ID:
                intent = new Intent(this, LocationActivity.class);
                break;
            case ListIDs.CONTROLS_ID:
                intent = new Intent(this, ControlsActivity.class);
                break;
            case ListIDs.DIAGNOSTIC_ID:
                intent = new Intent(this, DiagnosticsActivity.class);
                break;
            case ListIDs.ENGINEERING_ID:
                intent = new Intent(this, DeveloperActivity.class);
                break;
        }
        if (intent != null)
            setExtraIntent(intent, listObj);
        startActivity(intent);
    }

    private void setExtraIntent(Intent intent, ListObjIcon listObjIcon){
        String json = GsonConv.toJson(listObjIcon);
        intent.putExtra(HEADER, json);
    }

}
