package Worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.colin.projet.LoginActivity;
import com.example.colin.projet.R;
import com.example.colin.projet.Session;

import java.util.ArrayList;
import java.util.List;

import Playground.PlayGroundListMenuActivity;

public class WorkerListMenuActivity extends AppCompatActivity {

    private Session session;
    private ListView listWorker;
  //  private Button btnSwitchPlayGround;

    private String[] workers = new String[]{
      "Jean Alfred", "Pierre Richarc", "Porto Ricain", "Posey Douze"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list_menu);

        session = new Session(this);
        /*if(!session.loggedIn()){
        logout();
        }*/
        listWorker = (ListView) findViewById(R.id.list_view_worker);

//
//        btnSwitchPlayGround = (Button) findViewById(R.id.btnSwitchPlayGround);
//
//        btnSwitchPlayGround.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(WorkerListMenuActivity.this, PlayGroundListMenuActivity.class);
//                startActivity(intent);
//            }
//        });

        //affiche la liste des workers
        showListWorker();
        showListWorker2();
    }


    /*
    Méthode logout : renvoie à l'activité ActivitéLogin
     */
    private void logout(){
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(WorkerListMenuActivity.this, LoginActivity.class));
    }

    /*
 Méthode showListWorker: adapte la list dans la liste view
  */
    private void showListWorker(){
        ArrayAdapter<String> adapter = new ArrayAdapter(WorkerListMenuActivity.this, android.R.layout.simple_list_item_1, workers );

        listWorker.setAdapter(adapter);
        listWorker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(WorkerListMenuActivity.this, WorkerFicheActivity.class);
                intent.putExtra("IdWorker", position);
                startActivity(intent);
            }
        });
    }

    /*
   Méthode  genereWorkers : génère la liste
    */
    private List<Worker> genereWorkers(){
        List<Worker> workers = new ArrayList<>();

        workers.add(new Worker("Jean","batiste"));
        return workers;

    }

    /*
    Méthodes shoListWorker:
     */
    private void showListWorker2(){
        List<Worker> workers = genereWorkers();

        WorkerAdapter adapter = new WorkerAdapter(WorkerListMenuActivity.this, workers );
        listWorker.setAdapter(adapter);
    }

      /*
    Méthode pour la toolbar.
     */

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar2,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        /*
        if(id == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        */

        if(id == R.id.action_switch_worker){
            Intent intent = new Intent(this,WorkerListMenuActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}