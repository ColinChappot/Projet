package Worker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colin.projet.LoginActivity;
import com.example.colin.projet.R;
import com.example.colin.projet.Session;
import com.example.colin.projet.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import DB.DbHelper;
import DB.FeedReaderContract;
import Playground.PlayGroundListMenuActivity;
import Playground.Playground;

public class WorkerListMenuActivity extends AppCompatActivity {

    private Session session;
    private ListView listWorker;
    private WorkerAdapter adapter;
    private SearchView editSearch;
    private Button btnSwitchPlayGround;
    private String idWorker;
    private List<Worker> workers ;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list_menu);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");


        session = new Session(this);
        /*if(!session.loggedIn()){
        logout();
        }*/
        listWorker = (ListView) findViewById(R.id.list_view_worker);


        //affiche la liste des workers
        workers = genereWorkers();
        showListWorker();
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
        ArrayAdapter<String> adapter = new ArrayAdapter(WorkerListMenuActivity.this, android.R.layout.simple_list_item_1,workers );


        listWorker.setAdapter(adapter);
        listWorker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(WorkerListMenuActivity.this, WorkerFicheActivity.class);
                 c.moveToPosition(position);
                intent.putExtra("idWorker",c.getString(0));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), c.getString(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
   Méthode  genereWorkers : génère la liste
    */
    private List<Worker> genereWorkers(){
        List<Worker> workers = new ArrayList<>();

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();

        c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME, null);


        if (c.moveToFirst())
        {
            do {
                workers.add(new Worker(
                        c.getString(0),
                        c.getString(3),
                        c.getString(4)
                ));
            }while (c.moveToNext());
        }

        return workers;

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

        if(id == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            intent.putExtra("idWorker",idWorker);
            startActivity(intent);
            return true;
        }


        if(id == R.id.btnSwitchPlayGround){
            Intent intent = new Intent(this,PlayGroundListMenuActivity.class);
            intent.putExtra("idWorker",idWorker);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}