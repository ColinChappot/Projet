package Playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.colin.projet.R;

public class PlayGroundSubMenuActivity extends AppCompatActivity {

    TextView titlePlayGround;
    Button btnTaskTodo;
    Button btnLastUpdate;
    Button btnInfoZone;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play_ground_sub_menu);

            btnTaskTodo = (Button) findViewById(R.id.btnTaskToDo);
            btnLastUpdate = (Button) findViewById(R.id.btnLastUpdate);
            btnInfoZone = (Button) findViewById(R.id.btnInfoZone);


            btnTaskTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayGroundSubMenuActivity.this, Task.TaskToDoActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            btnLastUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayGroundSubMenuActivity.this, Task.TaskLastUpdate.class);
                    startActivity(intent);
                    finish();
                }
            });

            btnInfoZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayGroundSubMenuActivity.this, Task.TaskInfoZoneActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


//    TextView txtVTaskTodo;
//    TextView txtVLastUpdate;
//    TextView txtVInfozone;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_play_ground_sub_menu);
//
//        txtVTaskTodo=(TextView) findViewById(R.id.txtTaskTodo);
//        txtVLastUpdate=(TextView) findViewById(R.id.txtLastUpdate);
//        txtVInfozone=(TextView) findViewById(R.id.txtInfoZone);
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        //faire le switch case pour dire que si on clique sur l'un des trois onglets on va dessus
//
//        TaskToDoFragment fTaskToDo = new TaskToDoFragment();
//        TaskLastUpadteFragment fLastUpdate = new TaskLastUpadteFragment();
//        TaskInfoZone  fInfoZone = new TaskInfoZone();
//
//
//        ft.replace(R.id.fragmentPosition, fTaskToDo);
//        ft.commit();
//
//
//       txtVLastUpdate.setOnClickListener(
//               FragmentTransaction ft = new FragmentTransaction() {
//               } );
//
//
//
//    }


        }}
