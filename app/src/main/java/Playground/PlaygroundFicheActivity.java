package Playground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.colin.projet.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_fiche);

        getIntent().getIntExtra("IdPlayGround", 0);

        btnPreviewTask = (Button) findViewById(R.id.btnPreviewTask);
        btnNextTask =  (Button) findViewById(R.id.btnNextTask);
        btnAddMateriel = (Button) findViewById(R.id.btnAddMatriel);
        btnSave = (Button) findViewById(R.id.btnSave);
        txtVPlayGroundName = (TextView) findViewById(R.id.txtVLogin);
        txtTask = (TextView) findViewById((R.id.txtTask));
        txtBDescription = (TextView) findViewById((R.id.txtBDescritpion));
        txtBObservation = (TextView) findViewById((R.id.txtBObservation));
        txtBNeedMaterials = (TextView) findViewById((R.id.txtBDescritpion));
        eTxtDescription = (EditText) findViewById(R.id.eTxtDescription);
        eTxtObservation = (EditText) findViewById(R.id.eTxtObservation);
        listViewMateriel = (ListView) findViewById(R.id.listViewMateriel);

    }
}
