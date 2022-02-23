package com.jvt.devthread.collegeapplication.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentMyProfileBinding;

public class MyProfile extends Fragment {
    private FragmentMyProfileBinding binding;
    String uid,section;
    String regId;
    DatabaseReference databaseReferenceT,databaseReferenceTwoR;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferenceT = FirebaseDatabase.getInstance().getReference();
        databaseReferenceTwoR = FirebaseDatabase.getInstance().getReference();
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
        return view;
    }

    private void loadData(String regId) {
        databaseReferenceTwoR.child("CollegeStudentsInfo").child(regId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String admissionSession = snapshot.child("admissionSession").getValue().toString();
                    String contactNo = snapshot.child("contactNo").getValue().toString();
                    String correspondenceAddress = snapshot.child("correspondenceAddress").getValue().toString();
                    String dob = snapshot.child("dob").getValue().toString();
                    String emailId = snapshot.child("emailId").getValue().toString();
                    String fatherName = snapshot.child("fatherName").getValue().toString();
                    String gender = snapshot.child("gender").getValue().toString();
                    String motherName = snapshot.child("motherName").getValue().toString();
                    String permanentAddress = snapshot.child("permanentAddress").getValue().toString();
                    String section = snapshot.child("section").getValue().toString();
                    String studentBranch = snapshot.child("studentBranch").getValue().toString();
                    String studentName = snapshot.child("studentName").getValue().toString();
                    String studentRegNo = snapshot.child("studentRegNo").getValue().toString();
                    binding.father.setText(fatherName);
                    binding.mother.setText(motherName);
                    binding.pa.setText(permanentAddress);
                    binding.ca.setText(correspondenceAddress);
                    binding.dob.setText(dob);
                    binding.contact.setText(contactNo);
                    binding.email.setText(emailId);
                    binding.gender.setText(gender);
                    binding.admission.setText(admissionSession);
                    binding.section.setText(section);
                    binding.course.setText(studentBranch);
                    binding.user.setText(studentName);
                    binding.regId.setText(studentRegNo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}