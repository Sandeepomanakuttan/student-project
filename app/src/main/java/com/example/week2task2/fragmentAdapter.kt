package com.example.week2task2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class fragmentAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragmentlist : ArrayList<Fragment> = ArrayList()
    var fragmenttitle : ArrayList<String> = ArrayList()

    override fun getCount(): Int {
     return fragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
      return fragmentlist[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmenttitle[position]
    }

    fun addFragment(frag : Fragment, title : String){
        fragmentlist.add(frag)
        fragmenttitle.add(title)
    }
}