package com.jos.sss.whereyoube;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by joshs on 3/15/2015.
 */
public class WhereYouBeApplication extends Application {
    @Override
    public void onCreate(){
        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
    }
}
