package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.ModeRecycAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Listener.OnItemClickListener;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;
import com.hhtxproject.piafriendscollege.Tools.MySnapHelper;
import com.hhtxproject.piafriendscollege.Tools.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SelectionPatternFragment extends Fragment {

    @BindView(R.id.relat1)
    RelativeLayout relat1;
    @BindView(R.id.relat2)
    RelativeLayout relat2;
    @BindView(R.id.relat3)
    RelativeLayout relat3;
    @BindView(R.id.relat4)
    RelativeLayout relat4;
    @BindView(R.id.relat5)
    RelativeLayout relat5;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.cesshi2)
    LinearLayout cesshi2;
    @BindView(R.id.ceshi)
    LinearLayout ceshi;
    Unbinder unbinder;

    public SelectionPatternFragment() {
        // Required empty public constructor
    }

    public static SelectionPatternFragment newInstance() {

        Bundle args = new Bundle();

        SelectionPatternFragment fragment = new SelectionPatternFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection_pattern, container, false);
        unbinder = ButterKnife.bind(this, view);
        addAdapter();
        return view;
    }

    /**
     *
     */
    private void addAdapter() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerView.addItemDecoration(new SpaceItemDecoration(dip2px(getContext(),60),50,dip2px(getContext(),10)));
        Toast.makeText(getContext(), "当前屏幕为" + dip2px(getContext(), 360) + "px", Toast.LENGTH_SHORT).show();

        MySnapHelper mySnapHelper = new MySnapHelper();
        mySnapHelper.attachToRecyclerView(mRecyclerView);

        ModeRecycAdapter modeRecycAdapter = new ModeRecycAdapter(getContext());
        mRecyclerView.setAdapter(modeRecycAdapter);

        /**
         * 跳转
         */
        modeRecycAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        //单人模式
                        RxBus.getDefault().post(new JumpEvent(1));
                        break;
                    case 1:
                        //双人模式
                        break;
                    case 2:
                        //多人模式
                        break;
                    case 3:
                        //多人随机
                        break;
                    case 4:
                        //即兴演绎
                        break;
                }
            }
        });

        //监听滚动
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                if (firstItemPosition == 1) {
                    relat1.setVisibility(View.VISIBLE);
                    relat2.setVisibility(View.INVISIBLE);
//                        relat3.setVisibility(View.INVISIBLE);
//                        relat4.setVisibility(View.INVISIBLE);
//                        relat5.setVisibility(View.INVISIBLE);
//                        relat6.setVisibility(View.INVISIBLE);
                } else if (firstItemPosition == 2) {
                    relat1.setVisibility(View.INVISIBLE);
                    relat2.setVisibility(View.VISIBLE);
                    relat3.setVisibility(View.INVISIBLE);
//                        relat4.setVisibility(View.INVISIBLE);
//                        relat5.setVisibility(View.INVISIBLE);
//                        relat6.setVisibility(View.INVISIBLE);
                } else if (firstItemPosition == 3) {
//                        relat1.setVisibility(View.INVISIBLE);
                    relat2.setVisibility(View.INVISIBLE);
                    relat3.setVisibility(View.VISIBLE);
                    relat4.setVisibility(View.INVISIBLE);
//                        relat5.setVisibility(View.INVISIBLE);
//                        relat6.setVisibility(View.INVISIBLE);
                } else if (firstItemPosition == 4) {
//                        relat1.setVisibility(View.INVISIBLE);
//                        relat2.setVisibility(View.INVISIBLE);
                    relat3.setVisibility(View.INVISIBLE);
                    relat4.setVisibility(View.VISIBLE);
                    relat5.setVisibility(View.INVISIBLE);
//                        relat6.setVisibility(View.INVISIBLE);
                } else {
                    Log.i("滚动条：", "未能滚动");
                }
                if (linearLayoutManager.findFirstVisibleItemPosition() == 3) {
                    relat4.setVisibility(View.INVISIBLE);
                    relat5.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
