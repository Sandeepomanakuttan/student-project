package com.example.week2task2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import de.hdodenhof.circleimageview.CircleImageView

class user : AppCompatActivity() {

    lateinit var nametxt: TextView
    lateinit var emailtxt: TextView
    lateinit var regtxt: TextView
    lateinit var dobtxt: TextView
    lateinit var departmenttxt: TextView
    lateinit var proimage: CircleImageView
    lateinit var editImage: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        nametxt=findViewById(R.id.nametxt)
        emailtxt=findViewById(R.id.emailtxt)
        regtxt=findViewById(R.id.regtxt)
        dobtxt=findViewById(R.id.dobtxt)
        departmenttxt=findViewById(R.id.depart)
        proimage=findViewById(R.id.proimage)
        editImage=findViewById(R.id.editImage)

        var i=intent
        regtxt.text= i.getStringExtra("reg").toString()
        nametxt.text =i.getStringExtra("name").toString()

        var a=i.getStringExtra("name").toString()
        var b=i.getStringExtra("reg").toString()


       // proimage.setImageDrawable(getDrawable(R.drawable.ic_add))

        editImage.setOnClickListener(View.OnClickListener {

            var i=Intent(this,editPage::class.java)

            i.putExtra("reg",b)
            i.putExtra("name",a)
            startActivity(i)
        })
    }

}