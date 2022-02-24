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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class editPage : AppCompatActivity() {
    lateinit var etdNam: EditText
    lateinit var etdMail: EditText
    lateinit var edtDep: EditText
    lateinit var edtDob: EditText
    lateinit var nametxt:TextView
    lateinit var proimage: CircleImageView
    var image=100
    lateinit var btn: Button
    lateinit var strName: String
    lateinit var strMail: String
    lateinit var strDep: String
    lateinit var strDob: String
    lateinit var regNo:String
    val dbref = FirebaseDatabase.getInstance().getReference("StudentDetails")

    private  var imageUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_page)
        var i = intent
        etdNam = findViewById(R.id.etdName)
        etdMail = findViewById(R.id.etdMail)
        edtDep = findViewById(R.id.edtDep)
        edtDob = findViewById(R.id.edtDob)
        nametxt = findViewById(R.id.nametxt)
        proimage = findViewById(R.id.proimage)
        btn = findViewById(R.id.btn)


        regNo= i.getStringExtra("regNo")!!

        getData()
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

                updateto(imageUri!!,strName,strMail,strDep,strDob);


            }

        })


    }

    private fun updateto(imageUri: Uri, strName: String, strMail: String, strDep: String, strDob: String) {

        val user = mapOf<String,String>(
            "name" to strName,
            "email" to strMail,
            "department" to strDep,
            "dob" to strDob
        )

        dbref.child(regNo).updateChildren(user).addOnSuccessListener {

            nametxt.text=""
            etdNam.text.clear()
            etdMail.text.clear()
            edtDob.text.clear()
            edtDep.text.clear()

            val filestore = FirebaseStorage.getInstance().getReference("images/$regNo.jpg")

            filestore.putFile(imageUri).addOnSuccessListener {
                Toast.makeText(this, "succefully Submitted", Toast.LENGTH_SHORT).show()
            }



        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Faild to updating", Toast.LENGTH_SHORT).show()
        }

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

    private fun getData() {

        dbref.child(regNo).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(studentdata::class.java)

                val storage = FirebaseStorage.getInstance().reference.child("images/$regNo.jpg")
                val localfile= File.createTempFile("tempImage","jpg")

                storage.getFile(localfile).addOnSuccessListener {
                    Picasso.get().load(localfile.absoluteFile).into(proimage)
                }
                nametxt.text=data?.name
                etdNam.setText(data?.name,TextView.BufferType.EDITABLE)
                etdMail.setText(data?.email,TextView.BufferType.EDITABLE)
                edtDob.setText(data?.dob,TextView.BufferType.EDITABLE)
                edtDep.setText(data?.department,TextView.BufferType.EDITABLE)




            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}