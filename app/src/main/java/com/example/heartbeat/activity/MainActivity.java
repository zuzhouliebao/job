package com.example.heartbeat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;

import com.example.heartbeat.R;
import com.example.heartbeat.fragment.CourseFragment;
import com.example.heartbeat.fragment.ExerciseFragment;
import com.example.heartbeat.fragment.MyInfoFragment;
import com.example.heartbeat.fragment.RecyclerViewFragment;
import com.example.heartbeat.utils.StatusUtils;

public class MainActivity extends AppCompatActivity {
    private RadioGroup group;
    private Toolbar toolbar;

    // 定义标题的集合
    private SparseArray<String> titles;
    private SparseArray<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setImmersionMode(this);
        setContentView(R.layout.activity_main);
        StatusUtils.initToolbar(this, "我的", false, true);
        initView();
        initTitles();
        initFragment();
    }
    private void initFragment() {
        // 1. 创建fragment的列表
        fragments = new SparseArray<>();
        fragments.put(R.id.btn_my, MyInfoFragment.newInstance());
        fragments.put(R.id.btn_exercise, RecyclerViewFragment.newInstance("Activity向Fragment传值"));
        fragments.put(R.id.btn_course, CourseFragment.newInstance());


        // 2. 加载默认的Fragment
        replaceFragment(fragments.get(R.id.btn_course));
    }

    /**
     * 管理fragment
     * @param fragment 加载的fragment对象
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_body, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * 初始化标题的集合
     */
    private void initTitles() {
        titles = new SparseArray<>();
        titles.put(R.id.btn_course, "课程");
        titles.put(R.id.btn_exercise, "练习");
        titles.put(R.id.btn_message, "资讯");
        titles.put(R.id.btn_my, "我的");
    }

    /**
     * 根据按钮的id设置界面的标题
     * @param checkedId RadioGroup的选中Id
     */
    private void setToolbar(int checkedId) {
        if (checkedId == R.id.btn_my) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
            toolbar.setTitle(titles.get(checkedId));
        }
    }

    /**
     * 初始化界面控件，设置事件监听
     */
    private void initView() {
        group = findViewById(R.id.btn_group);
        toolbar = findViewById(R.id.title_toolbar);
        setToolbar(group.getCheckedRadioButtonId());

        // RadioGroup的选项改变事件的监听
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*Toast.makeText(MainActivity.this, titles.get(checkedId), Toast.LENGTH_SHORT).show();*/
                setToolbar(checkedId);
                replaceFragment(fragments.get(checkedId));
            }
        });
    }
}
