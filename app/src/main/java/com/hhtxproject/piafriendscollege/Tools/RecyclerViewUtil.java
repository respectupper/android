package com.hhtxproject.piafriendscollege.Tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewUtil {
    private boolean enable = true;
    private RecyclerView mRecyclerView;
    private OnLoadMoreListener mOnLoadMoreListener;

    private GestureDetector mGestureDetector = null;
    private RecyclerView.SimpleOnItemTouchListener mSimpleOnItemTouchListener;
    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;

    public RecyclerViewUtil(Context mContext, RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;

        mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            //长按事件
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                if (mOnItemLongClickListener != null) {
                    View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null) {
                        int position = mRecyclerView.getChildLayoutPosition(childView);
                        mOnItemLongClickListener.onItemLongClick(position, childView);
                    }
                }
            }

            //单击事件
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mOnItemClickListener != null) {
                    View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null) {
                        int position = mRecyclerView.getChildLayoutPosition(childView);
                        mOnItemClickListener.onItemClick(position, childView);
                        return true;
                    }
                }

                return super.onSingleTapUp(e);
            }
        });

        mSimpleOnItemTouchListener = new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (mGestureDetector.onTouchEvent(e)) {
                    return true;
                }
                return false;
            }
        };

        mRecyclerView.addOnItemTouchListener(mSimpleOnItemTouchListener);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && isBottom(mRecyclerView)) {
                    if (enable && mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    /**
     * true 打开上拉加载 false，关闭上拉加载
     * @param enable
     */
    public void setLoadMoreEnable(boolean enable) {
        this.enable = enable;
    }

    private boolean isBottom(RecyclerView recyclerView) {
        if (recyclerView == null)
            return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    //长按事件接口
    public interface OnItemLongClickListener {
        public void onItemLongClick(int position, View view);
    }

    //单击事件接口
    public interface OnItemClickListener {
        public void onItemClick(int position, View view);
    }

}
