package com.hhtxproject.piafriendscollege.NavFragment.WriteScript;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hhtxproject.piafriendscollege.Adapter.ViewPagerAdapter;
import com.hhtxproject.piafriendscollege.Entity.event.JumpEvent;
import com.hhtxproject.piafriendscollege.Entity.event.PeopleDataEvent;
import com.hhtxproject.piafriendscollege.Entity.event.SimpleDataEvent;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.ContentFragment;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.PeopleFragment;
import com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment.SimpleFragment;
import com.hhtxproject.piafriendscollege.R;
import com.hhtxproject.piafriendscollege.Rx.RxBus;
import com.hhtxproject.piafriendscollege.Tools.DialogExit;
import com.hhtxproject.piafriendscollege.Tools.ViewPagerSlide;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class BaseWriteActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPagerSlide viewpager;

    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;

    String appid = "1254224113";
    String region = "ap-beijing";
    String secretId = "AKID8VM8Q3pk2W3z2UqpSCNXWZs1zpjYqcae";
    String secretKey ="YBfy790HLTh1zpr0QACJZd8Xl3FICD8w";
    long keyDuration = 600; //SecretKey 的有效时间，单位秒
    private CosXmlService cosXmlService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_write);
        ButterKnife.bind(this);
        setInitViewpager();
        getRxBus();
        getRexBusDataSave();
        initCosxml();
    }

    private void setInitViewpager() {
        fragments = new ArrayList<>();
        fragments.add(SimpleFragment.newInstance());
        fragments.add(PeopleFragment.newInstance());
        fragments.add(ContentFragment.newInstance());
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager, fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(2);
        viewpager.setSlide(false);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (((keyCode == KeyEvent.KEYCODE_BACK) ||
                (keyCode == KeyEvent.KEYCODE_HOME))
                && event.getRepeatCount() == 0) {
            new DialogExit(BaseWriteActivity.this);
        }
        return false;
    }

    private void getRxBus(){
        RxBus.getDefault().toObservable(JumpEvent.class).subscribe(new Action1<JumpEvent>() {
            @Override
            public void call(JumpEvent jumpEvent) {
                switch (jumpEvent.getNum()){
                    case -1:
                        new DialogExit(BaseWriteActivity.this);
                        break;
                    case 0:
                        viewpager.setCurrentItem(1);
                        break;
                    case 1:
                        viewpager.setCurrentItem(0);
                        break;
                    case 2:
                        viewpager.setCurrentItem(2);
                        break;
                    case 3:
                        viewpager.setCurrentItem(1);
                        break;
                    case 4:
                        Toast.makeText(BaseWriteActivity.this,"完成",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void getRexBusDataSave(){
        RxBus.getDefault().toObservable(SimpleDataEvent.class).subscribe(new Action1<SimpleDataEvent>() {
            @Override
            public void call(SimpleDataEvent simpleDataEvent) {
                Log.i("Json","{name : "+simpleDataEvent.getName()+" , number : "+simpleDataEvent.getNumber()+" " +
                        ", introduce : "+simpleDataEvent.getIntroduce()+" , imagePath : "+simpleDataEvent.getImagePath()+"}");
            }
        });
        RxBus.getDefault().toObservable(PeopleDataEvent.class).subscribe(new Action1<PeopleDataEvent>() {
            @Override
            public void call(PeopleDataEvent peopleDataEvent) {
                for (int i = 0;i<peopleDataEvent.getList().size();i++){
                    Log.i("Json","{角色名 - "+i+": "+peopleDataEvent.getList().get(i).getName()+" , 形象代码 -  : "+peopleDataEvent.getList().get(i).getBG()+" " +
                            ", 性别 -  : "+peopleDataEvent.getList().get(i).getSex()+"}");
                }
            }
        });
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
        Context context = getApplicationContext();//应用的上下文

        cosXmlService = new CosXmlService(context,serviceConfig, shortTimeCredentialProvider);
    }

    private void upLoadImage(String fileName,String image_path){
        String bucket = "test-image"; // cos v5 的 bucket格式为：xxx-appid, 如 test-1253960454
        String cosPath = fileName; //格式如 cosPath = "test.txt";
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
                float result = (float) (progress * 100.0/max);
                Log.w("TEST","progress =" + (long)result + "%");
            }
        });

        //使用异步回调上传：sdk 为对象存储各项服务提供异步回调操作方法
        cosXmlService.putObjectAsync(putObjectRequest, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                Log.w("TEST","success =" + result.accessUrl);
            }

            @Override
            public void onFail(CosXmlRequest cosXmlRequest, CosXmlClientException clientException, CosXmlServiceException serviceException)  {

                String errorMsg = clientException != null ? clientException.toString() : serviceException.toString();
                Log.w("TEST",errorMsg+"123456");
            }
        });
    }
}
