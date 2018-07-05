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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.ViewFindUtils;
import com.hhtxproject.piafriendscollege.NavFragment.TabView.Script.Genre.GenreFragment;
import com.hhtxproject.piafriendscollege.NavFragment.TabView.Script.Rank.RankFragment;
import com.hhtxproject.piafriendscollege.NavFragment.TabView.Script.Recommend.RecommendFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScriptFragment extends Fragment implements OnTabSelectListener {

    private Context mContext = getContext();
    private View view;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "推荐", "榜单","分类"
    };
    private MyPagerAdapter mAdapter;

    public ScriptFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i("create","create");
    }

    private Toolbar toolbar;
    private TextView toolbar_title;
    private NestedScrollView nested_scrollview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_script, container, false);
        init();
        initToolbar();
        setPager();

        nested_scrollview.setFillViewport(true);

        ViewPager vp = ViewFindUtils.find(view, R.id.vp);
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mAdapter);

        SlidingTabLayout tabLayout_3 = ViewFindUtils.find(view, R.id.tl_3);
        tabLayout_3.setViewPager(vp);

        vp.setCurrentItem(0);

        return view;
    }

    public void setPager(){
        mFragments.add(new RecommendFragment());
        mFragments.add(new RankFragment());
        mFragments.add(new GenreFragment());
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

    private void init(){
        toolbar = view.findViewById(R.id.toolbar);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        nested_scrollview = view.findViewById(R.id.nested_scrollview);
    }

    private void initToolbar(){
        toolbar.setTitle("");
        toolbar_title.setText("剧本");
        getActivity().setActionBar(toolbar);
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
                Toast.makeText(getContext(), "剧本按钮1", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_voice_bt2:
                Toast.makeText(getContext(), "剧本按钮2", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_voice_bt3:
                Toast.makeText(getContext(), "剧本按钮3", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
