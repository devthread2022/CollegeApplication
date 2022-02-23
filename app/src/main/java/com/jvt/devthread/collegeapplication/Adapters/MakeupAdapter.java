package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Models.MakeupModel;
import com.jvt.devthread.collegeapplication.R;

import java.util.List;

public class MakeupAdapter extends RecyclerView.Adapter<MakeupAdapter.ViewHolder> {
    public Context context;
    private List<MakeupModel> makeupModelList;

    public MakeupAdapter(Context context, List<MakeupModel> makeupModelList) {
        this.context = context;
        this.makeupModelList = makeupModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.makeup_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MakeupModel makeupModel = makeupModelList.get(position);
        holder.time.setText("Time: "+makeupModel.getTime());
        holder.faculty.setText("Faculty: "+makeupModel.getTakenBy());
        holder.course.setText(makeupModel.getCourseCode());
    }

    @Override
    public int getItemCount() {
        return makeupModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course, faculty, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course);
            faculty = itemView.findViewById(R.id.taken);
            time = itemView.findViewById(R.id.time);
        }
    }
}
