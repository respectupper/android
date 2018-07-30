package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.request.target.Target;
import com.hhtxproject.piafriendscollege.R;

import java.io.FileNotFoundException;

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
    ImageView image;
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
    @BindView(R.id.addImg)
    ImageButton addImg;

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
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        unbinder = ButterKnife.bind(this, view);
        setImage();
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

    private void setImage() {
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        image.setImageBitmap(bitmap);
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

    }
}
