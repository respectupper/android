package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.CSScriptAdapter;
import com.hhtxproject.piafriendscollege.Adapter.ChoiceScriptAdapter;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.RecyclerViewUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoiceScriptActivity extends AppCompatActivity {

    @BindView(R.id.mode_return)
    ImageButton modeReturn;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.choice_script_up)
    ImageView choiceScriptUp;
    //    @BindView(R.id.mRecyclerView)
//    RecyclerView mRecyclerView;
    @BindView(R.id.choice_script_down)
    ImageView choiceScriptDown;
    @BindView(R.id.mRecyclerViewTwo)
    RecyclerView mRecyclerViewTwo;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.radio4)
    RadioButton radio4;
    @BindView(R.id.radio5)
    RadioButton radio5;
    @BindView(R.id.radio6)
    RadioButton radio6;
    @BindView(R.id.radio7)
    RadioButton radio7;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<String> data;
    private int START_POS_1 = 0;
    private int START_POS_2 = 20;
    private int START_POS_3 = 20;
    private int START_POS_4 = 20;
    private int START_POS_5 = 20;
    private int START_POS_6 = 20;
    private int START_POS_7 = 20;
    private RecyclerViewUtil mRecyclerViewUtil;
    private CSScriptAdapter csScriptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_script);
        ButterKnife.bind(this);
        fullScreen(ChoiceScriptActivity.this);
//        addAdapter();
        addScriptAdapter();
        mRadioButtonImgSize();
        //设置RadioGroup的监听
        initListener();
        addSwipeRefreshLayout();
    }

    private void addSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio1:
                        data.clear();
                        START_POS_1 = 0;
                        Sentimentdata();
                        csScriptAdapter.notifyDataSetChanged();
                        Toast.makeText(ChoiceScriptActivity.this,"已经刷新", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio2:
                        data.clear();
                        START_POS_2 = 20;
                        Antiquitydata();
                        csScriptAdapter.notifyDataSetChanged();
                        break;
                    case R.id.radio3:
                        break;
                    case R.id.radio4:
                        break;
                    case R.id.radio5:
                        break;
                    case R.id.radio6:
                        break;
                    case R.id.radio7:
                        break;
                }
