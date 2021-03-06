package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_title_right)
    TextView toolbarTitleRight;
    Unbinder unbinder;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance() {

        Bundle args = new Bundle();

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        setToolber();
        setJump();
        getRxBusData();
        return view;
    }

    private void setToolber() {
        toolbarTitle.setText("上一步");
        toolbarTitleRight.setText("提交");
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitleRight.setVisibility(View.VISIBLE);
    }

    private void setJump() {
        toolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new JumpEvent(3));
            }
        });

        toolbarTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new JumpEvent(4));
            }
        });
    }

    private void getRxBusData() {
        RxBus.getDefault().toObservable(PeopleDataEvent.class).subscribe(new Action1<PeopleDataEvent>() {
            @Override
            public void call(PeopleDataEvent peopleDataEvent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
