package com.hhtxproject.piafriendscollege.NavFragment.WriteScript;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.Visit;
import com.hhtxproject.piafriendscollege.Entity.ContentData;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.PeopleData;
import com.hhtxproject.piafriendscollege.Entity.SimpleData;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.ContentFragment;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.PeopleFragment;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.SimpleFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;
import com.hhtxproject.piafriendscollege.Tools.DialogExit;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class BaseWriteActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;

    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;

    private ArrayList<SimpleData> simpleList;
    private ArrayList<PeopleData> peopleList;
    private ArrayList<ContentData> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_write);
        ButterKnife.bind(this);
        setInitViewpager();
        getRxBus();
        getSimpleDataSave();
        getPeopleDataSave();
        getContentDataSave();
        Visit.BaseWriteActivity = this;
    }

    private void setInitViewpager() {
        fragments = new ArrayList<>();
        fragments.add(SimpleFragment.newInstance());
        fragments.add(PeopleFragment.newInstance());
        fragments.add(ContentFragment.newInstance());
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager, fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        viewpager.setSlide(false);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (((keyCode == KeyEvent.KEYCODE_BACK) ||
                (keyCode == KeyEvent.KEYCODE_HOME))
                && event.getRepeatCount() == 0) {
            new DialogExit(BaseWriteActivity.this);
        }
        return false;
    }

    private void getRxBus(){
        RxBus.getDefault().toObservable(JumpEvent.class).subscribe(new Action1<JumpEvent>() {
            @Override
            public void call(JumpEvent jumpEvent) {
                switch (jumpEvent.getNum()){
                    case -1:
                        new DialogExit(BaseWriteActivity.this);
                        break;
                    case 0:
                        viewpager.setCurrentItem(1);
                        break;
                    case 1:
                        viewpager.setCurrentItem(0);
                        break;
                    case 2:
                        viewpager.setCurrentItem(2);
                        break;
                    case 3:
                        viewpager.setCurrentItem(1);
                        break;
                    case 4:
                        Toast.makeText(BaseWriteActivity.this,"完成",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BaseWriteActivity.this,BaseReleaseActivity.class);
                        intent.putExtra("simpleData",simpleList);
                        intent.putExtra("peopleData", peopleList);
                        intent.putExtra("contentData",contentList);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void getSimpleDataSave(){
        RxBus.getDefault().toObservable(SimpleData.class).subscribe(new Action1<SimpleData>() {
            @Override
            public void call(SimpleData simpleData) {
                simpleList = new ArrayList<>();
                simpleList.add(simpleData);
                Log.i("SimpleData","{name : "+ simpleData.getName()+" , number : "+ simpleData.getNumber()+" " +
                        ", introduce : "+ simpleData.getIntroduce()+" , imagePath : "+ simpleData.getImagePath()+"}");

            }
        });
    }

    private void getPeopleDataSave(){
        RxBus.getDefault().toObservable(PeopleData.class).subscribe(new Action1<PeopleData>() {
            @Override
            public void call(PeopleData peopleData) {
                for (int i = 0; i< peopleData.getList().size(); i++){
                    peopleList = new ArrayList<>();
                    peopleList.addAll(peopleData.getList());
                    Log.i("PeopleData","{name:"+ peopleData.getList().get(i).getName()+",sexId"+ peopleData.getList().get(i).getBG()+"sex:"+ peopleData.getList().get(i).getSex()+"}");
                }
            }
        });
    }

    private void getContentDataSave(){
        RxBus.getDefault().toObservable(ContentData.class).subscribe(new Action1<ContentData>() {
            @Override
            public void call(ContentData contentData) {
                for (int i = 0; i< contentData.getList().size(); i++){
                    contentList = new ArrayList<>();
                    contentList.addAll(contentData.getList());
                    Log.i("ContentData","{point:"+ contentData.getList().get(i).getPointer()+",content:"+ contentData.getList().get(i).getContent()+"}");
                }
            }
        });
    }

}
