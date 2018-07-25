package com.hhtxproject.piafriendscollege.Adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhtxproject.piafriendscollege.Listener.OnItemClickListener;
import com.hhtxproject.piafriendscollege.R;


public class ModeRecycAdapter extends RecyclerView.Adapter<ModeRecycAdapter.ViewHolder>{

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private int [] inage = {R.mipmap.danren_mode,R.mipmap.danren_mode,R.mipmap.danren_mode,R.mipmap.danren_mode,R.mipmap.danren_mode};
    protected Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView back_card;
        public ViewHolder(View itemView) {
            super(itemView);
            back_card = itemView.findViewById(R.id.back_img_mode);
        }
    }
    public ModeRecycAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mode_item,parent,false);
        Log.i("viewType+++",viewType+"");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.back_card.setBackgroundResource(inage[position]);
        holder.back_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(holder,holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inage.length;
    }

}
