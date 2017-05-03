package Task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;

import Playground.PlaygroundFicheActivity;

public class TaskToDoActivity extends AppCompatActivity {

    private ListView listTasks;
    private TaskToDoAdapter adapter;
    private Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_to_do);

        listTasks = (ListView) findViewById(R.id.list_view_Task);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TaskToDoActivity.this, PlaygroundFicheActivity.class);
                startActivity(intent);
            }
        });


        showListTask();
        showListtask2();
    }

    private void showListTask(){
        Task t1 = new Task("Tache1");
        Task t2 = new Task ("Tache2");

        ArrayList<Task> listest = new ArrayList<Task>();
        listest.add(t1);
        listest.add(t2);

        listTasks.setAdapter(adapter);
        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TaskToDoActivity.this, Playground.PlaygroundFicheActivity.class);
                //intent.putExtra("IdPlayground", position);
                startActivity(intent);
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
