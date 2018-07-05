package com.hhtxproject.piafriendscollege.NavFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hhtxproject.piafriendscollege.NavFragment.TabView.Voice.Genre.GenreFragment;
import com.hhtxproject.piafriendscollege.NavFragment.TabView.Voice.Rank.RankFragment;
import com.hhtxproject.piafriendscollege.NavFragment.TabView.Voice.Recommend.RecommendFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.ViewFindUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends Fragment implements OnTabSelectListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.nested_scrollview)
    NestedScrollView nestedScrollview;

    private Toolbar toolbar;
    private Context mContext = getContext();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "推荐", "榜单", "分类"
    };
    private MyPagerAdapter mAdapter;

    public VoiceFragment() {
        // Required empty public constructor
        Log.i("VoiceFragment", "VoiceFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i("create", "create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_voice, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolbar = view.findViewById(R.id.toolbar);
        initToolbar();

        setPager();

        nestedScrollview.setFillViewport(true);
        setViewpager(view);

        return view;
    }

    @Override
    public void onTabSelect(int position) {
        Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void initToolbar() {
        toolbar.setTitle("");
        toolbarTitle.setText("音频");
        getActivity().setActionBar(toolbar);
    }

    private void setPager() {
        mFragments.add(new RecommendFragment());
        mFragments.add(new RankFragment());
        mFragments.add(new GenreFragment());
    }

    private void setViewpager(View view){
        ViewPager vp = ViewFindUtils.find(view, R.id.vp);
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mAdapter);

//        /** 默认 */
//        SlidingTabLayout tabLayout_1 = ViewFindUtils.find(decorView, R.id.tl_1);
//        /**自定义部分属性*/
//        SlidingTabLayout tabLayout_2 = ViewFindUtils.find(decorView, R.id.tl_2);
        /** 字体加粗,大写 */
        SlidingTabLayout tabLayout_3 = ViewFindUtils.find(view, R.id.tl_3);
        /** tab固定宽度 */
//        SlidingTabLayout tabLayout_4 = ViewFindUtils.find(decorView, R.id.tl_4);
//        /** indicator固定宽度 */
//        SlidingTabLayout tabLayout_5 = ViewFindUtils.find(decorView, R.id.tl_5);
//        /** indicator圆 */
//        SlidingTabLayout tabLayout_6 = ViewFindUtils.find(decorView, R.id.tl_6);
//        /** indicator矩形圆角 */
//        final SlidingTabLayout tabLayout_7 = ViewFindUtils.find(decorView, R.id.tl_7);
//        /** indicator三角形 */
//        SlidingTabLayout tabLayout_8 = ViewFindUtils.find(decorView, R.id.tl_8);
//        /** indicator圆角色块 */
//        SlidingTabLayout tabLayout_9 = ViewFindUtils.find(decorView, R.id.tl_9);
//        /** indicator圆角色块 */
//        SlidingTabLayout tabLayout_10 = ViewFindUtils.find(decorView, R.id.tl_10);

//        tabLayout_1.setViewPager(vp);
//        tabLayout_2.setViewPager(vp);
//        tabLayout_2.setOnTabSelectListener(this);
        tabLayout_3.setViewPager(vp);
//        tabLayout_4.setViewPager(vp);
//        tabLayout_5.setViewPager(vp);
//        tabLayout_6.setViewPager(vp);
//        tabLayout_7.setViewPager(vp, mTitles);
//        tabLayout_8.setViewPager(vp, mTitles, this, mFragments);
//        tabLayout_9.setViewPager(vp);
//        tabLayout_10.setViewPager(vp);

        vp.setCurrentItem(0);

//        tabLayout_1.showDot(4);
//        tabLayout_3.showDot(1);
//        tabLayout_2.showDot(4);

//        tabLayout_2.showMsg(3, 5);
//        tabLayout_2.setMsgMargin(3, 0, 10);
//        MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
//        if (rtv_2_3 != null) {
//            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
//        }
//
//        tabLayout_2.showMsg(5, 5);
//        tabLayout_2.setMsgMargin(5, 0, 10);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView", "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "onDestroy");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_voice, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_voice_bt1:
                Toast.makeText(getContext(), "音频按钮1", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_voice_bt2:
                Toast.makeText(getContext(), "音频按钮2", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_voice_bt3:
                Toast.makeText(getContext(), "音频按钮3", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
