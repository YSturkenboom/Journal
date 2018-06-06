package com.ananasco.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = this.getIntent();
        String title = intent.getStringExtra("title");
        ((TextView)findViewById(R.id.titleTextView)).setText(title);
        String content = intent.getStringExtra("content");
        ((TextView)findViewById(R.id.contentTextView)).setText(content);
        Long timestamp = intent.getLongExtra("timestamp", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
        ((TextView)findViewById(R.id.timestamp)).setText(sdf.format(timestamp));

        ((TextView)findViewById(R.id.contentTextView)).setMovementMethod(new ScrollingMovementMethod());

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
