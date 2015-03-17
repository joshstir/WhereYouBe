package com.jos.sss.whereyoube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

/**
 * Created by joshs on 3/15/2015.
 */
public class MainWhereYouBeActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private ParseLoginBuilder builder;
    private ParseUser currentUser;
    private Menu menu;
    private SearchView searchView;

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
        this.menu = menu;
        MenuItem item = menu.findItem(R.id.action_search);
        if (item != null){
            searchView = (SearchView)item.getActionView();
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        switch (id){
            case R.id.action_settings:
                Toast.makeText(this, "Hey you just hit " +item.getTitle(), Toast.LENGTH_LONG).show();
                break;
            case R.id.action_add:
                Intent addIntent = new Intent(this, AddFriendActivity.class);
                startActivity(addIntent);
                break;
            case R.id.action_search:
                if (searchView != null){
                    searchView.setIconified(false);
                }
                break;
            case R.id.action_logout:
                ParseUser.logOut();
                finish();
                CheckUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
