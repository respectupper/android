package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    @BindView(R.id.spinner)
    Spinner spinner;
    Unbinder unbinder;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.aclass)
    EditText aclass;
    @BindView(R.id.parent_view)
    LinearLayout parentView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_title_right)
    TextView toolbarTitleRight;
    @BindView(R.id.titles)
    EditText titles;
    @BindView(R.id.introduce)
    EditText introduce;

    private String aname;
    private String aclasss;
    private int count;
    private String atitles;
    private String aintroduce;

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance() {

        Bundle args = new Bundle();
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        unbinder = ButterKnife.bind(this, view);
        setSpinner();
        setEditTextListener();
        setToolber();
        setJump();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setSpinner() {
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            count = i + 1;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private void setEditTextListener() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                aname = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        aclass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                aclasss = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        parentView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (null != getActivity().getCurrentFocus()) {
                    /**
                     * 点击空白位置 隐藏软键盘
                     */
                    InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    return mInputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                }
                return false;
            }
        });

        titles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                atitles = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        introduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                aintroduce = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setToolber(){
        toolbarTitle.setText("返回");
        toolbarTitleRight.setText("下一步");
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitleRight.setVisibility(View.VISIBLE);
    }

    private void setJump(){
        toolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new JumpEvent(-1));
            }
        });

        toolbarTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(aname)){
                    Toast.makeText(getContext(),"剧本名不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(aclasss)){
                    Toast.makeText(getContext(),"剧本种类不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(atitles)){
                    Toast.makeText(getContext(),"剧本标题不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(aintroduce)){
                    Toast.makeText(getContext(),"剧本简介不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    RxBus.getDefault().post(new JumpEvent(0));
                    SimpleDataEvent event = new SimpleDataEvent();
                    event.setName(aname);
                    event.setAclass(aclasss);
                    event.setNumber(count);
                    event.setTitle(atitles);
                    event.setIntroduce(aintroduce);
                    RxBus.getDefault().post(event);
                }
            }
        });
    }
}
