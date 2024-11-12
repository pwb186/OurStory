package com.pabopwb.ourstory.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.pabopwb.ourstory.R;
import com.pabopwb.ourstory.adapter.StoryAdapter;
import com.pabopwb.ourstory.dao.InitDataBase;
import com.pabopwb.ourstory.dao.StoryDao;
import com.pabopwb.ourstory.databinding.FragmentMainBinding;
import com.pabopwb.ourstory.entity.EntityCard;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;    //使用 View Binding 获取 Fragment 的视图组件，简化了视图查找。
    InitDataBase dataBase;
    StoryDao storyDao;
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
        ArrayList<EntityCard> list = new ArrayList<>();
        list.add(new EntityCard(1, "1", "204/568/33", "1你好", "1FragmentNoteBinding: 这是由 Android Studio 自动生成的类，代表与布局文件 fragment_note.xml 相关联的绑定类。这个类会为布局文件中的所有视图元素生成相应的属性。"));
        list.add(new EntityCard(2, "1sadfe", "1", "1", "1"));
        list.add(new EntityCard(3, "1bdh", "1", "1", "1"));
        list.add(new EntityCard(4, "1rt", "1[]iji", "1", "1"));
        list.add(new EntityCard(5, "1bfg", "1", "1", "1"));
        list.add(new EntityCard(6, "1rt", "18755", "1", "1"));
        StoryAdapter storyAdapter = new StoryAdapter(list, getContext());

        /**
         * storyList: 这是一个 RecyclerView 的引用，它是在布局文件中定义的一个视图元素。通过 View Binding，storyList 可以直接访问，并且其类型会被自动推断为 RecyclerView
         * 将适配器（storyAdapter）设置给 RecyclerView（storyList），使得 RecyclerView 能够展示数据。
         * 设置布局管理器，使得 RecyclerView 以垂直的方式排列其子项。这种配置是实现动态列表显示的标准做法。
         * */
        binding.storyList.setAdapter(storyAdapter);
        binding.storyList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        View view = binding.getRoot().getRootView();
        MaterialToolbar topAppBar = view.findViewById(R.id.topAppBar);



        //View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}