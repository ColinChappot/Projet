package Task;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;

import DB.DbHelper;
import DB.FeedReaderContract;
import Playground.Installation;
import Playground.InstallationAdapter;
import Worker.Worker;

public class TaskInfoZoneActivity extends AppCompatActivity {

    private TextView txtBTown;
    private TextView txtVTown;
    private TextView txtBPlacesName;
    private TextView txtVPlacesName;
    private TextView txtBSurface;
    private TextView txtVSurface;
    private TextView txtBTimeToAvoid;
    private TextView txtVTimeToAvoid;
    private TextView txtBGPSlocation;
    private TextView txtVGPSlocation;
    private ImageView imgPlayground;
    private ListView  listViewInstallation;
    private InstallationAdapter adapter;
    private SQLiteDatabase dbR;
    private String idPlayground;
    private Button btnAddInstallation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info_zone);

        Intent intent = getIntent();
        idPlayground = intent.getStringExtra("idPlayground");

        txtVTown = (TextView) findViewById(R.id.txtVTown);
        txtVPlacesName = (TextView) findViewById(R.id.txtVPlacesName);
        txtVSurface = (TextView) findViewById(R.id.txtVSurfafce);
        txtVTimeToAvoid = (TextView) findViewById(R.id.txtVTimeToAvoid);
        txtBGPSlocation = (TextView) findViewById(R.id.txtBGPS);
        txtVGPSlocation = (TextView) findViewById(R.id.txtVGPS);
        imgPlayground = (ImageView) findViewById(R.id.ImgPlayGround);
        listViewInstallation = (ListView) findViewById(R.id.listViewInstallation);
        btnAddInstallation = (Button) findViewById(R.id.btnAddInstallation) ;

        dbR = new DbHelper(this).getReadableDatabase();
        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Playground.TABLE_NAME+
                " where "+ FeedReaderContract.Playground._ID+" = "+idPlayground, null);
        Resources res = getResources();
        String message;
        if (c.moveToFirst())
        {
            do {
                message=c.getString(1);
                txtVTown.setText(message);
                message = c.getString(2);
                txtVPlacesName.setText(message);
                message = c.getString(3);
                txtVSurface.setText(message);
                message = c.getString(4);
                txtVTimeToAvoid.setText(message);
                message = c.getString(5);
                txtVGPSlocation.setText(message);

                int resID = res.getIdentifier("parc"+c.getString(0), "drawable",  TaskInfoZoneActivity.this.getPackageName());
                imgPlayground .setImageResource(resID);

            }while (c.moveToNext());
        }


        showInstallation();

        btnAddInstallation.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add();
                Intent intent = new Intent(TaskInfoZoneActivity.this, TaskInfoZoneActivity.class);
                intent.putExtra("idPlayground",idPlayground);
                startActivity(intent);
                finish();
            }
        });
    }
    private void showInstallation(){
        ArrayList<Installation> listest = new ArrayList<>();
        dbR = new DbHelper(this).getReadableDatabase();
        Cursor c = dbR.rawQuery("SELECT install."+ FeedReaderContract.Installation.COLUMN_NAME_DESCRIPTION+" FROM " +FeedReaderContract.Installation.TABLE_NAME +" install, "+FeedReaderContract.InstallationPlaced.TABLE_NAME+" place "+
                 "where place."+ FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDPLAYGROUND+" = "+idPlayground+" and install."+ FeedReaderContract.Installation._ID+" = "+ FeedReaderContract.InstallationPlaced.COLUMN_NAME_IDINSTALLATION, null);
        String message;
        if (c.moveToFirst())
        {
            do {
                listest.add(new Installation(
                      c.getString(0)
                ));

            }while (c.moveToNext());
        }

        listViewInstallation.setAdapter(new InstallationAdapter(this, listest));

    }


    public  void add()
    {
        EditText material = (EditText) findViewById(R.id.editText);
        String message = material.getText().toString();
        DbHelper db = new DbHelper(this);
        SQLiteDatabase dbR = new DbHelper(this).getWritableDatabase();

        Cursor c = dbR.rawQuery("SELECT * FROM "+ FeedReaderContract.Installation.TABLE_NAME+"" +
                " where "+ FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION+" = '"+message+"'", null);

        if(c.moveToFirst())
        {
            db.InsertInstallationPlaced(this,Integer.valueOf(idPlayground),c.getString(0));
        }
        else
        {
            db.InsertInstallation(this, message);

            c = dbR.rawQuery("SELECT * FROM "+ FeedReaderContract.Installation.TABLE_NAME+"" +
                    " where "+ FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION+" = '"+message+"'", null);
            if(c.moveToFirst())
            {
                db.InsertInstallationPlaced(this,Integer.valueOf(idPlayground),c.getString(0));
            }
        }

    }
}
