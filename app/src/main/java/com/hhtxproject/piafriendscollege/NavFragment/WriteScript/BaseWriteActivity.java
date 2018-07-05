package com.hhtxproject.piafriendscollege.NavFragment.WriteScript;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_write);
        ButterKnife.bind(this);
        setInitViewpager();
        getRxBus();
        getRexBusDataSave();
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
                        break;
                }
            }
        });
    }

    private void getRexBusDataSave(){
        RxBus.getDefault().toObservable(SimpleDataEvent.class).subscribe(new Action1<SimpleDataEvent>() {
            @Override
            public void call(SimpleDataEvent simpleDataEvent) {
                Log.i("TEXTaname",simpleDataEvent.getName());
                Log.i("TEXTaclass",simpleDataEvent.getAclass());
                Log.i("TEXTnumber",simpleDataEvent.getNumber()+"");
                Log.i("TEXTatitle",simpleDataEvent.getTitle());
                Log.i("TEXTaintroduce",simpleDataEvent.getIntroduce());
            }
        });
        RxBus.getDefault().toObservable(PeopleDataEvent.class).subscribe(new Action1<PeopleDataEvent>() {
            @Override
            public void call(PeopleDataEvent peopleDataEvent) {
//                Log.i("TEXTname",peopleDataEvent.getName().get(1).toString()+"");
//                Log.i("TEXTasex",peopleDataEvent.getSex().get(1).toString()+"");
//                Log.i("TEXTaBH",peopleDataEvent.getBH().get(1).toString()+"");
//                Log.i("TEXTintroduce",peopleDataEvent.getIntroduce().get(1).toString()+"");
            }
        });
    }
}
