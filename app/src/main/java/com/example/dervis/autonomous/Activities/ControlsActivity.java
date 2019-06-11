package com.example.dervis.autonomous.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Constants.Commands;
import com.example.dervis.autonomous.Constants.ListItems;
import com.example.dervis.autonomous.Helpers.GsonConv;
import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObjControl;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;
import com.example.dervis.autonomous.RecyclerView.RecyclerListAdapterControl;
import com.example.dervis.autonomous.ViewModels.MainViewModel;

public class ControlsActivity extends AppCompatActivity implements RecyclerListAdapterControl.IItemClick{

    RecyclerView listView;
    RecyclerListAdapterControl listAdapter;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
        setHeaderText();

        listView = findViewById(R.id.recyclerListControl);
        listAdapter = new RecyclerListAdapterControl(this, ListItems.objListControl);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(listAdapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.startCommandThread();
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
    public void itemClicked(ListObjControl listObjControl) {
        byte[] command = null;
        switch (listObjControl.id){
            case 520: //Power
                if (listObjControl.isSwitchOn())
                    command = Commands.ARM_MOTORS;
                else
                    command = Commands.DISARM_MOTORS;
                break;
            case 521: //Door
                break;
            case 522: //Hazard
                command = Commands.HAZARD_LIGHT;
                break;
            case 523: //Horn
                command = Commands.HONK_HORN;
                break;
            case 524: //Headlights
                break;
        }
        if (command!=null)
            viewModel.addCommand(command);
    }
}
