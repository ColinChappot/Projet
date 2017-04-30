package Worker;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.colin.projet.R;

import DB.DbHelper;


public class WorkerFicheActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_fiche);

        getIntent().getIntExtra("IdWorker", 0);//Ligne pour sélectionner le bon worker

        SQLiteDatabase db = new DbHelper(this).getReadableDatabase();

       /* Cursor c = db.rawQuery("select " + FeedReaderContract.Worker.COLUMN_NAME_FIRSTNAME + " from "
                + FeedReaderContract.Worker.TABLE_NAME + " where name = ?", new String[]{"1"});

        String message = c.getString(1);

        TextView textView = (TextView) findViewById(R.id.textView9);
        textView.setText(message);*/

    }




}
