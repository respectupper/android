package com.hhtxproject.piafriendscollege.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.PiaVoice;
import com.hhtxproject.piafriendscollege.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Respectupper on 2018/4/25.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {//implements OnBannerListener {

    private List<PiaScript> listScript;
    private List<PiaVoice> listVoice;
    private String tag;
    class MyHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView iv_image;
        TextView tv_play_count;
        TextView tv_script_name;
        TextView tv_class;
        TextView tv_introduce;
        TextView tv_author;
        public MyHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_play_count = itemView.findViewById(R.id.tv_play_count);
            tv_script_name = itemView.findViewById(R.id.tv_script_name);
            tv_class = itemView.findViewById(R.id.tv_class);
            tv_introduce = itemView.findViewById(R.id.tv_introduce);
            tv_author = itemView.findViewById(R.id.tv_author);
        }
    }

    public RecyclerViewAdapter(Context context, List<PiaVoice> listData, List<PiaScript> listData2, String tag){
        this.listVoice = listData;
        this.listScript = listData2;
        this.tag = tag;
        Fresco.initialize(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_voice_item,parent,false);
        Log.i("viewType+++",viewType+"");
        return new RecyclerViewAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if ("voice".equals(tag)){
            holder.iv_image.setImageURI(listVoice.get(position).getImageAvatar());
            holder.tv_script_name.setText(listVoice.get(position).getPiaScript().getScriptName());
            holder.tv_play_count.setText(listVoice.get(position).getPlayerCount()+"");
            holder.tv_introduce.setText(listVoice.get(position).getPlayerIntroduce());
            holder.tv_author.setText(listVoice.get(position).getPiaScript().getPiaUser().getUsername());
            holder.tv_class.setText(listVoice.get(position).getPlayerClass());
        }else if ("script".equals(tag)){
            holder.iv_image.setImageURI(listScript.get(position).getScriptImgAvatar());
            holder.tv_script_name.setText(listScript.get(position).getScriptName());
            holder.tv_play_count.setText(listScript.get(position).getScriptBrowse()+"");
            holder.tv_introduce.setText(listScript.get(position).getScriptIntroduce());
            holder.tv_author.setText(listScript.get(position).getPiaUser().getUsername());
            holder.tv_class.setText(listScript.get(position).getScriptClass());
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        int count = 0;
//        if (listVoice.get(position).getType()==1){
//            count = listVoice.get(position).getType();
//        }else if (listScript.get(position).getTag()==0){
//            count = listScript.get(position).getType();
//        }
//        return count;
//
//    }

    @Override
    public int getItemCount() {
        int count = 0;
        if ("voice".equals(tag)){
            count = listVoice.size()==0?0:listVoice.size();
        }else if ("script".equals(tag)){
            count = listScript.size()==0?0:listScript.size();
        }
        return count;
    }

//    private void setBanner(Banner banner) {
//
//        ArrayList<String> list_path;
//        ArrayList<String> list_title;
//        //放图片地址的集合
//        list_path = new ArrayList<>();
//        //放标题的集合
//        list_title = new ArrayList<>();
//
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
//        list_title.add("好好学习");
//        list_title.add("天天向上");
//        list_title.add("热爱劳动");
//        list_title.add("不搞对象");
//        //设置内置样式，共有六种可以点入方法内逐一体验使用。
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        //设置图片加载器，图片加载器在下方
//        banner.setImageLoader(new MyLoader());
//        //设置图片网址或地址的集合
//        banner.setImages(list_path);
//        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
//        banner.setBannerAnimation(Transformer.Default);
//        //设置轮播图的标题集合
//        banner.setBannerTitles(list_title);
//        //设置轮播间隔时间
//        banner.setDelayTime(3000);
//        //设置是否为自动轮播，默认是“是”。
//        banner.isAutoPlay(true);
//        //设置指示器的位置，小点点，左中右。
//        banner.setIndicatorGravity(BannerConfig.CENTER)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//                .setOnBannerListener(this)
//                //必须最后调用的方法，启动轮播图。
//                .start();
//    }
//
//    //轮播图的监听方法
//    @Override
//    public void OnBannerClick(int position) {
//        Log.i("tag", "你点了第" + position + "张轮播图");
//    }
//
//    //自定义的图片加载器
//    private class MyLoader extends ImageLoader {
//
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//            Glide.with(context).load((String) path).into(imageView);
//        }
//    }

}
