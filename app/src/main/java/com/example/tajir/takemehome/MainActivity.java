package com.example.tajir.takemehome;

import android.content.Intent;
import android.os.Binder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button student = (Button) findViewById(R.id.student);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectToStudentActivity();
            }
        });
    }

    public void RedirectToStudentActivity() {
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
}
