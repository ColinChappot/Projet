package Task;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colin.projet.R;

import DB.DbHelper;
import DB.FeedReaderContract;

public class NewTask extends AppCompatActivity {

    /*
    Déclaration des variables
     */
    private TextView txtVPlayGroundName;
    private EditText etextTaskName;
    private EditText eTextBDescription;
    private EditText eTextObservation;
    private Button btnSaveTask2;
    private String idPlayground;

    /*
    Methode onCreate relie le java au layout
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        Intent intent = getIntent();
        idPlayground = intent.getStringExtra("idPlayground");

        txtVPlayGroundName = (TextView) findViewById(R.id.txtVPlayGroundName);

        SQLiteDatabase dbR = new DbHelper(this).getReadableDatabase();

        Cursor c = dbR.rawQuery("SELECT * FROM " + FeedReaderContract.Playground.TABLE_NAME+
                " where "+FeedReaderContract.Playground._ID+" = "+idPlayground, null);
        if(c.moveToFirst())
        {
            txtVPlayGroundName.setText(c.getString(2));
        }

        etextTaskName = (EditText) findViewById(R.id.eTextTaskName);
        eTextBDescription = (EditText)findViewById(R.id.eTxtDescription);
        eTextObservation = (EditText)findViewById(R.id.eTxtObservation);
        btnSaveTask2 = (Button) findViewById(R.id.btnSaveTask);
        btnSaveTask2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add();
                Intent intent=new Intent(NewTask.this, TaskToDoActivity.class);
                intent.putExtra("idPlayground",idPlayground);
                startActivity(intent);
                finish();
            }
        });
    }

    /*
    Méthode d'ajout d'une nouvelle tâche
     */
    public void add()
    {
        DbHelper db = new DbHelper(this);

        db.InsertTask(this,Integer.valueOf(idPlayground),0,eTextBDescription.getText().toString(),eTextObservation.getText().toString(),etextTaskName.getText().toString());

        Toast.makeText(getApplicationContext(), this.getString(R.string.newTaskCreated), Toast.LENGTH_SHORT).show();
    }
}
