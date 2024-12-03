package com.pabopwb.ourstory.page;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.pabopwb.ourstory.R;
import com.pabopwb.ourstory.adapter.StoryAdapter;
import com.pabopwb.ourstory.room.InitDataBase;
import com.pabopwb.ourstory.room.StoryDao;
import com.pabopwb.ourstory.databinding.FragmentMainBinding;
import com.pabopwb.ourstory.entity.EntityCard;
import com.pabopwb.ourstory.entity.EntityStory;
import com.pabopwb.ourstory.util.UtilMethod;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements StoryAdapter.OnStoryActionListener {

    FragmentMainBinding binding;    //使用 View Binding 获取 Fragment 的视图组件，简化了视图查找。
    InitDataBase initDataBase;
    StoryDao storyDao;
    List<EntityStory> list;
    StoryAdapter storyAdapter;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    /**
     * a. FragmentNoteBinding.inflate(getLayoutInflater())
     * FragmentNoteBinding: 这是由 Android Studio 自动生成的类，代表与布局文件 fragment_note.xml 相关联的绑定类。这个类会为布局文件中的所有视图元素生成相应的属性。
     * inflate: 这个方法将布局文件转换为相应的视图对象，并将其绑定到 binding 变量。inflate() 方法需要一个 LayoutInflater 对象（通过 getLayoutInflater() 获取）。
     * getLayoutInflater(): 获取当前 Fragment 的布局填充器，用于将 XML 布局文件转换为 View 对象。
     * b. return binding.getRoot();
     * getRoot(): 返回根视图，即布局文件的最外层视图。这个视图将被返回作为 Fragment 的内容。
     * 在 onCreateView() 方法中返回根视图是必需的，以便 Android 系统知道要显示什么内容。
     * */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(getLayoutInflater());
        initMethod();
        initList();

        // 初始化 ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            // 处理返回的结果
            if (result.getResultCode() == Activity.RESULT_OK) {
                // 执行刷新 Fragment 的操作
                refreshFragment();
            }
        });

        View view = binding.getRoot().getRootView();
        MaterialToolbar topAppBar = view.findViewById(R.id.topAppBar);

        binding.floatingActionButton.setOnClickListener(v -> {
            startEditActivity();
        });

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            initMethod();
            initList();
            swipeRefreshLayout.setRefreshing(false);
        });

        return binding.getRoot();
    }

    public void startEditActivity() {
        // 在 Fragment 中启动 Activity
        Intent intent = new Intent(getContext(), EditActivity.class);
        activityResultLauncher.launch(intent);
    }

    private void initList() {
        list = getList();
        if (list != null && !list.isEmpty()) {
            ArrayList<EntityCard> list = storyToCard(this.list);
            binding.storyNull.setVisibility(View.GONE);
            /* *
             * storyList: 这是一个 RecyclerView 的引用，它是在布局文件中定义的一个视图元素。通过 View Binding，storyList 可以直接访问，并且其类型会被自动推断为 RecyclerView
             * 将适配器（storyAdapter）设置给 RecyclerView（storyList），使得 RecyclerView 能够展示数据。
             * 设置布局管理器，使得 RecyclerView 以垂直的方式排列其子项。这种配置是实现动态列表显示的标准做法。
             * */
            storyAdapter = new StoryAdapter(list, getContext());
            binding.storyList.setAdapter(storyAdapter);
            binding.storyList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            binding.storyNull.setVisibility(View.VISIBLE);
        }
    }

    private ArrayList<EntityCard> storyToCard(List<EntityStory> list) {
        ArrayList<EntityCard> cards = new ArrayList<>();
        for (EntityStory story : list
        ) {
            EntityCard entityCard = new EntityCard();
            entityCard.setCardID(story.getStoryId());
            entityCard.setText(story.getText());
            entityCard.setTitle(story.getTitle());
            cards.add(entityCard);
        }
        return cards;
    }

    private void initMethod() {
        initDataBase = UtilMethod.getInstance(getContext());
        storyDao = initDataBase.storyDao();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshFragment() {
//        initMethod();
//        initList();
   //     System.out.println("shoodao delete");
        // 刷新逻辑，如重新加载数据或更新 RecyclerView
       //  storyAdapter.notifyDataSetChanged();
    }

    @Override
    public void countListen(int count) {

    }

    // 实现 OnStoryActionListener 接口方法
    @Override
    public void onStoryUpdated() {

    }

    private List<EntityStory> getList() {
        List<EntityStory> allStory = storyDao.getAllStory();
        if (!allStory.isEmpty()) {
            return allStory;
        } else {
            return null;
        }
    }
}