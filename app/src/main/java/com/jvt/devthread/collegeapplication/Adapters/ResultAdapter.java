package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.ResultModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    public Context context;
    private List<ResultModel> resultModelList;

    public ResultAdapter(Context context, List<ResultModel> resultModelList) {
        this.context = context;
        this.resultModelList = resultModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultModel resultModel = resultModelList.get(position);
        holder.course.setText(resultModel.getCourse());
        holder.grade.setText(resultModel.getGrade());
    }

    @Override
    public int getItemCount() {
        return resultModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course, grade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
