package Task;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colin.projet.R;

import java.util.ArrayList;

import DB.DbHelper;
import DB.FeedReaderContract;

public class TaskLastUpdate extends AppCompatActivity {

    /*
    Constructor
     */
    private ListView listLastTasks;
    private LastUpdateAdapter adapter;
    private String idPlayground;

    /*
    Méthode de liaison du java au layout
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_last_update);

        Intent intent = getIntent();
        idPlayground = intent.getStringExtra("idPlayground");


        listLastTasks = (ListView) findViewById(R.id.list_view_LastTask);

        showListLasTaks();
    }

    /*
    Méhtdo showLIstLasTaks affiche  la listView de l'activtié LastUpdate
     */
    private void showListLasTaks(){

        ArrayList<Task> listest = new ArrayList<Task>();

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();
        Toast.makeText(getApplicationContext(), idPlayground, Toast.LENGTH_SHORT).show();

        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Task.TABLE_NAME+
                " where "+ FeedReaderContract.Task.COLUMN_NAME_IDSTATE+" = 3 " +
                "AND "+ FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND+" = "+idPlayground, null);

        String nomTache ;
        String Worker;
        String date;

        if (c.moveToFirst())
        {
            nomTache = c.getString(5);
            do{
                listest.add(new Task(
                        c.getString(5)
                ));
            } while (c.moveToNext());
        }
        else
            Toast.makeText(getApplicationContext(), "vide", Toast.LENGTH_SHORT).show();

        listLastTasks.setAdapter(new LastUpdateAdapter(this, listest));
    }

    
}

