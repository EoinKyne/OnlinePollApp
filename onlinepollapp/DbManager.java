package eoinkyne.com.onlinepollapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by computer1 on 26/02/2016.
 */
public class DbManager {

    // Declare column names and information that will be added to each column
    public static final String KEY_ROWID = "_id";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_VOTERSTATUS = "voterstatus";
    public static final String KEY_LOCALITY = "locality";
    public static final String KEY_ISSUE = "issue";
    public static final String KEY_INCOME = "income";
    public static final String KEY_POLITICALPARTY = "political_party";
    public static final String KEY_TAOISEACH = "Taoiseach";
    public static final String KEY_FIRSTPREFERENCE = "FirstPreference";

    // Delcare the database name, database table name and database version
    private static final String DATABASE_NAME = "Galway_West_Poll";
    private static final String DATABASE_TABLE = "Galway_West_Poll_table";
    private static final int DATABASE_VERSION = 1;

    // sqlite command to create database and give each variable a data type (text) and constrain (not null)
    private static final String DATABASE_CREATE = "create table Galway_West_Poll_table " +
            "(_id integer primary key autoincrement, " +
            "age integer not null, " +
            "gender text not null, " +
            "voterstatus text not null, " +
            "locality text not null, " +
            "issue text not null, " +
            "income text not null, " +
            "political_party text not null, " +
            "Taoiseach text not null, " +
            "FirstPreference text not null);";


    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Contructor
    public DbManager(Context ctx)
    {
        //
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    //
    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        //
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME,
                    null, DATABASE_VERSION);
        }


        @Override
        // Runs when the database is first created
        public void onCreate(SQLiteDatabase db)
        {

            db.execSQL(DATABASE_CREATE);
        }

        @Override

        //When the database is upgraded changes are added and a new version number is added.
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {

        }
    }


    // Opens the database so it can be written to
    public DbManager open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Closes database
    public void close()
    {
        DBHelper.close();
    }

    // Method to insert poll.
    public long insertPoll(String age, String gender, String voterstatus, String locality, String issue, String income,
                           String political_party, String taoiseach, String first_preference)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_AGE, age);
        initialValues.put(KEY_GENDER, gender);
        initialValues.put(KEY_VOTERSTATUS, voterstatus);
        initialValues.put(KEY_LOCALITY, locality);
        initialValues.put(KEY_ISSUE, issue);
        initialValues.put(KEY_INCOME, income);
        initialValues.put(KEY_POLITICALPARTY, political_party);
        initialValues.put(KEY_TAOISEACH, taoiseach);
        initialValues.put(KEY_FIRSTPREFERENCE, first_preference);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }



    // Method to returns all polls with no constraints
    public Cursor getAllPolls()
    {
        return db.query(DATABASE_TABLE, new String[]{
                        KEY_ROWID,
                        KEY_AGE,
                        KEY_GENDER,
                        KEY_VOTERSTATUS,
                        KEY_LOCALITY,
                        KEY_ISSUE,
                        KEY_INCOME,
                        KEY_POLITICALPARTY,
                        KEY_TAOISEACH,
                        KEY_FIRSTPREFERENCE,
                },
                null,
                null,
                null,
                null,
                null);
    }
    // method to get the count for each Taoiseach candidate
    public Cursor getTaoiseachTotalCount(String Taoiseach) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                                KEY_ROWID,
                                KEY_AGE,
                                KEY_GENDER,
                                KEY_VOTERSTATUS,
                                KEY_LOCALITY,
                                KEY_ISSUE,
                                KEY_INCOME,
                                KEY_POLITICALPARTY,
                                KEY_TAOISEACH,
                                KEY_FIRSTPREFERENCE,
                        },
                        KEY_TAOISEACH + "=\"" + Taoiseach + "\"",
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Method to get all polls with Issue and Age constraints
    public Cursor getPollByIssueAndAge(String issue, int lage, int uage) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                                KEY_ROWID,
                                KEY_AGE,
                                KEY_GENDER,
                                KEY_VOTERSTATUS,
                                KEY_LOCALITY,
                                KEY_ISSUE,
                                KEY_INCOME,
                                KEY_POLITICALPARTY,
                                KEY_TAOISEACH,
                                KEY_FIRSTPREFERENCE,
                        },
                        KEY_AGE + ">" + lage +" AND "+ KEY_AGE +"<" + uage +" AND "+ KEY_ISSUE +"=\"" + issue +"\"",
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }





    /*
    public boolean deletePoll(long rowId)
    {
        //
        return db.delete(DATABASE_TABLE, KEY_ROWID +
                "=" + rowId, null) > 0;
    }
    //
    public Cursor getPoll(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                                KEY_ROWID,
                                KEY_AGE,
                                KEY_GENDER,
                                KEY_VOTERSTATUS,
                                KEY_LOCALITY,
                                KEY_ISSUE,
                                KEY_INCOME,
                                KEY_POLITICALPARTY,
                                KEY_TAOISEACH,
                                KEY_FIRSTPREFERENCE,
                        },
                        KEY_ROWID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //
    public boolean updatePoll(long rowId, int age, String gender, String voterstatus, String locality, String issue, String income,
                              String political_party, String taoiseach, String first_preference)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_AGE, age);
        args.put(KEY_GENDER, gender);
        args.put(KEY_VOTERSTATUS, voterstatus);
        args.put(KEY_LOCALITY, locality);
        args.put(KEY_ISSUE, issue);
        args.put(KEY_INCOME, income);
        args.put(KEY_POLITICALPARTY, political_party);
        args.put(KEY_TAOISEACH, taoiseach);
        args.put(KEY_FIRSTPREFERENCE, first_preference);
        return db.update(DATABASE_TABLE, args,
                KEY_ROWID + "=" + rowId, null) > 0;
    }*/
}
