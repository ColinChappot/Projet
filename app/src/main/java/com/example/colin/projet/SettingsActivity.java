package com.example.colin.projet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import cloud.EntityDB;
import cloud.ListPlaygroundAsyc;
import db.DbHelper;
import db.FeedReaderContract;


import static cloud.EntityDB.loadingDone;


public class SettingsActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnCH;
    private Button btnDE;
    private Button btnEN;
    private Button btnUpdate;
    private String idWorker;
    private Resources res;
    private ProgressDialog progress ;
    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");

        String message="";

        btnSave = (Button) findViewById(R.id.buttonSave);
        btnCH = (Button) findViewById(R.id.drapeauCH);
        btnEN = (Button) findViewById(R.id.drapeauEn);
        btnDE = (Button) findViewById(R.id.drapeauDE);
        btnUpdate = (Button) findViewById(R.id.btnupdate) ;

        //Action sur le bouton.
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             for(int i =0; i<loadingDone.size(); i++)
             {
                 loadingDone.set(i,false);
             }

                db.toCloudTask();
                db.toCloudWorker();
                db.toCloudPlayground();
                db.toCloudInstallationPlaced();
                db.toCloudInstallation();
                db.toCloudMaterial();
                db.toCloudMaterialNeeded();
                db.toCloudState();

                new ListPlaygroundAsyc(db).execute();



                laodingProcess();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save();
            }
        });
        btnCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToFr(res,v);
            }
        });
        btnDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToDE(res,v);
            }
        });
        btnEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToEN(res,v);
            }
        });

        SQLiteDatabase dbR= new DbHelper(this).getReadableDatabase();




        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME+" where "+FeedReaderContract.Worker._ID+" = "+idWorker, null);


        if (c.moveToFirst()) {
            // message = c.getString(3);
            message = c.getString(1);
            EditText editText = (EditText) findViewById(R.id.editTextLoginW);
            editText.setText(message);
            message = c.getString(2);
            editText = (EditText) findViewById(R.id.editTextPassowrdW);
            editText.setText(message);
            message = c.getString(3);
            editText = (EditText) findViewById(R.id.editTextFirstW);
            editText.setText(message);
            message = c.getString(4);
            editText = (EditText) findViewById(R.id.editTextLastW);
            editText.setText(message);
            message = c.getString(5);
            editText = (EditText) findViewById(R.id.editText2);
            editText.setText(message);
        }
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

    public void save()
    {
        EditText Login = (EditText) findViewById(R.id.editTextLoginW);
        EditText Password = (EditText) findViewById(R.id.editTextPassowrdW);
        EditText Firstname = (EditText) findViewById(R.id.editTextFirstW);
        EditText Lastname = (EditText) findViewById(R.id.editTextLastW);
        EditText Cellphone = (EditText) findViewById(R.id.editText2);


        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();

        String strSQL = "UPDATE worker SET login = '"+Login.getText().toString()+"',password = '"+Password.getText().toString()+"' ,firstname = '"+Firstname.getText().toString()+"' ,lastname = '"+Lastname.getText().toString()+
                "' ,cellphone = '"+Cellphone.getText().toString()+"' WHERE "+ FeedReaderContract.Worker._ID+" = "+idWorker ;
        db.execSQL(strSQL);
        dbHelper.toCloudWorker();
        Toast.makeText(getApplicationContext(), this.getString(R.string.settingsSaved), Toast.LENGTH_SHORT).show();
    }

    public void changeToFr(Resources res, View v)
    {


        String languageToLoad="fr";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();

        config.locale=locale;

        getResources().updateConfiguration(config,v.getResources().getDisplayMetrics());


        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("idWorker",idWorker);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
    public void changeToDE(Resources res, View v)
    {
        String languageToLoad="de";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();

        config.locale=locale;

        getResources().updateConfiguration(config,v.getResources().getDisplayMetrics());


        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("idWorker",idWorker);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void changeToEN(Resources res, View v)
    {
        String languageToLoad="en";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();

        config.locale=locale;

        getResources().updateConfiguration(config,v.getResources().getDisplayMetrics());


        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("idWorker",idWorker);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
}
