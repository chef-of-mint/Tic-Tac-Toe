package com.example.loginregisterfirebase

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class TabPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

    private val mFragmentList =ArrayList<Fragment>()
    private val mFragmentTitleList =ArrayList<String>()

    override fun getCount(): Int {

        return mFragmentTitleList.size
    }

    override fun getItem(position: Int): Fragment {

        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position].toString()
    }

    fun addFragment(fragment: Fragment,title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}
