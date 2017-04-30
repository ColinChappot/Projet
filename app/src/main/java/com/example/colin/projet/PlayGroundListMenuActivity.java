package com.example.colin.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class PlayGroundListMenuActivity extends ActionBarActivity {

    private Session session;
    private ListView listPlayGround;
    private PlayGroundAdapter adapter;
    private SearchView editSearch;
    private String[] playGrounds = new String[]{
            "Place du Midi", "Place de la planta", "place du carré", "Place du petit Bec", "Place de la Houlette"
    };

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground_list_menu);

        /*session = new Session(this);
        if(!session.loggedIn()){
            logout();
        }*/

        listPlayGround = (ListView) findViewById(R.id.list_view);

        //Affiche la liste des playgrounds
        showListPlayGround();

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlayGroundListMenuActivity.this, android.R.layout.simple_list_item_1, playGrounds);

        listPlayGround.setAdapter(adapter);
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
