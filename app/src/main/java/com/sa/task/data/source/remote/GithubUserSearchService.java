package com.sa.task.data.source.remote;

import com.sa.task.data.models.GitHubUsersResponse;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by temp on 19/11/17.
 */

public interface GithubUserSearchService {

    @GET("users")
    Observable<GitHubUsersResponse> getGithubUsersList(@Query("q") String searchName);

}
