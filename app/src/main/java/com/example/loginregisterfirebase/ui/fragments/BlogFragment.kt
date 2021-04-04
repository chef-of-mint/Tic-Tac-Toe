package com.example.loginregisterfirebase.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginregisterfirebase.BlogDataClass
import com.example.loginregisterfirebase.BlogsAdapter
import com.example.loginregisterfirebase.R
import com.example.loginregisterfirebase.ui.PostBlogActivity
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.layout_blog.view.*


class BlogFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val blogs=listOf(
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Moshi moshi","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah"),
                BlogDataClass(1,"Hola","The quick brown fox jump over the lazy dog","Abdullah")
        )


        recyclerViewBlogs.layoutManager = LinearLayoutManager(activity)
        recyclerViewBlogs.adapter = BlogsAdapter(blogs)

        fab_post_blog.setOnClickListener(){
                //contentMainId.removeAllViews()
                val appCompatActivity = it.context as AppCompatActivity
                appCompatActivity.supportFragmentManager.beginTransaction().replace(
                    R.id.contentMainId,
                    PostBlogFragment()
                ).addToBackStack(null).commit()

        }

        super.onActivityCreated(savedInstanceState)
    }

}