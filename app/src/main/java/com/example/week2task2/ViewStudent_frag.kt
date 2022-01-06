package com.example.week2task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout


class ViewStudent_frag : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_view_student_frag, container, false)

        var tab_viewpager= view.findViewById(R.id.tab_viewpager) as ViewPager
        var tab_tablayout= view.findViewById(R.id.tab_tablayout) as TabLayout

        val fAdaptor = fragmentAdapter(childFragmentManager)
        fAdaptor.addFragment(frag1(),"Total Students")
        fAdaptor.addFragment(ItemFragment(),"EEE Students")
        fAdaptor.addFragment(ItemFragmentCh(),"cH Students")
        fAdaptor.addFragment(ItemFragmentCs(),"cs Students")
        tab_viewpager.adapter=fAdaptor
        tab_tablayout.setupWithViewPager(tab_viewpager)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}


