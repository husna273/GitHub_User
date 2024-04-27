package com.example.githubuser.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.githubuser.R;
import com.example.githubuser.data.response.GitHubResponse;
import com.example.githubuser.data.response.ItemsItem;
import com.example.githubuser.data.retrofit.ApiConfig;
import com.example.githubuser.data.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GitHub_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview);

        ApiService apiService = ApiConfig.getApiService();
        Call<GitHubResponse> call = apiService.searchUsers("husna");

        call.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ItemsItem> itemsItems = response.body().getItems();
                    adapter = new GitHub_Adapter(itemsItems);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}