package playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colin.projet.R;

import db.DbHelper;
import worker.WorkerListMenuActivity;

public class NewPlayground extends AppCompatActivity {

    /*
    Declaration des variables
     */
    private EditText etxtTown;
    private EditText etxtPlacesName;
    private EditText etxtSurface;
    private EditText etxtGPS;
    private EditText etxtTimtoAvoid;
    private Button btnSavePlayground;
    private String idWorker;


    /*
    Méthode on create charge l'actitivité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playground);
        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");

        etxtTown = (EditText) findViewById(R.id.etxtTown);
        etxtPlacesName = (EditText) findViewById(R.id.etxtPlacename);
        etxtSurface = (EditText) findViewById(R.id.etxtSurface);
        etxtGPS = (EditText) findViewById(R.id.extGPS);
        etxtTimtoAvoid = (EditText) findViewById(R.id.etxtTimeToAvoid);
        btnSavePlayground = (Button)  findViewById((R.id.btnSavePlayground));
        btnSavePlayground.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                add();
                Intent intent=new Intent(NewPlayground.this, WorkerListMenuActivity.class);
                intent.putExtra("idWorker", idWorker);
                startActivity(intent);
                finish();
            }
        });

    }
    public void add()
    {
        DbHelper db = new DbHelper(this);

        db.InsertPlayground(this,etxtPlacesName.getText().toString(),etxtTown.getText().toString(),etxtSurface.getText().toString(),etxtGPS.getText().toString(),etxtTimtoAvoid.getText().toString());
        db.toCloudPlayground();
        Toast.makeText(getApplicationContext(), this.getString(R.string.newPlaygroundCreated), Toast.LENGTH_SHORT).show();
    }
}
