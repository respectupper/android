package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.introduction)
    EditText introduction;
    @BindView(R.id.last)
    Button last;
    @BindView(R.id.next)
    Button next;

    private static final int IMAGE_REQUEST_CODE = 0;

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
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        unbinder = ButterKnife.bind(this, view);
        requestReadExternalPermission();
        setImage();
        setSpinner();
        setHint();
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

    private void setHint(){
        SpannableString nameHint = new SpannableString("填写名称");//定义hint的值
        AbsoluteSizeSpan nameAss = new AbsoluteSizeSpan(15,true);//设置字体大小 true表示单位是sp
        nameHint.setSpan(nameAss, 0, nameHint.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        name.setHint(nameHint);

        SpannableString introduceHint = new SpannableString("填写作品简介");//定义hint的值
        AbsoluteSizeSpan introduceAss = new AbsoluteSizeSpan(15,true);//设置字体大小 true表示单位是sp
        introduceHint.setSpan(introduceAss, 0, introduceHint.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        introduction.setHint(introduceHint);
    }

    private void setImage() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case IMAGE_REQUEST_CODE://这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        image.setImageURI(selectedImage);
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void setJump() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (TextUtils.isEmpty(name.getText())){
//                    Toast.makeText(getContext(),"剧本名不能为空", Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(aclasss)){
//                    Toast.makeText(getContext(),"剧本种类不能为空",Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(atitles)){
//                    Toast.makeText(getContext(),"剧本标题不能为空",Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(aintroduce)){
//                    Toast.makeText(getContext(),"剧本简介不能为空",Toast.LENGTH_SHORT).show();
//                }else {
//                    RxBus.getDefault().post(new JumpEvent(0));
//                    SimpleDataEvent event = new SimpleDataEvent();
//                    RxBus.getDefault().post(event);
//                }
            }
        });
    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_people_count, R.layout.my_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @SuppressLint("NewApi")
    private void requestReadExternalPermission() {
        if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                // 0 是自己定义的请求coude
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }
                return;
            }
            default:
                break;

        }
    }
}
