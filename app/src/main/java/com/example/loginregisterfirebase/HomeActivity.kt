package com.example.loginregisterfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

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

    }
}