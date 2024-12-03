package com.pabopwb.ourstory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pabopwb.ourstory.databinding.ActivityMainBinding;
import com.pabopwb.ourstory.page.MainFragment;
import com.pabopwb.ourstory.page.PersonFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;    //视图绑定
    MainFragment mainFragment;
    PersonFragment personFragment;
    Fragment current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /**
         * LayoutInflater 类将 XML 布局文件转换为 View 对象:
         * inflate(): 这个方法用于加载 XML 布局文件，并返回一个绑定类实例
         * */
        binding = ActivityMainBinding.inflate(getLayoutInflater());     //将布局 XML 文件实例化为其相应的View对象。它从不直接使用。相反，使用Activity.getLayoutInflater()或Context#getSystemService来检索已连接到当前上下文并为您正在运行的设备正确配置的标准 LayoutInflater 实例。
        setContentView(binding.getRoot());
        initMethod();
    }

    private void initMethod() {
        mainFragment = new MainFragment();
        personFragment = new PersonFragment();
        changeFragment(null, mainFragment);
        current = mainFragment;

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.main_fragment_menu_button) {
                changeFragment(current, mainFragment);
            } else if (itemId == R.id.person_fragment_menu_button) {
                changeFragment(current, personFragment);
            } else {
                throw new IllegalStateException("Unexpected value: " + itemId);
            }

            item.setChecked(true);
            return false;
        });

    }

    /**
     * 切换fragment
     * */
    public void changeFragment(Fragment from, Fragment to) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();       //添加隐藏fragment
        if (!to.isAdded()) {
            if (from != null) {
                fragmentTransaction.hide(from);
            }
            fragmentTransaction.add(binding.navFragment.getId(), to).commit();
        } else {
            if (from != null) {
                fragmentTransaction.hide(from);
            }
            fragmentTransaction.show(to).commit();
        }
        current = to;
    }
}