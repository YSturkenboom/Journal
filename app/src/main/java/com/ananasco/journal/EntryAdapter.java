package com.ananasco.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
//        String id =  ""+cursor.getString(cursor.getColumnIndex("_id"));
//        ((TextView)view.findViewById(R.id.content)).setText(id);
        String content =  cursor.getString(cursor.getColumnIndex("content"));
        ((TextView)view.findViewById(R.id.content)).setText(content);
        String title =  cursor.getString(cursor.getColumnIndex("title"));
        if (title.equals("")){
            ((TextView)view.findViewById(R.id.title)).setText("Untitled");
        }
        else {
            ((TextView) view.findViewById(R.id.title)).setText(title);
        }
        Long timestamp =  cursor.getLong(cursor.getColumnIndex("timestamp"));
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
        ((TextView)view.findViewById(R.id.timestamp)).setText(sdf.format(timestamp));


        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        ImageView moodImage = view.findViewById(R.id.moodImage);
        switch (mood){
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
