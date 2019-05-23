package com.example.dervis.autonomous.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;

import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.MyViewHolder> {
    private IOnClickListener iOnClickListener;
    private List<ListObjIcon> listObjs;

    public RecyclerListAdapter(IOnClickListener iOnClickListener, List<ListObjIcon> listObjs) {
        this.iOnClickListener = iOnClickListener;
        this.listObjs = listObjs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_cell, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListObjIcon listObj = listObjs.get(position);
        holder.titleTV.setText(listObj.title);
        holder.descriptionTV.setText(listObj.description);
        holder.iconIV.setImageDrawable(listObj.icon);
    }

    @Override
    public int getItemCount() {
        return listObjs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTV;
        public TextView descriptionTV;
        public ImageView iconIV;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.diagnosticTextView);
            descriptionTV = itemView.findViewById(R.id.descriptionTextView);
            iconIV = itemView.findViewById(R.id.iconDiaImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iOnClickListener.onClick(listObjs.get(getAdapterPosition()));
        }
    }

    public interface IOnClickListener {
        public void onClick(ListObjIcon listObj);
    }
}
