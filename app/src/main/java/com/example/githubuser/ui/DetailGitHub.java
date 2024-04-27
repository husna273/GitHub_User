package com.example.githubuser.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubuser.R;
import com.example.githubuser.data.response.DetailResponse;
import com.example.githubuser.data.retrofit.ApiConfig;
import com.example.githubuser.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailGitHub extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView Name, Username, Bio;
    private ImageView igAvatar;
    String name, User_name, bio, Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_id);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("User_name");
            ApiService apiService = ApiConfig.getApiService();
            Call<DetailResponse> userCall = apiService.getUserDetail(username);

            Name = findViewById(R.id.Name);
            Username = findViewById(R.id.Username);
            Bio = findViewById(R.id.Bio);
            igAvatar = findViewById(R.id.igAvatar);

            showLoading(true);
            userCall.enqueue(new Callback<DetailResponse>() {
                @Override
                public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        DetailResponse user = response.body();
                        if (user != null){
                            name = "Name:" + user.getName();
                            User_name = " " + user.getLogin();
                            bio = "Bio:" + user.getBio();
                            Image = user.getAvatarUrl();

                            Name.setText(name);
                            Username.setText(User_name);
                            Bio.setText(bio);
                            Picasso.get().load(Image).into(igAvatar);
                        }else {
                            Toast.makeText(DetailGitHub.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DetailResponse> call, Throwable t) {
                    Toast.makeText(DetailGitHub.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}