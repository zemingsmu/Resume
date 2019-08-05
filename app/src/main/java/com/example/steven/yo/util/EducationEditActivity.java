package com.example.steven.yo.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.steven.yo.R;
import com.example.steven.yo.model.Education;

import java.util.ArrayList;
import java.util.Arrays;

public class EducationEditActivity extends AppCompatActivity {
    public static final String KEY_EDUCATION = "education";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_education_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_save) {
            saveAndExit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {
        Education education = new Education();
        education.school = ((EditText)findViewById(R.id.education_edit_school)).getText().toString();
        education.major = ((EditText)findViewById(R.id.education_edit_major)).getText().toString();
        education.courses = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.education_edit_courses)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();  //HashMap
        resultIntent.putExtra(KEY_EDUCATION, education);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
