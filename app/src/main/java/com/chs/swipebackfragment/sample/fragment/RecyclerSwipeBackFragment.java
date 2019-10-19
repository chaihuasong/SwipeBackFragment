package com.chs.swipebackfragment.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.chs.swipebackfragment.sample.BaseSwipeBackFragment;
import com.chs.swipebackfragment.sample.PagerAdapter;
import com.chs.swipebackfragment.sample.R;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerSwipeBackFragment extends BaseSwipeBackFragment {
    private Toolbar mToolbar;

    private RecyclerView mRecy;
    private PagerAdapter mAdapter;

    public static RecyclerSwipeBackFragment newInstance() {
        RecyclerSwipeBackFragment fragment = new RecyclerSwipeBackFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_recy, container, false);

        initView(view);

        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        _initToolbar(mToolbar);

        mAdapter = new PagerAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (mAddFragmentListener != null) {
                    mAddFragmentListener.onAddFragment(RecyclerSwipeBackFragment.this, FirstSwipeBackFragment.newInstance());
                }
            }
        });

        // Init Datas
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String item;
            item = "收藏 " + i;
            items.add(item);
        }
        mAdapter.setDatas(items);
    }
}
