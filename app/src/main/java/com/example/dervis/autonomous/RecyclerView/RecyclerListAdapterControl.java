package com.example.dervis.autonomous.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Objects.ListObjControl;
import com.example.dervis.autonomous.R;
import com.suke.widget.SwitchButton;

import java.util.List;

public class RecyclerListAdapterControl extends RecyclerView.Adapter<RecyclerListAdapterControl.MyViewHolder> {

    private List<ListObjControl> listObjControls;

    public RecyclerListAdapterControl(List<ListObjControl> listObjControls) {
        this.listObjControls = listObjControls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_cell_control, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListObjControl listObj = listObjControls.get(position);
        holder.iconIV.setImageDrawable(listObj.icon);
        holder.titleTV.setText(listObj.title);
        holder.descriptionTV.setText(listObj.getDescription());
        holder.onOffSB.setChecked(listObj.isSwitchOn());
    }

    @Override
    public int getItemCount() {
        return listObjControls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements SwitchButton.OnClickListener, SwitchButton.OnCheckedChangeListener {
        ImageView iconIV;
        TextView titleTV;
        TextView descriptionTV;
        SwitchButton onOffSB;

        public MyViewHolder(View itemView) {
            super(itemView);
            iconIV = itemView.findViewById(R.id.iconDiaImageView);
            titleTV = itemView.findViewById(R.id.diagnosticTextView);
            descriptionTV = itemView.findViewById(R.id.descriptionControlTextView);
            onOffSB = itemView.findViewById(R.id.controlSwitchButton);
            onOffSB.setOnCheckedChangeListener(this);
            onOffSB.setOnTouchListener(null);
        }

        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            ListObjControl listObj = listObjControls.get(getAdapterPosition());
            if(isChecked != listObj.isSwitchOn()) {
                listObj.setSwitchOn(isChecked);
                notifyItemChanged(getAdapterPosition());
            }
        }

        @Override
        public void onClick(View view) {
            ListObjControl listObj = listObjControls.get(getAdapterPosition());
            boolean isOn = !listObj.isSwitchOn();
            listObj.setSwitchOn(isOn);
            notifyItemChanged(getAdapterPosition());
        }
    }
}
