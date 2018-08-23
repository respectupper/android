package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.ModeRecycAdapter;
import com.hhtxproject.piafriendscollege.Listener.OnItemClickListener;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.MySnapHelper;
import com.hhtxproject.piafriendscollege.Tools.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ModeActivity extends AppCompatActivity {

    @BindView(R.id.mode_return)
    ImageButton modeReturn;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.cesshi2)
    LinearLayout cesshi2;
    @BindView(R.id.relat1)
    RelativeLayout relat1;
    @BindView(R.id.relat2)
    RelativeLayout relat2;
    @BindView(R.id.relat3)
    RelativeLayout relat3;
//    @BindView(R.id.relat4)
//    RelativeLayout relat4;
//    @BindView(R.id.relat5)
//    RelativeLayout relat5;
    @BindView(R.id.ceshi)
    LinearLayout ceshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        ButterKnife.bind(this);
        fullScreen(ModeActivity.this);
        addAdapter();
    }
    /**
     *添加RecyclerView适配器
     */
    private void addAdapter() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerView.addItemDecoration(new SpaceItemDecoration(dip2px(this, 60), 50, dip2px(this, 10)));
        Toast.makeText(this, "当前屏幕为" + dip2px(this, 360) + "px", Toast.LENGTH_SHORT).show();

        MySnapHelper mySnapHelper = new MySnapHelper();
        mySnapHelper.attachToRecyclerView(mRecyclerView);

        ModeRecycAdapter modeRecycAdapter = new ModeRecycAdapter(this);
        mRecyclerView.setAdapter(modeRecycAdapter);

        /**
         * 跳转
         */
        modeRecycAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(ModeActivity.this, position + "", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        //单人模式
                        startActivity(new Intent(ModeActivity.this,ChoiceScriptActivity.class));
                        break;
                    case 1:
                        //随机模式
                        startActivity(new Intent(ModeActivity.this,RandomSrtActivity.class));
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
//                int lastItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

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
//                } else if (firstItemPosition == 3) {
////                        relat1.setVisibility(View.INVISIBLE);
//                    relat2.setVisibility(View.INVISIBLE);
//                    relat3.setVisibility(View.VISIBLE);
//                    relat4.setVisibility(View.INVISIBLE);
////                        relat5.setVisibility(View.INVISIBLE);
////                        relat6.setVisibility(View.INVISIBLE);
//                } else if (firstItemPosition == 4) {
////                        relat1.setVisibility(View.INVISIBLE);
////                        relat2.setVisibility(View.INVISIBLE);
//                    relat3.setVisibility(View.INVISIBLE);
//                    relat4.setVisibility(View.VISIBLE);
//                    relat5.setVisibility(View.INVISIBLE);
////                        relat6.setVisibility(View.INVISIBLE);
                } else {
                    Log.i("滚动条：", "未能滚动");
                }
//              linearLayoutManager.findFirstVisibleItemPosition() == 总个数-2
                if (linearLayoutManager.findFirstVisibleItemPosition() == 1) {
                    relat2.setVisibility(View.INVISIBLE);
                    relat3.setVisibility(View.VISIBLE);
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

    /**
     * 通过设置全屏，设置状态栏透明
     * 沉浸式布局
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
