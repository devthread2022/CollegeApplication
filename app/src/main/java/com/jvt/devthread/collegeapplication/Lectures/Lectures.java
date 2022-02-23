package com.jvt.devthread.collegeapplication.Lectures;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Adapters.LecturesAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.LecturesModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentLecturesBinding;

import java.util.ArrayList;
import java.util.List;

public class Lectures extends Fragment {
    private FragmentLecturesBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<LecturesModel> lecturesModelList = new ArrayList<>();
    String uid,section;
    String regId;
    DatabaseReference databaseReferenceT,databaseReferenceTwo;
    FirebaseAuth firebaseAuth;
    String day = "Monday";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLecturesBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferenceT = FirebaseDatabase.getInstance().getReference();
        databaseReferenceTwo = FirebaseDatabase.getInstance().getReference();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("StudentsFid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(uid)){
                    regId = snapshot.child(uid).getValue().toString();
                    Common.regId = regId;
                    loadSection(regId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    private void loadSection(String regId) {
        databaseReferenceTwo.child("CollegeStudentsInfo").child(regId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    section = snapshot.child("section").getValue().toString();
                    Common.section = section;
                    loadData(section);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData(String section) {
        binding.lectureRecycler.setHasFixedSize(true);
        binding.lectureRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        switch (day){
            case "Monday":
                binding.mon.setOnClickListener(view -> {
                    day = "Monday";
                });
                break;
            case "Tuesday":
                binding.tue.setOnClickListener(view -> {
                    day = "Tuesday";
                });
                break;
            case "Wednesday":
                binding.wed.setOnClickListener(view -> {
                    day = "Wednesday";
                });
                break;
            case "Thursday":
                binding.thu.setOnClickListener(view -> {
                    day = "Thursday";
                });
                break;
            case "Friday":
                binding.fri.setOnClickListener(view -> {
                    day = "Friday";
                });
                break;
            case "Saturday":
                binding.sat.setOnClickListener(view -> {
                    day = "Saturday";
                });
                break;
        }
        databaseReference.child("Lectures").child(section).child(day).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    lecturesModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        LecturesModel lecturesModel = dataSnapshot.getValue(LecturesModel.class);
                        lecturesModelList.add(lecturesModel);
                    }
                    LecturesAdapter lecturesAdapter = new LecturesAdapter(getContext(),lecturesModelList);
                    binding.lectureRecycler.setAdapter(lecturesAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}