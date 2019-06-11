package com.example.dervis.autonomous.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Constants.ListIDs;
import com.example.dervis.autonomous.Constants.ListItems;
import com.example.dervis.autonomous.Constants.SocketObjects;
import com.example.dervis.autonomous.Helpers.GsonConv;
import com.example.dervis.autonomous.Objects.BatteryObj;
import com.example.dervis.autonomous.Objects.ListObjIcon;
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
    ImageView batteryStatus;
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
        viewModel.startDataGathering(SocketObjects.MAIN_SOCKETOBJ_LIST);
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getBattery().observe(this, batteryObserver);
    }

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
        int procentBattery = (int) batteryLeft*100;
        TextView batteryProcent = findViewById(R.id.batteryProcentTextView);
        batteryProcent.setText("" + procentBattery + "%");
    }

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

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.killSubscriberThreads();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.startDataGathering(SocketObjects.MAIN_SOCKETOBJ_LIST);
    }
}
