package com.jvt.devthread.collegeapplication.Announcement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.collegeapplication.Common.Common;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.FragmentAnnouncementDescriptionBinding;

public class AnnouncementDescription extends Fragment {
    private FragmentAnnouncementDescriptionBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnnouncementDescriptionBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.heading.setText(Common.aHead);
        binding.date.setText(Common.aDate);
        binding.content.setText(Common.aContent);
        binding.uploaded.setText(Common.aUploaded);
        return view;
    }
}