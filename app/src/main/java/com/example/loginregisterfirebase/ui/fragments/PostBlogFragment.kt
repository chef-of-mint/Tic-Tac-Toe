package com.example.loginregisterfirebase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.loginregisterfirebase.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_post_blog.*


class PostBlogFragment : Fragment() {

    val mydatabase = FirebaseDatabase.getInstance()
    val Ref = mydatabase.getReference("blog").push()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        post_button.setOnClickListener{
            postprogress.visibility=View.VISIBLE
            val title1 = title_text.text.toString().trim()
            val name1= name_text.text.toString().trim()
            val content1= content_text.text.toString().trim()

            Ref.child("title").setValue(title1)
            Ref.child("name").setValue(name1)
            Ref.child("content").setValue(content1)
                    .addOnCompleteListener{task->
                        postprogress.visibility=View.INVISIBLE
                        if(task.isSuccessful){
                            Toast.makeText(activity, "Blog posted successfully", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(activity, "ERROR posting blog, please retry", Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        super.onViewCreated(view, savedInstanceState)
    }


}