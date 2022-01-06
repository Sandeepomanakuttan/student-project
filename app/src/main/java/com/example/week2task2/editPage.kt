package com.example.week2task2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import de.hdodenhof.circleimageview.CircleImageView

class editPage : AppCompatActivity() {
    lateinit var etdNam: TextView
    lateinit var etdMail: EditText
    lateinit var edtDep: EditText
    lateinit var edtDob: EditText
    lateinit var proimage: CircleImageView
    var image=100
    lateinit var btn: Button
    lateinit var strName: String
    lateinit var strMail: String
    lateinit var strDep: String
    lateinit var strDob: String

    private  var imageUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_page)
        var i = intent
        etdNam = findViewById(R.id.etdName)
        etdMail = findViewById(R.id.etdMail)
        edtDep = findViewById(R.id.edtDep)
        edtDob = findViewById(R.id.edtDob)
        proimage = findViewById(R.id.proimage)
        btn = findViewById(R.id.btn)

        var a = i.getStringExtra("name").toString();
        etdMail.setText(i.getStringExtra("email"))
        etdNam.setText(a, TextView.BufferType.EDITABLE)

        proimage.setOnClickListener(View.OnClickListener {
            Opengalary()
        })

        btn.setOnClickListener(View.OnClickListener {


            strName = etdNam.text.toString()
            strMail = etdMail.text.toString()
            strDep = edtDep.text.toString()
            strDob = edtDob.text.toString()

            if (imageUri == null) {
                Toast.makeText(this, "select Student image", Toast.LENGTH_SHORT).show()
            }else if (strName == "") {
                Toast.makeText(this, "Enter Student Name", Toast.LENGTH_SHORT).show()
            } else if (strMail == "") {
                Toast.makeText(this, "Enter Student Email", Toast.LENGTH_SHORT).show()
            } else if (strDep == "") {
                Toast.makeText(this, "Enter Depertment", Toast.LENGTH_SHORT).show()
            } else if (strDob == "") {
                Toast.makeText(this, "Enter Dob", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "succefully Submitted", Toast.LENGTH_SHORT).show()


            }

        })


    }

    private fun Opengalary() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == image) {
            imageUri = data?.data
            proimage.setImageURI(imageUri)
        }
    }
}