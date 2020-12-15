package com.player.player.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.player.player.R;
import com.player.player.VideoAdapter;

import static com.player.player.Home.Home.videoFiles;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;

    View view;
    VideoAdapter videoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);


        recyclerView = view.findViewById(R.id.filesRV);
        if (videoFiles !=null && videoFiles.size() > 0)
        {
            videoAdapter = new VideoAdapter(getContext(),videoFiles);
            recyclerView.setAdapter(videoAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }


        return view;
    }
}