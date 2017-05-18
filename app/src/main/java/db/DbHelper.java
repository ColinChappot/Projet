package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.List;

import cloud.InsertInstallationAsyc;
import cloud.InsertInstallationPlacedAsyc;
import cloud.InsertMaterialAsyc;
import cloud.InsertMaterialNeedAsyc;
import cloud.InsertPlaygroundAsyc;
import cloud.InsertStateAsyc;
import cloud.InsertTaskAsyc;
import cloud.InsertWorkerAsyc;


import static db.FeedReaderContract.SQL_CREAT_INSTALLATION;
import static db.FeedReaderContract.SQL_CREAT_MATERIAL;
import static db.FeedReaderContract.SQL_CREAT_MATERIELNEEDED;
import static db.FeedReaderContract.SQL_CREAT_PLAYGROUND;
import static db.FeedReaderContract.SQL_CREAT_STATE;
import static db.FeedReaderContract.SQL_CREAT_TASK;
import static db.FeedReaderContract.SQL_CREAT_WORKER;
import static db.FeedReaderContract.SQL_DELETE_INSTALLATION;
import static db.FeedReaderContract.SQL_DELETE_INSTALLATIONPLACED;
import static db.FeedReaderContract.SQL_DELETE_MATERIAL;
import static db.FeedReaderContract.SQL_DELETE_MATERIALNEEDED;
import static db.FeedReaderContract.SQL_DELETE_PLAYGROUND;
import static db.FeedReaderContract.SQL_DELETE_STATE;
import static db.FeedReaderContract.SQL_DELETE_TASK;
import static db.FeedReaderContract.SQL_DELETE_WORKER;
import static db.FeedReaderContract.SQL_CREAT_INSTALLATIONPLACED;

//Classe qui sert pour l'initialisation de la DB ainsi que la mise a jour
public class DbHelper extends SQLiteOpenHelper {

    public static final  int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    public Context context;

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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
        db.execSQL(SQL_DELETE_INSTALLATIONPLACED);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    //Toutes les méthodes d'insertion dans la DB

