package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.TeachersModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;


public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.ViewHolder> {
    public Context context;
    private List<TeachersModel> teachersModelList;

    public TeachersAdapter(Context context, List<TeachersModel> teachersModelList) {
        this.context = context;
        this.teachersModelList = teachersModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachers_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeachersModel teachersModel = teachersModelList.get(position);
        holder.name.setText(teachersModel.getTeacherName());
        holder.available.setText(teachersModel.getAvailability());
        holder.specification.setText(teachersModel.getSpecialization());
        holder.seating.setText(teachersModel.getSeatingRoom());
    }

    @Override
    public int getItemCount() {
        return teachersModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, seating, specification, available;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teacher);
            seating = itemView.findViewById(R.id.seating);
            specification = itemView.findViewById(R.id.specialisation);
            available = itemView.findViewById(R.id.availability);
        }
    }
}
