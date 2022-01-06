package com.example.week2task2

import androidx.core.content.ContextCompat.startActivity
import android.content.Context as Context1

open class intentclass() {


    open fun Intent(activity: Login, page: Class<homePage>): Any {

        val v=Intent(activity,page)
        return v
    }

}