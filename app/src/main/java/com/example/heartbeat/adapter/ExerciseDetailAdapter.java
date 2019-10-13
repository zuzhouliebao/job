package com.example.heartbeat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heartbeat.R;
import com.example.heartbeat.entity.ExerciseDetail;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDetailAdapter extends RecyclerView.Adapter<ExerciseDetailAdapter.ViewHolder> {
    private List<ExerciseDetail> details;
    private List<String> selectedPos;
    private OnSelectListener onSelectListener;



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView subject ,tvA,tvB,tvC,tvD;
        ImageView ivA,ivB,ivC,ivD;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            subject = itemView.findViewById(R.id.tv_subject);
            tvA = itemView.findViewById(R.id.tv_a);
            tvB = itemView.findViewById(R.id.tv_b);
            tvC = itemView.findViewById(R.id.tv_c);
            tvD = itemView.findViewById(R.id.tv_d);
            ivA = itemView.findViewById(R.id.iv_a);
            ivB = itemView.findViewById(R.id.iv_b);
            ivC = itemView.findViewById(R.id.iv_c);
            ivD = itemView.findViewById(R.id.iv_d);

        }
    }
    public interface OnSelectListener{
        void onSelectA(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectB(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectC(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectD(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
    }
    //参数：习题的集合，监听器（由Activity实现）
    public ExerciseDetailAdapter(List<ExerciseDetail> details,OnSelectListener onSelectListener){
        this.details = details;
        selectedPos = new ArrayList<>();
        this.onSelectListener = onSelectListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_detail,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ExerciseDetail detail = details.get(position);
        holder.subject.setText(detail.getSubject());
        holder.tvA.setText(detail.getA());
        holder.tvB.setText(detail.getB());
        holder.tvC.setText(detail.getC());
        holder.tvD.setText(detail.getD());

        holder.ivA.setImageResource(R.mipmap.ic_exercise_a);
        holder.ivB.setImageResource(R.mipmap.ic_exercise_b);
        holder.ivC.setImageResource(R.mipmap.ic_exercise_c);
        holder.ivD.setImageResource(R.mipmap.ic_exercise_d);

        holder.ivA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = String.valueOf(position);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                } else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectA(position,holder.ivA,holder.ivB,holder.ivC,holder.ivD);
            }
        });
        holder.ivB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos = String.valueOf(position);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                } else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectB(position,holder.ivA,holder.ivB,holder.ivC,holder.ivD);
            }

        });
        holder.ivC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos = String.valueOf(position);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                } else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectC(position,holder.ivA,holder.ivB,holder.ivC,holder.ivD);
            }

        });
        holder.ivD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos = String.valueOf(position);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                } else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectD(position,holder.ivA,holder.ivB,holder.ivC,holder.ivD);
            }

        });

    }

    @Override
    public int getItemCount() {
        return details.size();
    }
    private void setSelectedDrawable(ViewHolder holder, int position) {
        holder.ivA.setImageResource(R.mipmap.ic_exercise_a);
        holder.ivB.setImageResource(R.mipmap.ic_exercise_b);
        holder.ivC.setImageResource(R.mipmap.ic_exercise_c);
        holder.ivD.setImageResource(R.mipmap.ic_exercise_d);
        setABCDEnable(true, holder.ivA, holder.ivB, holder.ivC, holder.ivD);
    }

    // 设置习题选项是否可点击
    public static void setABCDEnable(boolean value, ImageView ivA, ImageView ivB, ImageView ivC, ImageView ivD) {
        ivA.setEnabled(value);
        ivB.setEnabled(value);
        ivC.setEnabled(value);
        ivD.setEnabled(value);
    }
}
