package com.example.menno_000.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Random;


public class ChooseStoryActivity extends AppCompatActivity {

    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_story);

        // Setting up listeners
        findViewById(R.id.simple).setOnClickListener(new Listener());
        findViewById(R.id.tarzan).setOnClickListener(new Listener());
        findViewById(R.id.university).setOnClickListener(new Listener());
        findViewById(R.id.clothes).setOnClickListener(new Listener());
        findViewById(R.id.dance).setOnClickListener(new Listener());
        findViewById(R.id.random).setOnClickListener(new Listener());
    }


    // Get the story that was chosen and give it to the next screen
    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.simple:
                    source = "madlib0_simple.txt";
                    goToNext();
                    break;
                case R.id.tarzan:
                    source = "madlib1_tarzan.txt";
                    goToNext();
                    break;
                case R.id.university:
                    source = "madlib2_university.txt";
                    goToNext();
                    break;
                case R.id.clothes:
                    source = "madlib3_clothes.txt";
                    goToNext();
                    break;
                case R.id.dance:
                    source = "madlib4_dance.txt";
                    goToNext();
                    break;
                case R.id.random:
                    // Get a random text
                    String[] sources = new String[] {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt", "madlib3_clothes.txt", "madlib4_dance.txt"};
                    String randomSource = sources[new Random().nextInt(sources.length)];

                    // Go to the next screen
                    source = randomSource;
                    goToNext();
                    break;
            }
        }
    }


    // Go to the next screen
    public void goToNext() {

        Intent intent = new Intent(this, WordsActivity.class);
        intent.putExtra("pickedStory", source);

        startActivity(intent);
        finish();
    }
}
