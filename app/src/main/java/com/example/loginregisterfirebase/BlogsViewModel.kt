package com.example.loginregisterfirebase

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class BlogsViewModel :ViewModel() {
    private val dbBlogs = FirebaseDatabase.getInstance().getReference("blogs")
    
}