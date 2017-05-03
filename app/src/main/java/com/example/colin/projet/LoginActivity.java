package com.example.colin.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DB.DbHelper;
import Playground.PlayGroundListMenuActivity;


public class LoginActivity extends AppCompatActivity {

    // info pris de: https://www.youtube.com/watch?v=x0I5vJfaRIU

    /*Declaration des variables*/
    private EditText username;
    private EditText password;
    private Button signIn;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //db = new DbHelper(this);
        session = new Session(this);
        username = (EditText) findViewById(R.id.edTxtUserName);
        password = (EditText) findViewById(R.id.edTxtPassword);
        signIn = (Button) findViewById(R.id.btnSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* switch (v.getId()){
                    case R.id.btnSignIn :
                        Log.i("DEBUG", "Bouton Cliqué");*/

                        //pour voir dans la continuité des frame
                        //startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));
                        Intent intent=new Intent(LoginActivity.this, PlayGroundListMenuActivity.class);
                        startActivity(intent);


                        // checkData();
                   /*     break;
                }*/
            }
        });
/*
        if(session.loggedIn()){
            startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));
            finish();
        }
*/
    }

   /* //Méthode pour le lilstener du boutton sign in
    @Override
    public void  onClick(View v){
        switch (v.getId()){
            case R.id.btnSignIn :
                Log.i("DEBUG", "Bouton Cliqué");

                //pour voir dans la continuité des frame
                //startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));
                Intent intent=new Intent(LoginActivity.this, WorkerListMenuActivity.class);
                startActivity(intent);


                // checkData();
                break;
        }
    }*/

    //méthode pour checker si le login et le password sont justes
    /*
    public void checkData(){
        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();

        if(db.getDatabaseName(username, password)){
            session.setLoggedIn(true);
            startActivity(new Intent(LoginActivity.this, PlayGroundListMenuActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong username/password", Toast.LENGTH_SHORT).show();
        }
    }
*/


}
