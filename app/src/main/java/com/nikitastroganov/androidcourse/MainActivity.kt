package com.nikitastroganov.androidcourse

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView: RecyclerView = findViewById(R.id.usersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        adapter.userList = loadUsers()
        adapter.notifyDataSetChanged()
    }

    private fun loadUsers(): List<User> {
        return (1..1_000_000).toList().map { index ->
            User(
                avatarUri = Uri.EMPTY,
                userName = "user #$index",
                groupName = "group"
            )
        }
    }
}