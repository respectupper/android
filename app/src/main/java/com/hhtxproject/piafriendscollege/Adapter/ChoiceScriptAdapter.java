package com.hhtxproject.piafriendscollege.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.R;

public class ChoiceScriptAdapter extends RecyclerView.Adapter<ChoiceScriptAdapter.ViewHolder> {
    protected Context mContext;
    /**
     * choice_img1 悬疑
     * choice_img2 恐怖
     * choice_img3 言情
     * choice_img4 同人
     * choice_img5 古风
     * choice_img6 片段
     * choice_img7 诗词
     */
    private String[] texts={"言情","古风","悬疑","同人","片段","恐怖","诗词"};
    private int[] imgs={R.mipmap.choice_img3,R.mipmap.choice_img5,R.mipmap.choice_img1,R.mipmap.choice_img4,R.mipmap.choice_img6,R.mipmap.choice_img2,R.mipmap.choice_img7};

    public ChoiceScriptAdapter(Context context){
        this.mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_choice_script_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(texts[position]);
        holder.choice_script_back_item.setBackgroundResource(imgs[position]);
    }

    @Override
    public int getItemCount() {
        return texts.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView choice_script_back_item;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            choice_script_back_item = itemView.findViewById(R.id.choice_script_back_item);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
