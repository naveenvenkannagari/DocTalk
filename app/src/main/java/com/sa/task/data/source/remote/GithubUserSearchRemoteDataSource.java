package com.sa.task.data.source.remote;

import com.sa.task.data.models.GitHubUsersResponse;
import com.sa.task.data.source.common.IDataSource;

import javax.inject.Inject;
import javax.sql.DataSource;

import rx.Observable;

/**
 * Created by temp on 19/11/17.
 */

public class GithubUserSearchRemoteDataSource implements IDataSource {

    private GithubUserSearchService mGithubUserSearchService;

    @Inject
    public GithubUserSearchRemoteDataSource(GithubUserSearchService githubUserSearchService) {
        this.mGithubUserSearchService = githubUserSearchService;
    }

    @Override
    public Observable<GitHubUsersResponse> getUsersListBasedOnUsername(String name) {
        return mGithubUserSearchService.getGithubUsersList(name);
    }
}
