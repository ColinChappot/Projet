package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static DB.FeedReaderContract.SQL_CREAT_GRAVITY;
import static DB.FeedReaderContract.SQL_CREAT_INSTALLATION;
import static DB.FeedReaderContract.SQL_CREAT_MATERIAL;
import static DB.FeedReaderContract.SQL_CREAT_MATERIELNEEDED;
import static DB.FeedReaderContract.SQL_CREAT_PLAYGROUND;
import static DB.FeedReaderContract.SQL_CREAT_STATE;
import static DB.FeedReaderContract.SQL_CREAT_TASK;
import static DB.FeedReaderContract.SQL_CREAT_WORKER;
import static DB.FeedReaderContract.SQL_DELETE_GRAVITY;
import static DB.FeedReaderContract.SQL_DELETE_INSTALLATION;
import static DB.FeedReaderContract.SQL_DELETE_MATERIAL;
import static DB.FeedReaderContract.SQL_DELETE_MATERIALNEEDED;
import static DB.FeedReaderContract.SQL_DELETE_PLAYGROUND;
import static DB.FeedReaderContract.SQL_DELETE_STATE;
import static DB.FeedReaderContract.SQL_DELETE_TASK;
import static DB.FeedReaderContract.SQL_DELETE_WORKER;

/**
 * Created by Colin on 11.04.2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final  int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREAT_PLAYGROUND);
        db.execSQL(SQL_CREAT_WORKER);
        db.execSQL(SQL_CREAT_TASK);
        db.execSQL(SQL_CREAT_INSTALLATION);
        db.execSQL(SQL_CREAT_MATERIAL);
        db.execSQL(SQL_CREAT_MATERIELNEEDED);
        db.execSQL(SQL_CREAT_STATE);
        db.execSQL(SQL_CREAT_GRAVITY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_PLAYGROUND);
        db.execSQL(SQL_DELETE_WORKER);
        db.execSQL(SQL_DELETE_TASK);
        db.execSQL(SQL_DELETE_INSTALLATION);
        db.execSQL(SQL_DELETE_MATERIAL);
        db.execSQL(SQL_DELETE_MATERIALNEEDED);
        db.execSQL(SQL_DELETE_STATE);
        db.execSQL(SQL_DELETE_GRAVITY);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

}
