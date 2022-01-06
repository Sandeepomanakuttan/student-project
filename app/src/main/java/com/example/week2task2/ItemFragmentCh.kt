package com.example.week2task2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week2task2.data.studentData

/**
 * A fragment representing a list of Items.
 */
class ItemFragmentCh : Fragment(),RecyclerviewAdaptor.ClickLisner{

    lateinit var arrayList: ArrayList<studentData>
    lateinit var adaptor:RecyclerviewAdaptor
    lateinit var student: studentData
    lateinit var list:RecyclerView
    var count=0
    val listdata:ArrayList<studentData> =ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_ch_list, container, false)
        val name= arrayOf("sandeep","arshid")

        val image= arrayOf(android.R.drawable.ic_menu_add, android.R.drawable.ic_delete)

        val reg= arrayOf(1,2)

        if(count==0) {

            for (i in name.indices) {
               // listdata.add(studentData(reg[i], name[i], image[i]))
                count++

            }
        }

        list=view.findViewById(R.id.list)
        list.layoutManager= LinearLayoutManager(activity)
        adaptor= RecyclerviewAdaptor(listdata,this)
        list.adapter=adaptor

        return view
    }

    override fun onitemclick(data: studentData) {

        var i= Intent(requireContext(),user::class.java)
        i.putExtra("reg",data.Regno.toString())
        i.putExtra("name",data.Name)
       // i.putExtra("image",data.imageId)
        startActivity(i)

    }
}