package DB;

import android.provider.BaseColumns;

/**
 * Created by Colin on 11.04.2017.
 */

public final class FeedReaderContract {

    public FeedReaderContract(){}

    public static abstract class Playground  implements BaseColumns
    {
        public static final String TABLE_NAME = "Playground";
        public static final String COLUMN_NAME_TOWN = "town";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SURFACE = "surface";
        public static final String COLUMN_NAME_TIMETABLETOAVOID = "timetable";
        public static final String COLUMN_NAME_GPSLOCALISATION = "GPS";
        public static final String COLUMN_NAME_PICTURE = "picture";
    }
    public static abstract class Worker  implements BaseColumns
    {
        public static final String TABLE_NAME = "worker";
        public static final String COLUMN_NAME_LOGIN = "login";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_CELLPHONE = "cellphone";
    }
    public static abstract class Task  implements BaseColumns
    {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_IDPLAYGROUND = "idPlayground" ;
        public static final String COLUMN_NAME_IDWORKER = "idWorker";
        public static final String COLUMN_NAME_IDGRAVITY = "idGravity";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_OBSERVATION = "observation";
        public static final String COLUMN_NAME_IDSTATE = "idState";
    }
    public static abstract class Installation  implements BaseColumns
    {
        public static final String TABLE_NAME = "installation";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_STATE = "state";
    }
    public static abstract class Material  implements BaseColumns
    {
        public static final String TABLE_NAME = "material";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
    public static abstract class InstallationPlaced  implements BaseColumns
    {
        public static final String TABLE_NAME = "installationPlaced";
        public static final String COLUMN_NAME_IDPLAYGROUND = "idPlayground";
        public static final String COLUMN_NAME_IDINSTALLATION = "idInstallation";
    }

    public static abstract class MaterialNeeded  implements BaseColumns
    {
        public static final String TABLE_NAME = "materialNeeded";
        public static final String COLUMN_NAME_IDTASK = "idTask";
        public static final String COLUMN_NAME_IDMATERIAL = "idMaterial";
    }
    public static abstract class State  implements BaseColumns
    {
        public static final String TABLE_NAME = "state";
        public static final String TABLE_NAME_IDTASK = "idTask";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
    public static abstract class Gravity  implements BaseColumns
    {
        public static final String TABLE_NAME= "gravity";
        public static final String COLUMN_NAME_GRAVITYLEVEL= "GravityLevel";
    }

    private static final String TEXT_TYPE = "TEXT";
    private static final String INT_TYPE = "INTEGER";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREAT_PLAYGROUND=
            "CREATE TABLE" + Playground.TABLE_NAME + " ("
            +Playground._ID + INT_TYPE+ " PRIMARY KEY,"
            + Playground.COLUMN_NAME_TOWN +TEXT_TYPE + COMMA_SEP
            + Playground.COLUMN_NAME_NAME +TEXT_TYPE + COMMA_SEP
            + Playground.COLUMN_NAME_SURFACE+TEXT_TYPE+COMMA_SEP
            + Playground.COLUMN_NAME_TIMETABLETOAVOID+TEXT_TYPE+COMMA_SEP
            + Playground.COLUMN_NAME_GPSLOCALISATION+TEXT_TYPE+COMMA_SEP
            + Playground.COLUMN_NAME_PICTURE+TEXT_TYPE+COMMA_SEP+" )";

    public static final String SQL_CREAT_WORKER=
            "CREATE TABLE" + Worker.TABLE_NAME + " ("
                    +Worker._ID + INT_TYPE+ " PRIMARY KEY,"
                    + Worker.COLUMN_NAME_LOGIN +TEXT_TYPE + COMMA_SEP
                    + Worker.COLUMN_NAME_PASSWORD +TEXT_TYPE + COMMA_SEP
                    + Worker.COLUMN_NAME_FIRSTNAME+TEXT_TYPE+COMMA_SEP
                    + Worker.COLUMN_NAME_LASTNAME+TEXT_TYPE+COMMA_SEP
                    + Worker.COLUMN_NAME_CELLPHONE+TEXT_TYPE+COMMA_SEP+" )";

    public static final String SQL_CREAT_TASK=
            "CREATE TABLE" + Task.TABLE_NAME + " ("
                    +Task._ID + INT_TYPE+ " PRIMARY KEY,"
                    + Task.COLUMN_NAME_IDPLAYGROUND + INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+Task.COLUMN_NAME_IDPLAYGROUND+")"+
                    "References " + Playground.TABLE_NAME+ "("+Playground._ID+")"+ COMMA_SEP
                    + Task.COLUMN_NAME_IDWORKER + INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+Task.COLUMN_NAME_IDWORKER+")"+
                    "References " + Worker.TABLE_NAME+ "("+Worker._ID+")"+ COMMA_SEP
                    + Task.COLUMN_NAME_IDGRAVITY + INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+Task.COLUMN_NAME_IDGRAVITY+")"+
                    "References " + Gravity.TABLE_NAME+ "("+Gravity._ID+")"+ COMMA_SEP
                    + Task.COLUMN_NAME_DESCRIPTION+TEXT_TYPE+COMMA_SEP
                    + Task.COLUMN_NAME_OBSERVATION+TEXT_TYPE+COMMA_SEP
                    + Task.COLUMN_NAME_IDSTATE+INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+Task.COLUMN_NAME_IDSTATE+")"+
                    "References " + State.TABLE_NAME+ "("+State._ID+")"+ COMMA_SEP
                    +" )";



