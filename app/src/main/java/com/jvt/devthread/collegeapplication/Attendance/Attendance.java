package com.jvt.devthread.collegeapplication.Attendance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Adapters.AttendanceAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.AttendanceModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentAttendanceBinding;

import java.util.ArrayList;
import java.util.List;

public class Attendance extends Fragment {
    private FragmentAttendanceBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<AttendanceModel> attendanceModelList = new ArrayList<>();
    String uid,section;
    String regId;
    DatabaseReference databaseReferenceT,databaseReferenceTwo;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater,container,false);
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
                    loadData(section,regId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData(String section, String regId) {
        binding.attendanceRecycler.setHasFixedSize(true);
        binding.attendanceRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("Attendance").child(section).child(regId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    attendanceModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        AttendanceModel attendanceModel = dataSnapshot.getValue(AttendanceModel.class);
                        attendanceModelList.add(attendanceModel);
                    }
                    AttendanceAdapter attendanceAdapter = new AttendanceAdapter(getContext(),attendanceModelList);
                    binding.attendanceRecycler.setAdapter(attendanceAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}