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

        // Setting up the listener
        findViewById(R.id.tosecond).setOnClickListener(new OutputActivity.Listener());
    }


    // Listener for the "Make another story" button, go to the next screen
    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            goToSecond();
        }
    }


    // Go back to the second screen
    public void goToSecond() {
        Intent intent = new Intent(this, ChooseStoryActivity.class);
        startActivity(intent);
        finish();
    }
}
