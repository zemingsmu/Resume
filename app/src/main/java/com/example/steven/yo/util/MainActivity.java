package com.example.steven.yo.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.steven.yo.R;
import com.example.steven.yo.model.BasicInfo;
import com.example.steven.yo.model.Education;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_EDUCATION_EDIT = 100;

    private BasicInfo basicinfo;
    private List<Education> educations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fakeData();
        setupUI();
    }

    // handle data flow
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_EDUCATION_EDIT && resultCode == Activity.RESULT_OK) {
            Education result = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
            educations.add(result);
            setupEducationsUI();
        }
    }

    private void setupUI() {
        setContentView(R.layout.activity_main);

        setupBasicInfoUI();
        setupEducationsUI();
    }

    private void setupBasicInfoUI() {
        ((TextView)findViewById(R.id.name)).setText(basicinfo.name);
        ((TextView)findViewById(R.id.email)).setText(basicinfo.email);
    }

    private void setupEducationsUI() {
        LinearLayout educationContainer = (LinearLayout)findViewById(R.id.educations);
        educationContainer.removeAllViews();
        for (Education education : educations) {
            View view = getEducationView(education);
            educationContainer.addView(view);
        }

        findViewById(R.id.edit_education_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // collect data in EducationEditActivity to MainActivity
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });
    }

    private View getEducationView(Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);
        ((TextView) view.findViewById(R.id.education_courses)).setText(formatItems(education.courses));
        (view.findViewById(R.id.edit_education_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void fakeData() {
        basicinfo = new BasicInfo();
        basicinfo.name = "Zeming Liu";
        basicinfo.email = "zemingl.smu.edu";

        Education education1 = new Education();
        education1.school = "SMU";
        education1.major = "Computer Science";
        education1.courses = new ArrayList<>();
        education1.courses.add("Algorithm");
        education1.courses.add("XML");
        education1.courses.add("Information Retrieval and Web Search");
        education1.courses.add("Software Architecture");

        Education education2 = new Education();
        education2.school = "HIT";
        education2.major = "Computer Science";
        education2.courses = new ArrayList<>();
        education2.courses.add("Algorithm");
        education2.courses.add("Machine Learning");

        educations = new ArrayList<>();
        educations.add(education1);
        educations.add(education2);
    }

    private String formatItems(List<String> courses) {
        StringBuilder sb = new StringBuilder();
        for (String str : courses) {
            sb.append(" ").append("-").append(" ").append(str).append("\n");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
