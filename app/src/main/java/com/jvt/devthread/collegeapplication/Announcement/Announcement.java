package com.jvt.devthread.collegeapplication.Announcement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Adapters.AnnouncementAdapter;
import com.jvt.devthread.collegeapplication.Models.AnnouncementModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentAnnouncementBinding;

import java.util.ArrayList;
import java.util.List;

public class Announcement extends Fragment {
    private FragmentAnnouncementBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<AnnouncementModel> announcementModelList = new ArrayList<>();
    AnnouncementAdapter announcementAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnnouncementBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.announcementRecycler.setHasFixedSize(true);
        binding.announcementRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("Announcements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    announcementModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        AnnouncementModel announcementModel = dataSnapshot.getValue(AnnouncementModel.class);
                        announcementModelList.add(announcementModel);
                    }
                    announcementAdapter = new AnnouncementAdapter(getContext(),announcementModelList);
                    binding.announcementRecycler.setAdapter(announcementAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}