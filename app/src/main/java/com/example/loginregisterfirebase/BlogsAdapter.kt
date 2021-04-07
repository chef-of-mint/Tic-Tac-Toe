package com.example.loginregisterfirebase


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loginregisterfirebase.ui.fragments.ViewBlog
import kotlinx.android.synthetic.main.layout_blog.view.*
import kotlinx.android.synthetic.main.layout_blog.*

class BlogsAdapter(val blog: List<BlogDataClass>) : RecyclerView.Adapter<BlogsAdapter.BlogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_blog, parent, false)

        )


    }

    override fun getItemCount() = blog.size

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogs = blog[position]
        holder.view.blog_content.text = blogs.content
        holder.view.blog_author.text = blogs.author
        holder.view.blog_title.text = blogs.title

//below code have some bugs  :(
        holder.view.blog_card.setOnClickListener{
            val appCompatActivity = it.context as AppCompatActivity
            appCompatActivity.supportFragmentManager.beginTransaction().replace(
                R.id.contentMainId,
                ViewBlog()
            ).addToBackStack(null).commit()
        }

    }


    class BlogViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}