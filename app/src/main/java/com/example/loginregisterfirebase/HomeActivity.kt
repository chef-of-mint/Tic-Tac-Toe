package com.example.loginregisterfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.loginregisterfirebase.ui.fragments.BlogFragment
import com.example.loginregisterfirebase.ui.fragments.RequestsFragment
import com.example.loginregisterfirebase.ui.fragments.UsersFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val adapter = TabPagerAdapter(supportFragmentManager)
        adapter.addFragment(RequestsFragment(), "Requests")
        adapter.addFragment(UsersFragment(), "Users")
        adapter.addFragment(BlogFragment(), "Blogs")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_emoji_people_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_search_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_article_24)

        logout.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("Are you sure?")
                setPositiveButton("Yes") { _, _ ->

                    FirebaseAuth.getInstance().signOut()
                    logout()

                }
                setNegativeButton("Cancel") { _, _ ->
                }
            }.create().show()
        }

    }

}