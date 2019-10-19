package com.chs.swipebackfragment.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.swipebackfragment.sample.BaseSwipeBackFragment;
import com.chs.swipebackfragment.sample.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;


/**
 * Created by YoKeyword on 16/4/19.
 */
public class FirstSwipeBackFragment extends BaseSwipeBackFragment {
    private Toolbar mToolbar;

    public static FirstSwipeBackFragment newInstance() {

        Bundle args = new Bundle();

        FirstSwipeBackFragment fragment = new FirstSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_first, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("SwipeBackActivity的Fragment");
        _initToolbar(mToolbar);

        view.findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddFragmentListener != null) {
                    mAddFragmentListener.onAddFragment(FirstSwipeBackFragment.this, SecondSwipeBackFragment.newInstance());
                }
            }
        });

        return attachToSwipeBack(view);
    }


}
