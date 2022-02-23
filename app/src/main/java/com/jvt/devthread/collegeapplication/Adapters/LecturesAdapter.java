package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.LecturesModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;


public class LecturesAdapter extends RecyclerView.Adapter<LecturesAdapter.ViewHolder> {
    public Context context;
    private List<LecturesModel> lecturesModelList;

    public LecturesAdapter(Context context, List<LecturesModel> lecturesModelList) {
        this.context = context;
        this.lecturesModelList = lecturesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LecturesModel lecturesModel = lecturesModelList.get(position);
        holder.time.setText(lecturesModel.getTime());
        holder.course.setText(lecturesModel.getCourseCode());
        holder.room.setText(lecturesModel.getRoomNumber());
        holder.section.setText(lecturesModel.getSection());

    }

    @Override
    public int getItemCount() {
        return lecturesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time, course, room, section;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            course = itemView.findViewById(R.id.course);
            room = itemView.findViewById(R.id.room);
            section = itemView.findViewById(R.id.section);
        }
    }
}
