package com.jvt.devthread.collegeapplication.Assignments;

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
import com.jvt.devthread.collegeapplication.Adapters.AssignmentAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.AssignmentModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentAssignmentBinding;

import java.util.ArrayList;
import java.util.List;

public class Assignment extends Fragment {
    private FragmentAssignmentBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReferenceTwo = FirebaseDatabase.getInstance().getReference();
    private List<AssignmentModel> assignmentModelList = new ArrayList<>();
    String uid,section;
    String regId;
    DatabaseReference databaseReferenceT,databaseReferenceTwoR;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAssignmentBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferenceT = FirebaseDatabase.getInstance().getReference();
        databaseReferenceTwoR = FirebaseDatabase.getInstance().getReference();
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
        binding.assignmentRecycler.setHasFixedSize(true);
        binding.assignmentRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));;
        databaseReference.child("Assignments").child(section).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    assignmentModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        AssignmentModel assignmentModel = dataSnapshot.getValue(AssignmentModel.class);
                        assignmentModelList.add(assignmentModel);
                    }
                    AssignmentAdapter assignmentAdapter = new AssignmentAdapter(getContext(), assignmentModelList);
                    binding.assignmentRecycler.setAdapter(assignmentAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}