package com.jvt.devthread.collegeapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.collegeapplication.Announcement.AnnouncementDescription;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.AnnouncementModel;
import com.jvt.devthread.collegeapplication.R;

import org.w3c.dom.Text;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {
    public Context context;
    private List<AnnouncementModel> announcementModelList;

    public AnnouncementAdapter(Context context, List<AnnouncementModel> announcementModelList) {
        this.context = context;
        this.announcementModelList = announcementModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnnouncementModel announcementModel = announcementModelList.get(position);
        holder.department.setText(announcementModel.getDepartment());
        holder.date.setText(announcementModel.getDate());
        holder.heading.setText(announcementModel.getHeading());
        holder.itemView.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new AnnouncementDescription();
            Common.aDate = announcementModelList.get(position).getDate();
            Common.aHead = announcementModelList.get(position).getHeading();
            Common.aContent = announcementModelList.get(position).getContent();
            Common.aUploaded = announcementModelList.get(position).getUploadedBy();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment)
                    .addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return announcementModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView department, date, heading;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            department=itemView.findViewById(R.id.department);
            date = itemView.findViewById(R.id.date);
            heading = itemView.findViewById(R.id.heading);
        }
    }
}
