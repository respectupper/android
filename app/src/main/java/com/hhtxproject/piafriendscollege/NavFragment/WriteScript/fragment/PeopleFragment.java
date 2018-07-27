package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.PeopleAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SelectEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private int [] woman = {R.mipmap.gw1,R.mipmap.gw2,R.mipmap.gw3,R.mipmap.gw4,R.mipmap.gw5};
    private int [] man = {R.mipmap.gm1,R.mipmap.gm2,R.mipmap.gm3,R.mipmap.gm4,R.mipmap.gm5};

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance() {

        Bundle args = new Bundle();

        PeopleFragment fragment = new PeopleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        unbinder = ButterKnife.bind(this, view);
        getRxBus();
        return view;
    }

    private void getRxBus(){
        RxBus.getDefault().toObservable(SimpleDataEvent.class).subscribe(new Action1<SimpleDataEvent>() {
            @Override
            public void call(SimpleDataEvent simpleDataEvent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
