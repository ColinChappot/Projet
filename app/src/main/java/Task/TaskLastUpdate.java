package Task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;

public class TaskLastUpdate extends AppCompatActivity {

    private ListView listLastTasks;
    private LastUpdateAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_last_update);

        listLastTasks = (ListView) findViewById(R.id.list_view_LastTask);

        showListLasTaks();
        //showListLastTask();
    }


    private void showListLasTaks(){
        Task t3 = new Task("Ramasser les feuilles", 11, "24.01.17");
        Task t4 = new Task("Bruler les feuilles", 1, "01.05.17");
        ArrayList<Task> listest = new ArrayList<Task>();
        listest.add(t3);
        listest.add(t4);

        listLastTasks.setAdapter(new LastUpdateAdapter(this, listest));
    }

    //méthodes qui génère liste
    private List<Task> genereTasks(){
        List<Task> LastTasks = new ArrayList<Task>();

        LastTasks.add(new Task("tondre la pelouze", 12, "15.03.17"));
        return LastTasks ;
    }

    private void showListLastTask(){
        List<Task> LastTasks = genereTasks();

        LastUpdateAdapter  adapter = new LastUpdateAdapter(TaskLastUpdate.this, LastTasks );
        listLastTasks.setAdapter(adapter);
    }
    
}

