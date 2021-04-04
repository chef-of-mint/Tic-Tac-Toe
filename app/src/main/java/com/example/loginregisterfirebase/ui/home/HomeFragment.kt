package com.example.loginregisterfirebase.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginregisterfirebase.*
import com.example.loginregisterfirebase.ui.fragments.BlogFragment
import com.example.loginregisterfirebase.ui.fragments.RequestsFragment
import com.example.loginregisterfirebase.ui.fragments.UsersFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TabPagerAdapter(childFragmentManager)
        adapter.addFragment(RequestsFragment(), "Requests")
        adapter.addFragment(UsersFragment(), "Users")
        adapter.addFragment(BlogFragment(), "Blogs")

        this.viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_emoji_people_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_search_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_article_24)

        //tabs.setSelectedTabIndicatorColor(Color.parseColor("#000000"))

    }
}