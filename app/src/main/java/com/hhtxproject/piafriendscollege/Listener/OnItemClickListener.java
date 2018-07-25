package com.hhtxproject.piafriendscollege.Listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhtxproject.piafriendscollege.Adapter.ModeRecycAdapter;

/**
 * Created by Respectupper on 2018/6/11.
 */

public interface OnItemClickListener {
    void onItemClick(RecyclerView.ViewHolder view, int position);
}
