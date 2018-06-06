package com.ananasco.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class InputActivity extends AppCompatActivity {

    private String[] MOODS =
            new String[]{"happy", "suggestive", "sad", "angry", "neutral", "shocked"} ;
    private int currentMood = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // retrieve the current date, the user cannot change this
        ((TextView)findViewById(R.id.timestamp)).setText(getCurrentDate());

        if (savedInstanceState != null) {
            String content = savedInstanceState.getString("content");
            String title = savedInstanceState.getString("title");
            currentMood = savedInstanceState.getInt("currentMood");
            ((EditText) findViewById(R.id.contentTextView)).setText(content);
            ((EditText) findViewById(R.id.titleTextView)).setText(title);
        }
    }


    private String getCurrentDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
        return sdf.format(date);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("content",
                ((EditText) findViewById(R.id.contentTextView)).getText().toString());
        outState.putString("title",
                ((EditText) findViewById(R.id.titleTextView)).getText().toString());
        outState.putInt("currentMood", currentMood);
        super.onSaveInstanceState(outState);
    }

    public void addEntry(View view){
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        String title = ((EditText) findViewById(R.id.titleTextView)).getText().toString();
        String content = ((EditText) findViewById(R.id.contentTextView)).getText().toString();
        JournalEntry entry = new JournalEntry(1, title, content, MOODS[currentMood],
                System.currentTimeMillis());
        db.insert(entry);
        Log.d("a","TEST");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void changeMood(View view){

        Log.d("mood", String.valueOf(currentMood));

        if (currentMood < MOODS.length - 1) {
            currentMood++;
        }
        else {
            currentMood = 0;
        }

        ImageView emojiButton = findViewById(R.id.emojiButtonView);

        switch (MOODS[currentMood]) {
            case "happy":
                emojiButton.setImageResource(R.drawable.emoji_happy);
                break;
            case "suggestive":
                emojiButton.setImageResource(R.drawable.emoji_suggest);
                break;
            case "sad":
                emojiButton.setImageResource(R.drawable.emoji_sad);
                break;
            case "angry":
                emojiButton.setImageResource(R.drawable.emoji_angry);
                break;
            case "neutral":
                emojiButton.setImageResource(R.drawable.emoji_neutral);
                break;
            case "shocked":
                emojiButton.setImageResource(R.drawable.emoji_shocked);
                break;
        }
    }
}