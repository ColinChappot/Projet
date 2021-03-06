package com.example.colin.projet;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cloud.EntityDB;
import cloud.ListPlaygroundAsyc;
import db.DbHelper;
import db.FeedReaderContract;
import playground.PlayGroundListMenuActivity;

import static android.os.Build.VERSION_CODES.M;


public class LoginActivity extends AppCompatActivity {

    /*Declaration des variables*/
    private EditText username;
    private EditText password;
    private Button signIn;
    private DbHelper db;
    private Session session;
    private SQLiteDatabase dbR;
    private ProgressDialog progress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EntityDB entityDB = new EntityDB();

        session = new Session(this);
        username = (EditText) findViewById(R.id.edTxtUserName);
        password = (EditText) findViewById(R.id.edTxtPassword);
        signIn = (Button) findViewById(R.id.btnSignIn);


        db = new DbHelper(this);

        new ListPlaygroundAsyc(db).execute();

        laodingProcess();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });
        dbR = new DbHelper(this).getReadableDatabase();
        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME, null);

        if(c.moveToFirst()==false)
        {



            db.InsertWorker(this, "Marc", "123", "Marc", "Panatier", "11111111");
            db.InsertWorker(this, "Paul", "123", "Paul", "Zufferey", "00000000");
            db.InsertWorker(this, "Pierre", "123", "Pierre", "Smith", "0099990");
            db.InsertInstallation(this, "Balançoire");
            db.InsertInstallation(this, "Toboggan");
            db.InsertInstallation(this, "Cabane");
            db.InsertMaterial(this, "marteau");
            db.InsertMaterial(this, "échelle");
            db.InsertMaterial(this, "tronçonneuse");
            db.InsertMaterial(this, "pelle");
            db.InsertMaterial(this, "boite à outils");
            db.InsertMaterial(this, "perceuse");
            db.InsertMaterial(this, "visseuse");
            db.InsertMaterial(this, "autre");
            db.InsertMaterialNeeded(this,"1","1");
            db.InsertMaterialNeeded(this,"2","1");
            db.InsertMaterialNeeded(this,"3","1");
            db.InsertMaterialNeeded(this,"4","1");
            db.InsertState(this, "libre");
            db.InsertState(this, "en cours");
            db.InsertState(this, "terminer");
            db.InsertInstallationPlaced(this,1,"1");
            db.InsertInstallationPlaced(this,1,"2");
            db.InsertInstallationPlaced(this,2,"2");
            db.InsertInstallationPlaced(this,2,"3");
            db.InsertPlayground(this, "Parc des écoles", "Martigny", "40", "46.10192488017465 - 7.070822239184054", "10h00 - 10h30");
            db.InsertPlayground(this, "Parc municipale", "Martigny", "70", "46.097758806831635 - 7.073804855608614", "16h00 - 17h00");

            db.InsertTask(this, 1, 0, "Grand trou dans le parc", "Essayer de combler le trou avec des fleures","Combler trou");
            db.InsertTask(this, 2, 0, "Tondre le gazon", "Prendre tondeuse et faire bien attention aux arbustes","Tondre pelouse");
            db.InsertTask(this, 1, 0, "", "Prendre rateau","nettoyer parc");
            db.InsertTask(this, 2, 0, "arroser tout les arbres", "","arrosage");
            db.InsertTask(this, 1, 0, "réparer les installations", "","Maintenance");
            db.InsertTask(this, 2, 0, "peindre barrière", "couleur = brun","Peinture");
        }

        checkPermissions();
    }


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
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wrong username/password", Toast.LENGTH_SHORT).show();
            }


    }

    private void checkPermissions(){
        int PERMISSION_ALL = 1;


        //List of all the permissions needed by the app
        String[] permissions = new String[]{
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE};


        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_ALL);
        }
    }


    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
//méthode pour faire patienter pendant le téléchargement du cloud
    public void laodingProcess(){
        progress=new ProgressDialog(this);
        progress.setMessage(getString(R.string.loading)); // Setting Message
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progress.show(); // Display Progress Dialog
        progress.setCancelable(false);


        Log.d("Thread","starting");
        new Thread(new Runnable() {
            public void run() {
                try {
                    int cpt=0;
                    while (true){
                        cpt=0;

                        for(int i = 0; i< EntityDB.loadingDone.size(); i++){

                            if (EntityDB.loadingDone.get(i)){
                                cpt++;
                            }
                        }
                        if(cpt==8){
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                progress.dismiss();

            }

        }).start();
    };
}




