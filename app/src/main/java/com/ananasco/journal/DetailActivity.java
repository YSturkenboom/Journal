package com.ananasco.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/** Activity that shows a more detailed overview of a diary entry */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve the information of the diary entry
        Intent intent = this.getIntent();
        String title = intent.getStringExtra("title");
        ((TextView)findViewById(R.id.titleTextView)).setText(title);
        String content = intent.getStringExtra("content");
        ((TextView)findViewById(R.id.contentTextView)).setText(content);
        Long timestamp = intent.getLongExtra("timestamp", 0);

        // Format the date to human-readable
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
        ((TextView)findViewById(R.id.timestamp)).setText(sdf.format(timestamp));

        // Make sure that the textview scrolls on long inputs
        ((TextView)findViewById(R.id.contentTextView)).setMovementMethod(new ScrollingMovementMethod());

        // Set the right mood image for the diary entry
        String mood = intent.getStringExtra("mood");
        ImageView moodImage = findViewById(R.id.moodImage);
        switch (mood) {
            case "happy":
                moodImage.setImageResource(R.drawable.emoji_happy);
                break;
            case "suggestive":
                moodImage.setImageResource(R.drawable.emoji_suggest);
                break;
            case "sad":
                moodImage.setImageResource(R.drawable.emoji_sad);
                break;
            case "angry":
                moodImage.setImageResource(R.drawable.emoji_angry);
                break;
            case "neutral":
                moodImage.setImageResource(R.drawable.emoji_neutral);
                break;
            case "shocked":
                moodImage.setImageResource(R.drawable.emoji_shocked);
                break;
        }
    }
}
