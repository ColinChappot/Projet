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

import DB.DbHelper;
import DB.FeedReaderContract;
import Playground.PlaygroundFicheActivity;

public class TaskToDoActivity extends AppCompatActivity {

    /*
    Déclaration des variables
     */
    private ListView listTasks;
    private TaskToDoAdapter adapter;
    private Button btnAddTask;
    private String idPlayground;
    private Cursor c;


    /*
    Méthode de liaison de la classe java et du layout activity_task_todo et lance l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_to_do);

        Intent intent = getIntent();
        idPlayground = intent.getStringExtra("idPlayground");
        Toast.makeText(getApplicationContext(), idPlayground, Toast.LENGTH_SHORT).show();
        listTasks = (ListView) findViewById(R.id.list_view_Task);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TaskToDoActivity.this, NewTask.class);
                intent.putExtra("idPlayground",idPlayground);
                startActivity(intent);
                finish();
            }
        });

        showListTask();
    }

    /*
    Méthode showlistTask affiche la listView et récupère les données dedans
     */
    private void showListTask(){

        ArrayList<Task> listest = new ArrayList<Task>();

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();

        c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Task.TABLE_NAME+
                " where "+FeedReaderContract.Task.COLUMN_NAME_IDPLAYGROUND+" = "+idPlayground+
                " And "+ FeedReaderContract.Task.COLUMN_NAME_IDSTATE+" = 1", null);

        if (c.moveToFirst())
        {
            do{
                listest.add(new Task(
                        c.getString(5)
                ));
            } while (c.moveToNext());
        }
        else
            Toast.makeText(getApplicationContext(), idPlayground, Toast.LENGTH_SHORT).show();

        listTasks.setAdapter(new TaskToDoAdapter(this, listest));
        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TaskToDoActivity.this, PlaygroundFicheActivity.class);
                c.moveToPosition(position);
                intent.putExtra("idTask",c.getString(0));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), idPlayground, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
