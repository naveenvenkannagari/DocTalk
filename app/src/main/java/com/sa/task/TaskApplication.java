package com.sa.task;

import android.app.Application;

import com.sa.task.di.AppComponent;
import com.sa.task.di.ApplicationModule;
import com.sa.task.di.DaggerAppComponent;
import com.sa.task.di.DataModule;
import com.sa.task.di.NetworkModule;

/**
 * Created by temp on 19/11/17.
 */

public class TaskApplication extends Application {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(Constants.GITHUB_API_SEARCH_URL))
                .dataModule(new DataModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


}
