package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;

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
    private Uri selectedImage;
    private String image_path = "null";
    private int count = 1;

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
                        selectedImage = data.getData(); //获取系统返回的照片的Uri
                        image.setImageURI(selectedImage);
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        image_path = cursor.getString(columnIndex);

                        //获取图片扩展名
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(image_path, options);
                        String type = options.outMimeType;
                        if (TextUtils.isEmpty(type)) {
                            type = "未能识别的图片";
                        } else {
                            type = type.substring(6, type.length());
                        }
//                        upLoadImage("."+type,image_path);
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
                if (image_path.equals("null")){
                    Log.i("selectedImage","图片不能为空");
                }else if (TextUtils.isEmpty(introduction.getText())){
                    Log.i("introduction","简介不能为空");
                }else if (TextUtils.isEmpty(name.getText())){
                    Log.i("name","剧本名不能为空");
                }else {
                    saveRxData();
                    RxBus.getDefault().post(new JumpEvent(0));
                }
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new JumpEvent(-1));
            }
        });
    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_people_count, R.layout.my_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        /**选项选择监听*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                    getActivity().finish();
                }
                return;
            }
            default:
                break;
        }
    }

    private void saveRxData(){
        SimpleDataEvent simpleDataEvent = new SimpleDataEvent();
        simpleDataEvent.setName(name.getText().toString().trim());
        simpleDataEvent.setIntroduce(introduction.getText().toString().trim());
        simpleDataEvent.setNumber(count);
        simpleDataEvent.setImagePath(image_path);
        RxBus.getDefault().post(simpleDataEvent);
    }
}
