package Task;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;

import DB.DbHelper;
import DB.FeedReaderContract;
import Playground.PlaygroundFicheActivity;

public class TaskToDoActivity extends AppCompatActivity {

    private ListView listTasks;
    private TaskToDoAdapter adapter;
    private Button btnAddTask;
    private String idPlayground;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_to_do);

        Intent intent = getIntent();
        idPlayground = intent.getStringExtra("idPlayground");

        listTasks = (ListView) findViewById(R.id.list_view_Task);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TaskToDoActivity.this, NewTask.class);
                startActivity(intent);
            }
        });

        showListTask();
        //showListtask2();
    }

    private void showListTask(){

        ArrayList<Task> listest = new ArrayList<Task>();

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();

        c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Task.TABLE_NAME+
                " where "+FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND+" = '"+idPlayground+"'", null);


        if (c.moveToFirst())
        {
            do{
                listest.add(new Task(
                        c.getString(6)
                ));
            } while (c.moveToNext());
        }

        listTasks.setAdapter(new TaskToDoAdapter(this, listest));
        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TaskToDoActivity.this, PlaygroundFicheActivity.class);
                c.moveToPosition(position);
                intent.putExtra("idTask",c.getString(0));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), c.getString(0), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*
   Méthode  genereWorkers : génère la liste
    */
    private List<Task> generetaks(){
        List<Task> Tasks = new ArrayList<>();

        Tasks.add(new Task("tache Générée"));
        return Tasks;

    }

    /*
    Méthodes shoListWorker:
     */
    private void showListtask2(){
        List<Task> tasks = generetaks();

        TaskToDoAdapter adapter = new TaskToDoAdapter(TaskToDoActivity.this, tasks );
        listTasks.setAdapter(adapter);
    }


}
