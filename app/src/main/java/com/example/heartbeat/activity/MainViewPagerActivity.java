package com.example.heartbeat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;

import com.example.heartbeat.R;
import com.example.heartbeat.adapter.MainViewPagerAdapter;
import com.example.heartbeat.fragment.CourseFragment;
import com.example.heartbeat.fragment.ExerciseFragment;
import com.example.heartbeat.fragment.MyInfoFragment;
import com.example.heartbeat.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener  {
    private RadioGroup group;
    private Toolbar toolbar;
    private ViewPager viewPager;

    private SparseArray<String> titles;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setImmersionMode(this);
        setContentView(R.layout.activity_main_view_pager);
        StatusUtils.initToolbar(this, "课程视频", false, true);

        initTitles();
        initFragment();
        initView();

    }
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(CourseFragment.newInstance());
        fragments.add(ExerciseFragment.newInstance("data"));
        fragments.add(MyInfoFragment.newInstance());
        fragments.add(MyInfoFragment.newInstance());
    }

    private void initTitles() {
        titles = new SparseArray<>();
        titles.put(R.id.btn_course, "课程视频");
        titles.put(R.id.btn_exercise, "章节练习");
        titles.put(R.id.btn_message, "最新资讯");
        titles.put(R.id.btn_my, "我的信息");
    }
    private void initView() {
        group = findViewById(R.id.btn_group);
        toolbar = findViewById(R.id.title_toolbar);
        setToolbar(group.getCheckedRadioButtonId());

        /*viewPager = findViewById(R.id.main_body);*/
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setToolbar(checkedId);

                int current = 0;
                switch (checkedId) {
                    case R.id.btn_course:
                        current = 0;
                        break;
                    case R.id.btn_exercise:
                        current = 1;
                        break;
                    case R.id.btn_message:
                        current = 2;
                        break;
                    case R.id.btn_my:
                        current = 3;
                        break;
                }
                if(viewPager.getCurrentItem() != current) {
                    viewPager.setCurrentItem(current);
                }
            }
        });
    }

    private void setToolbar(int checkedId) {
        if (checkedId == R.id.btn_my) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
            toolbar.setTitle(titles.get(checkedId));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (viewPager.getCurrentItem()) {
            case 0:
                group.check(R.id.btn_course);
                break;
            case 1:
                group.check(R.id.btn_exercise);
                break;
            case 2:
                group.check(R.id.btn_message);
                break;
            case 3:
                group.check(R.id.btn_my);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
