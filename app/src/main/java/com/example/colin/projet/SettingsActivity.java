package com.example.colin.projet;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import DB.DbHelper;
import DB.FeedReaderContract;
import Worker.WorkerFicheActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button btnSave;
    private String idWorker;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");

        String message="";

        btnSave = (Button) findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save();
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

        String strSQL = "UPDATE worker SET (login,password,firstname,lastname,cellphone) = " +
                "('"+Login+"','"+Password+"','"+Firstname+"','"+Lastname+"','"+Cellphone+ "')" +
                " WHERE "+ FeedReaderContract.Worker._ID+" = "+idWorker ;
        db.execSQL(strSQL);
    }


}

