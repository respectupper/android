package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom.fragment.RoomFragment;
import com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom.fragment.SelectionPatternFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * 你好lalalalalaalla
 */
public class ModeRoomActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;

    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_room);
        ButterKnife.bind(this);
//        fullScreen(ModeRoomActivity.this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setInitViewpager();
        getRxBus();
    }

    private void setInitViewpager() {
        fragments = new ArrayList<>();
        fragments.add(SelectionPatternFragment.newInstance());
        fragments.add(RoomFragment.newInstance());
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager, fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        viewpager.setSlide(false);
    }

    private void getRxBus() {
        RxBus.getDefault().toObservable(JumpEvent.class).subscribe(new Action1<JumpEvent>() {
            @Override
            public void call(JumpEvent jumpEvent) {
                switch (jumpEvent.getNum()) {
                    case 0:
                        viewpager.setCurrentItem(0);
                        break;
                    case 1:
                        viewpager.setCurrentItem(1);
                        break;
                    case 2:
                        viewpager.setCurrentItem(0);
                        break;
                    case 3:
                        viewpager.setCurrentItem(0);
                        break;
                }
            }
        });
    }

    /**
     * 通过设置全屏，设置状态栏透明
     * 沉浸式布局
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

}
