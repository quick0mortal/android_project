package com.example.admisistrator.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    BlurView blurView;
    static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        blurView = (BlurView)findViewById(R.id.blurView);

        setupBlurView();
        setupViewPager();

        bundle.putString(Constant.USERNAME, getIntent().getStringExtra(Constant.USERNAME));

    }

    private void setupViewPager() {
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupBlurView() {
        final float radius = 16f;

        final View decorView = getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this, true)) //Optional, enabled by default. User can have custom implementation
                .blurRadius(radius);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return Page.values()[position].getFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Page.values()[position].getTitle();
        }

        @Override
        public int getCount() {
            return Page.values().length;
        }
    }

    enum Page {
        FIRST("信息") {
            @Override
            Fragment getFragment() {
                return new ListFragment1();
            }
        },
        SECOND("组队") {
            @Override
            Fragment getFragment() {
                return ListFragment2.newInstance(bundle);
            }
        },
        THIRD("项目") {
            @Override
            Fragment getFragment() {
                return ListFragment3.newInstance(bundle);
            }
        },
        FOURTH("个人") {
            @Override
            Fragment getFragment() { return ListFragment4.newInstance(bundle); }
        },
        FIFTH("消息") {
            @Override
            Fragment getFragment() {return ListFragment5.newInstance(bundle);}
        };

        private String title;

        Page(String title) {
            this.title = title;
        }

        String getTitle() {
            return title;
        }

        abstract Fragment getFragment();
    }
}
