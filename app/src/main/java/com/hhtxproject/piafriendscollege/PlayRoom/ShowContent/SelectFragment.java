package com.hhtxproject.piafriendscollege.PlayRoom.ShowContent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhtxproject.piafriendscollege.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends Fragment {


    public SelectFragment() {
        // Required empty public constructor
    }

    public static SelectFragment newInstance() {

        Bundle args = new Bundle();

        SelectFragment fragment = new SelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

}
