package com.pabopwb.ourstory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pabopwb.ourstory.R;
import com.pabopwb.ourstory.dao.InitDataBase;
import com.pabopwb.ourstory.dao.StoryDao;
import com.pabopwb.ourstory.entity.EntityCard;
import com.pabopwb.ourstory.util.UtilMethod;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    ArrayList<EntityCard> list;
    Context context;                //上下文，通常用于访问资源和启动新活动
    int deletePosition;
    InitDataBase initDataBase;      //数据库初始化对象，用于访问数据库
    StoryDao storyDao;
    CountListen countListen;        //一个接口，用于在适配器内部与外部通信，更新笔记数量

    public StoryAdapter(ArrayList<EntityCard> list, Context context) {
        this.list = list;
        this.context = context;
        //this.countListen = countListen;
        initDataBase = UtilMethod.getInstance(context);
        storyDao = initDataBase.storyDao();
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
        holder.introduction.setText(list.get(position).getIntroduction());
        holder.time.setText(list.get(position).getStoryCreateTime());
        //holder.cover.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getText());
        holder.set.setOnClickListener(view -> {
            System.out.println("has click");
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    /**
     * 内部类，持有视图元素的引用以提高性能。通过 itemView.findViewById 方法获取子视图的引用。
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView introduction;
        TextView time;
        ImageView cover;
        TextView content;
        Button set;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            introduction = itemView.findViewById(R.id.item_introduction);
            time = itemView.findViewById(R.id.item_time);
            cover = itemView.findViewById(R.id.item_cover);
            content = itemView.findViewById(R.id.item_content);
            set = itemView.findViewById(R.id.item_set);
        }
    }

    public interface CountListen {
        void countListen(int count);
    }
}
