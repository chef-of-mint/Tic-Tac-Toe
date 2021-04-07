package com.example.loginregisterfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_blog.view.*

import kotlinx.android.synthetic.main.layout_user.view.*

class UsersAdapter(val user: List<UsersModel>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_user, parent, false)

        )
    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = user[position]
        holder.view.user_name.text = users.name
        holder.view.user_bio.text = users.email
        holder.view.user_rating.text=users.rating

    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}