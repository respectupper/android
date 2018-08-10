package com.hhtxproject.piafriendscollege.NavFragment.WriteScript;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.ReleaseFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class BaseReleaseActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;
    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_release);
        ButterKnife.bind(this);
        setInitViewpager();
        getRxBusEvent();
    }

    private void setInitViewpager() {
        fragments = new ArrayList<>();
//        fragments.add(BrowseFragment.newInstance());
        fragments.add(ReleaseFragment.newInstance());
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager, fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        viewpager.setSlide(false);
    }

    private void getRxBusEvent(){
        RxBus.getDefault().toObservable(JumpEvent.class).subscribe(new Action1<JumpEvent>() {
            @Override
            public void call(JumpEvent jumpEvent) {
                switch (jumpEvent.getNum()){
                    case 5:
                        finish();
                        break;
                    case 6:
                        viewpager.setCurrentItem(1);
                        break;

                }
            }
        });
    }
}
