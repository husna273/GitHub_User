package com.example.githubuser.data.retrofit;

import com.example.githubuser.data.response.DetailResponse;
import com.example.githubuser.data.response.GitHubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Authorization: token <your token>"}) // Use placeholder
    @GET("search/users")
    Call<GitHubResponse> searchUsers(@Query("q") String query);

    @Headers({"Authorization: token <your token>"}) // Use placeholder
    @GET("users/{username}")
    Call<DetailResponse> getUserDetail(@Path("username")String username);
}
