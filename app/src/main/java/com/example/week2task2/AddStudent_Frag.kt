package com.example.week2task2

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Notification
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.week2task2.data.StudentDataCollection
import com.example.week2task2.data.StudentViewModel
import com.example.week2task2.data.studentData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView
import android.webkit.MimeTypeMap

import android.content.ContentResolver
import android.provider.MediaStore
import android.widget.ProgressBar
import com.example.week2task2.R.drawable
import com.example.week2task2.R.drawable.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*


lateinit var etdName:EditText
lateinit var etdMail:EditText
lateinit var edtReg:EditText
lateinit var edtPass:EditText
lateinit var edtDep:EditText
lateinit var edtDob:EditText
lateinit var btn:Button
lateinit var strName:String
lateinit var strPass:String
lateinit var strEmail:String
lateinit var strReg:String
lateinit var strDep:String
lateinit var strDob:String
lateinit var imgUri: Uri
lateinit var img:CircleImageView
lateinit var prograss:ProgressBar


class AddStudent_Frag : Fragment() {

    private lateinit var studentviewModel: StudentViewModel

   lateinit var database:DatabaseReference

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_student_, container, false)

//        studentviewModel =ViewModelProvider(this).get(viewModel::class.java)


        etdName=view.findViewById(R.id.etdName)
        etdMail=view.findViewById(R.id.etdMail)
        edtReg=view.findViewById(R.id.edtReg)
        edtPass=view.findViewById(R.id.edtPass)
        edtDep=view.findViewById(R.id.edtDep)
        edtDob=view.findViewById(R.id.edtDob)
        img=view.findViewById(R.id.img)
        btn=view.findViewById(R.id.btn)
        prograss=view.findViewById(R.id.prograss)

        btn.visibility=View.VISIBLE

        img.setOnClickListener {

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }



        btn.setOnClickListener(View.OnClickListener {

            strName= etdName.text.toString()
            strEmail= etdMail.text.toString()
            strReg= edtReg.text.toString()
            strPass= edtPass.text.toString()
            strDep= edtDep.text.toString()
            strDob= edtDob.text.toString()

            if (imgUri == null) {
                Toast.makeText(activity, "select Student image", Toast.LENGTH_SHORT).show()
            }
            else if (strName == ""){
                Toast.makeText(activity,"Enter Student Name",Toast.LENGTH_SHORT).show()
            }else if (strEmail == ""){
                Toast.makeText(activity,"Enter Student Email",Toast.LENGTH_SHORT).show()
            }else if (strReg == ""){
                Toast.makeText(activity,"Enter Student Register Number",Toast.LENGTH_SHORT).show()
            }else if (strPass == ""){
                Toast.makeText(activity,"Set Student Pass",Toast.LENGTH_SHORT).show()
            }else if (strDep == ""){
                Toast.makeText(activity,"Enter Depertment",Toast.LENGTH_SHORT).show()
            }else{

                btn.visibility=View.GONE
                prograss.visibility=View.VISIBLE


                val filestore = FirebaseStorage.getInstance().getReference("images/$strReg.jpg")

                filestore.putFile(imgUri).
                        addOnSuccessListener {
                            img.setImageURI(null)
                        database = FirebaseDatabase.getInstance().getReference("StudentDetails")
                        var studentdata = StudentDataCollection( strName, strEmail, strReg, strPass, strDep, strDob)
                        database.child(strReg).setValue(studentdata).addOnSuccessListener {
                            etdName.text.clear()
                            etdMail.text.clear()
                            edtReg.text.clear()
                            edtPass.text.clear()
                            edtDep.text.clear()
                            edtDob.text.clear()
                            funToast("successfully Inserted")
                            btn.visibility=View.VISIBLE
                            prograss.visibility=View.GONE
                        }.addOnFailureListener {
                            funToast("Faild")
                        }
                    }


            }

        })


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK){
            imgUri =data?.data!!
            img.setImageURI(imgUri)
        }
    }

    private fun funToast(s: String) {

        Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show()

    }




}
