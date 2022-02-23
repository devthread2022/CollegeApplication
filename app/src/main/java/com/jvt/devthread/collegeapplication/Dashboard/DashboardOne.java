package com.jvt.devthread.collegeapplication.Dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.Announcement.Announcement;
import com.jvt.devthread.collegeapplication.Assignments.Assignment;
import com.jvt.devthread.collegeapplication.Attendance.Attendance;
import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.Exam.Exams;
import com.jvt.devthread.collegeapplication.Lectures.Lectures;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.Result.Result;
import com.jvt.devthread.collegeapplication.databinding.FragmentDashboardOneBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashboardOne extends Fragment {
    private FragmentDashboardOneBinding binding;
    public static Fragment activeFragment;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    String section;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardOneBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.announcement.setOnClickListener(view1 -> {
            Fragment fragment = new Announcement();
            loadFragment(fragment,"Announcement");
        });
        binding.assignments.setOnClickListener(view1 -> {
            Fragment fragment = new Assignment();
            loadFragment(fragment,"Assignment");
        });
        binding.attendance.setOnClickListener(view1 -> {
            Fragment fragment = new Attendance();
            loadFragment(fragment,"Attendance");
        });
        binding.lecture.setOnClickListener(view1 -> {
            Fragment fragment = new Lectures();
            loadFragment(fragment,"Lectures");
        });
        binding.exam.setOnClickListener(view1 -> {
            Fragment fragment = new Exams();
            loadFragment(fragment,"Exams");
        });
        binding.result.setOnClickListener(view1 -> {
            Fragment fragment = new Result();
            loadFragment(fragment,"Result");
        });
        return view;
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