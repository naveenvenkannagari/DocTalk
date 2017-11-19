package com.sa.task.ui.githubusersearch.mvp;

import com.sa.task.data.models.Items;

import java.util.List;

/**
 * Created by temp on 19/11/17.
 */

public interface ISearchUsersView {


    void showUsersList(List<Items> items);

    void showMessage();
}
