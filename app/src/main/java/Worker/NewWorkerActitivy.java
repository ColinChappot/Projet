package Worker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.colin.projet.R;

public class NewWorkerActitivy extends AppCompatActivity {

    private EditText etxtFirstname;
    private EditText etxtLastname;
    private EditText etxtCellphone;
    private Button btnsave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_worker_actitivy);
    }
}
