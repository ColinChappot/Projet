package Playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.colin.projet.R;

public class NewPlayground extends AppCompatActivity {

    private EditText etxtTown;
    private EditText etxtPlacesName;
    private EditText etxtSurface;
    private EditText etxtGPS;
    private EditText etxtTimtoAvoid;
    private Button btnSavePlayground;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playground);

        etxtTown = (EditText) findViewById(R.id.etxtTown);
        etxtPlacesName = (EditText) findViewById(R.id.etxtPlacename);
        etxtSurface = (EditText) findViewById(R.id.etxtSurface);
        etxtGPS = (EditText) findViewById(R.id.extGPS);
        etxtTimtoAvoid = (EditText) findViewById(R.id.etxtTimeToAvoid);
        btnSavePlayground = (Button)  findViewById((R.id.btnSavePlayground));
        btnSavePlayground.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewPlayground.this, PlayGroundListMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
