package com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScriptDetailsActivity extends AppCompatActivity {

    @BindView(R.id.Script_Cover)
    ImageView ScriptCover;
    @BindView(R.id.Script_name)
    TextView ScriptName;
    @BindView(R.id.Script_class)
    TextView ScriptClass;
    @BindView(R.id.Script_number)
    TextView ScriptNumber;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_details);
        ButterKnife.bind(this);
        fullScreen(ScriptDetailsActivity.this);
        addData();
    }

    private void addData() {
        Intent i = getIntent();
        ScriptName.setText(i.getSerializableExtra("activity_name").toString());
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
