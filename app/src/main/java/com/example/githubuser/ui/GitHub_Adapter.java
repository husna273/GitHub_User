package com.example.githubuser.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuser.R;
import com.example.githubuser.data.response.ItemsItem;
import com.squareup.picasso.Picasso;


import java.util.List;

public class GitHub_Adapter extends RecyclerView.Adapter<GitHub_Adapter.ViewHolder> {

    private final List<ItemsItem> users;


    public GitHub_Adapter(List<ItemsItem> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemsItem user = users.get(position);
        holder.User_name.setText(user.getLogin());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatar);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), DetailGitHub.class);
            intent.putExtra("User_name", user.getLogin());
            intent.putExtra("avatarURL", user.getAvatarUrl());
            click.getContext().startActivity(intent);
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar; TextView User_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.Avatar);
            User_name = itemView.findViewById(R.id.Username);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
