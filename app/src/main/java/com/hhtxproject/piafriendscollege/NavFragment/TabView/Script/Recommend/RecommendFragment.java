package com.hhtxproject.piafriendscollege.NavFragment.TabView.Script.Recommend;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhtxproject.piafriendscollege.Adapter.RecyclerViewAdapter;
import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.SimpleData;
import com.hhtxproject.piafriendscollege.Net.LoadData;
import com.hhtxproject.piafriendscollege.R;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private List<SimpleData> listData;
    private RecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listData = new ArrayList<>();
        getData();
    }

    public RecommendFragment() {

    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        setSwipeRefresh();
        setRecyclerView();
        return view;
    }

    private void getData() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 200:
                        if (!"FAILED".equals(msg.obj)) {
                            listData.addAll(new GetRecommendData(msg).getListData());
                            adapter.notifyDataSetChanged();
                            swipeRefresh.setRefreshing(false);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams();
                new LoadData(handler, "getScript_pia", params,200).getData().sendToTarget();
            }
        }).start();

    }

    private void setRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerViewAdapter(getContext(), null, listData, "script");
        recyclerView.setAdapter(adapter);
    }

    private void setSwipeRefresh() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
