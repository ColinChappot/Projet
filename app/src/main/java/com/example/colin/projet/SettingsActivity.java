package com.example.colin.projet;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import db.DbHelper;
import db.FeedReaderContract;

public class SettingsActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnCH;
    private Button btnDE;
    private Button btnEN;
    private String idWorker;
    private Resources res;
    
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

    public void save()
    {
        EditText Login = (EditText) findViewById(R.id.editTextLoginW);
        EditText Password = (EditText) findViewById(R.id.editTextPassowrdW);
        EditText Firstname = (EditText) findViewById(R.id.editTextFirstW);
        EditText Lastname = (EditText) findViewById(R.id.editTextLastW);
        EditText Cellphone = (EditText) findViewById(R.id.editText2);


        
        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();

        String strSQL = "UPDATE worker SET login = '"+Login.getText().toString()+"',password = '"+Password.getText().toString()+"' ,firstname = '"+Firstname.getText().toString()+"' ,lastname = '"+Lastname.getText().toString()+
                "' ,cellphone = '"+Cellphone.getText().toString()+"' WHERE "+ FeedReaderContract.Worker._ID+" = "+idWorker ;
        db.execSQL(strSQL);
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

