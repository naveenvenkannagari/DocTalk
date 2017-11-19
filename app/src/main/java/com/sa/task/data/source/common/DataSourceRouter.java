package com.sa.task.data.source.common;

import android.support.annotation.NonNull;

import com.sa.task.data.models.GitHubUsersResponse;
import com.sa.task.data.source.remote.GithubUserSearchRemoteDataSource;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by temp on 19/11/17.
 */

public class DataSourceRouter implements IDataSource {

    @NonNull
    private IDataSource mGitHubSearchUserRemoteDataSource;


    @Inject
    public DataSourceRouter(GithubUserSearchRemoteDataSource githubUserSearchRemoteDataSource) {
        mGitHubSearchUserRemoteDataSource = githubUserSearchRemoteDataSource;
    }


    @Override
    public Observable<GitHubUsersResponse> getUsersListBasedOnUsername(String name) {
        return mGitHubSearchUserRemoteDataSource.getUsersListBasedOnUsername(name);
    }
}
