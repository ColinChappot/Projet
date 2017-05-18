package playground;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.colin.projet.R;
import com.example.colin.projet.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import db.DbHelper;
import db.FeedReaderContract;
import worker.WorkerListMenuActivity;


public class PlayGroundListMenuActivity extends ActionBarActivity {

    /*
    Declaration des variables
     */
    private ListView listPlayGround;
    private PlayGroundAdapter adapter;
    private Button btnAddPlayGround;
    private String idWorker;
    private Cursor c;

    /*
    Méthodes onCreat charge l'activité
     */
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_ground_list_menu);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");

        listPlayGround = (ListView) findViewById(R.id.list_view_Playground);
        btnAddPlayGround = (Button) findViewById(R.id.btnAddPlayGround);
        btnAddPlayGround.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlayGroundListMenuActivity.this, NewPlayground.class);
                intent.putExtra("idWorker",idWorker);
                startActivity(intent);
                finish();
            }
        });


       showListPlayGroundName();

    }

    /*
    Méthode qui affiche la liste de playground
     */
    private void showListPlayGroundName(){

        final ArrayList<Playground> listest = new ArrayList<Playground>();

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();

        c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Playground.TABLE_NAME, null);


        if (c.moveToFirst())
        {
           do{
                listest.add(new Playground(
                        c.getString(0),
                        c.getString(2)
                ));
            } while (c.moveToNext());
        }

        listPlayGround.setAdapter(new PlayGroundAdapter(this, listest));
        listPlayGround.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(PlayGroundListMenuActivity.this, PlayGroundSubMenuActivity.class);
                c.moveToPosition(position);
                intent.putExtra("IdPlayGround",c.getString(0));
                startActivity(intent);
            }
        });

    }

    //méthodes qui génère liste
    private  List<Playground> generePlayGrounds() {
        List<Playground> playgrounds = new ArrayList<Playground>();

            return playgrounds;

    }

    private void showListPlayGround(){


        List<Playground> playgrounds = generePlayGrounds();

        PlayGroundAdapter adapter = new PlayGroundAdapter(PlayGroundListMenuActivity.this, playgrounds );
        listPlayGround.setAdapter(adapter);
    }

    /*
    Méthode pour la toolbar.
     */

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
             intent.putExtra("idWorker", idWorker);
            startActivity(intent);
        }


        if(id == R.id.action_switch_worker){
            Intent intent = new Intent(PlayGroundListMenuActivity.this,WorkerListMenuActivity.class);
            intent.putExtra("idWorker", idWorker);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
