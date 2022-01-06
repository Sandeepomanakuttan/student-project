package com.example.week2task2

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {
        finish()
    }
    val delay = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed(Runnable {

           var sp = getSharedPreferences(Login().preffName,0);
            var checker=sp.getBoolean("loggedIn",false)

            if(checker){
                var intent=Intent(applicationContext,homePage::class.java)
                startActivity(intent)
                finish()
            }else{
                var intent=Intent(this,Login::class.java)
                startActivity(intent)
                finish()
            }

        }, delay.toLong())




    }

}



