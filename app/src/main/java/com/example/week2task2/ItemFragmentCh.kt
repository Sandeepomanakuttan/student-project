package com.example.week2task2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.week2task2.data.StudentDataCollection
import com.google.firebase.database.*

/**
 * A fragment representing a list of Items.
 */
class ItemFragmentCh : Fragment(),RecyclerviewAdaptor.ClickLisner{

    lateinit var dbref:DatabaseReference
    lateinit var arrayList: ArrayList<dataa>
    lateinit var adaptor:RecyclerviewAdaptor
    lateinit var list:RecyclerView
    var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_ch_list, container, false)
        val name= arrayOf("sandeep","arshid")

        list=view.findViewById(R.id.list)
        list.layoutManager= LinearLayoutManager(activity)
        list.setHasFixedSize(true)

        arrayList= arrayListOf<dataa>()
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
        touchHelper.attachToRecyclerView(list)

        return view
    }

    private fun getData() {

        dbref = FirebaseDatabase.getInstance().getReference("Student Details")
        var query=dbref.orderByChild("department").equalTo("ch")
        query.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (student in snapshot.children){
                        var stddata =student.getValue(dataa::class.java)
                        arrayList.add(stddata!!)
                    }
                    list.adapter=RecyclerviewAdaptor(arrayList,this@ItemFragmentCh)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onitemclick(data: dataa) {

        var i=Intent(requireContext(),user::class.java)
        i.putExtra("regNo",data.regNo)
        startActivity(i)

    }
}