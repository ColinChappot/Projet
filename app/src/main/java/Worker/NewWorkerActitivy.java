package Worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.colin.projet.R;

public class NewWorkerActitivy extends AppCompatActivity {

    private EditText etxtFirstname;
    private EditText etxtLastname;
    private EditText etxtCellphone;
    private Button btnsaveWorker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_worker_actitivy);



        etxtFirstname = (EditText) findViewById(R.id.eTxtFirstname);
        etxtLastname = (EditText) findViewById(R.id.eTxtLastname);
        etxtCellphone = (EditText) findViewById(R.id.eTxtCellphone);
        btnsaveWorker = (Button)  findViewById((R.id.btnSaveWorker));
        btnsaveWorker.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewWorkerActitivy.this, WorkerListMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
