package com.hhtxproject.piafriendscollege.PlayRoom.ShowContent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhtxproject.piafriendscollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.hhtxproject.piafriendscollege.Entity.StaticData.BG.boy;
import static com.hhtxproject.piafriendscollege.Entity.StaticData.BG.girl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImgFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.img)
    ImageView img;

    public ImgFragment() {
        // Required empty public constructor
    }

    public static ImgFragment newInstance(int sex, int pointer) {

        Bundle args = new Bundle();
        args.putSerializable("sex", sex);
        args.putSerializable("pointer", pointer);
        ImgFragment fragment = new ImgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_img, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        switch ((int) bundle.getSerializable("sex")) {
            case 0:
                img.setBackgroundResource(girl[(int)bundle.getSerializable("pointer")]);
                break;
            case 1:
                img.setBackgroundResource(boy[(int)bundle.getSerializable("pointer")]);
                break;
            default:
                break;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
