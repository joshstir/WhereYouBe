package com.jos.sss.whereyoube;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by joshs on 3/15/2015.
 */
public class MyDispatchActivity extends ParseLoginDispatchActivity {

/*    protected Class<?> getTargetClass() {
        return MainActivity.class;
    }*/
    @Override
    protected Class<?> getTargetClass() {
        return MainWhereYouBeActivity.class;
    }

}
