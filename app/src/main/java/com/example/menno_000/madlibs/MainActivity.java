package com.example.menno_000.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the listener
        findViewById(R.id.getstarted).setOnClickListener(new MainActivity.Listener());
    }

    // Listener for the "Get started!" button, go to the next screen
    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            goToNext();
        }
    }

    // Go to the next screen
    public void goToNext() {
        Intent intent = new Intent(this, ChooseStoryActivity.class);
        startActivity(intent);
    }
}
