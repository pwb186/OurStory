package com.pabopwb.ourstory.page;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
    long id;

    ActivityEditBinding binding;
    InitDataBase initDataBase;
    StoryDao storyDao;
    Procedure procedure;
    EntityStory story;
    boolean isAdd = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initMethod();
        binding.editSaveButton.setOnClickListener(v -> saveMethod());
        binding.editCloseButton.setOnClickListener(v -> finish());
        binding.editOtherOption.setOnClickListener(v -> {
            OtherEditOptionFragment bottomSheet = new OtherEditOptionFragment();
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    private void initMethod() {
        storyCreateTime = Procedure.getTime();
        binding.editCreateTime.setText(storyCreateTime);
        initDataBase = UtilMethod.getInstance(getApplicationContext());
        storyDao = initDataBase.storyDao();
        id = new Random().nextInt(1000);
    }

    private void saveMethod() {
        if (isAdd) {
            if (binding.editContent.getText().toString().trim().isEmpty()) {
                UtilMethod.showToast(getApplicationContext(), "Content is empty");
            } else if(binding.editTitle.getText().toString().trim().isEmpty()) {
                UtilMethod.showToast(getApplicationContext(), "Title is empty");
            } else {
                storyDao.insertStory(getCurrentStory());
                UtilMethod.showToast(getApplicationContext(), "Save note success!");
            }
        } else {
            if (binding.editContent.getText().toString().trim().isEmpty()) {
                UtilMethod.showToast(getApplicationContext(), "Content is empty~");
            } else {
                String content = binding.editContent.getText().toString().trim();
                story.setText(content);
                story.setTitle(!binding.editTitle.getText().toString().trim().isEmpty() ? binding.editTitle.getText().toString().trim() : "");
                //note.setNoteImageUrl(imageUri == null ? null : imageUri);
                storyDao.updateStory(story);
                UtilMethod.showToast(getApplicationContext(), "Save note success!");
                finish();
            }
        }
    }

    private EntityStory getCurrentStory() {
        String content = binding.editContent.getText().toString().trim();
        String title = binding.editTitle.getText().toString().trim();
        return new EntityStory(id, 6, "name", "slogan", storyCreateTime, 6, title, content);
    }

}