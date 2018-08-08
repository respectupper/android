package com.hhtxproject.piafriendscollege.NavFragment.WriteScript.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReleaseFragment extends Fragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;

    private MyRecyclerView adapter;
    private int [] cbg = {R.mipmap.yanqing,R.mipmap.yanqing,R.mipmap.yanqing,R.mipmap.yanqing,R.mipmap.yanqing,R.mipmap.yanqing,R.mipmap.yanqing};
    private String [] text = {"言情","古风","悬疑","同人","片段","恐怖","诗词"};

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_release, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecyclerview();
        return view;
    }

    private void setRecyclerview(){
        GridLayoutManager manager = new GridLayoutManager(getContext(),3,LinearLayoutManager.VERTICAL,false);
        adapter = new MyRecyclerView();
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }

    class MyRecyclerView extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_class,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setBackgroundResource(cbg[position]);
            holder.text.setText(text[position]);
        }

        @Override
        public int getItemCount() {
            return text.length;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