//                2131296441
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initListener() {
        radioGroup.check(R.id.radio1);
//        Toast.makeText(ChoiceScriptActivity.this, radioGroup.getChildAt(1) + "", Toast.LENGTH_SHORT).show();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio1:
                        mSwipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefreshLayout.setRefreshing(true);
                                mRecyclerViewTwo.smoothScrollToPosition(0);
                                data.clear();
                                START_POS_1 = 0;
                                Sentimentdata();
                                csScriptAdapter.notifyDataSetChanged();
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                        break;
                    case R.id.radio2:
                        mSwipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefreshLayout.setRefreshing(true);
                                mRecyclerViewTwo.smoothScrollToPosition(0);
                                data.clear();
                                START_POS_2 = 20;
                                Antiquitydata();
                                csScriptAdapter.notifyDataSetChanged();
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                        break;
                    case R.id.radio3:
                        Toast.makeText(ChoiceScriptActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio4:
                        Toast.makeText(ChoiceScriptActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio5:
                        Toast.makeText(ChoiceScriptActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio6:
                        Toast.makeText(ChoiceScriptActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio7:
                        Toast.makeText(ChoiceScriptActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void mRadioButtonImgSize() {
        int q = dip2px(this, 60);
        //定义底部标签图片大小
        Drawable drawableFirst = getResources().getDrawable(R.drawable.radiobutton1);
        drawableFirst.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio1.setCompoundDrawables(null, drawableFirst, null, null);//只放上面
        Drawable drawableFirst1 = getResources().getDrawable(R.drawable.radiobutton2);
        drawableFirst1.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio2.setCompoundDrawables(null, drawableFirst1, null, null);
        Drawable drawableFirst2 = getResources().getDrawable(R.drawable.radiobutton1);
        drawableFirst2.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio3.setCompoundDrawables(null, drawableFirst2, null, null);
        Drawable drawableFirst3 = getResources().getDrawable(R.drawable.radiobutton1);
        drawableFirst3.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio4.setCompoundDrawables(null, drawableFirst3, null, null);
        Drawable drawableFirst4 = getResources().getDrawable(R.drawable.radiobutton1);
        drawableFirst4.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio5.setCompoundDrawables(null, drawableFirst4, null, null);
        Drawable drawableFirst5 = getResources().getDrawable(R.drawable.radiobutton1);
        drawableFirst5.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio6.setCompoundDrawables(null, drawableFirst5, null, null);
        Drawable drawableFirst6 = getResources().getDrawable(R.drawable.radiobutton1);
        drawableFirst6.setBounds(0, 0, q, q);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radio7.setCompoundDrawables(null, drawableFirst6, null, null);
    }

    private void addScriptAdapter() {
        data = new ArrayList<>();
        int count = 20;
        for (int i = 0; i < count; i++) {
            data.add(String.valueOf(i));
        }

        START_POS_1 = START_POS_1 + count;

        GridLayoutManager manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewTwo.setLayoutManager(manager);
        csScriptAdapter = new CSScriptAdapter(data);
        mRecyclerViewTwo.setAdapter(csScriptAdapter);

        mRecyclerViewUtil = new RecyclerViewUtil(this, mRecyclerViewTwo);
        mRecyclerViewUtil.setOnLoadMoreListener(new RecyclerViewUtil.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getApplicationContext(), "已经到底，加载更多..."+START_POS_1, Toast.LENGTH_SHORT).show();
                mRecyclerViewUtil.setLoadMoreEnable(false);
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio1:
                        loadSentimentdata(START_POS_1,20);
                        break;
                    case R.id.radio2:
                        loadAntiquitydata(START_POS_2,20);
                        break;
                    case R.id.radio3:
                        break;
                    case R.id.radio4:
                        break;
                    case R.id.radio5:
                        break;
                    case R.id.radio6:
                        break;
                    case R.id.radio7:
                        break;
                }
            }
        });

        mRecyclerViewUtil.setOnItemClickListener(new RecyclerViewUtil.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
//                Toast.makeText(getApplicationContext(), "单击" + position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ChoiceScriptActivity.this,ScriptDetailsActivity.class);
                i.putExtra("activity_name",data.get(position));
                startActivity(i);
            }
        });

        mRecyclerViewUtil.setOnItemLongClickListener(new RecyclerViewUtil.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position, View view) {
                Toast.makeText(getApplicationContext(), "长按" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //言情
    private void loadSentimentdata(int startPos, int count) {
        for (int i = 0; i < count; i++) {
            data.add(startPos + i + "");
        }
        csScriptAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "加载了" + count + " 条数据", Toast.LENGTH_SHORT).show();

        START_POS_1 = startPos + count;
        mRecyclerViewUtil.setLoadMoreEnable(true);
    }
    //古风
    private void loadAntiquitydata(int startPos, int count) {
        for (int i = 0; i < count; i++) {
            data.add(startPos + i +100+ "");
        }
        csScriptAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "加载了" + count + " 条数据", Toast.LENGTH_SHORT).show();

        START_POS_2 = startPos + count;
        mRecyclerViewUtil.setLoadMoreEnable(true);
    }

    //古风数据加载
    private void Antiquitydata() {
        for (int i = 0; i < 20; i++) {
            data.add(String.valueOf(i + 100));
        }
    }

    //言情数据加载
    private void Sentimentdata() {
        for (int i = 0; i < 20; i++) {
            data.add(String.valueOf(i));
        }
    }

//    private void addAdapter() {
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
////        横向
////        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mAdapter = new ChoiceScriptAdapter(this);
//        mRecyclerView.setAdapter(mAdapter);
//    }

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
