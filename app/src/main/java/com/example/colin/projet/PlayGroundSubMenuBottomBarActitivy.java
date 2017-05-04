package com.example.colin.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class PlayGroundSubMenuBottomBarActitivy extends AppCompatActivity {

    private static  final String SELECTED_ITEM = "arg_selected$-item";
    private TextView txtVPlaygound;
    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.btnTaskToDo:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.btnLastUpdate:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.btnInfoZone:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground_sub_menu_bottom_bar_actitivy);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectActivity(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectActivity(selectedItem);
    }



    private void selectActivity(MenuItem item) {

        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.btnTaskToDo:
                Intent intent=new Intent(PlayGroundSubMenuBottomBarActitivy.this, Task.TaskToDoActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLastUpdate:
                Intent intent1=new Intent(PlayGroundSubMenuBottomBarActitivy.this, Task.TaskLastUpdate.class);
                startActivity(intent1);
                break;
            case R.id.btnInfoZone:
                Intent intent2=new Intent(PlayGroundSubMenuBottomBarActitivy.this, Task.TaskInfoZoneActivity.class);
                startActivity(intent2);
                break;
        }
    }

}
