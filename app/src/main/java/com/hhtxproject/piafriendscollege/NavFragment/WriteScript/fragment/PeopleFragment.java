package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Entity.Visit;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.sex_boy)
    Button sexBoy;
    @BindView(R.id.sex_girl)
    Button sexGirl;
    @BindView(R.id.up)
    Button up;
    @BindView(R.id.down)
    Button down;
    @BindView(R.id.last)
    Button last;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.left)
    Button left;
    @BindView(R.id.show)
    Button show;
    @BindView(R.id.right)
    Button right;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.pName)
    TextView pName;

    private int[] girl = {R.mipmap.gw1, R.mipmap.gw2, R.mipmap.gw3, R.mipmap.gw4, R.mipmap.gw5};
    private int[] boy = {R.mipmap.gm1, R.mipmap.gm2, R.mipmap.gm3, R.mipmap.gm4, R.mipmap.gm5};
    private int count = 0;
    private int pointer = 0;
    private int sexPointer = 0;
    private int indexPointer = 1;
    private List<PeopleDataEvent> list;
    private boolean key = false;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance() {

        Bundle args = new Bundle();
        PeopleFragment fragment = new PeopleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        setSex();
        setShowImage();
        setIndexPointer();
        setJump();
        return view;
    }

    private void setSex() {
        //默认显示
        image.setBackgroundResource(girl[0]);
        upDateSexAndBG();

        sexBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointer = 0;
                sexPointer = 1;
                image.setBackgroundResource(boy[pointer]);
                upDateSexAndBG();
                up.setVisibility(View.INVISIBLE);
                down.setVisibility(View.VISIBLE);
            }
        });

        sexGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointer = 0;
                sexPointer = 0;
                image.setBackgroundResource(girl[pointer]);
                upDateSexAndBG();
                up.setVisibility(View.INVISIBLE);
                down.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setShowImage(){
        up.setVisibility(View.INVISIBLE);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                down.setVisibility(View.VISIBLE);
                switch (sexPointer){
                    case 0:
                        if (pointer >= 1){
                            if (pointer==1){
                                up.setVisibility(View.INVISIBLE);
                            }
                            pointer--;
                            image.setBackgroundResource(girl[pointer]);
                        }
                        break;
                    case 1:
                        if (pointer >= 1){
                            if (pointer==1){
                                up.setVisibility(View.INVISIBLE);
                            }
                            pointer--;
                            image.setBackgroundResource(boy[pointer]);
                        }
                        break;
                }
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                up.setVisibility(View.VISIBLE);
                switch (sexPointer){
                    case 0:
                        if (pointer <= girl.length-1){
                            pointer++;
                            Log.i("poniter:====",pointer+",----"+sexPointer);
                            image.setBackgroundResource(girl[pointer]);
                            if (pointer==girl.length-1){
                                down.setVisibility(View.INVISIBLE);
                            }
                        }
                        break;
                    case 1:
                        if (pointer <= boy.length-1){
                            pointer++;
                            image.setBackgroundResource(boy[pointer]);
                            if (pointer==girl.length-1){
                                down.setVisibility(View.INVISIBLE);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setIndexPointer(){
        list = new ArrayList<>();
        if (indexPointer == 1){
            left.setVisibility(View.INVISIBLE);
        }
        show.setText(indexPointer+"");
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexPointer > 1){
                    pName.setText(list.get(indexPointer-2).getName());
                    sexPointer = list.get(indexPointer-2).getSex();
                    pointer = list.get(indexPointer-2).getBG();
                    indexPointer--;
                    show.setText(indexPointer+"");
                }
                upDateSexAndBG();
                if(indexPointer == 1){
                    left.setVisibility(View.INVISIBLE);
                }
                right.setVisibility(View.VISIBLE);
                right.setBackgroundColor(getResources().getColor(R.color.color_text_70blank));
                Log.i("log","index = "+indexPointer+",list.size"+list.size());
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(pName.getText().toString().trim())){
                    if (indexPointer > list.size()){
                        PeopleDataEvent data = new PeopleDataEvent();
                        data.setName(pName.getText().toString().trim());
                        data.setSex(sexPointer);
                        data.setBG(pointer);
                        list.add(data);
                        if (indexPointer != count){
                            pName.setText("");
                            sexPointer = 0;
                            pointer = 0;
                            image.setBackgroundResource(girl[0]);
                        }else {
                            Toast.makeText(getContext(),"已保存",Toast.LENGTH_SHORT).show();
                            key = true;
                        }
                    }else {
                        PeopleDataEvent data = new PeopleDataEvent();
                        data.setName(pName.getText().toString().trim());
                        data.setSex(sexPointer);
                        data.setBG(pointer);
                        list.set(indexPointer-1,data);

                        if (indexPointer < list.size()){
                            pName.setText(list.get(indexPointer).getName());
                            sexPointer = list.get(indexPointer).getSex();
                            pointer = list.get(indexPointer).getBG();
                        }
                        if (indexPointer == count){
                            Toast.makeText(getContext(),"已保存",Toast.LENGTH_SHORT).show();
                            key = true;
                        }
                    }
                    if (indexPointer < count){
                        indexPointer++;
                    }
                    show.setText(indexPointer+"");
                    upDateSexAndBG();
                    if (indexPointer >= count){
                        right.setBackgroundColor(getResources().getColor(R.color.dddddd));
                    }else {
                        right.setBackgroundColor(getResources().getColor(R.color.color_text_70blank));
                    }
                    left.setVisibility(View.VISIBLE);
                    Log.i("log","index = "+indexPointer+",list.size"+list.size());
                }else {
                    Toast.makeText(getContext(),"名不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {
        RxBus.getDefault().toObservable(SimpleDataEvent.class).subscribe(new Action1<SimpleDataEvent>() {
            @Override
            public void call(SimpleDataEvent peopleDataEvent) {
                count = peopleDataEvent.getNumber();
                Log.i("number", count + "");
            }
        });
    }

    private void upDateSexAndBG(){
        if (sexPointer == 0){
            sexGirl.setBackgroundColor(getResources().getColor(R.color.dddddd));
            sexBoy.setBackgroundColor(getResources().getColor(R.color.color_text_70blank));
            image.setBackgroundResource(girl[pointer]);
        }else {
            sexGirl.setBackgroundColor(getResources().getColor(R.color.color_text_70blank));
            sexBoy.setBackgroundColor(getResources().getColor(R.color.dddddd));
            image.setBackgroundResource(boy[pointer]);
        }
    }

    private void setJump(){
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                pointer = 0;
                sexPointer = 0;
                indexPointer = 1;
                list.clear();
                key = false;
                upDateSexAndBG();
                show.setText(indexPointer+"");
                JumpEvent event = new JumpEvent(1);
                RxBus.getDefault().post(event);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (indexPointer>=count&&key){
                    saveRxData();
                    JumpEvent event = new JumpEvent(2);
                    RxBus.getDefault().post(event);
                }else {
                    Toast.makeText(getContext(),"请点右上角的保存",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveRxData(){
        PeopleDataEvent event = new PeopleDataEvent();
        event.setList(list);
        RxBus.getDefault().post(event);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
