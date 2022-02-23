package com.jvt.devthread.collegeapplication.Dashboard;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Adapters.LecturesAdapter;
import com.jvt.devthread.collegeapplication.Adapters.PagerAdapter;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Gallery.Gallery;
import com.jvt.devthread.collegeapplication.Happenings.Happenings;
import com.jvt.devthread.collegeapplication.Messages.MyMessages;
import com.jvt.devthread.collegeapplication.Models.LecturesModel;
import com.jvt.devthread.collegeapplication.Profile.MyProfile;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.RMS.Rms;
import com.jvt.devthread.collegeapplication.databinding.FragmentDashboardBinding;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dashboard extends Fragment {
    private FragmentDashboardBinding binding;
    DatabaseReference databaseReference,databaseReferenceTwo;
    FirebaseAuth firebaseAuth;
    String uid,section;
    String regId,day;
    Dialog dialog;
    ImageView close;
    TextView myProfile,gallery,logout,user, userReg;
    public static Fragment activeFragment;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    private List<LecturesModel> lecturesModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDashboardBinding.inflate(inflater,container,false);
       View view = binding.getRoot();
        SimpleDateFormat simpleformat = new SimpleDateFormat("dd/MMMM/yyyy hh:mm:s");
        Format f = new SimpleDateFormat("EEEE");
        day = f.format(new Date());
        firebaseAuth = FirebaseAuth.getInstance();
       databaseReference = FirebaseDatabase.getInstance().getReference();
       databaseReferenceTwo = FirebaseDatabase.getInstance().getReference();
       uid = firebaseAuth.getCurrentUser().getUid();
       databaseReference.child("StudentsFid").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.hasChild(uid)){
                   regId = snapshot.child(uid).getValue().toString();
                   Common.regId = regId;
                   loadSection(regId,day);
                   userReg.setText(regId);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.menu_options_layout);
        close = dialog.findViewById(R.id.cancel);
        myProfile = dialog.findViewById(R.id.profile);
        gallery = dialog.findViewById(R.id.gallery);
        logout = dialog.findViewById(R.id.logout);
        user = dialog.findViewById(R.id.user);
        userReg = dialog.findViewById(R.id.regnumber);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        binding.menu.setOnClickListener(view1 -> {
            dialog.show();
        });
        close.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        myProfile.setOnClickListener(view1 -> {
            dialog.dismiss();
            Fragment fragment = new MyProfile();
            loadFragment(fragment,"MyProfile");
        });
        gallery.setOnClickListener(view1 -> {
            dialog.dismiss();
            Fragment fragment = new Gallery();
            loadFragment(fragment,"Gallery");
        });
        binding.dashboard.setOnClickListener(view1 -> {
            Fragment fragment = new Dashboard();
            loadFragment(fragment,"Dashboard");
        });
        binding.rms.setOnClickListener(view1 -> {
            Fragment fragment = new Rms();
            loadFragment(fragment,"Rms");
        });
        binding.gallery.setOnClickListener(view1 -> {
            Fragment fragment = new Gallery();
            loadFragment(fragment,"Gallery");
        });
        binding.happenings.setOnClickListener(view1 -> {
            Fragment fragment = new Happenings();
            loadFragment(fragment,"Happenings");
        });

       initviews();
       return view;
    }

    private void loadSection(String regId, String day) {
        databaseReferenceTwo.child("CollegeStudentsInfo").child(regId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    section = snapshot.child("section").getValue().toString();
                    String usern = snapshot.child("studentName").getValue().toString();
                    user.setText(usern);
                    Common.section = section;
                    loadLectures(regId,section,day);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadLectures(String regId, String section, String day) {
        binding.lectureRecycler.setHasFixedSize(true);
        binding.lectureRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference();
        databaseReference3.child("Lectures").child(section).child(day).addValueEventListener(new ValueEventListener() {
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

    private void initviews() {
        setupViewPager(binding.pager);
        binding.tabLayout.setupWithViewPager(binding.pager);
    }
    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new DashboardOne(),"");
        pagerAdapter.addFragment(new DashboardTwo(),"");
        viewPager.setAdapter(pagerAdapter);
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