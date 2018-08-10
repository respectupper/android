package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hhtxproject.piafriendscollege.Entity.ContentData;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.PeopleData;
import com.hhtxproject.piafriendscollege.Entity.SimpleData;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseFragment extends Fragment {


    @BindView(R.id.image)
    SimpleDraweeView image;
    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.release)
    Button release;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.introduction)
    TextView introduction;
    @BindView(R.id.edit)
    Button edit;

    private MyRecyclerView adapter;
    private ArrayList<SimpleData> simpleList;
    private ArrayList<PeopleData> peopleList;
    private ArrayList<ContentData> contentList;
    private int count = 1;

    public BrowseFragment() {
        // Required empty public constructor
    }

    public static BrowseFragment newInstance() {

        Bundle args = new Bundle();

        BrowseFragment fragment = new BrowseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecyclerview();
        getIntentData();
        setIntentData();
        editButton();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getIntentData() {
        simpleList = new ArrayList<>();
        peopleList = new ArrayList<>();
        contentList = new ArrayList<>();

        simpleList = (ArrayList<SimpleData>) getActivity().getIntent().getSerializableExtra("simpleData");
        peopleList = (ArrayList<PeopleData>) getActivity().getIntent().getSerializableExtra("peopleData");
        contentList = (ArrayList<ContentData>) getActivity().getIntent().getSerializableExtra("contentData");
        adapter.notifyDataSetChanged();
//        Log.i("simpleList", "{name : " + simpleList.get(0).getName() + " , number : " + simpleList.get(0).getNumber() + ", introduce : " + simpleList.get(0).getIntroduce() + " , imagePath : " + simpleList.get(0).getImagePath() + "}");
//        for (int i = 0; i < peopleList.size(); i++) {
//            Log.i("peopleList", "{name : " + peopleList.get(i).getName() + " , sex : " + peopleList.get(i).getSex() + " " +
//                    ", BG : " + peopleList.get(i).getBG() + "}");
//        }
//        for (int i = 0; i < contentList.size(); i++) {
//            Log.i("contentList", "{content : " + contentList.get(i).getContent() + " , pointer : " + contentList.get(i).getPointer() + "}");
//        }
    }

    private void setIntentData() {
        image.setImageURI(Uri.parse(simpleList.get(0).getImageUri()));
        name.setText(simpleList.get(0).getName());
        number.setText(simpleList.get(0).getNumber() + "");
        introduction.setText(simpleList.get(0).getIntroduce());
    }

    private void setRecyclerview() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new MyRecyclerView();
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);

        }
    }

    class MyRecyclerView extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.writescript_browse_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(peopleList.get(contentList.get(position).getPointer()).getName());
            holder.content.setText(contentList.get(position).getContent());
        }

        @Override
        public int getItemCount() {
            return contentList.size() == 0 ? 0 : contentList.size();
        }
    }

    private void editButton(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("提示")
                        .setMessage("返回编辑？")
                        .setIcon(R.drawable.testicon)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                JumpEvent event = new JumpEvent(5);
                                RxBus.getDefault().post(event);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                alertDialog.show();
            }
        });
        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JumpEvent event = new JumpEvent(6);
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
