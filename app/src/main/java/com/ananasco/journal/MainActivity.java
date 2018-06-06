package com.ananasco.journal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter entryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = EntryDatabase.getInstance(getApplicationContext());
        entryAdapter = new EntryAdapter(getApplicationContext(), db.selectAll());
        ((ListView) findViewById(R.id.entryList)).setAdapter(entryAdapter);
        ((ListView) findViewById(R.id.entryList)).setOnItemLongClickListener(new JournalEntryLongClickListener());
        ((ListView) findViewById(R.id.entryList)).setOnItemClickListener(new JournalEntryClickListener());
        updateData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    public void addEntry(View view){
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private void updateData() {
        entryAdapter.swapCursor(db.selectAll());
        entryAdapter.notifyDataSetChanged();
    }

    private class JournalEntryLongClickListener implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor c = (Cursor) adapterView.getItemAtPosition(i);
            db.delete(c.getInt(c.getColumnIndex("_id")));
            updateData();
            return false;
        }
    }

    private class JournalEntryClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor c = (Cursor) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("_id", c.getInt(c.getColumnIndex("_id")));
            intent.putExtra("title", c.getString(c.getColumnIndex("title")));
            intent.putExtra("content", c.getString(c.getColumnIndex("content")));
            intent.putExtra("mood", c.getString(c.getColumnIndex("mood")));
            intent.putExtra("timestamp", c.getLong(c.getColumnIndex("timestamp")));


            startActivity(intent);
        }
    }
}
