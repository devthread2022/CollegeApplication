package com.jvt.devthread.collegeapplication.RMS;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Models.RmsModel;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentRaiseRmsBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RaiseRms extends Fragment {
    private FragmentRaiseRmsBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String uid;
    String rmsId, date, department, category, content,status;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRaiseRmsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        uid = firebaseAuth.getCurrentUser().getUid();
        binding.submit.setOnClickListener(view1 -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Random random = new Random();
            int id = random.nextInt(99999)+10000;
            rmsId = String.valueOf(id);
            status = "Pending";
            date = dateTimeFormatter.format(now);
            department = binding.department.getSelectedItem().toString();
            category = binding.category.getSelectedItem().toString();
            content = binding.content.getText().toString();
            if (content.isEmpty()){
                Toast.makeText(getContext(), "Write description!", Toast.LENGTH_SHORT).show();
            }else if (department.equals("Select Department")){
                Toast.makeText(getContext(), "Select department!", Toast.LENGTH_SHORT).show();
            }else if (category.equals("Select Category")){
                Toast.makeText(getContext(), "Select category!", Toast.LENGTH_SHORT).show();
            }else {
                RmsModel rmsModel = new RmsModel(rmsId, date, department, category, content,status);
                databaseReference.child("CollegeStudentsInfo").child(Common.regId).child("RmsRequest").child(rmsId).setValue(rmsModel);
                Toast.makeText(getContext(), "Submitted!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}