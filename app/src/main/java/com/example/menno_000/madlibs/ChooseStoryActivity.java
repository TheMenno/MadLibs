package com.example.menno_000.madlibs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class ChooseStoryActivity extends AppCompatActivity {

    Story story;
    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_story);

        findViewById(R.id.simple).setOnClickListener(new Listener());
        findViewById(R.id.tarzan).setOnClickListener(new Listener());
        findViewById(R.id.university).setOnClickListener(new Listener());
        findViewById(R.id.clothes).setOnClickListener(new Listener());
        findViewById(R.id.dance).setOnClickListener(new Listener());
        findViewById(R.id.random).setOnClickListener(new Listener());
    }

    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.simple:
                    source = "madlib0_simple.txt";
                    goToThird();
                    break;
                case R.id.tarzan:
                    source = "madlib1_tarzan.txt";
                    goToThird();
                    break;
                case R.id.university:
                    source = "madlib2_university.txt";
                    goToThird();
                    break;
                case R.id.clothes:
                    source = "madlib3_clothes.txt";
                    goToThird();
                    break;
                case R.id.dance:
                    source = "madlib4_dance.txt";
                    goToThird();
                    break;
                case R.id.random:
                    String[] sources = new String[] {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt", "madlib3_clothes.txt", "madlib4_dance.txt"};
                    String randomSource = sources[new Random().nextInt(sources.length)];

                    source = randomSource;
                    goToThird();
                    break;
            }
        }
    }

    public Story inputStory(String storySource) {
        Context context = getApplicationContext();

        try {
            InputStream stream = context.getAssets().open(storySource);
            story = new Story(stream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return story;
    }

    public void goToThird() {
        Story story = inputStory(source);

        Intent intent = new Intent(this, WordsActivity.class);
        intent.putExtra("ourStory",story);

        startActivity(intent);
        finish();
    }
}
