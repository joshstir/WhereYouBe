package com.jos.sss.whereyoube;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.util.List;

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

        SearchManager manager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        if (item != null){
            searchView = (SearchView)item.getActionView();
            searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    loadData(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }

    private void loadData(String querytext) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("name", querytext);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, com.parse.ParseException e) {
                if (e == null){
                    //query ok
                    Log.d("QUERY",parseUsers.get(0).getEmail());
                }
                else{
                    //something was wrong
                    Log.d("QUERY", e.getMessage());
                }
            }
        });
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
