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
import androidx.room.Database
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import com.example.week2task2.data.StudentDataCollection
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class user : AppCompatActivity() {

    lateinit var nametxt: TextView
    lateinit var emailtxt: TextView
    lateinit var regtxt: TextView
    lateinit var dobtxt: TextView
    lateinit var departmenttxt: TextView
    lateinit var proimage: CircleImageView
    lateinit var editImage: ImageButton
    lateinit var regNo:String
    val dbref = FirebaseDatabase.getInstance().getReference("StudentDetails")


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
        regtxt.text= i.getStringExtra("regNo").toString()

        regNo=i.getStringExtra("regNo").toString()

        getData()

        editImage.setOnClickListener {
            val i =Intent(this,editPage::class.java)
            i.putExtra("regNo",regNo)
            startActivity(i);
        }



       // proimage.setImageDrawable(getDrawable(R.drawable.ic_add))


    }

    private fun getData() {

        dbref.child(regNo).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                        val data = snapshot.getValue(studentdata::class.java)

                val storage = FirebaseStorage.getInstance().reference.child("images/$regNo.jpg")
                val localfile= File.createTempFile("tempImage","jpg")

                storage.getFile(localfile).addOnSuccessListener {
                    Picasso.get().load(localfile.absoluteFile).into(proimage)
                }
                        nametxt.text=data?.name
                        emailtxt.text=data?.email
                        dobtxt.text=data?.dob
                        regtxt.text=data?.regNo
                        departmenttxt.text=data?.department




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}

