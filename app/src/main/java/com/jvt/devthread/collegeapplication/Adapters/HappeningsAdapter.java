package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jvt.devthread.collegeapplication.Models.HappeningsModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class HappeningsAdapter extends RecyclerView.Adapter<HappeningsAdapter.ViewHolder> {
    public Context context;
    private List<HappeningsModel> happeningsModelList;

    public HappeningsAdapter(Context context, List<HappeningsModel> happeningsModelList) {
        this.context = context;
        this.happeningsModelList = happeningsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.happenings_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HappeningsModel happeningsModel = happeningsModelList.get(position);
        Glide.with(context).load(happeningsModel.getImage()).into(holder.image);
        holder.head.setText(happeningsModel.getHeading());

    }

    @Override
    public int getItemCount() {
        return happeningsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView head;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            head = itemView.findViewById(R.id.heading);
        }
    }
}
