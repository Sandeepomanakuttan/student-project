package com.example.week2task2

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.week2task2.data.studentData


class studentAdaptor(private val context: Activity,
                     private val arraylist: ArrayList<studentData>) : ArrayAdapter<studentData>(context,R.layout.list_item,arraylist ) {


        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val inflater:LayoutInflater= LayoutInflater.from(context)
            val view=inflater.inflate(R.layout.list_item,null)

            val image:ImageView=view.findViewById(R.id.proimage)
            val name:TextView=view.findViewById(R.id.txtName)
            val btnedt:ImageButton=view.findViewById(R.id.editButton)
            val btndelete:ImageButton=view.findViewById(R.id.deleteButton)

           // image.setImageResource(arraylist[position].imageId)
            name.text = arraylist[position].Name

            return view
        }

    }

