package Task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.colin.projet.R;

import java.util.ArrayList;
import java.util.List;

import Playground.Installation;
import Playground.InstallationAdapter;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info_zone);

        txtBTown = (TextView) findViewById(R.id.txtBTown);
        txtVTown = (TextView) findViewById(R.id.txtVTown);
        txtBPlacesName = (TextView) findViewById(R.id.txtBName);
        txtVPlacesName = (TextView) findViewById(R.id.txtVPlacesName);
        txtBSurface = (TextView) findViewById(R.id.txtBSurface);
        txtVSurface = (TextView) findViewById(R.id.txtVSurfafce);
        txtBTimeToAvoid = (TextView) findViewById(R.id.txtBTimeToAvoid);
        txtVTimeToAvoid = (TextView) findViewById(R.id.txtVTimeToAvoid);
        txtBGPSlocation = (TextView) findViewById(R.id.txtBGPS);
        txtVGPSlocation = (TextView) findViewById(R.id.txtVGPS);
        imgPlayground = (ImageView) findViewById(R.id.ImgPlayGround);
        listViewInstallation = (ListView) findViewById(R.id.listViewInstallation);


    showInstallation();
    }
    private void showInstallation(){
        Installation instal1 = new Installation("Tyrolliène","usé");
        Installation instal2 = new Installation("Tobogan","defectueux");

        ArrayList<Installation> listest = new ArrayList<>();
        listest.add(instal1);
        listest.add(instal2);

        listViewInstallation.setAdapter(new InstallationAdapter(this, listest));

    }

    private List<Installation> genereInstallation(){
        List<Installation> installations = new ArrayList<>();

        installations.add(new Installation("balançoire", "neuf"));
        return installations;
    }

    private void showListInstal(){
        List<Installation> installations = genereInstallation();

       InstallationAdapter adapter = new InstallationAdapter(TaskInfoZoneActivity.this, installations);
        listViewInstallation.setAdapter(adapter);
    }
}
