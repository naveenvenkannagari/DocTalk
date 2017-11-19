package com.sa.task.ui.githubusersearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.sa.task.R;
import com.sa.task.TaskApplication;
import com.sa.task.data.models.Items;
import com.sa.task.ui.customcomponents.RxSearch;
import com.sa.task.ui.githubusersearch.mvp.ISearchUsersView;
import com.sa.task.ui.githubusersearch.mvp.SearchUsersPresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by temp on 19/11/17.
 */

public class SearchUsersActivity extends AppCompatActivity implements ISearchUsersView {

    private static final long DEBOUNCE_DELAY_TIME = 100;
    private Unbinder mUnbinder;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @BindView(R.id.users_list)
    RecyclerView mUsersList;

    @Inject
    SearchUsersPresenter mSearchUsersPresenter;

    private SearchUsersAdapter mSearchUsersAdapter;
    private CompositeSubscription mCompositeSubscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users_view);
        mUnbinder = ButterKnife.bind(this);
        mCompositeSubscription = new CompositeSubscription();
        initDependencyInjection();
        initUI();


    }

    private void initUI() {
        mSearchView.setIconified(false);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                return false;
            }
        });
        mSearchUsersAdapter = new SearchUsersAdapter(this);
        mSearchUsersPresenter.setView(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mUsersList.setLayoutManager(manager);
        mUsersList.setAdapter(mSearchUsersAdapter);


        Subscription subscription = RxSearch.fromSearchView(mSearchView)
                .debounce(DEBOUNCE_DELAY_TIME, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() > 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    getUsersBasedonSearchQuery(query);
                });
        mCompositeSubscription.add(subscription);
    }

    private void getUsersBasedonSearchQuery(String query) {
        mSearchUsersPresenter.getUsersBasedonSearchQuery(query);
    }

    private void initDependencyInjection() {
        ((TaskApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mSearchUsersPresenter.onStop();
        mCompositeSubscription.clear();
    }


    @Override
    public void showUsersList(List<Items> items) {
        mSearchUsersAdapter.UpdateDataSet(items);

    }

    @Override
    public void showMessage() {
        Toast.makeText(this, "No Items Found", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
