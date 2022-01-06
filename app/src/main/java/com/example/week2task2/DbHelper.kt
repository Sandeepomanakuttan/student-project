package com.example.week2task2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "Student_Record", factory, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
//        val query = ("CREATE TABLE " + TABLE_NAME + " ("
//                + ID_COL + " INTEGER PRIMARY KEY, " +
//                NAME_COl + " TEXT," +
//                AGE_COL + " TEXT" + ")")

        db?.execSQL("create Table student(Reg_no INTEGER primary key,Name TEXT,Email TEXT,Password TEXT,Department TEXT,Dob TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + "student")
        onCreate(db)
    }

    fun insertUser(reg_no:Int,Name:String,email:String,pass:String,dept:String,dob:String): Boolean {

        val value=ContentValues()

        value.put("Reg_no",reg_no)
        value.put("Name",Name)
        value.put("Email",email)
        value.put("Password",pass)
        value.put("Department",dept)
        value.put("Dob",dob)

        val db=this.writableDatabase

        var result=db.insert("student",null,value)
        return result > 0

    }

    fun checkuserExit(reg:Int): Boolean {
        val db=this.readableDatabase

        var result=db.rawQuery("SELECT EXISTS (SELECT * FROM student WHERE Reg_no='$reg' LIMIT 1)",null)
        if (result.moveToFirst()) {
            result.close()
            return false
        }else {
            return true
        }
    }


    fun getData(reg: Int): Cursor? {
        var db=this.readableDatabase
        return db.rawQuery("SELECT * FROM  student WHERE Reg_no='$reg'", null)
    }
}