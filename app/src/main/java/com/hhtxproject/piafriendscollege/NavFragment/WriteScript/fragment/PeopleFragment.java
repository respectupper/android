package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.PeopleAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SelectEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
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
public class PeopleFragment extends Fragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_title_right)
    TextView toolbarTitleRight;
    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private List<String> name;
    private List<String> introduce;
    private List<Integer> sex;
    private List<Integer> BH;
    private int count;
    private MyRecyclerViewAdapter adapter;
    private int [] woman = {R.mipmap.gw1,R.mipmap.gw2,R.mipmap.gw3,R.mipmap.gw4,R.mipmap.gw5};
    private int [] man = {R.mipmap.gm1,R.mipmap.gm2,R.mipmap.gm3,R.mipmap.gm4,R.mipmap.gm5};

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
        initArray();
        setToolber();
        setJump();
        setRecyclerView();
        getRxBus();
        return view;
    }

    private void setToolber() {
        toolbarTitle.setText("上一步");
        toolbarTitleRight.setText("下一步");
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitleRight.setVisibility(View.VISIBLE);
    }

    private void setJump() {
        toolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new JumpEvent(1));
            }
        });

        toolbarTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.size()!=count&&sex.size()!=count&&introduce.size()!=count&&BH.size()!=count){
                    Toast.makeText(getContext(),"请完善信息",Toast.LENGTH_SHORT).show();
                }else {
                    RxBus.getDefault().post(new JumpEvent(2));
                    PeopleDataEvent event = new PeopleDataEvent();
                    event.setName(name);
                    event.setSex(sex);
                    event.setBH(BH);
                    event.setIntroduce(introduce);
                    RxBus.getDefault().post(event);
                }
            }
        });
    }

    private void getRxBus(){
        RxBus.getDefault().toObservable(SimpleDataEvent.class).subscribe(new Action1<SimpleDataEvent>() {
            @Override
            public void call(SimpleDataEvent simpleDataEvent) {
                count = simpleDataEvent.getNumber();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter = new MyRecyclerViewAdapter();
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    class MyHolderView extends RecyclerView.ViewHolder{

        ImageView people;
        EditText name;
        Spinner sex_spinner;
        EditText introduce;
        Spinner spinner;
        public MyHolderView(View itemView) {
            super(itemView);
            people = itemView.findViewById(R.id.people);
            name = itemView.findViewById(R.id.name);
            spinner = itemView.findViewById(R.id.spinner);
            sex_spinner = itemView.findViewById(R.id.sex_spinner);
            introduce = itemView.findViewById(R.id.introduce);
        }
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyHolderView>{

        @Override
        public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_select_people_item,parent,false);
            return new MyHolderView(view);
        }

        @Override
        public void onBindViewHolder(MyHolderView holder, int position) {
            holder.name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    name.add(position,charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            holder.introduce.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    introduce.add(position,charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            PeopleAdapter pa = new PeopleAdapter(getContext(),new String[] {"女","男"});
            holder.sex_spinner.setAdapter(pa);
            switch (position){
                case 0:
                    holder.sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sex.add(position,i);
                            PeopleAdapter BHS = new PeopleAdapter(getContext(),new String[] {"BH0","BH1","BH2","BH3","BH4"});
                            holder.spinner.setAdapter(BHS);
                            switch (i){
                                case 0:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(woman[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                                case 1:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(man[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
                case 1:
                    holder.sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sex.add(position,i);
                            PeopleAdapter BHS = new PeopleAdapter(getContext(),new String[] {"BH0","BH1","BH2","BH3","BH4"});
                            holder.spinner.setAdapter(BHS);
                            switch (i){
                                case 0:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(woman[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                                case 1:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(man[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
                case 2:
                    holder.sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sex.add(position,i);
                            PeopleAdapter BHS = new PeopleAdapter(getContext(),new String[] {"BH0","BH1","BH2","BH3","BH4"});
                            holder.spinner.setAdapter(BHS);
                            switch (i){
                                case 0:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(woman[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                                case 1:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(man[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
                case 3:
                    holder.sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sex.add(position,i);
                            PeopleAdapter BHS = new PeopleAdapter(getContext(),new String[] {"BH0","BH1","BH2","BH3","BH4"});
                            holder.spinner.setAdapter(BHS);
                            switch (i){
                                case 0:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(woman[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                                case 1:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(man[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
                case 4:
                    holder.sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sex.add(position,i);
                            PeopleAdapter BHS = new PeopleAdapter(getContext(),new String[] {"BH0","BH1","BH2","BH3","BH4"});
                            holder.spinner.setAdapter(BHS);
                            switch (i){
                                case 0:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(woman[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                                case 1:
                                    holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            holder.people.setBackgroundResource(man[i]);
                                            BH.add(position,i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return count==0?0:count;
        }
    }

    private void initArray(){
        name = new ArrayList<>();
        sex = new ArrayList<>();
        BH = new ArrayList<>();
        introduce = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
