package com.hhtxproject.piafriendscollege.PlayRoom.ShowContent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.ContentData;
import com.hhtxproject.piafriendscollege.Entity.PeopleData;
import com.hhtxproject.piafriendscollege.Entity.SimpleData;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {


    @BindView(R.id.exit)
    TextView exit;
    @BindView(R.id.l_my_info)
    LinearLayout lMyInfo;
    Unbinder unbinder;
    @BindView(R.id.my_sex)
    ImageView mySex;
    @BindView(R.id.tishideng)
    ImageView tishideng;
    @BindView(R.id.show_name)
    Button showName;
    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;
    @BindView(R.id.my_people)
    ImageView myPeople;
    @BindView(R.id.show_people)
    ViewPagerSlide showPeople;

    private SimpleData simpleData;
    private List<Fragment> imgFragment;
    private List<Fragment> conFragment;

    public ShowFragment() {
        // Required empty public constructor
    }

    public static ShowFragment newInstance(String scriptData) {

        Bundle args = new Bundle();
        args.putSerializable("scriptData", scriptData);
        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        initPeopleViewPager();
        initContentViewPager();
        return view;
    }

    private void getData() {
        Bundle bundle = getArguments();
        try {
            JSONObject object = new JSONObject(bundle.getSerializable("scriptData").toString());
            JSONObject simple = new JSONObject(new JSONObject(object.optString("simpleData")).optString("simple_data"));
            JSONArray arrayPeople = new JSONArray(new JSONObject(object.optString("peopleData")).optString("peopleData"));
            JSONArray arrayContent = new JSONArray(new JSONObject(object.optString("contentData")).optString("contentData"));
            simpleData.setName(simple.optString("scriptName"));
            simpleData.setTitle(simple.optString("scriptTitle"));
            simpleData.setIntroduce(simple.optString("scriptIntroduce"));
            simpleData.setType(simple.optString("scriptType"));
            simpleData.setNumber(simple.optInt("scriptNumber"));
            simpleData.setImageAvatar("http://" + simple.optString("scriptImageAvatar"));
            for (int i = 0; i < arrayPeople.length(); i++) {
                PeopleData data = new PeopleData();
                data.setName(arrayPeople.getJSONObject(i).optString("peopleName"));
                data.setBG(arrayPeople.getJSONObject(i).optInt("peopleBG"));
                data.setSex(arrayPeople.getJSONObject(i).optInt("peopleSex"));
                conFragment = new ArrayList<>();
                conFragment.add(ImgFragment.newInstance(data.getSex(),data.getBG()));
            }
            for (int i = 0; i < arrayContent.length(); i++) {
                ContentData data = new ContentData();
                data.setPointer(arrayPeople.getJSONObject(i).optInt("contentPoint"));
                data.setContent(arrayPeople.getJSONObject(i).optString("contents"));
                conFragment = new ArrayList<>();
                conFragment.add(InfoFragment.newInstance(data));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initPeopleViewPager() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager, imgFragment);
        showPeople.setAdapter(adapter);
        showPeople.setCurrentItem(0);
        showPeople.setSlide(false);
    }

    private void initContentViewPager() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager, conFragment);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        viewpager.setSlide(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
