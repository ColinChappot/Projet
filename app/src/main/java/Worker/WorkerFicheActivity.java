package Worker;


import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colin.projet.LoginActivity;
import com.example.colin.projet.R;

import java.util.ArrayList;

import DB.DbHelper;
import DB.FeedReaderContract;


public class WorkerFicheActivity extends AppCompatActivity {

    private TextView txtBFirstName;
    private TextView txtVFirstName;
    private TextView txtBLastName;
    private TextView txtVLastName;
    private TextView txtBCellPhone;
    private TextView txtVCellPhone;
    private Button btnCall;
    private Button btnMessage;
    private ImageView imgWorker;

    String idWorker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_fiche);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");
        Toast.makeText(getApplicationContext(), idWorker, Toast.LENGTH_SHORT).show();

        String message="";


        btnCall = (Button) findViewById(R.id.btnCall);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        imgWorker = (ImageView) findViewById((R.id.imgWorker));


        getIntent().getIntExtra("IdWorker", 0);//Ligne pour sélectionner le bon worker




        SQLiteDatabase dbR= new DbHelper(this).getReadableDatabase();




        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME+" where "+FeedReaderContract.Worker._ID+" = "+idWorker, null);
        Resources res = getResources();


        if (c.moveToFirst()) {
            message = c.getString(3);
            TextView  textView = (TextView) findViewById(R.id.textView14);
            textView.setText(message);
            message = c.getString(4);
            textView = (TextView) findViewById(R.id.textView10);
            textView.setText(message);
            message = c.getString(5);
            textView = (TextView) findViewById(R.id.textView11);
            textView.setText(message);

           int resID = res.getIdentifier("worker"+c.getString(0), "drawable",  WorkerFicheActivity.this.getPackageName());

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageResource(resID);
        }
    }




}
