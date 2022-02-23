package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.AssignmentModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {
    public Context context;
    private List<AssignmentModel> assignmentModelList;

    public AssignmentAdapter(Context context, List<AssignmentModel> assignmentModelList) {
        this.context = context;
        this.assignmentModelList = assignmentModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssignmentModel assignmentModel = assignmentModelList.get(position);
        holder.type.setText(assignmentModel.getAssignmentType());
        holder.date.setText(assignmentModel.getUploadedTime());
        holder.assignedBy.setText(assignmentModel.getTeacherName());
        holder.heading.setText(assignmentModel.getSubject());
        holder.deadline.setText("Deadline: "+assignmentModel.getDeadline());
        holder.course.setText(assignmentModel.getSubject());
        holder.content.setText(assignmentModel.getContent());
    }

    @Override
    public int getItemCount() {
        return assignmentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, date, assignedBy, course,deadline, heading, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            date = itemView.findViewById(R.id.date);
            assignedBy = itemView.findViewById(R.id.assignedBy);
            content = itemView.findViewById(R.id.content);
            course = itemView.findViewById(R.id.course);
            deadline = itemView.findViewById(R.id.deadline);
            heading = itemView.findViewById(R.id.heading);

        }
    }
}
