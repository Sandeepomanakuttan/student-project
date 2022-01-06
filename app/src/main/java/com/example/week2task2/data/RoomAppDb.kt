package com.example.week2task2.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [studentData::class], version = 1)
abstract class RoomAppDb : RoomDatabase() {

    abstract fun StudentDao(): AddDoa?


}