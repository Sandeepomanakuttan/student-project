package com.example.week2task2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class Login : AppCompatActivity() {

    lateinit var etdemail: EditText
    lateinit var etdPass: EditText
    lateinit var btn: Button
    lateinit var strEmail:String
    lateinit var strPass:String
    lateinit var link: ImageView
    open var preffName="myPreff"

    override fun onBackPressed() {
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        etdemail=findViewById(R.id.etdMail)
        etdPass=findViewById(R.id.edtPass)
        link=findViewById(R.id.link)
        btn=findViewById(R.id.btn)

        btn.setOnClickListener(View.OnClickListener {


            strEmail=etdemail.text.toString()
            strPass=etdPass.text.toString()



            if (strEmail.equals("")){
                Toast.makeText(this,"Please Enter email", Toast.LENGTH_SHORT).show()
            }
            else if (strPass.equals("")){
                Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show()
            }else{
                if (strEmail!=("sandeep@gmail.com")){
                    Toast.makeText(this,"Please Enter valid email", Toast.LENGTH_SHORT).show()
                }else if(strPass!=("pass")){
                    Toast.makeText(this,"Please Enter valid password", Toast.LENGTH_SHORT).show()
                }else{

                    var preffer=getSharedPreferences(Login().preffName,0)
                    var edit=preffer.edit()
                    edit.putBoolean("loggedIn",true)
                    edit.commit()

                    val intent= Intent(this, homePage::class.java)
                    startActivity(intent)
                    finish()

                }
            }
        })

        link.setOnClickListener(View.OnClickListener {
            val intent= Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.linkedin.com/in/sandeep-karippayil-521594205/")
            startActivity(intent)
        })
    }
}