package com.jvt.devthread.collegeapplication.RMS;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Adapters.RmsAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.RmsModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentRmsBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Rms extends Fragment {

   private FragmentRmsBinding binding;
   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
   private List<RmsModel> rmsModelList = new ArrayList<>();
   FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
   String uid,regId;
    public static Fragment activeFragment;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    DatabaseReference databaseReferenceT = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRmsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReferenceT.child("StudentsFid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(uid)){
                    regId = snapshot.child(uid).getValue().toString();
                    Common.regId = regId;
                    loadData(regId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.raiseRms.setOnClickListener(view1 -> {
            Fragment fragment = new RmsTerms();
            loadFragment(fragment,"RmsTerms");
        });
        return view;
    }

    private void loadData(String regId) {
        binding.rmsRecycler.setHasFixedSize(true);
        binding.rmsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("CollegeStudentsInfo").child(regId).child("RmsRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    rmsModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        RmsModel rmsModel = dataSnapshot.getValue(RmsModel.class);
                        rmsModelList.add(rmsModel);
                    }
                    RmsAdapter rmsAdapter = new RmsAdapter(getContext(),rmsModelList);
                    binding.rmsRecycler.setAdapter(rmsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadFragment(Fragment fragment, String tag) {
        executorService.execute(() -> {
            if (fragment != null) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(() -> {
                activeFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            });
        });
    }
}