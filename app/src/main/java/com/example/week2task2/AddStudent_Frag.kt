package com.example.week2task2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.week2task2.data.StudentViewModel
import com.example.week2task2.data.studentData

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
class AddStudent_Frag : Fragment() {

    private lateinit var studentviewModel: StudentViewModel

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
        btn=view.findViewById(R.id.btn)


        btn.setOnClickListener(View.OnClickListener {

            strName= etdName.text.toString()
            strEmail= etdMail.text.toString()
            strReg= edtReg.text.toString()
            strPass= edtPass.text.toString()
            strDep= edtDep.text.toString()
            strDob= edtDob.text.toString()

            if (strName == ""){
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

                    insertDataToDatabase()
                    funToast(activity,"Succefully Inserted")
            }

        })


        return view
    }

    private fun funToast(activity: FragmentActivity?, s: String) {

        Toast.makeText(activity,s,Toast.LENGTH_SHORT).show()

    }

    private fun insertDataToDatabase() {
        val obj=studentData(Integer.parseInt(strReg), strName,strEmail, strPass, strDep, strDob )
        studentviewModel.addStudent(obj)

    }



}
