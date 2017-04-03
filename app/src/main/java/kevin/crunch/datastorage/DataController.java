package kevin.crunch.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataController {
    public static final String NAME="Name";
    public static final String DESCRIPTION="Description";
    public static final String PRICE="Price";
    public static final String REVIEW="Review";
    public static final String TABLE_NAME="Msg_Table";
    public static final String DATABASE_NAME="Assignment2.db";
    public static final int DATABASE_VERSION=4;
    public static final String TABLE_CREATE="create table Msg_Table (Name text not null, Description text, Price text, Review text);";

    DataBaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public DataController(Context context)
    {
        this.context=context;
        dbHelper=new DataBaseHelper(context);
    }

    public DataController open()
    {
        db=dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insert(String name,String desc, String price, String review)
    {
        ContentValues content=new ContentValues();
        content.put(NAME, name);
        content.put(DESCRIPTION, desc);
        content.put(PRICE, price);
        content.put(REVIEW, review);
        return db.insertOrThrow(TABLE_NAME, null, content);
    }

    public Cursor retrieve(String... name)
    {
        return db.query(TABLE_NAME, new String[]{NAME,DESCRIPTION,PRICE,REVIEW}, NAME, name, null, null, null);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try {
                db.execSQL(TABLE_CREATE);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS Msg_Table");
            onCreate(db);
        }
    }
}
