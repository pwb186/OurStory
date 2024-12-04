package com.pabopwb.ourstory.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.pabopwb.ourstory.R;
import com.pabopwb.ourstory.page.EditActivity;
import com.pabopwb.ourstory.room.InitDataBase;
import com.pabopwb.ourstory.room.StoryDao;
import com.pabopwb.ourstory.entity.EntityCard;
import com.pabopwb.ourstory.util.UtilMethod;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    ArrayList<EntityCard> list;
    Context context;                //上下文，通常用于访问资源和启动新活动
    InitDataBase initDataBase;      //数据库初始化对象，用于访问数据库
    StoryDao storyDao;
    // OnStoryActionListener storyActionListener;        //一个接口，用于在适配器内部与外部通信，更新笔记数量

    public StoryAdapter(ArrayList<EntityCard> list, Context context) {
        this.list = list;
        this.context = context;
        initDataBase = UtilMethod.getInstance(context);
        storyDao = initDataBase.storyDao();
        // 在后台线程中执行操作
//        Executors.newSingleThreadExecutor().execute(() -> {
//            storyDao = initDataBase.storyDao();
//        });

    }
    /**
     * 当需要新视图时调用，使用 LayoutInflater 加载 item_note 布局，并返回一个 ViewHolder 对象。
     * */
    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }
    /**
     * 绑定数据到视图。根据当前项的 position，设置视图中的各个 UI 元素的内容，如用户名、口号、创建时间、标题和内容
     * */
    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        //内部类的title[拿到在xml中的空间].设置文本(在列表中的位置0123.得到这个位置的title)
        holder.title.setText(list.get(position).getTitle());
        // holder.introduction.setText(list.get(position).getIntroduction());
        holder.time.setText(list.get(position).getStoryCreateTime());
        Log.d(list.get(position).getTitle(), "onBindViewHolder: list.imgurl" + list.get(position).getImgUrl());
        if (list.get(position).getImgUrl() == null) {
            holder.imageView.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position).getImgUrl()).into(holder.imageView);

        }
        holder.content.setText(list.get(position).getText());
        holder.itemStory.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("needAdd", false);
            intent.putExtra("storyId", list.get(position).getCardID());
            context.startActivity(intent);
        });
    }

    // 更新适配器中的数据
//    public void onStoryUpdated(ArrayList<EntityCard> newCardList) {
//        this.list = newCardList;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    /**
     * 内部类，持有视图元素的引用以提高性能。通过 itemView.findViewById 方法获取子视图的引用。
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView itemStory;
        TextView title;
        TextView introduction;
        TextView time;
        ImageView imageView;
        TextView content;
        Button set;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemStory = itemView.findViewById(R.id.item_story);
            title = itemView.findViewById(R.id.item_title);
            // introduction = itemView.findViewById(R.id.item_introduction);
            time = itemView.findViewById(R.id.item_time);
            imageView = itemView.findViewById(R.id.item_image);
            content = itemView.findViewById(R.id.item_content);
            // set = itemView.findViewById(R.id.item_set);
        }
    }

    public interface OnStoryActionListener {
        void countListen(int count);    //用于计数
        void onStoryUpdated(); // 用于刷新 MainFragment
    }
}
