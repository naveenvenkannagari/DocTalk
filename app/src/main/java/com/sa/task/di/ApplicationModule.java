package com.sa.task.di;

import android.app.Application;
import android.content.Context;

import com.sa.task.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by temp on 19/11/17.
 */

@Module
public class ApplicationModule {

    Application mApplication;
    Context mContext;

    public ApplicationModule(Application application) {
        mApplication = application;
        mContext = application.getApplicationContext();
    }


    @Provides
    @ApplicationScope
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    Context providesContext() {
        return mContext;
    }

}
