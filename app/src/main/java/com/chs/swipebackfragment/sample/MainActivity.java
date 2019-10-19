package com.chs.swipebackfragment.sample;

import android.os.Bundle;
import android.widget.Toast;

import com.chs.swipebackfragment.SwipeBackActivity;
import com.chs.swipebackfragment.sample.fragment.FirstSwipeBackFragment;

import androidx.fragment.app.Fragment;

public class MainActivity extends SwipeBackActivity implements BaseSwipeBackFragment.OnAddFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 给其下Fragment的默认背景 (默认使用Fragment根布局的background属性,如若没有则使用Theme的windowBackground属性)
        setDefaultFragmentBackground(android.R.color.white);

        if (savedInstanceState == null) {
            FirstSwipeBackFragment firstFragment = FirstSwipeBackFragment.newInstance();
            loadFragment(firstFragment);
        } else {
            Toast.makeText(MainActivity.this, "啊哦,app被强杀喽~", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity可以滑动退出, 并且总是优先;  false: Activity不允许滑动退出
     */
    @Override
    public boolean swipeBackPriority() {
        return super.swipeBackPriority();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void addFragment(Fragment fromFragment, Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit)
                .add(R.id.fl_container, toFragment, toFragment.getClass().getSimpleName())
                .hide(fromFragment)
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }

    private void loadFragment(Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, toFragment, toFragment.getClass().getSimpleName())
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onAddFragment(Fragment fromFragment, Fragment toFragment) {
        addFragment(fromFragment, toFragment);
    }
}
