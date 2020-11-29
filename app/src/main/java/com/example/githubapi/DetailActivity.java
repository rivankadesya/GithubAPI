package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.githubapi.model.User;
import com.example.githubapi.service.GithubApi;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_PARCEL = "extra_parcel";
    private User user;
    private CircleImageView ivAvatar;
    private TextView tvLogin, tvName, tvFollowers, tvFollowing, tvRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null) {
            user = getIntent().getParcelableExtra(EXTRA_PARCEL);

        }
        ivAvatar = findViewById(R.id.iv_avatar);
        tvFollowers = findViewById(R.id.tv_followers);
        tvName = findViewById(R.id.tv_name);
        tvFollowing = findViewById(R.id.tv_following);
        tvRepos = findViewById(R.id.tv_repos);
        tvLogin = findViewById(R.id.tv_login);

        GithubApi githubApi = new GithubApi();
        githubApi.getDetailUser(userListener, user.getLogin());

    }

    ApiListener<User> userListener = new ApiListener<User>() {
        @Override
        public void onSuccess(User userdetail) {

            Picasso.get()
                    .load(userdetail.getAvatar_url())
                    .into(ivAvatar);


            tvLogin.setText(userdetail.getLogin());
            tvFollowers.setText(userdetail.getFollowers());
            tvFollowing.setText(userdetail.getFollowing());
            tvRepos.setText(userdetail.getPublic_repos());
            tvName.setText(userdetail.getName());



        }

        @Override
        public void onFailed(String msg) {

            Log.d("ERROR API", msg);
        }
    };
}