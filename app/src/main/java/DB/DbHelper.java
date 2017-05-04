package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

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
import static DB.FeedReaderContract.SQL_DELETE_INSTALLATIONPLACED;
import static DB.FeedReaderContract.SQL_DELETE_MATERIAL;
import static DB.FeedReaderContract.SQL_DELETE_MATERIALNEEDED;
import static DB.FeedReaderContract.SQL_DELETE_PLAYGROUND;
import static DB.FeedReaderContract.SQL_DELETE_STATE;
import static DB.FeedReaderContract.SQL_DELETE_TASK;
import static DB.FeedReaderContract.SQL_DELETE_WORKER;
import static DB.FeedReaderContract.SQL_CREAT_INSTALLATIONPLACED;

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
        db.execSQL(SQL_CREAT_INSTALLATIONPLACED);
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
        db.execSQL(SQL_DELETE_INSTALLATIONPLACED);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void InsertPlayground(Context context, String name, String town, String surface, String GPS, String timeTable, ArrayList<String> installation) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();


        value.put(FeedReaderContract.Playground.COLUMN_NAME_TOWN, town);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_NAME, name);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_SURFACE, surface);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_TIMETABLETOAVOID, timeTable);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_GPSLOCALISATION, GPS);
        ;

        db.insert(FeedReaderContract.Playground.TABLE_NAME, null, value);

        SQLiteDatabase dbR = new DbHelper(context).getReadableDatabase();
        int id=0;
        //Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Playground.TABLE_NAME + " where " + FeedReaderContract.Playground.COLUMN_NAME_NAME + " = '" + name+"'", null);
        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Playground.TABLE_NAME + " ORDER BY "+FeedReaderContract.Playground._ID+" DESC LIMIT 1" , null);
        if (c.moveToFirst())
        {
            id = Integer.valueOf(c.getString(0));
        }

        InsertInstallationPlaced(context, id , installation);

    }

    public void UpdatePlayground(Context context,int Id, String name, String town, String surface, String GPS,String image, String timeTable, String installation)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Playground.COLUMN_NAME_NAME, name);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_TOWN, town);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_SURFACE, surface);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_GPSLOCALISATION, GPS);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_TIMETABLETOAVOID, timeTable);

        db.update(FeedReaderContract.Playground.TABLE_NAME, value, FeedReaderContract.Playground._ID + " = ?", new String[] {String.valueOf(Id)});
    }

    public void InsertWorker(Context context, String login, String password, String firstname, String lastname, String phone)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Worker.COLUMN_NAME_LOGIN, login);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_PASSWORD, password);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_FIRSTNAME, firstname);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_LASTNAME, lastname);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_CELLPHONE, phone);

        db.insert(FeedReaderContract.Worker.TABLE_NAME, null,value);

    }

    public void UpdateWorker(Context context,int Id, String login, String password, String firstname, String lastname, String phone)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Worker.COLUMN_NAME_LOGIN, login);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_PASSWORD, password);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_FIRSTNAME, firstname);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_LASTNAME, lastname);
        value.put(FeedReaderContract.Worker.COLUMN_NAME_CELLPHONE, phone);

        db.update(FeedReaderContract.Worker.TABLE_NAME, value, FeedReaderContract.Worker._ID + " = ?", new String[] {String.valueOf(Id)});

    }

    public void InsertTask(Context context, int playground, int worker, int gravity, String description, String observation,String name, ArrayList material)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        int state=1;

        value.put(FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND, playground);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDWORKER, worker);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDGRAVITY, gravity);
        value.put(FeedReaderContract.Task.COLUMN_NAME_DESCRIPTION, description);
        value.put(FeedReaderContract.Task.COLUMN_NAME_OBSERVATION, observation);
        value.put(FeedReaderContract.Task.COLUMN_NAME_NAME,name);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDSTATE, state);
        value.put(FeedReaderContract.Task.COLUMN_NAME_DATE, "");

        db.insert(FeedReaderContract.Task.TABLE_NAME, null,value);
    }

    public void UpdateTask(Context context,int Id, int playground, int worker, int gravity, String description, String observation, String name, int state, ArrayList material)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND, playground);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDWORKER, worker);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDGRAVITY, gravity);
        value.put(FeedReaderContract.Task.COLUMN_NAME_DESCRIPTION, description);
        value.put(FeedReaderContract.Task.COLUMN_NAME_OBSERVATION, observation);
        value.put(FeedReaderContract.Task.COLUMN_NAME_NAME,name);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDSTATE, state);


        db.update(FeedReaderContract.Task.TABLE_NAME, value, FeedReaderContract.Task._ID + " = ?", new String[] {String.valueOf(Id)});
    }



    public void InsertInstallation(Context context, String description, String state)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Installation.COLUMN_NAME_DESCRIPTION, description);
        value.put(FeedReaderContract.Installation.COLUMN_NAME_STATE, state);

        db.insert(FeedReaderContract.Installation.TABLE_NAME, null,value);
    }

    public void InsertInstallationPlaced(Context context,int playground, ArrayList<String> installation)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        for (int i =0; i<installation.size();i++)
        {
            value.put(FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDPLAYGROUND, playground);
            value.put(FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDINSTALLATION, Integer.valueOf(installation.get(i)));
            db.insert(FeedReaderContract.InstallationPlaced.TABLE_NAME, null,value);
        }
    }

    public void InsertMaterial(Context context, String description)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION, description);

        db.insert(FeedReaderContract.Material.TABLE_NAME, null,value);
    }

    public void InsertMaterialNeeded(Context context, String task, String materiel)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();


            value.put(FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDTASK, task);
            value.put(FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDMATERIAL, materiel);
            db.insert(FeedReaderContract.Material.TABLE_NAME, null,value);

    }

    public void InsertState(Context context, String description)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.State.COLUMN_NAME_DESCRIPTION, description);

        db.insert(FeedReaderContract.State.TABLE_NAME, null,value);
    }

    public void InsertGravity(Context context, int level)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Gravity.COLUMN_NAME_GRAVITYLEVEL, level);

        db.insert(FeedReaderContract.Gravity.TABLE_NAME, null,value);
    }

    public int DeletePlayground(Context context,int Id)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(FeedReaderContract.InstallationPlaced.TABLE_NAME,FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDPLAYGROUND+" = ?",new String[]{String.valueOf(Id)});

        return db.delete(FeedReaderContract.Playground.TABLE_NAME,FeedReaderContract.Playground._ID+" = ?",new String[]{String.valueOf(Id)});
    }
    public int DeleteWorker(Context context,int Id)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        return db.delete(FeedReaderContract.Worker.TABLE_NAME,FeedReaderContract.Worker._ID+" = ?",new String[]{String.valueOf(Id)});
    }
    public int DeleteTask(Context context,int Id)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(FeedReaderContract.MaterialNeeded.TABLE_NAME,FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDTASK+" = ?",new String[]{String.valueOf(Id)});

        return db.delete(FeedReaderContract.Task.TABLE_NAME,FeedReaderContract.Task._ID+" = ?",new String[]{String.valueOf(Id)});
    }





}
