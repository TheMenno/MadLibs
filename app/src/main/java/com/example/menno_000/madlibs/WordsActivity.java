package com.example.menno_000.madlibs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class WordsActivity extends AppCompatActivity {

    Story story;
    String source;
    int numOfWords;
    String placeHolder;
    String finalStory;
    TextView wordsRemaining;
    TextView placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // Get the chosen story
        Intent intent = getIntent();
        source = (String) intent.getSerializableExtra("pickedStory");

        // Open the chosen story
        story = inputStory(source);

        // Set the box that shows the remaining amount of words to be typed
        wordsRemaining = findViewById(R.id.remainingWords);
        numOfWords = story.getPlaceholderRemainingCount();
        wordsRemaining.setText(String.format("%d", numOfWords));

        // Set the box that shows what to type
        TextView placeholder = findViewById(R.id.placeholder);
        placeHolder = story.getNextPlaceholder();
        placeholder.setText(placeHolder);

        // Initialise the listener
        findViewById(R.id.inputWords).setOnClickListener(new Listener());
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the story on rotation
        outState.putSerializable("story", story);
    }


    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // Restore the story on rotation
        story = (Story) inState.getSerializable("story");

        // Reset the box that shows the remaining amount of words to be typed
        wordsRemaining = findViewById(R.id.remainingWords);
        numOfWords = story.getPlaceholderRemainingCount();
        wordsRemaining.setText(String.format("%d", numOfWords));

        // Reset the box that shows what to type
        placeholder = findViewById(R.id.placeholder);
        placeHolder = story.getNextPlaceholder();
        placeholder.setText(placeHolder);
    }


    public Story inputStory(String storySource) {

        // Open the correct storyfile based on the chosen story and return the story
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


    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            createStory();
        }
    }


    public void createStory() {

        EditText editText = findViewById(R.id.storyWords);
        String inputText = editText.getText().toString();

        if(inputText.matches("")) {

            // Make sure that something is typed in
            editText.setText("Please enter a word");
        } else {

            // Reset the inputfield
            editText.setText("");
            story.fillInPlaceholder(inputText);

            // Set a new value for the box that shows the remaining amount of words to be typed
            TextView wordsRemaining = findViewById(R.id.remainingWords);
            numOfWords = story.getPlaceholderRemainingCount();
            wordsRemaining.setText(String.format("%d", numOfWords));

            // Set a new hint for the box that shows what to type
            TextView placeholder = findViewById(R.id.placeholder);
            placeHolder = story.getNextPlaceholder();
            placeholder.setText(placeHolder);

            if (story.isFilledIn() == true) {

                // Go to the outputscreen if the story is completely filled in
                finalStory = story.toString();
                goToNext();
            }
        }
    }

    public void goToNext() {

        // Go to the next screen
        Intent intent = new Intent(this, OutputActivity.class);
        intent.putExtra("outputStory", finalStory);

        startActivity(intent);
        finish();
    }
}