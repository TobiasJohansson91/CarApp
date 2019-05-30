package com.example.dervis.autonomous.RecyclerView;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ConnectedSocketObj;
import com.example.dervis.autonomous.Objects.ListObjDiagnostics;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;

import java.util.List;

public class RecyclerListAdapterDiagnostic extends RecyclerView.Adapter<RecyclerListAdapterDiagnostic.MyViewHolder> {

    private List<ListObjDiagnostics> listObjDiagnostics;

    public RecyclerListAdapterDiagnostic(List<ListObjDiagnostics> listObjDiagnostics) {
        this.listObjDiagnostics = listObjDiagnostics;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_cell_diagnostics, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListObjDiagnostics listObj = listObjDiagnostics.get(position);
        Drawable connectedImage = listObj.isConnected() ? ResourceGetter.getDrawable(R.drawable.icon_check_24px) : ResourceGetter.getDrawable(R.drawable.icon_error_24px);
        holder.titleTV.setText(listObj.title);
        holder.iconIV.setImageDrawable(listObj.icon);
        holder.checkIV.setImageDrawable(connectedImage);
    }

    @Override
    public int getItemCount() {
        return listObjDiagnostics.size();
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

    public void updateView(ConnectedSocketObj connectedSocketObj){
        int index = getIndexBy(connectedSocketObj.getSocketName());
        listObjDiagnostics.get(index).setConnected(connectedSocketObj.isConnected());
        notifyItemChanged(index);
    }

    private int getIndexBy(String socketName){
        for (int i = 0; i < listObjDiagnostics.size(); i++) {
            if (listObjDiagnostics.get(i).socketName.equals(socketName))
                return i;
        }
        return -1;
    }
}
