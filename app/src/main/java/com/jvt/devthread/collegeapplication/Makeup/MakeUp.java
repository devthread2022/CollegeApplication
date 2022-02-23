package com.jvt.devthread.collegeapplication.Makeup;

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
import com.jvt.devthread.collegeapplication.Adapters.MakeupAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.MakeupModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentMakeUpBinding;

import java.util.ArrayList;
import java.util.List;

public class MakeUp extends Fragment {
    private FragmentMakeUpBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<MakeupModel> makeupModelList = new ArrayList<>();
    String uid,section;
    String regId;
    DatabaseReference databaseReferenceT,databaseReferenceTwo;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMakeUpBinding.inflate(inflater,container,false);
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
        binding.makeupRecycler.setHasFixedSize(true);
        binding.makeupRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("MakeUps").child(section).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    makeupModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        MakeupModel makeupModel = dataSnapshot.getValue(MakeupModel.class);
                        makeupModelList.add(makeupModel);
                    }
                    MakeupAdapter makeupAdapter = new MakeupAdapter(getContext(),makeupModelList);
                    binding.makeupRecycler.setAdapter(makeupAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}