    public static final String SQL_CREAT_INSTALLATION=
            "CREATE TABLE" + Installation.TABLE_NAME + " ("
                    +Installation._ID + INT_TYPE+ " PRIMARY KEY,"
                    + Installation.COLUMN_NAME_DESCRIPTION+TEXT_TYPE+COMMA_SEP
                    + Installation.COLUMN_NAME_STATE+TEXT_TYPE+COMMA_SEP+" )";

    public static final String SQL_CREAT_MATERIAL=
            "CREATE TABLE" + Material.TABLE_NAME+ " ("
                    +Material._ID + INT_TYPE+ " PRIMARY KEY,"
                    + Material.COLUMN_NAME_DESCRIPTION+TEXT_TYPE+COMMA_SEP+" )";

    public static final String SQL_CREAT_INSTALLATIONPLACED=
            "CREATE TABLE" + InstallationPlaced.TABLE_NAME+ " ("
                    +InstallationPlaced._ID + INT_TYPE+ " PRIMARY KEY,"
                    + InstallationPlaced.COLUMN_NAME_IDPLAYGROUND+ INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+InstallationPlaced.COLUMN_NAME_IDPLAYGROUND+")"+
                    "References " + Playground.TABLE_NAME+ "("+Playground._ID+")"+ COMMA_SEP
                    + InstallationPlaced.COLUMN_NAME_IDINSTALLATION + INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+InstallationPlaced.COLUMN_NAME_IDINSTALLATION+")"+
                    "References " + Installation.TABLE_NAME+ "("+Installation._ID+")"+ COMMA_SEP+" )";

    public static final String SQL_CREAT_MATERIELNEEDED=
            "CREATE TABLE" + MaterialNeeded.TABLE_NAME + " ("
                    +MaterialNeeded._ID + INT_TYPE+ " PRIMARY KEY,"
                    + MaterialNeeded.COLUMN_NAME_IDTASK+ INT_TYPE +"NOT NULL"
                    + "FOREIGNE KEY ("+MaterialNeeded.COLUMN_NAME_IDTASK+")"+
                    "References " + Task.TABLE_NAME+ "("+Task._ID+")"+ COMMA_SEP
                    + MaterialNeeded.COLUMN_NAME_IDMATERIAL + INT_TYPE +"NOT NULL"
                      + "FOREIGNE KEY ("+MaterialNeeded.COLUMN_NAME_IDMATERIAL+")"+
                    "References " + Material.TABLE_NAME+ "("+Material._ID+")"+ COMMA_SEP+" )";

    public static final String SQL_CREAT_STATE=
            "CREATE TABLE" + State.TABLE_NAME + " ("
                    +State._ID + INT_TYPE+ " PRIMARY KEY,"
                    + State.COLUMN_NAME_DESCRIPTION+TEXT_TYPE+COMMA_SEP+" )";

    public static final String SQL_CREAT_GRAVITY=
            "CREATE TABLE" + Gravity.TABLE_NAME + " ("
                    +Gravity._ID + INT_TYPE+ " PRIMARY KEY,"
                    + Gravity.COLUMN_NAME_GRAVITYLEVEL+INT_TYPE+COMMA_SEP+" )";



    public static final String SQL_DELETE_PLAYGROUND=
            "DROP TABLE IF EXISTS" + Playground.TABLE_NAME;
    public  static final String SQL_DELETE_WORKER=
            "DROP TABLE IF EXISTS" + Worker.TABLE_NAME;
    public  static  final String SQL_DELETE_TASK=
            "DROP TABLE IF EXISTS" + Task.TABLE_NAME;
    public static final String SQL_DELETE_INSTALLATION=
            "DROP TABLE IF EXISTS" + Installation.TABLE_NAME;
    public static final String SQL_DELETE_MATERIAL=
            "DROP TABLE IF EXISTS" + Material.TABLE_NAME;
    public static final String SQL_DELETE_MATERIALNEEDED=
            "DROP TABLE IF EXISTS" + MaterialNeeded.TABLE_NAME;
    public static final String SQL_DELETE_INSTALLATIONPLACED=
            "DROP TABLE IF EXISTS" + InstallationPlaced.TABLE_NAME;
    public static final String SQL_DELETE_STATE=
            "DROP TABLE IF EXISTS" + State.TABLE_NAME;
    public static final String SQL_DELETE_GRAVITY=
            "DROP TABLE IF EXIsts" + Gravity.TABLE_NAME;
}
