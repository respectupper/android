package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhtxproject.piafriendscollege.Entity.ContentData;
import com.hhtxproject.piafriendscollege.Entity.PeopleData;
import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.SimpleData;
import com.hhtxproject.piafriendscollege.Net.LoadData;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.app.PApplication;
import com.loopj.android.http.RequestParams;
import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.model.object.PutObjectRequest;
import com.tencent.qcloud.core.auth.ShortTimeCredentialProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.hhtxproject.piafriendscollege.Entity.StaticData.FL.cbg;
import static com.hhtxproject.piafriendscollege.Entity.StaticData.FL.text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReleaseFragment extends Fragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.release)
    TextView release;
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.introduction)
    TextView introduction;
    @BindView(R.id.title)
    EditText title;

    private static final int ImageUpLoadFinish = 201;
    private static final int ScriptUpLoadFinish = 202;
    private ProgressBar progressBar;
    private View progressView;

    String appid = "1254224113";
    String region = "ap-beijing";
    String secretId = "AKID8VM8Q3pk2W3z2UqpSCNXWZs1zpjYqcae";
    String secretKey ="YBfy790HLTh1zpr0QACJZd8Xl3FICD8w";
    long keyDuration = 600; //SecretKey 的有效时间，单位秒
    private CosXmlService cosXmlService;

    private ArrayList<SimpleData> simpleList;
    private ArrayList<PeopleData> peopleList;
    private ArrayList<ContentData> contentList;
    private MyRecyclerView adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ImageUpLoadFinish:
                    simpleList.get(0).setImageAvatar((String) msg.obj);
                    break;
                case ScriptUpLoadFinish:
                    if("true".equals(msg.obj.toString())){

                    }else {
                        Toast.makeText(getContext(),"上传失败请重试",Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public ReleaseFragment() {
        // Required empty public constructor
    }

    public static ReleaseFragment newInstance() {

        Bundle args = new Bundle();

        ReleaseFragment fragment = new ReleaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_release, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecyclerview();
        getIntentData();
//        setIntentData();
        setRecyclerClickEvent();
        UpLoderData();
        initCosxml();
        return view;
    }

    private void getIntentData() {
        simpleList = new ArrayList<>();
        peopleList = new ArrayList<>();
        contentList = new ArrayList<>();

        simpleList = (ArrayList<SimpleData>) getActivity().getIntent().getSerializableExtra("simpleData");
        peopleList = (ArrayList<PeopleData>) getActivity().getIntent().getSerializableExtra("peopleData");
        contentList = (ArrayList<ContentData>) getActivity().getIntent().getSerializableExtra("contentData");


    }

    private void setIntentData() {
        image.setImageURI(Uri.parse(simpleList.get(0).getImageUri()));
        name.setText(simpleList.get(0).getName());
        number.setText(simpleList.get(0).getNumber() + "");
        introduction.setText(simpleList.get(0).getIntroduce());
        simpleList.get(0).setType("null");
        simpleList.get(0).setTitle("null");
    }

    private void setRecyclerview() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        adapter = new MyRecyclerView();
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }
    public interface OnItemLongClickListener{
        public void onItemLongClick(View view,int position);
    }

    class MyRecyclerView extends RecyclerView.Adapter<ViewHolder> {

        private OnItemClickListener mOnItemClickListener;
        private OnItemLongClickListener mOnItemLongClickListener;

        public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
            this.mOnItemLongClickListener = mOnItemLongClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_class, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setBackgroundResource(cbg[position]);
            holder.text.setText(text[position]);

            if(mOnItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(holder.itemView,position);
                    }
                });
            }
            if(mOnItemLongClickListener!=null){
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                        return true;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return text.length;
        }
    }

    private void setRecyclerClickEvent(){
        adapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                simpleList.get(0).setType(text[position]);
                Toast.makeText(getContext(),"你选的类型是"+text[position],Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpLoderData(){
        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"null".equals(title.getText().toString().trim())&&!"null".equals(simpleList.get(0).getType())){
                    AlertDialog alertDialog = null;
                    if (progressBar.getProgress()==0){
                        try {
                            setUploaderProgress();
                            simpleList.get(0).setTitle(title.getText().toString().trim());
                            alertDialog = new AlertDialog.Builder(getContext())
                                    .setTitle("上传")
                                    .setIcon(R.drawable.testicon)
                                    .setView(progressView)
                                    .create();
                            alertDialog.show();
                            UpLoading();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        alertDialog.show();
                    }
                }else {
                    Toast.makeText(getContext(),"请完善信息",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void UpLoading() throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Date day=new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    upLoadImage(simpleList.get(0).getName()+"-"+df.format(day),simpleList.get(0).getImagePath());
                    RequestParams params = new RequestParams();
                    params.add("pia_user_id", String.valueOf(new PApplication().getUserId()));
                    params.add("pia_release","0");
                    params.add("pia_simple_content",upLoadSimpleData());
                    params.add("pia_people_content",upLoadPeopleData());
                    params.add("pia_content_content",upLoadContentData());
                    new LoadData(handler,"/request/uploadScript",params,ScriptUpLoadFinish).getData().sendToTarget();;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setUploaderProgress(){
        progressView = View.inflate(getContext(), R.layout.layout_dialog_progressbar, null);
        progressBar = progressView.findViewById(R.id.progressBar);//设置滚动条可见
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    private void initCosxml(){
        //创建 CosXmlServiceConfig 对象，根据需要修改默认的配置参数
        CosXmlServiceConfig serviceConfig = new CosXmlServiceConfig.Builder()
                .setAppidAndRegion(appid, region)
                .setDebuggable(true)
                .builder();

        //创建获取签名类(请参考下面的生成签名示例，或者参考 sdk中提供的ShortTimeCredentialProvider类）
        ShortTimeCredentialProvider shortTimeCredentialProvider = new ShortTimeCredentialProvider(secretId, secretKey, keyDuration);

        //创建 CosXmlService 对象，实现对象存储服务各项操作.
        Context context = getActivity().getApplicationContext();//应用的上下文

        cosXmlService = new CosXmlService(context,serviceConfig, shortTimeCredentialProvider);
    }

    private void upLoadImage(String fileName,String image_path){
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

        String bucket = "test-image"; // cos v5 的 bucket格式为：xxx-appid, 如 test-1253960454
        String cosPath = fileName+"."+type; //格式如 cosPath = "test.txt";
        String srcPath = image_path; // 如 srcPath = Environment.getExternalStorageDirectory().getPath() + "/test.txt";

        long signDuration = 600; //签名的有效期，单位为秒

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, cosPath, srcPath);

        putObjectRequest.setSign(signDuration,null,null); //若不调用，则默认使用sdk中sign duration（60s）

/*设置进度显示
  实现 CosXmlProgressListener.onProgress(long progress, long max)方法，
  progress 已上传的大小， max 表示文件的总大小
*/
        putObjectRequest.setProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long progress, long max) {
                float result = (float) (progress/max);
            }
        });

        //使用异步回调上传：sdk 为对象存储各项服务提供异步回调操作方法
        cosXmlService.putObjectAsync(putObjectRequest, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Message msg = Message.obtain(handler);
                msg.what = ImageUpLoadFinish;
                msg.obj = result.accessUrl;
                msg.sendToTarget();
                Log.w("TEST","success =" + result.accessUrl);
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException)  {

                String errorMsg = clientException != null ? clientException.toString() : serviceException.toString();
                Log.w("TEST",errorMsg+"上传错误");
            }
        });
    }

    private String upLoadSimpleData() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("scriptUserId",new PApplication().getUserId());
        object.put("scriptName",simpleList.get(0).getName());
        object.put("scriptTitle",simpleList.get(0).getTitle());
        object.put("scriptIntroduce",simpleList.get(0).getIntroduce());
        object.put("scriptType",simpleList.get(0).getType());
        object.put("scriptNumber",simpleList.get(0).getNumber());
        object.put("scriptImageAvatar",simpleList.get(0).getImageAvatar());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("simpleData",object);
        return jsonObject.toString();
    }

    private String upLoadPeopleData() throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0;i<peopleList.size();i++){
            JSONObject object = new JSONObject();
            object.put("peopleName",peopleList.get(i).getName());
            object.put("peopleBG",peopleList.get(i).getBG());
            object.put("peopleSex",peopleList.get(i).getSex());
            array.put(object);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("peopleData", array);
        return jsonObject.toString();
    }

    private String upLoadContentData() throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0;i<contentList.size();i++){
            JSONObject object = new JSONObject();
            object.put("contentPoint",contentList.get(i).getPointer());
            object.put("contents",contentList.get(i).getContent());
            array.put(object);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentData", array);
        return jsonObject.toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
