package com.example.week2task2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Student")
 data class studentData(
   @PrimaryKey
    @ColumnInfo(name="RegNo")val Regno:Int,
   @ColumnInfo(name="Name")val Name:String,
   @ColumnInfo(name="Email")val Email:String,
   @ColumnInfo(name="Password")val Password:String,
   @ColumnInfo(name="Department")val Department:String,
   @ColumnInfo(name="Dob")val Dob:String
)





