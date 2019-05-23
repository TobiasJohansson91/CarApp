package com.example.dervis.autonomous.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Helpers.GsonConv;
import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.Objects.ListObjTimer;
import com.example.dervis.autonomous.Objects.TimeObject;
import com.example.dervis.autonomous.R;
import com.example.dervis.autonomous.RecyclerView.RecyclerListAdapterTimer;

import java.util.ArrayList;

public class ClimateActivity extends AppCompatActivity implements RecyclerListAdapterTimer.IOnClickListener {

    RecyclerView listView;
    RecyclerListAdapterTimer listAdapter;

    ArrayList<ListObjTimer> tempList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate);
        setHeaderText();

        tempList.add(new ListObjTimer(TimeObject.MONDAY, 8, 5, false, 22));
        tempList.add(new ListObjTimer(TimeObject.FRIDAY, 21, 14, true, 23));
        tempList.add(new ListObjTimer(TimeObject.WEDNESDAY, 5, 25, true, 24));
        tempList.add(new ListObjTimer(TimeObject.SUNDAY, 12, 35, true, 25));

        listView = findViewById(R.id.recyclerViewClimate);
        listAdapter = new RecyclerListAdapterTimer(tempList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(listAdapter);
    }

    private void setHeaderText() {
        String json = getIntent().getStringExtra(MainActivity.HEADER);
        ListObjIcon obj = GsonConv.toObject(json);
        ((TextView)findViewById(R.id.activityTitle)).setText(obj.title);
        ((TextView) findViewById(R.id.activityDescription)).setText(obj.description);
        ((ImageView) findViewById(R.id.activityIcon)).setImageDrawable(ResourceGetter.getDrawable(obj.iconId));
    }

    public void clickBackArrow(View view) {
        finish();
        overridePendingTransition(R.anim.enter_back_anim, R.anim.exit_back_anim);
    }

    @Override
    public void onListClick(ListObjTimer listObjTimer) {
        //Start popup to edit existing alarm
    }

    public void clickCreateAlarm(View view){
        //Start popup to create new alarm
    }
}
