package com.example.menno_000.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        // Retrieve the created story
        Intent intent = getIntent();
        String receivedStory = (String) intent.getSerializableExtra("outputStory");

        // Show the created story
        TextView textView = findViewById(R.id.output);
        textView.setText(receivedStory);
    }

    public void goToFirst(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
