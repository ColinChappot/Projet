package playground;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colin.projet.R;

import db.DbHelper;
import db.FeedReaderContract;

public class PlayGroundSubMenuActivity extends AppCompatActivity {

    TextView titlePlayGround;
    Button btnTaskTodo;
    Button btnLastUpdate;
    Button btnInfoZone;
    String idPlayground;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play_ground_sub_menu);

            btnTaskTodo = (Button) findViewById(R.id.btnTaskToDo);
            btnLastUpdate = (Button) findViewById(R.id.btnLastUpdate);
            btnInfoZone = (Button) findViewById(R.id.btnInfoZone);
            titlePlayGround = (TextView) findViewById(R.id.textView) ;

            Intent intent = getIntent();
           idPlayground = intent.getStringExtra("IdPlayGround");
            Toast.makeText(getApplicationContext(), idPlayground, Toast.LENGTH_SHORT).show();

            SQLiteDatabase dbR = new DbHelper(this).getWritableDatabase();

            Cursor c = dbR.rawQuery("SELECT * FROM "+ FeedReaderContract.Playground.TABLE_NAME+"" +
                    " where "+ FeedReaderContract.Playground._ID+" = "+idPlayground, null);

            if(c.moveToFirst())
            {
                titlePlayGround.setText(c.getString(2));
            }


            btnTaskTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayGroundSubMenuActivity.this, task.TaskToDoActivity.class);
                    intent.putExtra("idPlayground",idPlayground);
                    startActivity(intent);
                    finish();
                }
            });

            btnLastUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayGroundSubMenuActivity.this, task.TaskLastUpdate.class);
                    intent.putExtra("idPlayground",idPlayground);
                    startActivity(intent);
                    finish();
                }
            });

            btnInfoZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayGroundSubMenuActivity.this, task.TaskInfoZoneActivity.class);
                    intent.putExtra("idPlayground",idPlayground);
                    startActivity(intent);
                    finish();
                }
            });


//
//    }

        }




    }

