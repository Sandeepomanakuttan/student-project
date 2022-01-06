package com.example.week2task2.data

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update

interface AddDoa {

    @Query("SELECT * FROM Student ORDER BY RegNo DESC")
    fun getAllStudentInfo():List<studentData>

    @Insert
    fun InsertStudent(student:studentData?)

    @Delete
    fun delectStudent(student:studentData?)

    @Update
    fun updateStudent(student:studentData?)

}