package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
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

import com.hhtxproject.piafriendscollege.Entity.event.ContentEvent;
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
    private List<String> nameList;
    private int indexPointer = 0;
    private List<ContentEvent> list;

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
        setSpinner();
//        Reset();
        return view;
    }

    private void setJump() {

    }

    private void getRxBusData() {
        nameList = new ArrayList<>();
        data = new ArrayList<>();
        list = new ArrayList<>();
        RxBus.getDefault().toObservable(PeopleDataEvent.class).subscribe(new Action1<PeopleDataEvent>() {
            @Override
            public void call(PeopleDataEvent peopleDataEvent) {
                for (int i = 0; i < peopleDataEvent.getList().size(); i++) {
                    data.add(peopleDataEvent.getList().get(i));
                    nameList.add(peopleDataEvent.getList().get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,android.R.id.text1,nameList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });
    }

    private void leftAndRight(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();
            }
        });
    }

    private void setSpinner(){
        /**选项选择监听*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                peoplePointer = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Reset(){
        switch (data.get(0).getSex()){
            case 0:
                image.setBackgroundResource(girl[data.get(0).getBG()]);
                break;
            case 1:
                image.setBackgroundResource(boy[data.get(0).getBG()]);
                break;
            default:
                break;
        }
        content.setText("");
    }

    private void Refresh(int sex,int pointer) {
        switch (sex){
            case 0:
                image.setBackgroundResource(girl[pointer]);
                break;
            case 1:
                image.setBackgroundResource(boy[pointer]);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
