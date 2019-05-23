package com.example.dervis.autonomous.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dervis.autonomous.Objects.ListObj;
import com.example.dervis.autonomous.R;

import java.util.List;

public class RecyclerListAdapterCarInfo extends RecyclerView.Adapter<RecyclerListAdapterCarInfo.MyViewHolder> {

    private List<ListObj> listObjects;

    public RecyclerListAdapterCarInfo(List<ListObj> listObjects) {
        this.listObjects = listObjects;
    }

    @NonNull
    @Override
    public RecyclerListAdapterCarInfo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_cell_carinfo, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerListAdapterCarInfo.MyViewHolder holder, int position) {
        ListObj listObj = listObjects.get(position);
        holder.titleTV.setText(listObj.title);
        holder.descriptionTV.setText(listObj.description);
    }

    @Override
    public int getItemCount() {
        return listObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public TextView descriptionTV;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.diagnosticTextView);
            descriptionTV = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
