package com.example.week2task2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.week2task2.R.id.fragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.stream.DoubleStream.builder

class homePage : AppCompatActivity() {
    lateinit var bottomNavView:BottomNavigationView
    lateinit var etdsearch:EditText


    override fun onBackPressed() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Are you Sure?")
        alert.setMessage("Do you want to exits?")

        alert.setPositiveButton("yes") { dialoge: DialogInterface, which ->
            finish()
        }

        alert.setNegativeButton("No",{dialog:DialogInterface,which-> })
        alert.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_page)

        bottomNavView=findViewById(R.id.bottomNavView)
       val nav = findNavController(fragmentContainerView)

        bottomNavView.setupWithNavController(nav)


  
    }


}


