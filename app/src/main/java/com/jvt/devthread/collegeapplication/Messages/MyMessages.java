package com.jvt.devthread.collegeapplication.Messages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentMyMessagesBinding;

public class MyMessages extends Fragment {
    private FragmentMyMessagesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyMessagesBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}