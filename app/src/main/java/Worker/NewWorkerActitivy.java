package Worker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colin.projet.R;

import DB.DbHelper;
import DB.FeedReaderContract;

public class NewWorkerActitivy extends AppCompatActivity {

    private EditText etxtFirstname;
    private EditText etxtLastname;
    private EditText etxtCellphone;
    private EditText etxtLogin;
    private EditText etxtPassword;

    private Button btnsaveWorker;

    String idWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_worker_actitivy);

        Intent intent = getIntent();
        idWorker = intent.getStringExtra("idWorker");

        etxtLogin = (EditText) findViewById(R.id.eTxtLogin);
        etxtPassword = (EditText) findViewById(R.id.eTxtPassword);
        etxtFirstname = (EditText) findViewById(R.id.eTxtFirstname);
        etxtLastname = (EditText) findViewById(R.id.eTxtLastname);
        etxtCellphone = (EditText) findViewById(R.id.eTxtCellphone);
        btnsaveWorker = (Button)  findViewById((R.id.btnSaveWorker));
        btnsaveWorker.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                add();
                Intent intent=new Intent(NewWorkerActitivy.this, WorkerListMenuActivity.class);
                intent.putExtra("idWorker",idWorker);
                startActivity(intent);
                finish();
            }
        });
    }
    public void add()
    {
        DbHelper db = new DbHelper(this);

        db.InsertWorker(this,etxtLogin.getText().toString(), etxtPassword.getText().toString(),etxtFirstname.getText().toString(),etxtLastname.getText().toString(),etxtCellphone.getText().toString());

        Toast.makeText(getApplicationContext(), this.getString(R.string.newWorkerCreated), Toast.LENGTH_SHORT).show();

    }
}
