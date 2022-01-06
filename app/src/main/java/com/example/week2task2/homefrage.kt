package com.example.week2task2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


lateinit var logout: ImageView
lateinit var linear: LinearLayout
var c:Int=0


class homefrage : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view= inflater.inflate(R.layout.fragment_homefrage, container, false)
        logout=view.findViewById(R.id.logout)
        linear=view.findViewById(R.id.linear)

        linear.setOnClickListener(View.OnClickListener {

            c=0

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Are you Sure?")
            alert.setMessage("Do you want to Logout?")

            alert.setPositiveButton("yes") { dialoge: DialogInterface, which ->

                val  preferences = this.requireActivity().getSharedPreferences("myPreff", Context.MODE_PRIVATE);
                var edit = preferences.edit()
                edit.putBoolean("loggedIn", false)
                edit.commit()
                var i=Intent(activity,Login::class.java)
                startActivity(i)
                activity?.finish()
            }


            alert.setNegativeButton("No",{ dialog: DialogInterface, which-> })
            alert.show()



        })
        return view
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}

//open class e :AppCompatActivity(){
//
//    open fun h() {
//        var intent = Intent(this, Login::class.java)
//        startActivity(intent)
//        finish()
//    }
//}