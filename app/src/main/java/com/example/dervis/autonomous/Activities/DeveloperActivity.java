package com.example.dervis.autonomous.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Constants.ListIDs;
import com.example.dervis.autonomous.Constants.ListItems;
import com.example.dervis.autonomous.Helpers.GsonConv;
import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;
import com.example.dervis.autonomous.RecyclerView.RecyclerListAdapter;
import com.example.dervis.autonomous.ViewModels.MainViewModel;

/**
 * this class shows errors (not used at the moment)
 */
public class DeveloperActivity extends AppCompatActivity implements RecyclerListAdapter.IOnClickListener {

    RecyclerView listView;
    RecyclerListAdapter listAdapter;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setHeaderText();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        findViewById(R.id.connectCarImageButton).setVisibility(View.VISIBLE);
        listView = findViewById(R.id.recyclerList);
        listAdapter = new RecyclerListAdapter(this, ListItems.objListEngineering);
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
    public void onClick(ListObjIcon listObj) {
        Intent intent = null;
        switch(listObj.id){
            case (ListIDs.REMOTE_ID):
                intent = new Intent(this, VideoActivity.class);
                break;
            case (ListIDs.DASHBOARD_ID):
                break;
            case (ListIDs.DEVELOPER_ID):
                intent = new Intent(this, EngineeringDiagnosticActivity.class);
                break;
        }
        if (intent != null) {
            setExtraIntent(intent, listObj);
            startActivity(intent);
        }
    }

    private void setExtraIntent(Intent intent, ListObjIcon listObjIcon){
        String json = GsonConv.toJson(listObjIcon);
        intent.putExtra(MainActivity.HEADER, json);
    }

    public void clickConnectCar(View view){
        final View connectionView = getLayoutInflater().inflate(R.layout.server_connect, null);
        AlertDialog.Builder connection = new AlertDialog.Builder(this);
        Button connectNowButton = connectionView.findViewById(R.id.connectNowButton);
        connection.setView(connectionView);
        final AlertDialog dialog = connection.create();

        connectNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ((TextView) connectionView.findViewById(R.id.ipAddressEditText)).getText().toString();
                dialog.cancel();
                viewModel.connectToIp(ip);
            }
        });
        dialog.show();
    }
}
