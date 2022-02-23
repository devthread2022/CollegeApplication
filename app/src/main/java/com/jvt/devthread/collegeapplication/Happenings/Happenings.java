package com.jvt.devthread.collegeapplication.Happenings;

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
import com.jvt.devthread.collegeapplication.Adapters.HappeningsAdapter;
import com.jvt.devthread.collegeapplication.Models.HappeningsModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentHappeningsBinding;

import java.util.ArrayList;
import java.util.List;

public class Happenings extends Fragment {

    private FragmentHappeningsBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<HappeningsModel> happeningsModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHappeningsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.happeningsRecycler.setHasFixedSize(true);
        binding.happeningsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("Happenings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    happeningsModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        HappeningsModel happeningsModel = dataSnapshot.getValue(HappeningsModel.class);
                        happeningsModelList.add(happeningsModel);
                    }
                    HappeningsAdapter happeningsAdapter = new HappeningsAdapter(getContext(),happeningsModelList);
                    binding.happeningsRecycler.setAdapter(happeningsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}