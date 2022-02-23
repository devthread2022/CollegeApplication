package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.AttendanceModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    public Context context;
    private List<AttendanceModel> attendanceModelList;

    public AttendanceAdapter(Context context, List<AttendanceModel> attendanceModelList) {
        this.context = context;
        this.attendanceModelList = attendanceModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AttendanceModel attendanceModel = attendanceModelList.get(position);
        holder.course.setText(attendanceModel.getCourseName());
        holder.teacher.setText("Faculty: "+attendanceModel.getFaculty());
        holder.seating.setText("Seating: "+attendanceModel.getFacultySeating());
        holder.attended.setText("Attended: "+attendanceModel.getAttended());
        holder.delivered.setText("Delivered: "+attendanceModel.getDelivered());
        holder.section.setText("Section: "+attendanceModel.getSectionName());
        holder.roll.setText("R.No: "+attendanceModel.getRollNumber());

        int atten = Integer.parseInt(attendanceModelList.get(position).getAttended());
        int deliver = Integer.parseInt(attendanceModelList.get(position).getDelivered());
        double perce = (atten / deliver)*100;
        holder.percent.setText(perce+"%");

    }

    @Override
    public int getItemCount() {
        return attendanceModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course, teacher, seating, attended, delivered, percent, section, roll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course);
            teacher = itemView.findViewById(R.id.teacher);
            seating = itemView.findViewById(R.id.seating);
            attended = itemView.findViewById(R.id.attended);
            delivered = itemView.findViewById(R.id.delivered);
            percent = itemView.findViewById(R.id.percent);
            section = itemView.findViewById(R.id.section);
            roll = itemView.findViewById(R.id.roll);
        }
    }
}
