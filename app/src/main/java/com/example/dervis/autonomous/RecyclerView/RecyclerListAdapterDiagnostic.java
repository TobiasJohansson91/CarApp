package com.example.dervis.autonomous.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;

import java.util.List;

public class RecyclerListAdapterDiagnostic extends RecyclerView.Adapter<RecyclerListAdapterDiagnostic.MyViewHolder> {

    private List<ListObjIcon> listObjIcons;

    public RecyclerListAdapterDiagnostic(List<ListObjIcon> listObjIcons) {
        this.listObjIcons = listObjIcons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_cell_diagnostics, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListObjIcon listObjIcon = listObjIcons.get(position);
        holder.titleTV.setText(listObjIcon.title);
        holder.iconIV.setImageDrawable(listObjIcon.icon);
        holder.checkIV.setImageDrawable(ResourceGetter.getDrawable(R.drawable.icon_check_24px));
    }

    @Override
    public int getItemCount() {
        return listObjIcons.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTV;
        public ImageView iconIV;
        public ImageView checkIV;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.diagnosticTextView);
            iconIV = itemView.findViewById(R.id.iconDiaImageView);
            checkIV = itemView.findViewById(R.id.checkImageView);
        }
    }
}
