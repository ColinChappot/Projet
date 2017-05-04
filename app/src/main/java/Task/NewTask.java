package Task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.colin.projet.R;

public class NewTask extends AppCompatActivity {

    private EditText etextTaskName;
    private EditText eTextBDescription;
    private EditText eTextObservation;
    private Button btnSaveTask2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        etextTaskName = (EditText) findViewById(R.id.eTextTaskName);
        eTextBDescription = (EditText)findViewById(R.id.eTxtDescription);
        eTextObservation = (EditText)findViewById(R.id.eTxtObservation);
        btnSaveTask2 = (Button) findViewById(R.id.btnSaveTask);
        btnSaveTask2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewTask.this, TaskToDoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
