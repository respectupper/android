package com.hhtxproject.piafriendscollege.Base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Login.service.CheckLogin;
import com.hhtxproject.piafriendscollege.NavFragment.FindFragment;
import com.hhtxproject.piafriendscollege.NavFragment.GoFragment;
import com.hhtxproject.piafriendscollege.NavFragment.MineFragment;
import com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom.ModeActivity;
import com.hhtxproject.piafriendscollege.NavFragment.ModeAndRoom.ModeRoomActivity;
import com.hhtxproject.piafriendscollege.NavFragment.ScriptFragment;
import com.hhtxproject.piafriendscollege.NavFragment.VoiceFragment;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.BaseWriteActivity;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.BottomBar;
import com.hhtxproject.piafriendscollege.app.PApplication;
import com.rance.library.ButtonData;
import com.rance.library.ButtonEventListener;
import com.rance.library.SectorMenuButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View main_fragment;
    private BottomBar navigation;
    private PApplication application;

    private static int[] imageResources = new int[]{
            R.drawable.mic,
            R.drawable.write,
    };
    private static int index = 0;
    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];
    }
    private static String [] text = new String[]{"音色动人","一展文采"};
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.i("applicationInfo",application.getmSharedPreferences().getString("Telephone","NULL")+"");
        initNavition();
        setData();
        initSectorMenuButton();
    }

    private void init(){
        application = (PApplication) getApplicationContext();
        application.setmSharedPreferences(getSharedPreferences("loginUser", Context.MODE_PRIVATE));
        navigation = findViewById(R.id.navigation);
        main_fragment = findViewById(R.id.main_fragment);
    }

    private void initNavition(){
        navigation.setContainer(R.id.main_fragment)
                .setTitleBeforeAndAfterColor("#C283E7", "#C283E7")
                .setFragmentManager(getSupportFragmentManager())
                .addItem(VoiceFragment.class,
                        "音频",
                        R.drawable.unvoice,
                        R.drawable.voice)
                .addItem(ScriptFragment.class,
                        "剧本",
                        R.drawable.unscript,
                        R.drawable.script)
                .addItem(GoFragment.class,
                        "GO",
                        R.drawable.go,
                        R.drawable.go)
                .addItem(FindFragment.class,
                        "花园",
                        R.drawable.unfind,
                        R.drawable.find)
                .addItem(MineFragment.class,
                        "小窝",
                        R.drawable.unmine,
                        R.drawable.mine)
                .build();
    }

    private void setData(){

        if (new CheckLogin(application.getmSharedPreferences()).cheak()){
            application.setTelephone(application.getmSharedPreferences().getString("Telephone","NULL"));
            application.setLogin(true);
            Log.i("true","True"+application.getTelephone());
        }else {
            Log.i("false","False");
        }
    }

    private void initSectorMenuButton() {
        SectorMenuButton sectorMenuButton = findViewById(R.id.bottom_sector_menu);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.drawable.go, R.drawable.sector_xie, R.drawable.sector_luyin};
        for (int i = 0; i < 3; i++) {
            //最后一个参数表示padding
            ButtonData buttonData = ButtonData.buildIconButton(this, drawable[i], 10);
            buttonData.setBackgroundColorId(this, R.color.color_main_theme);
            buttonDatas.add(buttonData);
        }
        sectorMenuButton.setButtonDatas(buttonDatas);
        setListener(sectorMenuButton);
    }

    private void setListener(final SectorMenuButton button) {
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                switch (index){
                    case 1:
                        Intent intent = new Intent(MainActivity.this, BaseWriteActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(MainActivity.this, ModeActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onExpand() {

            }

            @Override
            public void onCollapse() {

            }
        });
    }
}
