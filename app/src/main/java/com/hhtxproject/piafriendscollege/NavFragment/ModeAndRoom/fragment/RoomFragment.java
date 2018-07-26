package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhtxproject.piafriendscollege.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomFragment extends Fragment {


    public RoomFragment() {
        // Required empty public constructor
    }
    public static RoomFragment newInstance() {

        Bundle args = new Bundle();

        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        // Inflate the layout for this fragment


        return view;
    }

}
