package com.ananasco.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Map;

/** SQLiteDatabase that stores Journal entries */

public class EntryDatabase extends android.database.sqlite.SQLiteOpenHelper{

    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        return new EntryDatabase(context, "EntryDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Firstly, create the table
        final String prepared =
                "create table Entries (_id integer primary key autoincrement not null , " +
                        "title varchar(255), content varchar(255), mood varchar(255), " +
                        "timestamp datetime);";
        sqLiteDatabase.execSQL(prepared);

        // Example entries
        final String example =
                "insert into Entries values (0, 'First day at work', 'The people were really nice" +
                        "next time Ill bring my own coffee', 'excited', '2018-04-29 17:01:59')";

        sqLiteDatabase.execSQL(example);

        final String exampl2 =
                "insert into Entries values (1, 'Second day at work', 'The people were really nice" +
                        "next time Ill bring my own coffee', 'excited', '2018-04-29 17:01:59')";

        sqLiteDatabase.execSQL(exampl2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // When the app is updated the table is currently dropped and then recreated.
        // A real app would have to use migrations or you would keep losing your data.
        final String drop = "drop table Entries;";
        final String prepared =
                "create table Entries (_id int primary key autoincrement not null , title varchar(255), content varchar(255), mood varchar(255), " +
                        "timestamp datetime);";
        sqLiteDatabase.execSQL(drop);
        sqLiteDatabase.execSQL(prepared);

        // insert a few example entries
        final String example =
                "insert into Entries values (0, 'First day at work', 'The people were really nice" +
                        "next time Ill bring my own coffee', 'excited', '2018-04-29 17:01:59')";

        sqLiteDatabase.execSQL(example);

        final String exampl2 =
                "insert into Entries values (1, 'Second day at work', 'The people were really nice" +
                        "next time Ill bring my own coffee', 'excited', '2018-04-29 17:01:59')";

        sqLiteDatabase.execSQL(exampl2);
    }

    // returns all entries, sorted by descending date
    public Cursor selectAll() {
        SQLiteDatabase wr = getWritableDatabase();
        Cursor c = wr.rawQuery("SELECT _id, title, content, mood, timestamp FROM Entries ORDER BY timestamp DESC",null);
        return c;
    }

    // inserts a new entry into the database, requires a Journal object.
    public void insert(JournalEntry entry) {
        SQLiteDatabase wr = getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("mood", entry.getMood());
        insertValues.put("title", entry.getTitle());
        insertValues.put("content", entry.getContent()  );
        insertValues.put("timestamp", entry.getTimestamp());
        wr.insert("Entries", null, insertValues);
    }

    // delete a certain journal entry based on id, used with long press gesture in the main menu
    public void delete(int id){
        SQLiteDatabase wr = getWritableDatabase();
        wr.delete("Entries","_id == " + id, null);
    }
}
