package com.hhtxproject.piafriendscollege.NavFragment.WriteScript;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.ContentDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.BrowseFragment;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.ReleaseFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseReleaseActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;
    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;

    private ArrayList<SimpleDataEvent> simpleList;
    private ArrayList<PeopleDataEvent> peopleList;
    private ArrayList<ContentDataEvent> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_release);
        ButterKnife.bind(this);
        getIntentData();
        setInitViewpager();
    }

    private void getIntentData(){
        simpleList = new ArrayList<>();
        peopleList = new ArrayList<>();
        contentList = new ArrayList<>();

        simpleList = (ArrayList<SimpleDataEvent>) getIntent().getSerializableExtra("simpleData");
        peopleList = (ArrayList<PeopleDataEvent>) getIntent().getSerializableExtra("peopleData");
        contentList = (ArrayList<ContentDataEvent>) getIntent().getSerializableExtra("contentData");

        Log.i("simpleList","{name : "+simpleList.get(0).getName()+" , number : "+simpleList.get(0).getNumber()+", introduce : "+simpleList.get(0).getIntroduce()+" , imagePath : "+simpleList.get(0).getImagePath()+"}");
        for (int i = 0;i<peopleList.size();i++){
            Log.i("peopleList","{name : "+peopleList.get(i).getName()+" , sex : "+peopleList.get(i).getSex()+" " +
                    ", BG : "+peopleList.get(i).getBG()+"}");
        }
        for (int i =0;i<contentList.size();i++){

            Log.i("contentList","{content : "+contentList.get(i).getContent()+" , pointer : "+contentList.get(i).getPointer()+"}");
        }
    }

    private void setInitViewpager() {
        fragments = new ArrayList<>();
        fragments.add(BrowseFragment.newInstance());
        fragments.add(ReleaseFragment.newInstance());
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager, fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        viewpager.setSlide(false);
    }
}
