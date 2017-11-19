package com.sa.task.domain;

import com.sa.task.data.models.GitHubUsersResponse;
import com.sa.task.data.source.common.DataSourceRouter;

import rx.Observable;

/**
 * Created by temp on 19/11/17.
 */

public class UsersSearchUsecaseController {

    private DataSourceRouter dataSourceRouter;

    public UsersSearchUsecaseController(DataSourceRouter dataSourceRouter) {
        this.dataSourceRouter = dataSourceRouter;
    }


    public Observable<GitHubUsersResponse> getUsersBasedOnSearch(String searchText) {
        return dataSourceRouter.getUsersListBasedOnUsername(searchText);
    }
}
