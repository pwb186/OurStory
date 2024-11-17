package com.pabopwb.ourstory.page.bottomSheets;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pabopwb.ourstory.R;
import com.pabopwb.ourstory.databinding.FragmentOtherEditOptionListDialogItemBinding;
import com.pabopwb.ourstory.databinding.FragmentOtherEditOptionListDialogBinding;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     OtherEditOptionFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class OtherEditOptionFragment extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_edit_option_list_dialog_item, container, false);

        Button option1 = view.findViewById(R.id.edit_add_video);
        option1.setOnClickListener(v -> {

            dismiss();
        });

        Button option2 = view.findViewById(R.id.edit_add_location);
        option2.setOnClickListener(v -> {

            dismiss();
        });

        Button option3 = view.findViewById(R.id.edit_close_other_bottom_sheet);
        option3.setOnClickListener(v -> {

            dismiss();
        });

        return view;
    }
/*
    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentOtherEditOptionListDialogBinding binding;

    // TODO: Customize parameters
    public static OtherEditOptionFragment newInstance(int itemCount) {
        final OtherEditOptionFragment fragment = new OtherEditOptionFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentOtherEditOptionListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView 配置
        RecyclerView recyclerView = binding.list;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // 检查 Arguments 是否为 null
        Bundle arguments = getArguments();
        int itemCount = (arguments != null) ? arguments.getInt(ARG_ITEM_COUNT, 0) : 0;

        recyclerView.setAdapter(new ItemAdapter(itemCount));

        // 获取按钮并设置点击事件
        binding.getRoot().findViewById(R.id.btnCancel).setOnClickListener(v -> {
            dismiss(); // 关闭 BottomSheet
        });

        binding.getRoot().findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            // 处理确认逻辑
            // 这里可以传递数据给 Activity 或其他 Fragment
            dismiss();
        });
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//        final RecyclerView recyclerView = (RecyclerView) view;
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        assert getArguments() != null;
//        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final LinearLayout text;

        ViewHolder(FragmentOtherEditOptionListDialogItemBinding binding) {
            super(binding.getRoot());
            text = binding.bottomSheetEditOtherOptionLayout;
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ViewHolder(FragmentOtherEditOptionListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //holder.text.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }
    */
}