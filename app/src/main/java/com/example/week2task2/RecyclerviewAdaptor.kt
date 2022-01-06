package com.example.week2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week2task2.data.studentData

class RecyclerviewAdaptor(val listData:List<studentData>, val clickLisner:ClickLisner) :RecyclerView.Adapter<RecyclerviewAdaptor.myViewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewAdaptor.myViewholder{
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return myViewholder(view)
    }

    override fun onBindViewHolder(holder: myViewholder, position: Int) {
       holder.txt.text=listData.get(position).Name
        holder.itemView.setOnClickListener(View.OnClickListener {
            clickLisner.onitemclick(listData.get(position))
        })
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class myViewholder(view: View):RecyclerView.ViewHolder(view){
        var txt:TextView

        init {
            txt=view.findViewById(R.id.txtName)
        }
    }

    interface ClickLisner{
        fun onitemclick(data: studentData)
    }
}