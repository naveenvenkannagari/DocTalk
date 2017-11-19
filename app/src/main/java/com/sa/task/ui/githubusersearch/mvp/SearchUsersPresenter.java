package com.sa.task.ui.githubusersearch.mvp;

import android.support.annotation.NonNull;

import com.sa.task.data.models.GitHubUsersResponse;
import com.sa.task.data.source.common.DataSourceRouter;
import com.sa.task.domain.UsersSearchUsecaseController;
import com.sa.task.ui.base.BasePresenter;
import com.sa.task.ui.githubusersearch.SearchUsersActivity;
import com.sa.task.utils.RxUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by temp on 19/11/17.
 */

public class SearchUsersPresenter implements BasePresenter {

    @NonNull
    private CompositeSubscription mSubscriptions;
    private UsersSearchUsecaseController mUsersSearchUsecaseController;
    private ISearchUsersView mSearchUsersView;

    @Inject
    public SearchUsersPresenter(DataSourceRouter dataSourceRouter) {
        mUsersSearchUsecaseController = new UsersSearchUsecaseController(dataSourceRouter);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mSubscriptions.clear();
    }

    public void getUsersBasedonSearchQuery(String searchText) {
        Observable<GitHubUsersResponse> response = mUsersSearchUsecaseController.getUsersBasedOnSearch(searchText);
        RxUtils.addToCompositeSubscription(mSubscriptions, response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Subscriber<GitHubUsersResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(GitHubUsersResponse imageSearchResponse) {
                if (imageSearchResponse != null && imageSearchResponse.getItems().size() > 0) {
                    mSearchUsersView.showUsersList(imageSearchResponse.getItems());
                } else {
                    mSearchUsersView.showMessage();
                }
            }
        }));
    }


    public void setView(ISearchUsersView iSearchUsersView) {
        this.mSearchUsersView = iSearchUsersView;
    }
}
