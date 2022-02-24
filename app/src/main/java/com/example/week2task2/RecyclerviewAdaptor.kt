package com.example.week2task2

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class RecyclerviewAdaptor(val listData: ArrayList<dataa>, val clickLisner:ClickLisner) :
    RecyclerView.Adapter<RecyclerviewAdaptor.myViewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewholder{
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return myViewholder(view)
    }

    override fun onBindViewHolder(holder: myViewholder, position: Int) {
        val currentitem =listData[position]
        val id = currentitem.regNo;
        val storage = FirebaseStorage.getInstance().reference.child("images/$id.jpg")
        val localfile= File.createTempFile("tempImage","jpg")

        storage.getFile(localfile).addOnSuccessListener {
//            val bitmap =BitmapFactory.decodeFile(localfile.absolutePath)
//            holder.img.setImageBitmap(bitmap)
            Picasso.get().load(localfile.absoluteFile).into(holder.img)

        }
        holder.txt.text=currentitem.name


        holder.itemView.setOnClickListener(View.OnClickListener {
            clickLisner.onitemclick(listData.get(position))

        })
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    fun deleteItem(i: Int){

        var s= listData[i]
        var s1=s.regNo
        var dbref = FirebaseDatabase.getInstance().getReference("StudentDetails").child(s1.toString())

        dbref.removeValue()

        listData.removeAt(i)
        notifyDataSetChanged()
    }

    class myViewholder(view: View):RecyclerView.ViewHolder(view){
        var txt:TextView =view.findViewById(R.id.txtName)
        val img: CircleImageView =view.findViewById(R.id.proimage)

    }

    interface ClickLisner{
        fun onitemclick(data: dataa)
    }
}