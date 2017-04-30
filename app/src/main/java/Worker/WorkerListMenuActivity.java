package Worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.colin.projet.LoginActivity;
import com.example.colin.projet.R;
import com.example.colin.projet.Session;

import java.util.ArrayList;
import java.util.List;

public class WorkerListMenuActivity extends AppCompatActivity {

    private Session session;
    private ListView listWorker;
    private WorkerAdapter adapter;
    private SearchView editSearch;
    private String[] workers = new String[]{
      "Jean Alfred", "Pierre Richarc", "Porto Ricain", "Posey Douze"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list_menu);

        session = new Session(this);
        if(!session.loggedIn()){
        logout();
        }


        listWorker = (ListView) findViewById(R.id.list_view);

        //affiche la liste des workers
        showListWorker();

        editSearch = (SearchView) findViewById(R.id.search);
        editSearch.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

    }

    /*
    Méthodes
     */

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
    private void showListPlayGround(){
        List<Worker> workers = genereWorkers();

        WorkerAdapter adapter = new WorkerAdapter(WorkerListMenuActivity.this, workers );
        listWorker.setAdapter(adapter);
    }

    /*

    //méthode permettant valider la recherche du search...
    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }
    //méthode permettant d'afficher la nouvelle sélection
    @Override
    public boolean onQueryTextChange(String newText){
        String.text = newText;
        adapter.filter(text);
        return false;
    }

   */

    /*
    Méthodes onClickListener: Permet de prends l'id de la session et d'ouvrir WorkerFicheActivity
     */
    
}
