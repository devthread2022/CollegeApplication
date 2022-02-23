package com.jvt.devthread.collegeapplication.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.collegeapplication.Happenings.Happenings;
import com.jvt.devthread.collegeapplication.Makeup.MakeUp;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.RMS.Rms;
import com.jvt.devthread.collegeapplication.Teachers.Teachers;
import com.jvt.devthread.collegeapplication.databinding.FragmentDashboardTwoBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashboardTwo extends Fragment {
    private FragmentDashboardTwoBinding binding;
    public static Fragment activeFragment;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardTwoBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.teachers.setOnClickListener(view1 -> {
            Fragment fragment = new Teachers();
            loadFragment(fragment,"Teachers");
        });
        binding.makeup.setOnClickListener(view1 -> {
            Fragment fragment = new MakeUp();
            loadFragment(fragment,"MakeUp");
        });
        binding.rms.setOnClickListener(view1 -> {
            Fragment fragment = new Rms();
            loadFragment(fragment,"Rms");
        });
        binding.happenings.setOnClickListener(view1 -> {
            Fragment fragment = new Happenings();
            loadFragment(fragment,"Happenings");
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