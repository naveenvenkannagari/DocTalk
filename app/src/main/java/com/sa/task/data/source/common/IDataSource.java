package com.sa.task.data.source.common;


import com.sa.task.data.models.GitHubUsersResponse;

import rx.Observable;

/**
 * Created by temp on 19/11/17.
 */

public interface IDataSource {

     Observable<GitHubUsersResponse> getUsersListBasedOnUsername(String name);
}
