package Playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.colin.projet.LoginActivity;
import com.example.colin.projet.R;
import com.example.colin.projet.Session;

import java.util.ArrayList;
import java.util.List;

import Worker.WorkerListMenuActivity;


public class PlayGroundListMenuActivity extends ActionBarActivity {

    private Session session;
    private ListView listPlayGround;
    private PlayGroundAdapter adapter;
    private SearchView editSearch;
    private Button button2;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_ground_list_menu);

        //A décommenter quand on pourra utilisez le login avec la BD
        /*session = new Session(this);
        if(!session.loggedIn()){
            logout();
        }*/

        listPlayGround = (ListView) findViewById(R.id.list_view_Playground);
        button2 =(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlayGroundListMenuActivity.this, WorkerListMenuActivity.class);
                startActivity(intent);
            }
        });


        //Affiche la liste des playgrounds
        showListPlayGround();
       // showListPlayGroundName();

        //Localiser le edit text dans la listeView du fichier xml
        editSearch = (SearchView) findViewById(R.id.search);
//        editSearch.setOnQueryTextListener(this);
    }


    //méthode qui renvoie à la page login si la sessin n'est pas logée
    private void logout(){
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(PlayGroundListMenuActivity.this, LoginActivity.class));
    }

    //méthode qui affiche la liste
    private void showListPlayGroundName(){
        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlayGroundListMenuActivity.this, R.layout.row_playground,playGrounds);

        Playground a = new Playground("a");
        Playground b = new Playground("b");
        ArrayList<Playground> listest = new ArrayList<Playground>();
        listest.add(a);
        listest.add(b);

        listPlayGround.setAdapter(new PlayGroundAdapter(this, listest));
        listPlayGround.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(PlayGroundListMenuActivity.this, PlayGroundSubMenuActivity.class);
                intent.putExtra("IdWorker", position);
                startActivity(intent);
            }
        });

    }
    //méthodes qui génère liste
    private  List<Playground> generePlayGrounds(){
        List<Playground> playgrounds = new ArrayList<Playground>();

        playgrounds.add(new Playground("Place du majeur"));
        return playgrounds ;
    }

    private void showListPlayGround(){
        List<Playground> playgrounds = generePlayGrounds();

        PlayGroundAdapter adapter = new PlayGroundAdapter(PlayGroundListMenuActivity.this, playgrounds );
        listPlayGround.setAdapter(adapter);
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




//Code pour gérer la recherche a voir comment le tourner
/*public class PlayGroundListMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] animalNameList;
    ArrayList<AnimalNames> arraylist = new ArrayList<AnimalNames>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate sample data

        animalNameList = new String[]{"Lion", "Tiger", "Dog",
                "Cat", "Tortoise", "Rat", "Elephant", "Fox",
                "Cow","Donkey","Monkey"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < animalNameList.length; i++) {
            AnimalNames animalNames = new AnimalNames(animalNameList[i]);
            // Binds all strings into an array
            arraylist.add(animalNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
*/

}
