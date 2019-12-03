package com.noorganization.googlecertificationkotlin.codelab_user_navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager, numTabs: Int) : FragmentStatePagerAdapter(fm) {
    var mNumOfTabs: Int = 0

    init {
        this.mNumOfTabs = numTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TabFragment1()
            1 -> TabFragment2()
            2 -> TabFragment3()
            else -> TabFragment3()
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}