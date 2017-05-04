package Worker;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colin.projet.R;

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
    private ImageView imgWorker;

    String idWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_fiche);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");
        Toast.makeText(getApplicationContext(), idWorker, Toast.LENGTH_SHORT).show();

        String message = "";


        btnCall = (Button) findViewById(R.id.btnCall);
        imgWorker = (ImageView) findViewById((R.id.imgWorker));


        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();


        final Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Worker.TABLE_NAME + " where " + FeedReaderContract.Worker._ID + " = " + idWorker, null);
        Resources res = getResources();


        if (c.moveToFirst()) {
            message = c.getString(3);
            TextView textView = (TextView) findViewById(R.id.txtVFirstname);
            textView.setText(message);
            message = c.getString(4);
            textView = (TextView) findViewById(R.id.txtVLastname);
            textView.setText(message);
            message = c.getString(5);
            textView = (TextView) findViewById(R.id.txtVCellphone);
            textView.setText(message);

            int resID = res.getIdentifier("worker" + c.getString(0), "drawable", WorkerFicheActivity.this.getPackageName());

            ImageView imageView = (ImageView) findViewById(R.id.imgWorker);
            imageView.setImageResource(resID);
        }

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + c.getString(5)));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(intent);
            }
        });

    }


}
