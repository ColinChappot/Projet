package Worker;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colin.projet.R;

import DB.DbHelper;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_fiche);

        txtBFirstName = (TextView) findViewById(R.id.txtbFirstname);
        txtVFirstName = (TextView) findViewById(R.id.txtVFirstname);
        txtBLastName = (TextView) findViewById(R.id.txtBlastName);
        txtVLastName = (TextView) findViewById(R.id.txtVLastname);
        txtBCellPhone = (TextView) findViewById(R.id.txtbCellPhone);
        txtVCellPhone = (TextView) findViewById(R.id.txtVCellphone);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        imgWorker = (ImageView) findViewById((R.id.imgWorker));


        getIntent().getIntExtra("IdWorker", 0);//Ligne pour sélectionner le bon worker

        SQLiteDatabase db = new DbHelper(this).getReadableDatabase();

       /* Cursor c = db.rawQuery("select " + FeedReaderContract.Worker.COLUMN_NAME_FIRSTNAME + " from "
                + FeedReaderContract.Worker.TABLE_NAME + " where name = ?", new String[]{"1"});

        String message = c.getString(1);

        TextView textView = (TextView) findViewById(R.id.textView9);
        textView.setText(message);*/

    }




}
