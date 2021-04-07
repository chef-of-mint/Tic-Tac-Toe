package com.example.loginregisterfirebase.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginregisterfirebase.BlogDataClass
import com.example.loginregisterfirebase.BlogsAdapter
import com.example.loginregisterfirebase.R
import com.example.loginregisterfirebase.UsersModel
import com.example.loginregisterfirebase.ui.PostBlogActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.layout_blog.view.*


class BlogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var blogdatabase = FirebaseDatabase.getInstance()
    var blogRef = blogdatabase.getReference("blog")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val BlogsList = ArrayList<BlogDataClass>()


        blogRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for (blogSnapshot in dataSnapshot.children) {

                    val blogCard = blogSnapshot.getValue(BlogDataClass::class.java)

                    blogCard?.id = blogSnapshot.key
                    blogCard?.let { BlogsList.add(it) }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(activity, "Not able to fetch data", Toast.LENGTH_SHORT).show()
            }
        })


        recyclerViewBlogs.layoutManager = LinearLayoutManager(activity)
        recyclerViewBlogs.adapter = BlogsAdapter(BlogsList)

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