package com.chs.swipebackfragment.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chs.swipebackfragment.SwipeBackLayout;
import com.chs.swipebackfragment.sample.BaseSwipeBackFragment;
import com.chs.swipebackfragment.sample.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

/**
 * Created by YoKeyword on 16/4/19.
 */
public class SecondSwipeBackFragment extends BaseSwipeBackFragment {
    private Toolbar mToolbar;

    public static SecondSwipeBackFragment newInstance() {

        Bundle args = new Bundle();

        SecondSwipeBackFragment fragment = new SecondSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_second, container, false);

        initToolbar(view);

        view.findViewById(R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddFragmentListener != null) {
                    mAddFragmentListener.onAddFragment(SecondSwipeBackFragment.this, ThirdSwipeBackFragment.newInstance());
                }
            }
        });

        getSwipeBackLayout().addSwipeListener(new SwipeBackLayout.OnSwipeListener() {
            @Override
            public void onDragStateChange(int state) {
            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
            }

            @Override
            public void onDragScrolled(float scrollPercent) {
            }
        });
        return attachToSwipeBack(view);
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        _initToolbar(mToolbar);

        Button btnSet = (Button) view.findViewById(R.id.btn_set);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(), v, GravityCompat.END);
                popupMenu.inflate(R.menu.swipe_orientation);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_left:
                                getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_LEFT);
                                Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_right:
                                getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_RIGHT);
                                Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_all:
                                getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
                                Toast.makeText(getActivity(), "all", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        popupMenu.dismiss();
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}
