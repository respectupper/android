package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Entity.event.ContentDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.left)
    Button left;
    @BindView(R.id.right)
    Button right;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.end)
    TextView end;

    private int[] girl = {R.mipmap.gw1, R.mipmap.gw2, R.mipmap.gw3, R.mipmap.gw4, R.mipmap.gw5};
    private int[] boy = {R.mipmap.gm1, R.mipmap.gm2, R.mipmap.gm3, R.mipmap.gm4, R.mipmap.gm5};
    private List<PeopleDataEvent> data;
    private int peoplePointer = 0;
    private int indexPointer = 0;
    private List<ContentDataEvent> list;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance() {

        Bundle args = new Bundle();
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        setJump();
        getRxBusData();
        saveData();
        return view;
    }

    private void setJump() {
        list = new ArrayList<>();
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexPointer >= 1){
                    right.setVisibility(View.VISIBLE);
                    if (indexPointer == 1){
                        left.setVisibility(View.INVISIBLE);
                        Log.i("!!!+++!!!!!","一号通道");
                    }
                    if (indexPointer+1>list.size()){
                        ContentDataEvent event = new ContentDataEvent();
                        event.setPointer(peoplePointer);
                        event.setContent(content.getText().toString().trim());
                        list.add(event);
                        Log.i("!!!+++!!!!!","二号通道");
                    }else {
                        ContentDataEvent event = new ContentDataEvent();
                        event.setPointer(peoplePointer);
                        event.setContent(content.getText().toString().trim());
                        list.set(indexPointer,event);
                        Log.i("!!!+++!!!!!","三号通道");
                    }
                    indexPointer--;
                    peoplePointer = list.get(indexPointer).getPointer();
                    content.setText(list.get(indexPointer).getContent());
                    spinner.setSelection(list.get(indexPointer).getPointer());
                    Refresh(data.get(indexPointer).getSex(),list.get(indexPointer).getPointer());
                    Log.i("!!!+++!!!!!","四号通道");
                }
                Log.i("index",indexPointer+"");
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left.setVisibility(View.VISIBLE);
                if (indexPointer+1<=list.size()){
                    if (indexPointer==list.size()){
                        indexPointer++;
                        Reset();
                        Log.i("!!!+++!!!!!","五号通道");
                    }else {
                        ContentDataEvent event = new ContentDataEvent();
                        event.setPointer(peoplePointer);
                        event.setContent(content.getText().toString().trim());
                        list.set(indexPointer,event);
                        indexPointer++;
                        content.setText(list.get(indexPointer).getContent());
                        spinner.setSelection(list.get(indexPointer).getPointer());
                        spinner.setSelection(2);
                        peoplePointer = list.get(indexPointer).getPointer();
                        Refresh(data.get(indexPointer).getSex(),list.get(indexPointer).getPointer());
                        Log.i("!!!+++!!!!!","六号通道"+",sexid = "+list.get(indexPointer).getPointer());
                    }
                }else {
                    if (!content.getText().toString().trim().equals("")){
                        ContentDataEvent data = new ContentDataEvent();
                        data.setPointer(peoplePointer);
                        data.setContent(content.getText().toString().trim());
                        list.add(data);
                        indexPointer++;
                        peoplePointer = 0;
                        Reset();
                        Log.i("!!!+++!!!!!","七号通道");
                    }else {
                        Toast.makeText(getContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
                    }
                }
                Log.i("index",indexPointer+"");
            }
        });
    }

    private void getRxBusData() {
        data = new ArrayList<>();
        list = new ArrayList<>();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 200){
                    List<PeopleDataEvent> list = (List<PeopleDataEvent>) msg.obj;
                    data.addAll(list);
                    List<String> nameList = new ArrayList<>();
                    for (int i = 0; i <list.size(); i++) {
                        nameList.add(list.get(i).getName());
                    }
                    setSpinner(nameList);
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                RxBus.getDefault().toObservable(PeopleDataEvent.class).subscribe(new Action1<PeopleDataEvent>() {
                    @Override
                    public void call(PeopleDataEvent peopleDataEvent) {
                        Message msg = new Message();
                        msg.obj = peopleDataEvent.getList();
                        msg.what = 200;
                        handler.sendMessage(msg);
                    }
                });
            }
        }).start();
    }

    private void setSpinner(List<String> lists){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,android.R.id.text1,lists);
        spinner.setAdapter(adapter);
        spinner.setPrompt("选择名字");
        /**选项选择监听*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                peoplePointer = i;
                switch (data.size()==0?2:data.get(i).getSex()){
                    case 0:
                        image.setBackgroundResource(0);
                        image.setBackgroundResource(girl[data.get(i).getBG()]);
                        break;
                    case 1:
                        image.setBackgroundResource(0);
                        image.setBackgroundResource(boy[data.get(i).getBG()]);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void Reset(){
        switch (data.get(0).getSex()){
            case 0:
                image.setBackgroundResource(0);
                image.setBackgroundResource(girl[data.get(0).getBG()]);
                break;
            case 1:
                image.setBackgroundResource(0);
                image.setBackgroundResource(boy[data.get(0).getBG()]);
                break;
            default:
                break;
        }
        spinner.setSelection(0);
        content.setText("");
    }

    private void Refresh(int sex,int pointer) {
        switch (sex){
            case 0:
                image.setBackgroundResource(0);
                image.setBackgroundResource(girl[pointer]);
                break;
            case 1:
                image.setBackgroundResource(0);
                image.setBackgroundResource(boy[pointer]);
                break;
            default:
                break;
        }
    }

    private void saveData(){
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentDataEvent data = new ContentDataEvent();
                data.setList(list);
                RxBus.getDefault().post(data);

                JumpEvent event = new JumpEvent(4);
                RxBus.getDefault().post(event);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
