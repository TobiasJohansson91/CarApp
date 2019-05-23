package com.example.dervis.autonomous.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dervis.autonomous.Objects.ListObjTimer;
import com.example.dervis.autonomous.R;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;

public class RecyclerListAdapterTimer extends RecyclerView.Adapter<RecyclerListAdapterTimer.MyViewHolder> {

    private ArrayList<ListObjTimer> listObjs;
    private IOnClickListener iOnClickListener;

    public RecyclerListAdapterTimer(ArrayList<ListObjTimer> listObjs, IOnClickListener iOnClickListener) {
        this.listObjs = listObjs;
        this.iOnClickListener = iOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_list_cell_timer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListObjTimer obj = listObjs.get(position);
        holder.timerText.setText(obj.toString());
        holder.switchButton.setChecked(obj.isAlarmOn());
    }

    @Override
    public int getItemCount() {
        return listObjs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SwitchButton.OnCheckedChangeListener {

        TextView timerText;
        SwitchButton switchButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            timerText = itemView.findViewById(R.id.timeTextView);
            switchButton = itemView.findViewById(R.id.controlSwitchButton);
            switchButton.setOnCheckedChangeListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ListObjTimer listObjTimer = listObjs.get(getAdapterPosition());
            listObjTimer.setPosition(getAdapterPosition());
            iOnClickListener.onListClick(listObjTimer);
        }

        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            listObjs.get(getAdapterPosition()).setAlarmOn(isChecked);
        }
    }

    public interface IOnClickListener{
        public void onListClick(ListObjTimer listObjTimer);
    }
}
