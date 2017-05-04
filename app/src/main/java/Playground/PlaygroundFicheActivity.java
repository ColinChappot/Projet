package Playground;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;
import DB.DbHelper;
import DB.FeedReaderContract;
import Worker.Worker;
import Worker.WorkerFicheActivity;

public class PlaygroundFicheActivity extends AppCompatActivity {

    private Button btnPreviewTask;
    private Button btnNextTask;
    private Button btnAddMateriel;
    private Button btnSave;
    private TextView txtVPlayGroundName;
    private TextView txtTask;
    private TextView txtBDescription;
    private TextView txtBObservation;
    private TextView txtBNeedMaterials;
    private EditText eTxtDescription;
    private EditText eTxtObservation;
    private ListView listViewMateriel;
    private String idTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_fiche);

        btnAddMateriel = (Button) findViewById(R.id.btnAddMatriel);
        btnSave = (Button) findViewById(R.id.btnSave);
        txtVPlayGroundName = (TextView) findViewById(R.id.txtVLogin);
        txtTask = (TextView) findViewById((R.id.txtTask));
        listViewMateriel = (ListView) findViewById(R.id.listViewMateriel);

        Intent intent = getIntent();
        idTask = intent.getStringExtra("idTask");


        btnSave = (Button) findViewById(R.id.btnAddTask);
/*        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            save();
            }
        });*/
        SQLiteDatabase dbR= new DbHelper(this).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT idPlayground FROM " + FeedReaderContract.Task.TABLE_NAME+
                " task where task."+FeedReaderContract.Task._ID+" = "+idTask, null);

        String message = "";
        c.moveToFirst();
        String idPlayground = c.getString(0);



         c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Playground.TABLE_NAME+
                " playground where playground."+FeedReaderContract.Playground._ID+" = "+idPlayground, null);

        if (c.moveToFirst()) {
            message = c.getString(2);
            TextView textView = (TextView) findViewById(R.id.txtVPlayGroundName);
            textView.setText(message);
        }
         c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Task.TABLE_NAME+" where "+FeedReaderContract.Task._ID+" = "+idTask, null);

        if (c.moveToFirst()) {
            message = c.getString(0);
            TextView textView = (TextView) findViewById(R.id.txtTask);
            textView.setText(message);
            message = c.getString(4);
            EditText editText = (EditText) findViewById(R.id.eTxtDescription);
            editText.setText(message);
            message = c.getString(5);
            editText = (EditText) findViewById(R.id.eTxtObservation);
            editText.setText(message);
        }

        showMaterial();
    }
    private void showMaterial(){


        ArrayList<Material> listest = new ArrayList<>();

        SQLiteDatabase dbR= new DbHelper(this).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * FROM "+ FeedReaderContract.Material.TABLE_NAME+" material , "+ FeedReaderContract.MaterialNeeded.TABLE_NAME+
                " need where material."+ FeedReaderContract.Material._ID+" = need."+ FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDMATERIAL+"" +
                " and need."+ FeedReaderContract.MaterialNeeded.COLUMN_NAME_IDTASK+" = "+idTask, null);

        if (c.moveToFirst())
        {
            do {
                listest.add(new Material(
                       c.getString(1)
                ));
            }while (c.moveToNext());
        }

        listViewMateriel.setAdapter(new MaterialAdapter(this, listest));

    }

    private List<Material> genereMaterial(){
        List<Material> materials = new ArrayList<>();

        materials.add(new Material("Gant"));
        return materials;
    }

    private void showMaterial2(){
        List<Material> materials = genereMaterial();

        MaterialAdapter adapter = new MaterialAdapter(PlaygroundFicheActivity.this, materials);
        listViewMateriel.setAdapter(adapter);
    }

    public void save()
    {
        EditText descritpion = (EditText) findViewById(R.id.txtBDescritpion);
        EditText observation = (EditText) findViewById(R.id.txtBObservation);

        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();

        String strSQL = "UPDATE task SET (description,observation) = ('"+descritpion+"','"+observation+"') WHERE "+ FeedReaderContract.Task._ID+" = "+idTask ;
        db.execSQL(strSQL);
        Toast.makeText(getApplicationContext(), this.getString(R.string.TaskModified), Toast.LENGTH_SHORT).show();
    }
    public  void add()
    {
        EditText material = (EditText) findViewById(R.id.addMaterial);
        String message = material.getText().toString();
        DbHelper db = new DbHelper(this);
        SQLiteDatabase dbR = new DbHelper(this).getWritableDatabase();

        Cursor c = dbR.rawQuery("SELECT * FROM "+ FeedReaderContract.Material.TABLE_NAME+"" +
                " where "+ FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION+" = '"+message+"'", null);

        if(c.moveToFirst())
        {
            db.InsertMaterialNeeded(this,idTask,c.getString(0));
        }
        else
        {
            db.InsertMaterial(this, message);

            c = dbR.rawQuery("SELECT * FROM "+ FeedReaderContract.Material.TABLE_NAME+"" +
                    " where "+ FeedReaderContract.Material.COLUMN_NAME_DESCRIPTION+" = '"+message+"'", null);
            c.moveToFirst();
            db.InsertMaterialNeeded(this,idTask,c.getString(0));
        }

    }


}
