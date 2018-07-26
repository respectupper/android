package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.spinner)
    Spinner spinner;

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance() {

        Bundle args = new Bundle();
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    private void setToolber() {
//        toolbarTitle.setText("返回");
//        toolbarTitleRight.setText("下一步");
//        toolbarTitle.setVisibility(View.VISIBLE);
//        toolbarTitleRight.setVisibility(View.VISIBLE);
//    }

//    private void setJump() {
//        toolbarTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RxBus.getDefault().post(new JumpEvent(-1));
//            }
//        });
//
//        toolbarTitleRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (TextUtils.isEmpty(aname)){
////                    Toast.makeText(getContext(),"剧本名不能为空",Toast.LENGTH_SHORT).show();
////                }else if (TextUtils.isEmpty(aclasss)){
////                    Toast.makeText(getContext(),"剧本种类不能为空",Toast.LENGTH_SHORT).show();
////                }else if (TextUtils.isEmpty(atitles)){
////                    Toast.makeText(getContext(),"剧本标题不能为空",Toast.LENGTH_SHORT).show();
////                }else if (TextUtils.isEmpty(aintroduce)){
////                    Toast.makeText(getContext(),"剧本简介不能为空",Toast.LENGTH_SHORT).show();
////                }else {
////                    RxBus.getDefault().post(new JumpEvent(0));
////                    SimpleDataEvent event = new SimpleDataEvent();
////                    RxBus.getDefault().post(event);
////                }
//            }
//        });
//    }
    private void setSpinner(){

    }
}
