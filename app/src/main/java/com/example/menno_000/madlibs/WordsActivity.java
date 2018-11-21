package com.example.menno_000.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WordsActivity extends AppCompatActivity {

    Story story;
    int numOfWords;
    String holderOfPlace;
    String finalStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("ourStory");

        TextView wordsRemaining = findViewById(R.id.remainingWords);
        numOfWords = story.getPlaceholderRemainingCount();
        wordsRemaining.setText(String.format("%d", numOfWords));

        TextView placeholder = findViewById(R.id.placeholder);
        holderOfPlace = story.getNextPlaceholder();
        placeholder.setText(holderOfPlace);

        findViewById(R.id.inputWords).setOnClickListener(new Listener());
    }

    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            createStory();
        }
    }

    public void createStory() {
        EditText editText = (EditText) findViewById(R.id.storyWords);
        String inputText = editText.getText().toString();

        if(inputText.matches("")) {
            editText.setText("Please enter a word");
        }
        else {
            editText.setText("");
            story.fillInPlaceholder(inputText);

            TextView wordsRemaining = findViewById(R.id.remainingWords);
            numOfWords = story.getPlaceholderRemainingCount();
            wordsRemaining.setText(String.format("%d", numOfWords));

            TextView placeholder = findViewById(R.id.placeholder);
            holderOfPlace = story.getNextPlaceholder();
            placeholder.setText(holderOfPlace);

            if (story.isFilledIn() == true) {

                finalStory = story.toString();
                goToFourth();
            }
        }
    }

    public void goToFourth() {
        Intent intent = new Intent(this, OutputActivity.class);
        intent.putExtra("outputStory", finalStory);

        startActivity(intent);
        finish();
    }
}