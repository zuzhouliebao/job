package com.example.heartbeat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.heartbeat.R;
import com.example.heartbeat.adapter.ExerciseDetailAdapter;
import com.example.heartbeat.entity.ExerciseDetail;
import com.example.heartbeat.utils.IOUtils;
import com.example.heartbeat.utils.StatusUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDetailActivity extends AppCompatActivity implements ExerciseDetailAdapter.OnSelectListener {

    private int id;
    private String title;
    private List<ExerciseDetail> details;
    private RecyclerView lvDetails;
    private ExerciseDetailAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setImmersionMode(this); //沉浸式状态栏可选
        setContentView(R.layout.activity_exercise_detail);
        initData();
        initView();
    }



    private void initData() {
        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        details = new ArrayList<>();
        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            details = IOUtils.getXmlContents(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initView() {
        lvDetails = findViewById(R.id.lv_details);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lvDetails.setLayoutManager(manager);
        adapter = new ExerciseDetailAdapter(details,this);
        lvDetails.setAdapter(adapter);
    }



    @Override
    public void onSelectA(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
        ExerciseDetail detail = details.get(position);
        if (detail.getAnswer() != 1){
            detail.setSelect(1);
        } else {
            detail.setSelect(0);
        }
        switch (detail.getAnswer()){
            case 1:
                ivA.setImageResource(R.mipmap.ic_exercise_answer_right);
                break;
            case 2:
                ivB.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivA.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 3:
                ivC.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivA.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 4:
                ivD.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivA.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
        }

    }

    @Override
    public void onSelectB(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
        ExerciseDetail detail = details.get(position);
        if (detail.getAnswer() != 2){
            detail.setSelect(2);
        } else {
            detail.setSelect(0);
        }
        switch (detail.getAnswer()){
            case 1:
                ivA.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivB.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 2:
                ivB.setImageResource(R.mipmap.ic_exercise_answer_right);
                break;
            case 3:
                ivC.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivB.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 4:
                ivD.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivB.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
        }
    }

    @Override
    public void onSelectC(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
        ExerciseDetail detail = details.get(position);
        if (detail.getAnswer() != 3){
            detail.setSelect(3);
        } else {
            detail.setSelect(0);
        }
        switch (detail.getAnswer()){
            case 1:
                ivA.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivC.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 2:
                ivB.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivC.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 3:
                ivC.setImageResource(R.mipmap.ic_exercise_answer_right);
                break;
            case 4:
                ivD.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivC.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
        }
    }

    @Override
    public void onSelectD(int position, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
        ExerciseDetail detail = details.get(position);
        if (detail.getAnswer() != 4){
            detail.setSelect(4);
        } else {
            detail.setSelect(0);
        }
        switch (detail.getAnswer()){
            case 1:
                ivA.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivD.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 2:
                ivB.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivD.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 3:
                ivC.setImageResource(R.mipmap.ic_exercise_answer_right);
                ivD.setImageResource(R.mipmap.ic_exercise_answer_error);
                break;
            case 4:
                ivD.setImageResource(R.mipmap.ic_exercise_answer_right);
                break;
        }
    }
}