    public void InsertPlayground(Context context, String name, String town, String surface, String GPS, String timeTable) {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();


        value.put(FeedReaderContract.Playground.COLUMN_NAME_TOWN, town);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_NAME, name);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_SURFACE, surface);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_TIMETABLETOAVOID, timeTable);
        value.put(FeedReaderContract.Playground.COLUMN_NAME_GPSLOCALISATION, GPS);


        db.insert(FeedReaderContract.Playground.TABLE_NAME, null, value);

    }

    public void toCloudPlayground(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.Playground.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.playgroundApi.model.Playground playground = new com.example.colin.myapplication.backend.classes.playgroundApi.model.Playground();
                playground.setId(Long.valueOf(c.getString(0)));
                playground.setTown(c.getString(1));
                playground.setName(c.getString(2));
                playground.setSurface(Double.valueOf(c.getString(3)));
                playground.setTimeTableToAvoid(c.getString(4));
                playground.setGpslocalisation(c.getString(5));


                new InsertPlaygroundAsyc(playground).execute();

            }while (c.moveToNext());
        }
    }

    public void fromCloudPlayground(List<com.example.colin.myapplication.backend.classes.playgroundApi.model.Playground> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.Playground.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.playgroundApi.model.Playground t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.Playground._ID, t.getId());
            value.put(FeedReaderContract.Playground.COLUMN_NAME_TOWN, t.getTown());
            value.put(FeedReaderContract.Playground.COLUMN_NAME_NAME, t.getName());
            value.put(FeedReaderContract.Playground.COLUMN_NAME_SURFACE, t.getSurface());
            value.put(FeedReaderContract.Playground.COLUMN_NAME_TIMETABLETOAVOID, t.getTimeTableToAvoid());
            value.put(FeedReaderContract.Playground.COLUMN_NAME_GPSLOCALISATION, t.getGpslocalisation());
            sqlDB.insert(FeedReaderContract.Playground.TABLE_NAME, null, value);
        }
        sqlDB.close();
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

    public void toCloudWorker(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.Worker.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.workerApi.model.Worker worker = new com.example.colin.myapplication.backend.classes.workerApi.model.Worker();
                worker.setId(Long.valueOf(c.getString(0)));
                worker.setLogin(c.getString(1));
                worker.setPassword(c.getString(2));
                worker.setFirstname(c.getString(3));
                worker.setLastname(c.getString(4));
                worker.setPhone(c.getString(5));

                new InsertWorkerAsyc(worker).execute();

            }while (c.moveToNext());
        }
        Log.e("debugCloud","all worker data saved");
    }

    public void fromCloudWorker(List<com.example.colin.myapplication.backend.classes.workerApi.model.Worker> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.Worker.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.workerApi.model.Worker t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.Playground._ID, t.getId());
            value.put(FeedReaderContract.Worker.COLUMN_NAME_LOGIN, t.getLogin());
            value.put(FeedReaderContract.Worker.COLUMN_NAME_PASSWORD, t.getPassword());
            value.put(FeedReaderContract.Worker.COLUMN_NAME_FIRSTNAME, t.getFirstname());
            value.put(FeedReaderContract.Worker.COLUMN_NAME_LASTNAME, t.getLastname());
            value.put(FeedReaderContract.Worker.COLUMN_NAME_CELLPHONE, t.getPhone());

            sqlDB.insert(FeedReaderContract.Worker.TABLE_NAME, null,value);
        }
        sqlDB.close();
        Log.e("debugCloud","all worker data got");
    }


    public void InsertTask(Context context, int playground, int worker, String description, String observation,String name)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        int state=1;

        value.put(FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND, playground);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDWORKER, worker);
        value.put(FeedReaderContract.Task.COLUMN_NAME_DESCRIPTION, description);
        value.put(FeedReaderContract.Task.COLUMN_NAME_OBSERVATION, observation);
        value.put(FeedReaderContract.Task.COLUMN_NAME_NAME,name);
        value.put(FeedReaderContract.Task.COLUMN_NAME_IDSTATE, state);
        value.put(FeedReaderContract.Task.COLUMN_NAME_DATE, "");

        db.insert(FeedReaderContract.Task.TABLE_NAME, null,value);
    }

    public void toCloudTask(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.Task.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.taskApi.model.Task task = new com.example.colin.myapplication.backend.classes.taskApi.model.Task();
                task.setId(Long.valueOf(c.getString(0)));
                task.setIdPlayground(Integer.valueOf(c.getString(1)));
                task.setIdWorker(Integer.valueOf(c.getString(2)));
                task.setDescription(c.getString(3));
                task.setObservation(c.getString(4));
                task.setNom(c.getString(5));
                task.setIdState(Integer.valueOf(c.getString(6)));
                task.setDate(c.getString(7));

                new InsertTaskAsyc(task).execute();

            }while (c.moveToNext());
        }
        Log.e("debugCloud","all task data saved");
    }

  public void fromCloudTask(List<com.example.colin.myapplication.backend.classes.taskApi.model.Task> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.Task.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.taskApi.model.Task t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.Task._ID, t.getId());
            value.put(FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND, t.getIdPlayground());
            value.put(FeedReaderContract.Task.COLUMN_NAME_IDWORKER, t.getIdWorker());
            value.put(FeedReaderContract.Task.COLUMN_NAME_DESCRIPTION, t.getDescription());
            value.put(FeedReaderContract.Task.COLUMN_NAME_OBSERVATION, t.getObservation());
            value.put(FeedReaderContract.Task.COLUMN_NAME_NAME,t.getNom());
            value.put(FeedReaderContract.Task.COLUMN_NAME_IDSTATE, t.getIdState());
            value.put(FeedReaderContract.Task.COLUMN_NAME_DATE, t.getIdState());

            sqlDB.insert(FeedReaderContract.Task.TABLE_NAME, null,value);
        }
        sqlDB.close();
        Log.e("debugCloud","all task data got");
    }




    public void InsertInstallation(Context context, String description)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();


        value.put(FeedReaderContract.Installation.COLUMN_NAME_DESCRIPTION, description);

        db.insert(FeedReaderContract.Installation.TABLE_NAME, null,value);
    }

    public void toCloudInstallation(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.Installation.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.installationApi.model.Installation installation = new com.example.colin.myapplication.backend.classes.installationApi.model.Installation();
                installation.setId(Long.valueOf(c.getString(0)));
                installation.setDescription(c.getString(1));
                new InsertInstallationAsyc(installation).execute();

            }while (c.moveToNext());
        }
        Log.e("debugCloud","all installation data saved");
    }

    public void fromCloudInstallation(List<com.example.colin.myapplication.backend.classes.installationApi.model.Installation> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.Installation.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.installationApi.model.Installation t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.Installation._ID, t.getId());
            value.put(FeedReaderContract.Installation.COLUMN_NAME_DESCRIPTION, t.getDescription());

            sqlDB.insert(FeedReaderContract.Installation.TABLE_NAME, null,value);
        }
        sqlDB.close();
        Log.e("debugCloud","all installation data got");
    }



    public void InsertInstallationPlaced(Context context,int playground, String idinstallation)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();


            value.put(FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDPLAYGROUND, playground);
            value.put(FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDINSTALLATION, Integer.valueOf(idinstallation));
            db.insert(FeedReaderContract.InstallationPlaced.TABLE_NAME, null,value);

    }

    public void toCloudInstallationPlaced(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.InstallationPlaced.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced installationPlaced = new com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced();
                installationPlaced.setId(Long.valueOf(c.getString(0)));
                installationPlaced.setIdPlayground(Integer.valueOf(c.getString(1)));
                installationPlaced.setIdInstallation(Integer.valueOf(c.getString(2)));

                new InsertInstallationPlacedAsyc(installationPlaced).execute();

            }while (c.moveToNext());
        }
        Log.e("debugCloud","all installationPlaced data saved");
    }

    public void fromCloudInstallationPlaced(List<com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.InstallationPlaced.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.InstallationPlaced._ID, t.getId());
            value.put(FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDPLAYGROUND, t.getIdPlayground());
            value.put(FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDINSTALLATION, t.getIdInstallation());

            sqlDB.insert(FeedReaderContract.InstallationPlaced.TABLE_NAME, null,value);
        }
        sqlDB.close();
        Log.e("debugCloud","all installationPlaced data got");
    }

    public void InsertMaterial(Context context, String description)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION, description);

        db.insert(FeedReaderContract.Material.TABLE_NAME, null,value);
    }

    public void toCloudMaterial(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.Material.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.materialApi.model.Material material= new      com.example.colin.myapplication.backend.classes.materialApi.model.Material();
                material.setId(Long.valueOf(c.getString(0)));
                material.setMaterialName(c.getString(1));

                new InsertMaterialAsyc(material).execute();

            }while (c.moveToNext());
        }
    }

    public void fromCloudMaterial(List<com.example.colin.myapplication.backend.classes.materialApi.model.Material> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.Material.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.materialApi.model.Material t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.Material._ID, t.getId());
            value.put(FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION, t.getMaterialName());

            sqlDB.insert(FeedReaderContract.Material.TABLE_NAME, null,value);
        }
        sqlDB.close();
        Log.e("debugCloud","all material data got");
    }



    public void InsertMaterialNeeded(Context context, String task, String materiel)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();


            value.put(FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDTASK, Integer.valueOf(task));
            value.put(FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDMATERIAL, Integer.valueOf(materiel));
            db.insert(FeedReaderContract.MaterialNeeded.TABLE_NAME, null,value);

    }

    public void toCloudMaterialNeeded(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.MaterialNeeded.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded materialNeeded = new          com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded();
                materialNeeded.setId(Long.valueOf(c.getString(0)));
                materialNeeded.setIdTask(Integer.valueOf(c.getString(1)));
                materialNeeded.setIdMaterial(Integer.valueOf(c.getString(2)));
                new InsertMaterialNeedAsyc(materialNeeded).execute();

            }while (c.moveToNext());
        }
        Log.e("debugCloud","all materialNeeded data saved");
    }

    public void fromCloudMaterialNeeded(List<com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.MaterialNeeded.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.MaterialNeeded._ID, t.getId());
            value.put(FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDTASK, t.getIdTask());
            value.put(FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDMATERIAL, t.getIdMaterial());

            sqlDB.insert(FeedReaderContract.MaterialNeeded.TABLE_NAME, null,value);

        }
        sqlDB.close();
    }


    public void InsertState(Context context, String description)
    {
        DbHelper mDbHelper = new DbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(FeedReaderContract.State.COLUMN_NAME_DESCRIPTION, description);

        db.insert(FeedReaderContract.State.TABLE_NAME, null,value);
    }

    public void toCloudState(){

        SQLiteDatabase dbR= new DbHelper(context).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * from "+ FeedReaderContract.State.TABLE_NAME, null);

        if (c.moveToFirst())
        {
            do {
                com.example.colin.myapplication.backend.classes.stateApi.model.State state= new com.example.colin.myapplication.backend.classes.stateApi.model.State();
                state.setId(Long.valueOf(c.getString(0)));
                state.setDescription(c.getString(1));

                new InsertStateAsyc(state).execute();

            }while (c.moveToNext());
        }
        Log.e("debugCloud","all state data saved");
    }

    public void fromCloudState(List<com.example.colin.myapplication.backend.classes.stateApi.model.State> items){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        sqlDB.delete(FeedReaderContract.State.TABLE_NAME, null, null);

        for (com.example.colin.myapplication.backend.classes.stateApi.model.State t : items) {
            ContentValues value = new ContentValues();
            value.put(FeedReaderContract.State._ID, t.getId());
            value.put(FeedReaderContract.State.COLUMN_NAME_DESCRIPTION, t.getDescription());

            sqlDB.insert(FeedReaderContract.State.TABLE_NAME, null,value);
        }
        sqlDB.close();
        Log.e("debugCloud","all state data got");
    }


    //Toutes les méthodes deletes employée

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
