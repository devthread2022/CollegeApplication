package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.ExamModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    public Context context;
    private List<ExamModel> examModelList;

    public ExamAdapter(Context context, List<ExamModel> examModelList) {
        this.context = context;
        this.examModelList = examModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExamModel examModel = examModelList.get(position);
        holder.room.setText("Room: "+examModel.getRoomNumber());
        holder.time.setText("Time: "+examModel.getTime());
        holder.date.setText("Date: "+examModel.getDate());
        holder.course.setText(examModel.getCourseCode());

    }

    @Override
    public int getItemCount() {
        return examModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course, date, time, room;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            room = itemView.findViewById(R.id.room);
        }
    }
}
