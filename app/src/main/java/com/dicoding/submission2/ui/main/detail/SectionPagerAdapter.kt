package com.dicoding.submission2.ui.main.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.submission2.R

class SectionPagerAdapter(private val mCtx: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLE = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mCtx.resources.getString(TAB_TITLE[position])
    }

}