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

    // Initialising variables
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


    // Save the story on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("story", story);
    }


    // Restore the story on rotation
    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // Retrieve the story
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


    // Open the correct storyfile based on the chosen story and return the story
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


    // Listener for the "Next word!" button, go to the next screen
    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            createStory();
        }
    }


    // Create the story
    public void createStory() {

        // Initialising variables
        EditText editText = findViewById(R.id.storyWords);
        String inputText = editText.getText().toString();

        // // Make sure that something is typed in
        if(inputText.matches("")) {

            editText.setText("Please enter a word");
        } else {

            // Add the new word to the story
            story.fillInPlaceholder(inputText);

            // Go to the outputscreen if the story is completely filled in
            if (story.isFilledIn() == true) {

                finalStory = story.toString();
                goToNext();
            } else {

                // Reset the input field
                editText.setText("");

                // Set a new value for the box that shows the remaining amount of words to be typed
                TextView wordsRemaining = findViewById(R.id.remainingWords);
                numOfWords = story.getPlaceholderRemainingCount();
                wordsRemaining.setText(String.format("%d", numOfWords));

                // Set a new hint for the box that shows what to type
                TextView placeholder = findViewById(R.id.placeholder);
                placeHolder = story.getNextPlaceholder();
                placeholder.setText(placeHolder);
            }
        }
    }


    // Go to the next screen and give the story
    public void goToNext() {

        Intent intent = new Intent(this, OutputActivity.class);
        intent.putExtra("outputStory", finalStory);

        startActivity(intent);
        finish();
    }
}