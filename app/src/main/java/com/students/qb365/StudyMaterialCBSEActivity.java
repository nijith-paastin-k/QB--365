package com.students.qb365;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StudyMaterialCBSEActivity extends AppCompatActivity {
    ImageView ivback;
    LinearLayout linearLayout6th,linearLayout7th,linearLayout8th,linearLayout9th,linearLayout10th,linearLayout11th,linearLayout12th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_material_cbseactivity);
        ivback = findViewById(R.id.ivBack);
        linearLayout6th = findViewById(R.id.tamilmedium);
        linearLayout7th = findViewById(R.id.seventh);
        linearLayout8th = findViewById(R.id.eight);
        linearLayout9th = findViewById(R.id.nineth);
        linearLayout10th = findViewById(R.id.tenth);
        linearLayout11th = findViewById(R.id.eleventh);
        linearLayout12th = findViewById(R.id.twelth);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout6th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSESixthStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayout7th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSESeventhStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayout8th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSEEighthStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayout9th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSENinethStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayout10th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSETenthStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayout11th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSEEleventhStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayout12th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyMaterialCBSEActivity.this,StudyMaterialCBSETwelthStandardActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}