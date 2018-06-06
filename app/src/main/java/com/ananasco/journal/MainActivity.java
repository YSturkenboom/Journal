package com.ananasco.journal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/** This is the main activity of the app. It shows a list of all diary entries (if there are any) */

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter entryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the single database instance (there may never be more than one)
        db = EntryDatabase.getInstance(getApplicationContext());

        // create a new custom adapter with in the Cursor all journal entries
        entryAdapter = new EntryAdapter(getApplicationContext(), db.selectAll());

        // set the listview with entrie sup with the adapter, and two ClickListeners
        ((ListView) findViewById(R.id.entryList)).setAdapter(entryAdapter);
        ((ListView) findViewById(R.id.entryList))
                .setOnItemLongClickListener(new JournalEntryLongClickListener());
        ((ListView) findViewById(R.id.entryList))
                .setOnItemClickListener(new JournalEntryClickListener());

        updateData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    // This function is called when the floating action button is pressed
    public void addEntry(View view){

        // go to the input activity
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // This helper function swaps the cursor with a fresh 'select all' to update the list
    private void updateData() {
        entryAdapter.swapCursor(db.selectAll());
        entryAdapter.notifyDataSetChanged();
    }

    // The long click gesture will delete an entry
    private class JournalEntryLongClickListener implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor c = (Cursor) adapterView.getItemAtPosition(i);

            // deleting is done by id, so we need to get the id of an entry first
            db.delete(c.getInt(c.getColumnIndex("_id")));

            updateData();
            return false;
        }
    }

    // A short click takes the user to the detail activity
    private class JournalEntryClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor c = (Cursor) adapterView.getItemAtPosition(i);

            // These values are stored so they can be retrieved by the detail acvtivity.
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
