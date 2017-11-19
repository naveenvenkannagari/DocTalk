package com.sa.task.di;

import android.content.Context;

import com.sa.task.di.scope.ApplicationScope;
import com.sa.task.ui.githubusersearch.SearchUsersActivity;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by temp on 19/11/17.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {

    Context getContext();

    Retrofit getRetrofit();

    void inject(SearchUsersActivity searchUsersActivity);

}
