package com.pabopwb.ourstory.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.pabopwb.ourstory.R;
import com.pabopwb.ourstory.page.bottomSheets.OtherEditOptionFragment;
import com.pabopwb.ourstory.room.InitDataBase;
import com.pabopwb.ourstory.room.StoryDao;
import com.pabopwb.ourstory.databinding.ActivityEditBinding;
import com.pabopwb.ourstory.entity.EntityStory;
import com.pabopwb.ourstory.util.Procedure;
import com.pabopwb.ourstory.util.UtilMethod;

import java.util.Random;

public class EditActivity extends AppCompatActivity {

    String storyCreateTime;
    long storyId = 0;
    String imgUri;

    ActivityEditBinding binding;
    InitDataBase initDataBase;
    StoryDao storyDao;
    EntityStory story;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    boolean needAdd = true;   //true代表还没添加到数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDataBase = UtilMethod.getInstance(getApplicationContext());
        storyDao = initDataBase.storyDao();

        Intent intent = getIntent();
        if ( ! (intent != null && intent.getBooleanExtra("needAdd", true) ) ) {
            //如果intent为null或者"isAdd"的值为false，则条件判断为真，代表已经添加到了数据库中
            needAdd = false;
            if (intent == null) throw new AssertionError();
            initMethodAgain(intent);
        }
        else {
            initMethodFirst();
        }

        binding.editOtherOption.setOnClickListener(v -> {
            OtherEditOptionFragment bottomSheet = new OtherEditOptionFragment();
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        });

        binding.editTopAppBar.setOnMenuItemClickListener(menuItem -> {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.edit_save) {
                saveMethod();
                return true;
            }
            else if (itemId == R.id.edit_save_and_exit) {
                saveMethod();
                return true;
            }
            else if (itemId == R.id.delete) {
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Are you sure you want delete this story?")
                        .setPositiveButton("yes", (dialogInterface, i) -> {
                            deleteStory();
                        })
                        .setNegativeButton("no", null)
                        .show();
                return true;
            }
            else {
                return false; // 如果 `itemId` 不匹配以上任意情况，则返回 `false`
            }
        });

        pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        imgUri = UtilMethod.getPath(getApplicationContext(), uri);
                        Glide.with(getApplicationContext()).load(uri).into(binding.storyImage);
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });

        binding.editAddPicture.setOnClickListener(view -> {
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });
    }

    private void deleteStory() {
        if (storyId != 0 ) {
            storyDao.deleteStory(story);
        }
        // Set result to notify MainFragment to reload data
        setResult(Activity.RESULT_OK);
        finish();  // Close the EditActivity

        Intent intent = new Intent("com.example.YOUR_ACTION");
        intent.putExtra("delete_result", true); // 假设删除成功
        sendBroadcast(intent);
    }

    private void initMethodFirst() {
        //设置时间
        storyCreateTime = Procedure.getTime();
        binding.editCreateTime.setText(storyCreateTime);

    }

    private void initMethodAgain(Intent intent) {
        storyId = intent.getLongExtra("storyId", 0);
        story = storyDao.getStoryById(storyId);
        binding.editTitle.setText(!story.getTitle().isEmpty() ? story.getTitle() : "");
        binding.editContent.setText(story.getText());
        binding.editCreateTime.setText(story.getStoryCreateTime());
        if (story.getImgUrl() != null && !story.getImgUrl().isEmpty()) {
            imgUri = story.getImgUrl();
            Glide.with(getApplicationContext()).load(imgUri).into(binding.storyImage);
        }
}

    private void saveMethod() {
        if (binding.editContent.getText().toString().trim().isEmpty()) {
            UtilMethod.showToast(getApplicationContext(), "Content is empty");
        }
        else {
            if(needAdd) {
                if(storyId == 0) {
                    storyId = new Random().nextInt(1000) + 1; // 生成1到1000之间的随机数
                }
                storyDao.insertStory(getCurrentStory());
            }
            else {
                //note.setNoteImageUrl(imageUri == null ? null : imageUri);
                story.setText(binding.editContent.getText().toString().trim());
                story.setTitle(!binding.editTitle.getText().toString().trim().isEmpty() ? binding.editTitle.getText().toString().trim() : "");
                storyDao.updateStory(story);
            }
            UtilMethod.showToast(getApplicationContext(), "Save note success!");
            // Set result to notify MainFragment to reload data
            setResult(Activity.RESULT_OK);

            finish();
        }
    }

    private EntityStory getCurrentStory() {
        String content = binding.editContent.getText().toString().trim();
        String title = !binding.editTitle.getText().toString().trim().isEmpty() ? binding.editTitle.getText().toString().trim() : "";
        return new EntityStory(storyId, 6, "name", "slogan", storyCreateTime, 6, title, content, imgUri);
    }
}