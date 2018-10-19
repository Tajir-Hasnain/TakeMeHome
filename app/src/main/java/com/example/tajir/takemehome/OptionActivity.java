package com.example.tajir.takemehome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        init();
    }

    public void init() {

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        tempooButton();
        busButton();
    }

    public void tempooButton() {
        Button button = findViewById(R.id.tempoo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectToStudentActivity();
            }
        });
    }

    public void busButton() {
        Button button = findViewById(R.id.bus);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectToBusActivity();
            }
        });
    }

    public void RedirectToStudentActivity() {
        Intent intent = new Intent(this, StudentActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void RedirectToBusActivity() {
        Intent intent = new Intent(this, BusActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
