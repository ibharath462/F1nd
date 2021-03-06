package com.F1nd.V1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bharath on 8/12/17.
 */

public class dbHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "dict.db";
    public static final String WORD = "word";
    public static final String MEANING = "meaning";
    private SQLiteDatabase database;

    private  String DB_PATH = "";

    private  String DB_NAME = "dict";

    private final Context myContext;



    public dbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        Log.i("Database22: ", "Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {



        onCreate(sqLiteDatabase);

    }

    public JSONObject search(String q){
        DB_PATH = myContext.getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        String myPath = DB_PATH + DB_NAME;
        Log.d("RestartServiceReceiver", "" + myPath);
        //Log.i("Database22: ", "" + q);
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        String selectQuery = "SELECT  * FROM dict WHERE UPPER("+WORD+") LIKE '"+q.toUpperCase()+"%' limit 10";
        Cursor cursor = database.rawQuery(selectQuery, null);
        JSONObject t = new JSONObject();
        if (cursor.moveToFirst()) {
            do {
                try {
                    t.put(cursor.getString(cursor.getColumnIndex(WORD)),cursor.getString(cursor.getColumnIndex(MEANING)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        database.close();
        Log.v("Database22: ", " " + t.toString());
        return t;
    }

    public String getMeaning(String word){
        String meaning = "";
        DB_PATH = myContext.getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        String myPath = DB_PATH + DB_NAME;
        Log.d("RestartServiceReceiver", "" + myPath);
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT * FROM dict where UPPER(word)='" + word + "'";
        Cursor cursor = database.rawQuery(query,null);
        Log.i("Database : ", "" + cursor.toString());
        if((cursor.moveToFirst()) || cursor.getCount() != 0){
            cursor.moveToFirst();
            meaning = cursor.getString(cursor.getColumnIndex(MEANING));
            cursor.close();
            return meaning;
        }
        database.close();
        return null;
    }

    public JSONObject getWordOfTheDay(){
        JSONObject t =new JSONObject();
        Log.v("Database22: ", "Helloooo");
        DB_PATH = myContext.getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        String myPath = DB_PATH + DB_NAME;
        Log.d("RestartServiceReceiver", "" + myPath);
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        String selectQuery = "SELECT * FROM dict ORDER BY RANDOM() LIMIT 1;";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    t.put(cursor.getString(cursor.getColumnIndex(WORD)),cursor.getString(cursor.getColumnIndex(MEANING)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        Log.v("Database22: ", " " + t.toString());
        database.close();
        return t;
    }
}
