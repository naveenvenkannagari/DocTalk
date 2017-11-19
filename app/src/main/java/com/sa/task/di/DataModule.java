package com.sa.task.di;

import android.app.Application;

import com.sa.task.data.source.common.DataSourceRouter;
import com.sa.task.data.source.remote.GithubUserSearchRemoteDataSource;
import com.sa.task.data.source.remote.GithubUserSearchService;
import com.sa.task.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by temp on 19/11/17.
 */

@Module
public class DataModule {


    @Provides
    @ApplicationScope
    public GithubUserSearchService provideGithubUserService(Retrofit retrofit) {
        return retrofit.create(GithubUserSearchService.class);
    }


    @Provides
    @ApplicationScope
    public GithubUserSearchRemoteDataSource provideGithubUserSearchRemoteDataSource(GithubUserSearchService githubUserSearchService) {
        return new GithubUserSearchRemoteDataSource(githubUserSearchService);
    }

    @Provides
    @ApplicationScope
    public DataSourceRouter provideDataService(GithubUserSearchRemoteDataSource githubUserSearchRemoteDataSource) {
        return new DataSourceRouter(githubUserSearchRemoteDataSource);
    }

}
