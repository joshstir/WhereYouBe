package com.jos.sss.whereyoube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

/**
 * Created by joshs on 3/15/2015.
 */
public class MainWhereYouBeActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ParseLoginBuilder builder;
    private ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wherebe_main);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        builder = new ParseLoginBuilder(MainWhereYouBeActivity.this);
        CheckUser();
    }

    private void CheckUser() {
        //check if the user is logged in
        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null){

        }
        else{
            Intent intent = builder.build();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == R.id.action_settings){
            Toast.makeText(this, "Hey you just hit " +item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_logout){
            ParseUser.logOut();
            finish();
            CheckUser();
        }

        return super.onOptionsItemSelected(item);
    }
}
