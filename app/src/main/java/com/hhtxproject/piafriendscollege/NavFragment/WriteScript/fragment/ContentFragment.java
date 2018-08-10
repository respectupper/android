package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.hhtxproject.piafriendscollege.Entity.ContentData;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.PeopleData;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

import static com.hhtxproject.piafriendscollege.Entity.StaticData.BG.boy;
import static com.hhtxproject.piafriendscollege.Entity.StaticData.BG.girl;

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
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.preview)
    TextView preview;
    @BindView(R.id.delete)
    TextView delete;

    private List<PeopleData> data;
    private int peoplePointer = 0;
    private int indexPointer = 0;
    private List<ContentData> list;

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
        left.setVisibility(View.INVISIBLE);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!content.getText().toString().trim().equals("") || indexPointer == list.size()) {
                    if (indexPointer >= 1) {
                        right.setVisibility(View.VISIBLE);
                        if (indexPointer == 1) {
                            left.setVisibility(View.INVISIBLE);
                            Log.i("!!!+++!!!!!", "一号通道");
                        }
                        if (indexPointer == list.size()) {
                            if (!"".equals(content.getText().toString().trim())) {
                                ContentData event = new ContentData();
                                event.setPointer(peoplePointer);
                                event.setContent(content.getText().toString().trim());
                                list.add(event);
                                Log.i("!!!+++!!!!!", "二号通道");
                            }
                        } else {
                            ContentData event = new ContentData();
                            event.setPointer(peoplePointer);
                            event.setContent(content.getText().toString().trim());
                            list.set(indexPointer, event);
                            Log.i("!!!+++!!!!!", "三号通道");
                        }
                        indexPointer--;
                        peoplePointer = list.get(indexPointer).getPointer();
                        content.setText(list.get(indexPointer).getContent());
                        spinner.setSelection(list.get(indexPointer).getPointer());
                        Refresh(data.get(peoplePointer).getSex(), data.get(peoplePointer).getBG());
                        Log.i("!!!+++!!!!!", "四号通道");
                    }
                    Log.i("indexPointer!!!+++!!!!!", "indexPointer = " + indexPointer + ", list.size() = " + list.size());
                } else {
                    Toast.makeText(getContext(), "内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!content.getText().toString().trim().equals("")) {
                    left.setVisibility(View.VISIBLE);
                    if (indexPointer == list.size()) {
                        ContentData event = new ContentData();
                        event.setPointer(peoplePointer);
                        event.setContent(content.getText().toString().trim());
                        list.add(event);
                        indexPointer++;
                        peoplePointer = 0;
                        Reset(data.get(peoplePointer).getSex(), data.get(peoplePointer).getBG());
                        Log.i("!!!+++!!!!!", "七号通道");
                    } else {
                        ContentData event = new ContentData();
                        event.setPointer(peoplePointer);
                        event.setContent(content.getText().toString().trim());
                        list.set(indexPointer, event);
                        indexPointer++;
                        if (indexPointer == list.size()) {
                            Reset(data.get(0).getSex(), data.get(0).getBG());
                        } else {
                            content.setText(list.get(indexPointer).getContent());
                            spinner.setSelection(list.get(indexPointer).getPointer());
                            peoplePointer = list.get(indexPointer).getPointer();
                            Refresh(data.get(peoplePointer).getSex(), data.get(peoplePointer).getBG());
                        }
                    }
                    Log.i("indexPointer!!!+++!!!!!", "indexPointer = " + indexPointer + ", list.size() = " + list.size());
                } else {
                    Toast.makeText(getContext(), "内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size()!=0){
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("提示")
                            .setMessage("确定删除？")
                            .setIcon(R.drawable.testicon)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    list.remove(indexPointer);
                                    if (indexPointer > 0){
                                        indexPointer--;
                                    }
                                    peoplePointer = list.get(indexPointer).getPointer();
                                    content.setText(list.get(indexPointer).getContent());
                                    spinner.setSelection(list.get(indexPointer).getPointer());
                                    Refresh(data.get(peoplePointer).getSex(), data.get(peoplePointer).getBG());
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
                else {
                    Toast.makeText(getContext(),"已经是第一条",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getRxBusData() {
        data = new ArrayList<>();
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 200) {
                    List<PeopleData> list = (List<PeopleData>) msg.obj;
                    data.addAll(list);
                    List<String> nameList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        nameList.add(list.get(i).getName());
                    }
                    setSpinner(nameList);
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                RxBus.getDefault().toObservable(PeopleData.class).subscribe(new Action1<PeopleData>() {
                    @Override
                    public void call(PeopleData peopleData) {
                        Message msg = new Message();
                        msg.obj = peopleData.getList();
                        msg.what = 200;
                        handler.sendMessage(msg);
                    }
                });
            }
        }).start();
    }

    private void setSpinner(List<String> lists) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, android.R.id.text1, lists);
        spinner.setAdapter(adapter);
        spinner.setPrompt("选择名字");
        /**选项选择监听*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                peoplePointer = i;
                switch (data.size() == 0 ? 2 : data.get(i).getSex()) {
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

    private void Reset(int sex, int position) {
        switch (sex) {
            case 0:
                image.setBackgroundResource(0);
                image.setBackgroundResource(girl[position]);
                break;
            case 1:
                image.setBackgroundResource(0);
                image.setBackgroundResource(boy[position]);
                break;
            default:
                break;
        }
        spinner.setSelection(0);
        content.setText("");
    }

    private void Refresh(int sex, int pointer) {
        switch (sex) {
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

    class MyHolderView extends RecyclerView.ViewHolder {

        TextView name;
        TextView content;

        public MyHolderView(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
        }
    }

    class MyRecyclerView extends RecyclerView.Adapter<MyHolderView> {

        @Override
        public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.writescript_browse_item, parent, false);
            return new MyHolderView(view);
        }

        @Override
        public void onBindViewHolder(MyHolderView holder, int position) {
            holder.name.setText(data.get(list.get(position).getPointer()).getName());
            holder.content.setText(list.get(position).getContent());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private void saveData() {
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRecyclerView adapter = new MyRecyclerView();
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                View view_preview = View.inflate(getContext(), R.layout.layout_preview, null);
                RecyclerView recyclerView = view_preview.findViewById(R.id.recyc_preview);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("预览")
                        .setIcon(R.drawable.testicon)
                        .setView(view_preview)
                        .create();
                alertDialog.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("提示")
                        .setMessage("返回上页面后此页面数据将被清空,确认返回吗？")
                        .setIcon(R.drawable.testicon)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.clear();
                                peoplePointer = 0;
                                indexPointer = 0;
                                list.clear();
                                JumpEvent event = new JumpEvent(3);
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
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexPointer == list.size()) {
                    if (content.getText().toString().trim().equals("")) {
                        ContentData data = new ContentData();
                        data.setList(list);
                        RxBus.getDefault().post(data);
                        Log.i("text", "zhwlitongguo1");
                    } else {
                        ContentData event = new ContentData();
                        event.setPointer(peoplePointer);
                        event.setContent(content.getText().toString().trim());
                        list.add(event);
                        ContentData data = new ContentData();
                        data.setList(list);
                        RxBus.getDefault().post(data);
                        Log.i("text", "zhwlitongguo2");
                    }
                } else {
                    ContentData data = new ContentData();
                    data.setPointer(peoplePointer);
                    data.setContent(content.getText().toString().trim());
                    list.set(indexPointer, data);
                    data.setList(list);
                    RxBus.getDefault().post(data);
                }
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
