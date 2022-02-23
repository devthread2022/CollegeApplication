package com.jvt.devthread.collegeapplication.Result;

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
import com.jvt.devthread.collegeapplication.Adapters.ResultAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.ResultModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentResultBinding;

import java.util.ArrayList;
import java.util.List;

public class Result extends Fragment {
    private FragmentResultBinding binding;
    DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference4,databaseReference5,databaseReference6,databaseReference7,databaseReference8;
    String uid,section;
    String regId;
    DatabaseReference databaseReferenceT,databaseReferenceTwoR;
    FirebaseAuth firebaseAuth;
    private List<ResultModel> resultModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferenceT = FirebaseDatabase.getInstance().getReference();
        databaseReferenceTwoR = FirebaseDatabase.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        databaseReference3 = FirebaseDatabase.getInstance().getReference();
        databaseReference4 = FirebaseDatabase.getInstance().getReference();
        databaseReference5 = FirebaseDatabase.getInstance().getReference();
        databaseReference6 = FirebaseDatabase.getInstance().getReference();
        databaseReference7 = FirebaseDatabase.getInstance().getReference();
        databaseReference8 = FirebaseDatabase.getInstance().getReference();


        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReferenceT.child("StudentsFid").addValueEventListener(new ValueEventListener() {
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
        databaseReferenceTwoR.child("CollegeStudentsInfo").child(regId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
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
        binding.t1Recycler.setHasFixedSize(true);
        binding.t1Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t2Recycler.setHasFixedSize(true);
        binding.t2Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t3Recycler.setHasFixedSize(true);
        binding.t3Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t4Recycler.setHasFixedSize(true);
        binding.t4Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t5Recycler.setHasFixedSize(true);
        binding.t5Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t6Recycler.setHasFixedSize(true);
        binding.t6Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t7Recycler.setHasFixedSize(true);
        binding.t7Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.t8Recycler.setHasFixedSize(true);
        binding.t8Recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference1.child("Results").child(section).child(regId).child("Term1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t1Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference2.child("Results").child(section).child(regId).child("Term2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t2Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference3.child("Results").child(section).child(regId).child("Term3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t3Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference4.child("Results").child(section).child(regId).child("Term4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t4Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference5.child("Results").child(section).child(regId).child("Term5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t5Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference6.child("Results").child(section).child(regId).child("Term6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t6Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference7.child("Results").child(section).child(regId).child("Term7").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t7Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference8.child("Results").child(section).child(regId).child("Term8").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    resultModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ResultModel resultModel = dataSnapshot.getValue(ResultModel.class);
                        resultModelList.add(resultModel);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(getContext(),resultModelList);
                    binding.t8Recycler.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}