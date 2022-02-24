package com.example.week2task2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class frag1 : Fragment(),RecyclerviewAdaptor.ClickLisner {

    private lateinit var dbref:DatabaseReference
    lateinit var adaptor:RecyclerviewAdaptor
    lateinit var recyclerView: RecyclerView
    lateinit var arraylist:ArrayList<dataa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_frag1, container, false)

        recyclerView=view.findViewById(R.id.listview)
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        arraylist= arrayListOf<dataa>()
        getData()
        val swipetodelete= object :SwipeToDelete(requireContext()){

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){

                    ItemTouchHelper.LEFT->{

                        adaptor.deleteItem(viewHolder.adapterPosition)

                    }

                    ItemTouchHelper.RIGHT->{

                        adaptor.deleteItem(viewHolder.adapterPosition)


                    }
                }

            }
        }

        val touchHelper = ItemTouchHelper(swipetodelete)
        touchHelper.attachToRecyclerView(recyclerView)
        return view
    }

    private fun getData() {
        dbref = FirebaseDatabase.getInstance().getReference("StudentDetails")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (studentSnap in snapshot.children){
                        val student =studentSnap.getValue(dataa::class.java)
                        arraylist.add(student!!)
                    }
                    adaptor=RecyclerviewAdaptor(arraylist,this@frag1)
                    recyclerView.adapter=adaptor

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    override fun onitemclick(data: dataa) {
        var c=data.regNo.toString()

        var i= Intent(requireContext(),user::class.java)
        i.putExtra("regNo",data.regNo)
        startActivity(i)    }


}