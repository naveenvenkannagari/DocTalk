package com.sa.task.ui.githubusersearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sa.task.R;
import com.sa.task.data.models.Items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by temp on 19/11/17.
 */

public class SearchUsersAdapter extends RecyclerView.Adapter<SearchUsersAdapter.CustomViewHolder> {

    private List<Items> mGitHubUsersList;
    private Context mContext;


    public SearchUsersAdapter(Context context) {
        mGitHubUsersList = new ArrayList<>();
        this.mContext = context;
    }

    public void UpdateDataSet(List<Items> gitHubUsersList) {
        mGitHubUsersList.clear();
        this.mGitHubUsersList.addAll(gitHubUsersList);
        notifyDataSetChanged();
    }

    @Override
    public SearchUsersAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.users_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchUsersAdapter.CustomViewHolder holder, int position) {
        Items userItem = mGitHubUsersList.get(position);
        Picasso.with(mContext).load(userItem.getAvatar_url()).into(holder.usersImage);

        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append("UserName :-");
        nameBuilder.append(userItem.getLogin());
        holder.usersName.setText(nameBuilder.toString());


        StringBuilder idBuilder = new StringBuilder();
        idBuilder.append("UserId :-");
        idBuilder.append(userItem.getId());
        holder.usersId.setText(idBuilder.toString());


        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append("Score :-");
        scoreBuilder.append(userItem.getLogin());
        holder.usersScore.setText(scoreBuilder.toString());


    }

    @Override
    public int getItemCount() {
        return mGitHubUsersList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.users_image)
        ImageView usersImage;

        @BindView(R.id.name)
        TextView usersName;


        @BindView(R.id.id)
        TextView usersId;


        @BindView(R.id.score)
        TextView usersScore;


        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
