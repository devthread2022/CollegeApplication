package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.RmsModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class RmsAdapter extends RecyclerView.Adapter<RmsAdapter.ViewHolder> {
    public Context context;
    private List<RmsModel> rmsModelList;

    public RmsAdapter(Context context, List<RmsModel> rmsModelList) {
        this.context = context;
        this.rmsModelList = rmsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rms_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RmsModel rmsModel = rmsModelList.get(position);
        holder.status.setText(rmsModel.getStatus());
        holder.date.setText(rmsModel.getDate());
        holder.content.setText(rmsModel.getContent());
        holder.category.setText(rmsModel.getCategory());
    }

    @Override
    public int getItemCount() {
        return rmsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, date, category, content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            status = itemView.findViewById(R.id.status);
            category = itemView.findViewById(R.id.Category);
            content = itemView.findViewById(R.id.content);
        }
    }
}
