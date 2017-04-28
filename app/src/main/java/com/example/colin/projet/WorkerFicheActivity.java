package com.example.colin.projet;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import DB.DbHelper;
import DB.FeedReaderContract;


public class WorkerFicheActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_fiche);


        SQLiteDatabase db = new DbHelper(this).getReadableDatabase();

       /* Cursor c = db.rawQuery("select " + FeedReaderContract.Worker.COLUMN_NAME_FIRSTNAME + " from "
                + FeedReaderContract.Worker.TABLE_NAME + " where name = ?", new String[]{"1"});

        String message = c.getString(1);

        TextView textView = (TextView) findViewById(R.id.textView9);
        textView.setText(message);*/

    }




}
