package Task;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;

import DB.DbHelper;
import DB.FeedReaderContract;
import Playground.Playground;

public class TaskLastUpdate extends AppCompatActivity {

    private ListView listLastTasks;
    private LastUpdateAdapter adapter;
    private String idPlayground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_last_update);

        Intent intent = getIntent();
        idPlayground = intent.getStringExtra("IdPlayGround");

        listLastTasks = (ListView) findViewById(R.id.list_view_LastTask);

        showListLasTaks();
        //showListLastTask();
    }


    private void showListLasTaks(){

        ArrayList<Task> listest = new ArrayList<Task>();

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Task.TABLE_NAME+"" +
                " where "+ FeedReaderContract.Task.COLUMN_NAME_IDSTATE+" = 3 " +
                "AND "+ FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND+" = "+idPlayground, null);


        if (c.moveToFirst())
        {
            do{
                listest.add(new Task(
                        c.getString(0),
                        c.getString(4),
                        c.getString(2),
                        c.getString(8)
                ));
            } while (c.moveToNext());
        }

        listLastTasks.setAdapter(new LastUpdateAdapter(this, listest));
    }

    //méthodes qui génère liste
    private List<Task> genereTasks(){
        List<Task> lastTasks = new ArrayList<Task>();


        return lastTasks ;
    }

    private void showListLastTask(){
        List<Task> LastTasks = genereTasks();

        LastUpdateAdapter  adapter = new LastUpdateAdapter(TaskLastUpdate.this, LastTasks );
        listLastTasks.setAdapter(adapter);
    }
    
}

