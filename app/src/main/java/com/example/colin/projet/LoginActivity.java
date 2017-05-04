package com.example.colin.projet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import DB.DbHelper;
import DB.FeedReaderContract;
import Playground.PlayGroundListMenuActivity;
import Worker.WorkerFicheActivity;
import Worker.WorkerListMenuActivity;


public class LoginActivity extends AppCompatActivity {

    // info pris de: https://www.youtube.com/watch?v=x0I5vJfaRIU
    /*Declaration des variables*/
    private EditText username;
    private EditText password;
    private Button signIn;
    private DbHelper db;
    private Session session;
    SQLiteDatabase dbR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //db = new DbHelper(this);
        session = new Session(this);
        username = (EditText) findViewById(R.id.edTxtUserName);
        password = (EditText) findViewById(R.id.edTxtPassword);
        signIn = (Button) findViewById(R.id.btnSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* switch (v.getId()){
                    case R.id.btnSignIn :
                        Log.i("DEBUG", "Bouton Cliqué");*/

                        //pour voir dans la continuité des frame
                        //startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));

                checkData();
                   /*     break;
                }*/
            }
        });
        dbR = new DbHelper(this).getReadableDatabase();
        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME, null);


        if(c.moveToFirst()==false)
        {

            db = new DbHelper(this);

            db.InsertWorker(this, "Marc", "123", "Marc", "Panatier", "11111111");
            db.InsertWorker(this, "Paul", "123", "Paul", "Zufferey", "00000000");
            db.InsertWorker(this, "Pierre", "123", "Pierre", "Smith", "0099990");
            db.InsertInstallation(this, "Balançoire", "Bon état");
            db.InsertInstallation(this, "Toboggan", "Très bon état");
            db.InsertInstallation(this, "Cabane", "Etat moyen");
            db.InsertMaterial(this, "marteau");
            db.InsertMaterial(this, "échelle");
            db.InsertMaterial(this, "tronçonneuse");
            db.InsertMaterial(this, "pelle");
            db.InsertMaterial(this, "boite à outils");
            db.InsertMaterial(this, "perceuse");
            db.InsertMaterial(this, "visseuse");
            db.InsertMaterial(this, "autre");
            db.InsertGravity(this, 1);
            db.InsertGravity(this, 2);
            db.InsertGravity(this, 3);
            db.InsertGravity(this, 4);
            db.InsertGravity(this, 5);
            db.InsertState(this, "libre");
            db.InsertState(this, "en cours");
            db.InsertState(this, "terminer");
            ArrayList<String> installation = new ArrayList<String>();
            installation.add("1");
            installation.add("2");
            db.InsertPlayground(this, "Parc des écoles", "Martigny", "40", "46.10192488017465 - 7.070822239184054", "10h00 - 10h30", installation);
            installation.remove(1);
            installation.add("3");
            db.InsertPlayground(this, "Parc municipale", "Martigny", "70", "46.097758806831635 - 7.073804855608614", "16h00 - 17h00", installation);
            ArrayList<String> material = new ArrayList<String>();
            material.add("1");
            material.add("7");
            db.InsertTask(this, 1, 1, 5, "Grand trou dans le parc", "Essayer de combler le trou avec des fleures","Combler trou", material);
            material.remove(0);
            db.InsertTask(this, 2, 1, 3, "Tondre le gazon", "Prendre tondeuse et faire bien attention aux arbustes","Tondre pelouse", material);
        }

/*
        if(session.loggedIn()){
            startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));
            finish();
        }
*/
    }

   /* //Méthode pour le lilstener du boutton sign in
    @Override
    public void  onClick(View v){
        switch (v.getId()){
            case R.id.btnSignIn :
                Log.i("DEBUG", "Bouton Cliqué");

                //pour voir dans la continuité des frame
                //startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));
                Intent intent=new Intent(LoginActivity.this, WorkerListMenuActivity.class);
                startActivity(intent);


                // checkData();
                break;
        }
    }*/

    //méthode pour checker si le login et le password sont justes

    public void checkData() {
        String strUsername = username.getText().toString().trim();
        String strPassword = password.getText().toString().trim();


        dbR = new DbHelper(this).getReadableDatabase();
        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME+
                " where "+ FeedReaderContract.Worker.COLUMN_NAME_LOGIN+" = '"+strUsername+"'" +
                " and "+ FeedReaderContract.Worker.COLUMN_NAME_PASSWORD+" = '"+strPassword+"'", null);

            if(c.moveToFirst())
            {
                session.setLoggedIn(true);
                Intent intent = new Intent(this, PlayGroundListMenuActivity.class);
                String message= c.getString(0);
                intent.putExtra("idWorker", message);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wrong username/password", Toast.LENGTH_SHORT).show();
            }


    }
}




